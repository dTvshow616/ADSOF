import java.time.LocalDate;

/**
 * Nombre de la clase: SensorPresion
 * <p>
 * Descripción: Implementa el sensor de presión
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 * @see UdsMedidaPres
 */
public class SensorPresion extends Sensor {
    /** Nombre que define un sensor de presión */
    static String nombre = "PRES";
    /** Unidades de medida del sensor */
    private UdsMedidaPres medida;

    /**
     * Constructor para un sensor de presión
     * @param offset           el offset del sensor
     * @param min_rango        el valor mínimo del rango de valores aceptados
     * @param max_rango        el valor máximo del rango de valores aceptados
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param medida           las unidades de medida del sensor
     */
    SensorPresion(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion, UdsMedidaPres medida) {
        super(TipoSensor.PRES, offset, min_rango, max_rango, fechaInstalacion);
        this.medida = medida;
    }

    /**
     * Constructor para un sensor de presión sin fecha de instalación especificada
     * @param offset    el offset del sensor
     * @param min_rango el valor mínimo del rango de valores aceptados
     * @param max_rango el valor máximo del rango de valores aceptados
     * @param medida    las unidades de medida del sensor
     */
    SensorPresion(double offset, double min_rango, double max_rango, UdsMedidaPres medida) {
        super(TipoSensor.PRES, offset, min_rango, max_rango);
        this.medida = medida;
    }
}