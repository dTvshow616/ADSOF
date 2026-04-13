package sensor;

/**
 * Implementa la excepción para un sensor sin calibrar
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class SensorSinCalibrar extends Exception {
    private final double lectura;
    private final double minRango;
    private final double maxRango;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor de la excepción
     * @param lectura  lectura del sensor
     * @param minRango limite inferior del sensor
     * @param maxRango limite superior del sensor
     */
    SensorSinCalibrar(double lectura, double minRango, double maxRango) {
        this.lectura = lectura;
        this.minRango = minRango;
        this.maxRango = maxRango;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Define el mensaje de la excepción
     * @return descripción de la excepción
     */
    public String toString() {
        return "Sensor sin calibrar, lectura " + lectura + " fuera del rango [" + minRango + ", " + maxRango + "]";
    }
}