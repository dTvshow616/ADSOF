package sensor;

import java.time.*;

/**
 * Nombre de la clase: sensor.Sensor
 * <p>
 * Descripción: Implementa un sensor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public abstract class Sensor {
    /** El tiempo tras el cual caduca la calibración de un sensor */
    private static Period periodoCaducidad = Period.ofDays(365); // NOTE: Valor default
    /** Identificador único del sensor */
    private final String id;
    /** Offset de calibración del sensor */
    private double offset;
    /** Fecha y hora de la última lectura del sensor */
    private LocalDateTime fechaUltimaLectura;
    /* El valor de la última lectura */
    private double valorUltimaLectura;
    /** La calibración del sensor */
    private boolean calibrado;
    /** Valor mínimo del rango de valores aceptados */
    private double min_rango;
    /** Valor máximo del rango de valores aceptados */
    private double max_rango;
    /** La fecha de instalación del sensor */
    private LocalDate fechaInstalacion;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor de un sensor
     * @param id               el id de sensor
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha de instalación del sensor
     */
    Sensor(String id, double offset, LocalDate fechaInstalacion) {
        this.id = id;
        this.offset = offset;
        this.fechaInstalacion = fechaInstalacion;
        this.calibrado = true;
    }

    /**
     * Constructor de un sensor sin fecha de instalación definida
     * @param id     el id de sensor
     * @param offset el offset del sensor
     */
    Sensor(String id, double offset) {
        this.id = id;
        this.offset = offset;
        this.fechaInstalacion = LocalDate.now();
        this.calibrado = true;
    }

    public boolean isCalibrado() {
        return calibrado;
    }

    /**
     * Comprueba si la calibración del sensor ha caducado
     */
    public void comprobarCaducidad() {
        LocalDate fechaCaducidad = this.fechaInstalacion.plus(periodoCaducidad);
        if (LocalDate.now().isAfter(fechaCaducidad)) {
            this.calibrado = false;
        }
    }

    /**
     * Permite al sensor medir un valor
     * @param valor el valor a medir
     */
    public void leerValor(double valor) {
        this.valorUltimaLectura = valor - this.offset;
        if (this.valorUltimaLectura > max_rango || this.valorUltimaLectura < min_rango) {
            /* El sensor se considera no calibrado cuando se realice una lectura fuera de rango */
            this.calibrado = false;
        }
        this.fechaUltimaLectura = LocalDateTime.now();
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

    public LocalDate getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(LocalDate newFechaInstalacion) {
        this.fechaInstalacion = newFechaInstalacion;
    }

    public LocalDateTime getFechaUltimaLectura() {
        return fechaUltimaLectura;
    }

    public void setFechaUltimaLectura(LocalDateTime newFechaUltimaLectura) {
        this.fechaUltimaLectura = newFechaUltimaLectura;
    }

    public String getId() {
        return id;
    }

    public double getMax_rango() {
        return max_rango;
    }

    public void setMax_rango(double newMax_rango) {
        this.max_rango = newMax_rango;
    }

    public double getMin_rango() {
        return min_rango;
    }

    public void setMin_rango(double newMin_rango) {
        this.min_rango = newMin_rango;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double newOffset) {
        this.offset = newOffset;
    }

    public double getValorUltimaLectura() {
        return valorUltimaLectura;
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