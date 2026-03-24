import java.time.LocalDate;

/**
 * Nombre de la clase: SensorTemperatura
 * <p>
 * Descripción: Implementa el sensor de temperatura
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 * @see UdsMedidaTemp
 */
public class SensorTemperatura extends Sensor {
    /** Nombre que define un sensor de temperatura */
    static String nombre = "TEMP";
    /** Unidades de medida del sensor */
    private UdsMedidaHum medida;

    /**
     * Constructor para un sensor de temperatura
     * @param offset           el offset del sensor
     * @param min_rango        el valor mínimo del rango de valores aceptados
     * @param max_rango        el valor máximo del rango de valores aceptados
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param medida           las unidades de medida del sensor
     */
    SensorTemperatura(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                      UdsMedidaHum medida) {
        super(TipoSensor.PRES, offset, min_rango, max_rango, fechaInstalacion);
        this.medida = medida;
    }

    /**
     * Constructor para un sensor de temperatura sin fecha de instalación especificada
     * @param offset    el offset del sensor
     * @param min_rango el valor mínimo del rango de valores aceptados
     * @param max_rango el valor máximo del rango de valores aceptados
     * @param medida    las unidades de medida del sensor
     */
    SensorTemperatura(double offset, double min_rango, double max_rango, UdsMedidaHum medida) {
        super(TipoSensor.PRES, offset, min_rango, max_rango);
        this.medida = medida;
    }
}