package alerta;

import sensor.Sensor;

public class SensorYaInstalado extends Exception {
    /** El sensor de la excepción */
    private final Sensor sensor;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para esta excepción
     * @param sensor el sensor
     */
    public SensorYaInstalado(Sensor sensor) {
        this.sensor = sensor;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Define el mensaje de la excepción
     * @return descripción de la excepción
     */
    @Override
    public String toString() {
        return "Un sensor con el ID: " + this.sensor.getId() + "ya se encuentra instalado";
    }
}