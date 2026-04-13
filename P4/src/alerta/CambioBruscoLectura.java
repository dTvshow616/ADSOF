package alerta;

import sensor.Sensor;

import java.time.LocalDateTime;

/**
 * The type Cambio brusco lectura.
 */
public class CambioBruscoLectura extends Exception {
    /** El sensor de la excepción */
    private final Sensor sensor;
    /** Último valor leído */
    private final double lecturaAnterior;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor de la excepción para un cambio brusco en las lecturas de un sensor
     * @param sensor          el sensor
     * @param lecturaAnterior la lectura anterior del sensor
     */
    public CambioBruscoLectura(Sensor sensor, double lecturaAnterior) {
        this.sensor = sensor;
        this.lecturaAnterior = lecturaAnterior;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Define el mensaje de la excepción
     * @return descripción de la excepción
     */
    @Override
    public String toString() {
        return "- [" + LocalDateTime.now() + "] Cambio brusco en " + this.sensor.getId() + ": " +
               this.sensor.getValorUltimaLectura() + this.sensor.getSimboloMedida() + "(anterior: " +
               this.lecturaAnterior + this.sensor.getSimboloMedida() + ")";
    }
}