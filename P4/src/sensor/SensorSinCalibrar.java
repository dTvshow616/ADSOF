package sensor;

public class SensorSinCalibrar extends Exception {
    private double lectura;
    private double minRango;
    private double maxRango;
    private boolean calibrado;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    SensorSinCalibrar(double lectura, double minRango, double maxRango, boolean calibrado) {
        this.lectura = lectura;
        this.minRango = minRango;
        this.maxRango = maxRango;
        this.calibrado = calibrado;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    public String toString() {
        if (!this.calibrado) {
            return "Sensor no calibrado";
        }
        return "Sensor sin calibrar, lectura " + lectura + " fuera del rango [" + minRango + ", " + maxRango + "]";
    }
}