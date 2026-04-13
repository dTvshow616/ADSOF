package sensor;

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
    private LocalDateTime fechaCaducidad;
    /** Offset de calibración del sensor */
    private double offset;
    /** Fecha y hora de la última lectura del sensor */
    private LocalDateTime fechaUltimaLectura;
    /* El valor de la última lectura */
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

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor de un sensor con un tipo de lectura específico, tiempo de calibrado estándar (1 año)
     * @param id               el id de sensor
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param lecturaSensor    el tipo de lectura de sensor
     */
    Sensor(String id, double offset, LocalDateTime fechaInstalacion, TipoLecturaSensor lecturaSensor) {
        this(id, offset, fechaInstalacion);
        this.lecturaSensor = lecturaSensor;
    }

    /**
     * Constructor de un sensor sin tipo de lectura especificada, tiempo de calibrado estándar (1 año)
     * @param id               el id de sensor
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha de instalación del sensor
     */
    Sensor(String id, double offset, LocalDateTime fechaInstalacion) {
        this.id = id;
        this.offset = offset;
        this.calibrado = true;
        this.sumaValores = 0;
        this.cantidadLecturas = 0;
        this.fechaInstalacion = fechaInstalacion;
        this.fechaCaducidad = this.fechaInstalacion.plus(Period.ofDays(365));
        this.lecturaSensor = TipoLecturaSensor.MINMAX;
    }

    /**
     * Constructor de un sensor sin tipo de lectura especificada y sin fecha de instalación definida, se asignará la del
     * día actual, tiempo de calibrado estándar (1 año)
     * @param id     el id de sensor
     * @param offset el offset del sensor
     */
    Sensor(String id, double offset) {
        this(id, offset, LocalDateTime.now());
    }

    /**
     * Constructor de un sensor con un tipo de lectura específico, tiempo de especificado en días
     * @param id                      el id de sensor
     * @param offset                  el offset del sensor
     * @param fechaInstalacion        la fecha de instalación del sensor
     * @param lecturaSensor           el tipo de lectura de sensor
     * @param diasDuracionCalibracion los días que durará el sensor calibrado desde que se instaló
     * @throws IllegalArgumentException los días de duración del calibrado deben ser mayores o iguales a 1
     */
    Sensor(String id, double offset, LocalDateTime fechaInstalacion, TipoLecturaSensor lecturaSensor,
           int diasDuracionCalibracion) throws IllegalArgumentException {
        this(id, offset, fechaInstalacion, lecturaSensor);
        try {
            this.calibrar(diasDuracionCalibracion);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al calibrar el sensor: " + e.getMessage());
        }
    }

    /**
     * Constructor de un sensor sin tipo de lectura especificada, tiempo de calibrado especificado en días
     * @param id                      el id de sensor
     * @param offset                  el offset del sensor
     * @param fechaInstalacion        la fecha de instalación del sensor
     * @param diasDuracionCalibracion los días que durará el sensor calibrado desde que se instaló
     * @throws IllegalArgumentException los días de duración del calibrado deben ser mayores o iguales a 1
     */
    Sensor(String id, double offset, LocalDateTime fechaInstalacion, int diasDuracionCalibracion)
            throws IllegalArgumentException {
        this(id, offset, fechaInstalacion);
        try {
            this.calibrar(diasDuracionCalibracion);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al calibrar el sensor: " + e.getMessage());
        }
    }

    /**
     * Constructor de un sensor sin tipo de lectura especificada y sin fecha de instalación definida, se asignará la del
     * día actual, tiempo de calibrado especificado en días
     * @param id                      el id de sensor
     * @param offset                  el offset del sensor
     * @param diasDuracionCalibracion los días que durará el sensor calibrado desde que se instaló
     * @throws IllegalArgumentException los días de duración del calibrado deben ser mayores o iguales a 1
     */
    Sensor(String id, double offset, int diasDuracionCalibracion) throws IllegalArgumentException {
        this(id, offset);
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
     * @param fechaInstalacion  la fecha de instalación del sensor
     * @param lecturaSensor     el tipo de lectura de sensor
     * @param fechaFinCalibrado la nueva fecha de caducidad del sensor
     * @throws IllegalArgumentException la fecha de fin debe ser posterior a la de instalación
     */
    Sensor(String id, double offset, LocalDateTime fechaInstalacion, TipoLecturaSensor lecturaSensor,
           LocalDateTime fechaFinCalibrado) throws IllegalArgumentException {
        this(id, offset, fechaInstalacion, lecturaSensor);
        try {
            this.calibrar(fechaFinCalibrado);
        } catch (IllegalArgumentException e) {
            System.out.println("Error al calibrar el sensor: " + e.getMessage());
        }
    }

    /**
     * Constructor de un sensor sin tipo de lectura especificada, fecha de fin de calibrado especificada
     * @param id                el id de sensor
     * @param offset            el offset del sensor
     * @param fechaInstalacion  la fecha de instalación del sensor
     * @param fechaFinCalibrado la nueva fecha de caducidad del sensor
     * @throws IllegalArgumentException la fecha de fin debe ser posterior a la de instalación
     */
    Sensor(String id, double offset, LocalDateTime fechaInstalacion, LocalDateTime fechaFinCalibrado)
            throws IllegalArgumentException {
        this(id, offset, fechaInstalacion);
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
        if (LocalDateTime.now().isAfter(fechaCaducidad)) {
            this.calibrado = false;
        }
    }

    /**
     * Permite al sensor medir un valor
     * @param valor el valor a medir
     * @throws SensorSinCalibrar el sensor no está calibrado
     */
    public void leerValor(double valor) throws SensorSinCalibrar {
        if (!this.calibrado) {
            throw new SensorSinCalibrar(this, false);
        }

        this.valorUltimaLectura = valor - this.offset;
        this.cantidadLecturas = this.cantidadLecturas + 1;
        this.sumaValores = this.sumaValores + this.valorUltimaLectura;

        if (this.valorUltimaLectura > maxRango || this.valorUltimaLectura < minRango) {
            /* El sensor se considera no calibrado cuando se realice una lectura fuera de rango */
            this.calibrado = false;
            throw new SensorSinCalibrar(this, true);
        }

        this.fechaUltimaLectura = LocalDateTime.now();
    }

    /**
     * Simula la lectura por parte de un sensor
     * @param valorConfigurable el valor a medir
     * @return true si todo funciona correctamente, false en caso contrario
     */
    public boolean simulacionLectura(double valorConfigurable) {
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

        try {
            leerValor(valor);
        } catch (SensorSinCalibrar e) {
            System.out.println(e.getMessage());
        }

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
        this.fechaCaducidad = fechaFin;
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
        this.fechaCaducidad = fechaInstalacion.plus(Period.ofDays(diasDuracionCalibracion));
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
    }

    public int getCantidadLecturas() {
        return cantidadLecturas;
    }

    public void setCantidadLecturas(int newCantidadLecturas) {
        this.cantidadLecturas = newCantidadLecturas;
    }

    public LocalDateTime getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDateTime newFechaCaducidad) {
        this.fechaCaducidad = newFechaCaducidad;
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