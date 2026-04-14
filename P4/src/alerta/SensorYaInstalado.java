package alerta;

import sensor.Sensor;

public class SensorYaInstalado extends Exception {
    /** El sensor de la excepción */
    private final Sensor sensor;
    /** El sensor ya instalado de la excepción */
    private final Sensor sensorInstalado;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para esta excepción
     * @param sensor el sensor
     */
    public SensorYaInstalado(Sensor sensor, Sensor sensorInstalado) {
        this.sensor = sensor;
        this.sensorInstalado = sensorInstalado;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Define el mensaje de la excepción
     * @return descripción de la excepción
     */
    @Override
    public String toString() {
        return "Un sensor con el ID: " + this.sensor.getId() + "ya se encuentra instalado, el sensor instalado es:" +
               sensorInstalado;
    }
}