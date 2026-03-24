import java.time.LocalDate;

/**
 * Nombre de la clase: SensorHumedad
 * <p>
 * Descripción: Implementa el sensor de humedad
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 * @see UdsMedidaHum
 */
public class SensorHumedad extends Sensor {
    /** Nombre que define un sensor de humedad */
    static String nombre = "HUM";
    /** Unidades de medida del sensor */
    private UdsMedidaHum medida;

    /**
     * Constructor para un sensor de humedad
     * @param id               el id del sensor
     * @param offset           el offset del sensor
     * @param min_rango        el valor mínimo del rango de valores aceptados
     * @param max_rango        el valor máximo del rango de valores aceptados
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param medida           las unidades de medida del sensor
     */
    SensorHumedad(String id, double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                  UdsMedidaHum medida) {
        super(id, offset, min_rango, max_rango, fechaInstalacion);
        this.medida = medida;
    }

    /**
     * Constructor para un sensor de humedad sin fecha de instalación especificada
     * @param id        el id del sensor
     * @param offset    el offset del sensor
     * @param min_rango el valor mínimo del rango de valores aceptados
     * @param max_rango el valor máximo del rango de valores aceptados
     * @param medida    las unidades de medida del sensor
     */
    SensorHumedad(String id, double offset, double min_rango, double max_rango, UdsMedidaHum medida) {
        super(id, offset, min_rango, max_rango);
        this.medida = medida;
    }
}