package sensor;

import alerta.CambioBruscoLectura;
import alerta.SensorSinCalibrar;
import conversor.ProcesadorDatos;

import java.time.*;
import java.util.Random;

/**
 * Implementa un sensor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see ProcesadorDatos
 */
public abstract class Sensor {
    /** Identificador único del sensor */
    private final String id;
    /** Procesador de datos */
    private final ProcesadorDatos procesadorDeDatos;
    /** Cantidad de todos los valores obtenidos hasta el momento */
    public int cantidadLecturas;
    /** El tiempo tras el cual caduca la calibración de un sensor */
    private Period tiempoCaducidad;
    /** Fecha en la que se calibró el sensor por última vez */
    private LocalDateTime fechaCalibracion;
    /** Fecha en la uqe el sensor caducará */
    private LocalDateTime fechaCaducidad;
    /** Offset de calibración del sensor */
    private double offset;
    /** Fecha y hora de la última lectura del sensor */
    private LocalDateTime fechaUltimaLectura;
    /** El valor de la última lectura */
    private double valorUltimaLectura;
    /** La calibración del sensor */
    private boolean calibrado;
    /** Valor mínimo del rango de valores aceptados */
    private double minRango;
    /** Valor máximo del rango de valores aceptados */
    private double maxRango;
    /** Suma de todos los valores obtenidos hasta el momento */
    private double sumaValores;
    /** La fecha de instalación del sensor */
    private LocalDate fechaInstalacion;
    /** Tipo de lectura de sensor */
    private TipoLecturaSensor lecturaSensor;
    /** El porcentaje de fluctuación máximo que puede haber entre dos lecturas consecutivas */
    private double porcentajeCambioMax = 50.0;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor de un sensor sin tipo de lectura especificada y sin fecha de instalación definida, se asignará la del
     * día actual, tiempo de calibrado estándar (1 año)
     * @param id                el id de sensor
     * @param offset            el offset del sensor
     * @param procesadorDeDatos el procesador de datos
     */
    public Sensor(String id, double offset, ProcesadorDatos procesadorDeDatos) {
        this.id = id;
        this.offset = offset;
        this.setCalibrado(true);
        this.sumaValores = 0;
        this.cantidadLecturas = 0;
        this.fechaInstalacion = LocalDate.now();
        this.tiempoCaducidad = Period.ofDays(365);
        this.fechaCaducidad = this.fechaCalibracion.plus(this.tiempoCaducidad);
        this.lecturaSensor = TipoLecturaSensor.MINMAX;
        this.procesadorDeDatos = procesadorDeDatos;
        this.valorUltimaLectura = Double.NaN;
    }

    /**
     * Constructor de un sensor con un tipo de lectura específico, tiempo de calibrado estándar (1 año)
     * @param id            el id de sensor
     * @param offset        el offset del sensor
     * @param lecturaSensor el tipo de lectura de sensor
     */
    public Sensor(String id, double offset, TipoLecturaSensor lecturaSensor, ProcesadorDatos procesadorDeDatos) {
        this(id, offset, procesadorDeDatos);
        this.lecturaSensor = lecturaSensor;
    }

    /**
     * Constructor de un sensor con un tipo de lectura específico, tiempo de calibrado especificado en días
     * @param id                      el id de sensor
     * @param offset                  el offset del sensor
     * @param lecturaSensor           el tipo de lectura de sensor
     * @param procesadorDeDatos       el procesador de datos
     * @param diasDuracionCalibracion los días que durará el sensor calibrado desde que se instaló
     * @throws IllegalArgumentException los días de duración del calibrado deben ser mayores o iguales a 1
     */
    public Sensor(String id, double offset, TipoLecturaSensor lecturaSensor, ProcesadorDatos procesadorDeDatos,
                  int diasDuracionCalibracion) throws IllegalArgumentException {
        this(id, offset, lecturaSensor, procesadorDeDatos);
        try {
            this.calibrar(diasDuracionCalibracion);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al calibrar el sensor: " + e.getMessage());
        }
    }

    /**
     * Constructor de un sensor con un tipo de lectura específico, fecha de fin de calibrado especificada
     * @param id                el id de sensor
     * @param offset            el offset del sensor
     * @param lecturaSensor     el tipo de lectura de sensor
     * @param procesadorDeDatos el procesador de datos
     * @param fechaFinCalibrado la nueva fecha de caducidad del sensor
     * @throws IllegalArgumentException la fecha de fin debe ser posterior a la de instalación
     */
    public Sensor(String id, double offset, TipoLecturaSensor lecturaSensor, ProcesadorDatos procesadorDeDatos,
                  LocalDateTime fechaFinCalibrado) throws IllegalArgumentException {
        this(id, offset, lecturaSensor, procesadorDeDatos);
        try {
            this.calibrar(fechaFinCalibrado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al calibrar el sensor: " + e.getMessage());
        }
    }

    /**
     * Comprueba si la calibración del sensor ha caducado
     */
    public void comprobarCaducidad() {
        if (LocalDateTime.now().isAfter(this.fechaCaducidad)) {
            this.calibrado = false;
        }
    }

    /**
     * Permite al sensor medir un valor
     * @param valor el valor a medir
     * @throws SensorSinCalibrar el sensor no está calibrado
     */
    public void leerValor(double valor) throws SensorSinCalibrar, CambioBruscoLectura {
        this.fechaUltimaLectura = LocalDateTime.now();
        comprobarCaducidad();

        if (!this.calibrado) {
            throw new SensorSinCalibrar(this, false);
        }

        double anteriorLectura = this.valorUltimaLectura;
        double valorReal = valor - this.offset;

        this.valorUltimaLectura = valorReal;
        this.cantidadLecturas = this.cantidadLecturas + 1;
        this.sumaValores = this.sumaValores + this.valorUltimaLectura;

        this.procesadorDeDatos.addRegistro(this.fechaUltimaLectura, this.valorUltimaLectura);

        if (valorReal > maxRango || valorReal < minRango) {
            /* El sensor se considera no calibrado cuando se realice una lectura fuera de rango */
            this.calibrado = false;
            throw new SensorSinCalibrar(this, true);
        }

        if (!Double.isNaN(anteriorLectura)) {
            if ((Math.abs(valorReal - anteriorLectura) / anteriorLectura) * 100 >= porcentajeCambioMax) {
                throw new CambioBruscoLectura(this, anteriorLectura);
            }
        }
    }

    /**
     * Simula la lectura por parte de un sensor
     * @return true si funciona correctamente, false en caso contrario
     */
    public boolean simulacionLectura() throws SensorSinCalibrar, CambioBruscoLectura {
        Random rand = new Random();
        if (this.lecturaSensor == TipoLecturaSensor.MINMAX) {
            return this.simulacionLectura(rand.nextDouble());
        } else {
            return this.simulacionLectura(rand.nextDouble() * 100);
        }
    }

    /**
     * Simula la lectura por parte de un sensor
     * @param valorConfigurable el valor a medir
     * @return true si todo funciona correctamente, false en caso contrario
     */
    public boolean simulacionLectura(double valorConfigurable) throws SensorSinCalibrar, CambioBruscoLectura {
        Random rand = new Random();
        double valor;
        double porcentaje;
        double media;

        if (this.lecturaSensor == TipoLecturaSensor.MINMAX) {
            if (valorConfigurable < 0 || valorConfigurable > 1) {
                return false;
            }

            if (rand.nextDouble() < valorConfigurable) {
                valor = this.minRango + (rand.nextDouble() * (this.maxRango - this.minRango));
            } else {
                valor = this.maxRango + (rand.nextDouble() * (this.maxRango - this.minRango));
            }

        } else if (this.lecturaSensor == TipoLecturaSensor.RANGOCONFIGURABLE) {
            if (valorConfigurable < 0 || valorConfigurable > 100) {
                return false;
            }

            porcentaje = (this.valorUltimaLectura * valorConfigurable) / 100;

            valor = (this.valorUltimaLectura - porcentaje) + (rand.nextDouble() *
                                                              ((this.valorUltimaLectura + porcentaje) -
                                                               (this.valorUltimaLectura - porcentaje)));

        } else if (this.lecturaSensor == TipoLecturaSensor.RANGOMEDIA) {
            if (valorConfigurable < 0 || valorConfigurable > 100) {
                return false;
            }

            media = this.sumaValores / this.cantidadLecturas;

            porcentaje = (media * valorConfigurable) / 100;

            valor = (media - porcentaje) + (rand.nextDouble() * ((media + porcentaje) - (media - porcentaje)));
        } else {
            return false;
        }

        leerValor(valor);

        return true;
    }

    /**
     * Permite modificar la fecha de caducidad del sensor
     * @param fechaFin la nueva fecha de caducidad del sensor
     * @throws IllegalArgumentException la fecha de fin debe ser posterior a la de instalación
     */
    public void calibrar(LocalDateTime fechaFin) throws IllegalArgumentException {
        if (fechaFin.isBefore(this.fechaInstalacion.atStartOfDay())) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de instalación");
        }
        this.fechaCalibracion = LocalDateTime.now();
    }

    /**
     * Permite modificar la fecha de caducidad del sensor
     * @param diasDuracionCalibracion los días que durará el sensor calibrado desde que se instaló
     * @throws IllegalArgumentException los días de duración del calibrado deben ser mayores o iguales a 1
     */
    public void calibrar(int diasDuracionCalibracion) throws IllegalArgumentException {
        if (diasDuracionCalibracion < 1) {
            throw new IllegalArgumentException("Los días de duración del calibrado deben ser mayores o iguales a 1");
        }
        this.tiempoCaducidad = Period.ofDays(diasDuracionCalibracion);
        this.fechaCaducidad = this.fechaCalibracion.plusDays(this.tiempoCaducidad.getDays());
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Permite consultar si el sensor está correctamente calibrado
     * @return true si lo está, false si no
     */
    public boolean getCalibrado() {
        comprobarCaducidad();
        return this.calibrado;
    }

    /**
     * Permite marcar el sensor como calibrado o no
     * @param newCalibrado true si está calibrado, false si no
     */
    public void setCalibrado(boolean newCalibrado) {
        this.calibrado = newCalibrado;
        this.fechaCalibracion = LocalDateTime.now();
    }

    /**
     * Obtiene la fecha en la que se calibró el sensor por última vez
     * @return la fecha en la que se calibró el sensor por última vez
     */
    public LocalDateTime getFechaCalibracion() {
        return fechaCalibracion;
    }

    /**
     * Devuelve la fecha de la última lectura
     * @return la fecha de la última lectura
     */
    public LocalDateTime getFechaUltimaLectura() {
        return this.fechaUltimaLectura;
    }

    /**
     * Devuelve el ID del sensor
     * @return el ID del sensor
     */
    public String getId() {
        return this.id;
    }

    /**
     * Obtiene el procesador de datos del sensor
     * @return el procesador de datos del sensor
     */
    public ProcesadorDatos getProcesadorDeDatos() {
        return procesadorDeDatos;
    }

    /**
     * Obtiene el símbolo correspondiente a las medidas del sensor
     * @return el símbolo correspondiente a las medidas del sensor
     */
    public abstract String getSimboloMedida();

    /**
     * Obtiene el periodo de validez de la calibración de un sensor
     * @return el periodo de validez de la calibración de un sensor
     */
    public Period getTiempoCaducidad() {
        return tiempoCaducidad;
    }

    /**
     * Permite alterar el periodo de validez de la calibración de un sensor
     * @param newTiempoCaducidad el periodo de validez de la calibración de un sensor
     */
    public void setTiempoCaducidad(Period newTiempoCaducidad) {
        this.tiempoCaducidad = newTiempoCaducidad;
        this.fechaCaducidad = this.fechaCalibracion.plusDays(this.tiempoCaducidad.getDays());
    }

    /**
     * Devuelve el último valor leído
     * @return el último valor leído
     */
    public double getValorUltimaLectura() {
        return this.valorUltimaLectura;
    }

    public void setValorUltimaLectura(double valor) {
        this.valorUltimaLectura = valor;
    }

    /**
     * Permite asignar la fecha de caducidad del sensor
     * @param fechaCaducidad la fecha de caducidad del sensor
     */
    public void setFechaCaducidad(LocalDateTime fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Permite marcar la fecha de instalación del sensor
     * @param newFechaInstalacion la fecha de instalación del sensor
     */
    public void setFechaInstalacion(LocalDate newFechaInstalacion) {
        this.fechaInstalacion = newFechaInstalacion;
    }

    public void setLecturaSensor(TipoLecturaSensor a) {
        this.lecturaSensor = a;
    }

    /**
     * Establece el valor máximo del rango de valores válidos del sensor
     * @param newRangoMax el valor máximo del rango de valores válidos del sensor
     */
    public void setMaxRango(double newRangoMax) {
        this.maxRango = newRangoMax;
    }

    /**
     * Establece el valor mínimo del rango de valores válidos del sensor
     * @param newRangoMin el valor mínimo del rango de valores válidos del sensor
     */
    public void setMinRango(double newRangoMin) {
        this.minRango = newRangoMin;
    }

    /**
     * Permite cambiar el offset del sensor
     * @param newOffset el offset del sensor
     */
    public void setOffset(double newOffset) {
        this.offset = newOffset;
    }

    public void setPorcentajeCambioMax(double porcentaje) {
        this.porcentajeCambioMax = porcentaje;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return this.id + " (desde: " + this.fechaInstalacion + "): Sensor ";
    }
}