import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

/**
 * Nombre de la clase: Sensor
 * <p>
 * Descripción: Implementa un sensor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public abstract class Sensor {
    /** Identificador único del sensor */
    String id;
    /** Offset de calibración del sensor */
    double offset;
    /** Fecha y hora de la última lectura del sensor */
    LocalDateTime fechaUltimaLectura;
    /* El valor de la última lectura */
    double valorUltimaLectura;
    /** La calibración del sensor */
    boolean calibrado;
    /** Valor mínimo del rango de valores aceptados */
    double min_rango;
    /** Valor máximo del rango de valores aceptados */
    double max_rango;
    /** El tiempo tras el cual caduca la calibración de un sensor */
    Period periodoCaducidad;
    /** La fecha de instalación del sensor */
    LocalDate fechaInstalacion;

    /**
     * Constructor de un sensor
     * @param id el id del sensor
     * @param offset el offset del sensor
     * @param min_rango el valor mínimo del rango de valores aceptados
     * @param max_rango el valor máximo del rango de valores aceptados
     */
    Sensor(String id, double offset, double min_rango, double max_rango) {
        this.id = id;
        this.offset = offset;
        this.min_rango = min_rango;
        this.max_rango = max_rango;
    }

    /**
     * Comprueba si la calibración del sensor ha caducado
     */
    public void comprobarCaducidad() {
        LocalDate fechaCaducidad = this.fechaInstalacion.plus(this.periodoCaducidad);
        if (LocalDate.now().isAfter(fechaCaducidad)) {
            this.calibrado = false;
        }
    }

    public void leerValor(double valor) { // DUE: Hacer

    }
}