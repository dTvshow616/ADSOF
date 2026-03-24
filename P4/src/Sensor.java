import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * Nombre de la clase: Sensor
 * <p>
 * Descripción: Implementa un sensor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public class Sensor {
    /** El tiempo tras el cual caduca la calibración de un sensor */
    private static Period periodoCaducidad = Period.ofDays(1); // NOTE: Valor default
    /** Contador de ids de todos los sensores */
    private static int generalId = 0;
    /** Identificador único del sensor */
    private String id;
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

    /**
     * Constructor de un sensor
     * @param tipo             el tipo de sensor
     * @param offset           el offset del sensor
     * @param min_rango        el valor mínimo del rango de valores aceptados
     * @param max_rango        el valor máximo del rango de valores aceptados
     * @param fechaInstalacion la fecha de instalación del sensor
     */
    Sensor(TipoSensor tipo, double offset, double min_rango, double max_rango, LocalDate fechaInstalacion) {
        this.id = tipo.toString() + "-" + String.format("%04d", ++generalId);
        this.offset = offset;
        this.fechaUltimaLectura = null;
        this.calibrado = true;
        this.min_rango = min_rango;
        this.max_rango = max_rango;
        this.fechaInstalacion = fechaInstalacion;
    }

    /**
     * Constructor de un sensor sin fecha de instalación definida
     * @param tipo      el tipo de sensor
     * @param offset    el offset del sensor
     * @param min_rango el valor mínimo del rango de valores aceptados
     * @param max_rango el valor máximo del rango de valores aceptados
     */
    Sensor(TipoSensor tipo, double offset, double min_rango, double max_rango) {
        this.id = tipo.toString() + "-" + String.format("%04d", ++generalId);
        this.offset = offset;
        this.fechaUltimaLectura = null;
        this.calibrado = true;
        this.min_rango = min_rango;
        this.max_rango = max_rango;
        this.fechaInstalacion = LocalDate.now();
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

    /**
     * Permite consultar si el sensor está correctamente calibrado
     * @return true si lo está, false si no
     */
    public boolean getCalibrado() {
        comprobarCaducidad();
        return this.calibrado;
    }
}