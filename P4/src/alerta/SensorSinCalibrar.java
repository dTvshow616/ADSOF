package alerta;

import sensor.Sensor;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Implementa la excepción para un sensor sin calibrar
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public class SensorSinCalibrar extends Exception {
    /** El sensor de la excepción */
    private final Sensor sensor;
    /** Si el sensor estaba calibrado previamente (true) o no (false) */
    private final boolean fueraRango;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para la excepción
     * @param sensor     el sensor
     * @param fueraRango true si el sensor no estaba caducado previamente, falso si no
     */
    public SensorSinCalibrar(Sensor sensor, boolean fueraRango) {
        this.sensor = sensor;
        this.fueraRango = fueraRango;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Define el mensaje de la excepción
     * @return descripción de la excepción
     */
    @Override
    public String toString() {
        if (this.fueraRango) {
            return "[" + LocalDateTime.now() + "] Lectura fuera de rango en " + this.sensor.getId() + ": " +
                   this.sensor.getValorUltimaLectura() + this.sensor.getSimboloMedida();
        } else {
            return "[" + LocalDateTime.now() + "] Sensor " + this.sensor.getId() +
                   " sin calibrar (calibración caducada desde " +
                   this.sensor.getFechaCalibracion().plus(this.sensor.getTiempoCaducidad())
                              .truncatedTo(ChronoUnit.SECONDS) + ")";
        }
    }
}