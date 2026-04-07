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
    /** Contador de ids de todos los sensores de este tipo */
    private static int totalId = -1;
    /** Unidades de medida del sensor */
    private UdsMedidaTemp medida;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para un sensor de temperatura
     * @param offset           el offset del sensor
     * @param min_rango        el valor mínimo del rango de valores aceptados
     * @param max_rango        el valor máximo del rango de valores aceptados
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param medida           las unidades de medida del sensor
     */
    SensorTemperatura(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                      UdsMedidaTemp medida) {
        super(TipoSensor.TEMPERATURA.getNombre() + "-" + String.format("%04d", ++totalId), offset, min_rango, max_rango,
                fechaInstalacion);
        this.medida = medida;
    }

    /**
     * Constructor para un sensor de temperatura sin fecha de instalación especificada
     * @param offset    el offset del sensor
     * @param min_rango el valor mínimo del rango de valores aceptados
     * @param max_rango el valor máximo del rango de valores aceptados
     * @param medida    las unidades de medida del sensor
     */
    SensorTemperatura(double offset, double min_rango, double max_rango, UdsMedidaTemp medida) {
        super(TipoSensor.TEMPERATURA.getNombre() + "-" + String.format("%04d", ++totalId), offset, min_rango,
                max_rango);
        this.medida = medida;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return super.toString() + "Temperatura (" + this.getValorUltimaLectura() + this.medida.getSimbolo() + ") " +
               "última " + "lectura: " + this.getFechaUltimaLectura();
    }
}