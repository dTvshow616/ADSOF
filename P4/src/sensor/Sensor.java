package sensor;

import alerta.CambioBruscoLectura;
import alerta.SensorSinCalibrar;
import conversor.ProcesadorDatos;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

/**
 * Implementa un sensor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public abstract class Sensor {
    /** Identificador único del sensor */
    private final String id;
    /** Cantidad de todos los valores obtenidos hasta el momento */
    public int cantidadLecturas;
    /** El tiempo tras el cual caduca la calibración de un sensor */
    private Period tiempoCaducidad;
    /** Fecha en la que se calibró el sensor por última vez */
    private LocalDateTime fechaCalibracion;
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
    private LocalDateTime fechaInstalacion;
    /** Tipo de lectura de sensor */
    private TipoLecturaSensor lecturaSensor;
    /** Procesador de datos */
    private ProcesadorDatos procesadorDeDatos;
    /** El porcentaje de fluctuación máximo que puede haber entre dos lecturas consecutivas */
    private double porcentajeCambioMax = 50.0;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor de un sensor sin tipo de lectura especificada y sin fecha de instalación definida, se asignará la del
     * día actual, tiempo de calibrado estándar (1 año)
     * @param id     el id de sensor
     * @param offset el offset del sensor
     */
    public Sensor(String id, double offset) {
        this.id = id;
        this.offset = offset;
        this.setCalibrado(true);
        this.sumaValores = 0;
        this.cantidadLecturas = 0;
        this.fechaInstalacion = LocalDateTime.now();
        this.tiempoCaducidad = Period.ofDays(365);
        this.lecturaSensor = TipoLecturaSensor.MINMAX;
    }

    /**
     * Constructor de un sensor con un tipo de lectura específico, tiempo de calibrado estándar (1 año)
     * @param id            el id de sensor
     * @param offset        el offset del sensor
     * @param lecturaSensor el tipo de lectura de sensor
     */
    public Sensor(String id, double offset, TipoLecturaSensor lecturaSensor) {
        this(id, offset);
        this.lecturaSensor = lecturaSensor;
    }

    /**
     * Constructor de un sensor con un tipo de lectura específico, tiempo de calibrado especificado en días
     * @param id                      el id de sensor
     * @param offset                  el offset del sensor
     * @param lecturaSensor           el tipo de lectura de sensor
     * @param diasDuracionCalibracion los días que durará el sensor calibrado desde que se instaló
     * @throws IllegalArgumentException los días de duración del calibrado deben ser mayores o iguales a 1
     */
    public Sensor(String id, double offset, TipoLecturaSensor lecturaSensor, int diasDuracionCalibracion)
            throws IllegalArgumentException {
        this(id, offset, lecturaSensor);
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
     * @param fechaFinCalibrado la nueva fecha de caducidad del sensor
     * @throws IllegalArgumentException la fecha de fin debe ser posterior a la de instalación
     */
    public Sensor(String id, double offset, TipoLecturaSensor lecturaSensor, LocalDateTime fechaFinCalibrado)
            throws IllegalArgumentException {
        this(id, offset, lecturaSensor);
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
        if (LocalDateTime.now().isAfter(this.fechaCalibracion.plusDays(this.tiempoCaducidad.getDays()))) {
            this.calibrado = false;
        }
    }

    /**
     * Permite al sensor medir un valor
     * @param valor el valor a medir
     * @throws SensorSinCalibrar el sensor no está calibrado
     */
    public void leerValor(double valor) throws SensorSinCalibrar, CambioBruscoLectura {
        if (!this.calibrado) {
            throw new SensorSinCalibrar(this, false);
        }

        double valorReal = valor - this.offset;

        if (valorReal > maxRango || valorReal < minRango) {
            /* El sensor se considera no calibrado cuando se realice una lectura fuera de rango */
            this.calibrado = false;
            throw new SensorSinCalibrar(this, true);
        }

        double anteriorLectura = this.procesadorDeDatos.getUltimoRegistro();

        if (valorReal > anteriorLectura) {
            if (((valorReal / anteriorLectura) * 100) >= porcentajeCambioMax) {
                throw new CambioBruscoLectura(this, anteriorLectura);
            }
        } else {
            if (((anteriorLectura / valorReal)) * 100 >= porcentajeCambioMax) {
                throw new CambioBruscoLectura(this, anteriorLectura);
            }
        }

        this.valorUltimaLectura = valorReal;
        this.cantidadLecturas = this.cantidadLecturas + 1;
        this.sumaValores = this.sumaValores + this.valorUltimaLectura;
        this.fechaUltimaLectura = LocalDateTime.now();
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
        if (fechaFin.isBefore(this.fechaInstalacion)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la de instalación");
        }
        this.fechaCalibracion = LocalDateTime.now();
    }

    public boolean isCalibrado() {
        return calibrado;
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

    public void setCalibrado(boolean newCalibrado) {
        this.calibrado = newCalibrado;
        this.fechaCalibracion = LocalDateTime.now();
    }

    public int getCantidadLecturas() {
        return cantidadLecturas;
    }

    public void setCantidadLecturas(int newCantidadLecturas) {
        this.cantidadLecturas = newCantidadLecturas;
    }

    public LocalDateTime getFechaCalibracion() {
        return fechaCalibracion;
    }

    public void setFechaCalibracion(LocalDateTime newFechaCalibracion) {
        this.fechaCalibracion = newFechaCalibracion;
    }

    public LocalDateTime getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDateTime newFechaInstalacion) {
        this.fechaInstalacion = newFechaInstalacion;
    }

    /**
     * Devuelve la fecha de la última lectura
     * @return la fecha de la última lectura
     */
    public LocalDateTime getFechaUltimaLectura() {
        return this.fechaUltimaLectura;
    }

    public void setFechaUltimaLectura(LocalDateTime newFechaUltimaLectura) {
        this.fechaUltimaLectura = newFechaUltimaLectura;
    }

    /**
     * Devuelve el ID del sensor
     * @return el ID del sensor
     */
    public String getId() {
        return this.id;
    }

    public TipoLecturaSensor getLecturaSensor() {
        return lecturaSensor;
    }

    public void setLecturaSensor(TipoLecturaSensor newLecturaSensor) {
        this.lecturaSensor = newLecturaSensor;
    }

    public double getMaxRango() {
        return maxRango;
    }

    /**
     * Establece el valor máximo del rango de valores válidos del sensor
     * @param newRangoMax el valor máximo del rango de valores válidos del sensor
     */
    public void setMaxRango(double newRangoMax) {
        this.maxRango = newRangoMax;
    }

    public double getMinRango() {
        return minRango;
    }

    /**
     * Establece el valor mínimo del rango de valores válidos del sensor
     * @param newRangoMin el valor mínimo del rango de valores válidos del sensor
     */
    public void setMinRango(double newRangoMin) {
        this.minRango = newRangoMin;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double newOffset) {
        this.offset = newOffset;
    }

    public ProcesadorDatos getProcesadorDeDatos() {
        return procesadorDeDatos;
    }

    public void setProcesadorDeDatos(ProcesadorDatos newProcesadorDeDatos) {
        this.procesadorDeDatos = newProcesadorDeDatos;
    }

    public abstract String getSimboloMedida();

    public double getSumaValores() {
        return sumaValores;
    }

    public void setSumaValores(double newSumaValores) {
        this.sumaValores = newSumaValores;
    }

    public Period getTiempoCaducidad() {
        return tiempoCaducidad;
    }

    public void setTiempoCaducidad(Period newTiempoCaducidad) {
        this.tiempoCaducidad = newTiempoCaducidad;
    }

    /**
     * Devuelve el último valor leído
     * @return el último valor leído
     */
    public double getValorUltimaLectura() {
        return this.valorUltimaLectura;
    }

    public void setValorUltimaLectura(double newValorUltimaLectura) {
        this.valorUltimaLectura = newValorUltimaLectura;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return this.id + " (desde: " + this.fechaInstalacion + "): sensor.Sensor ";
    }
}