package sensor;

import java.time.LocalDate;

/**
 * Nombre de la clase: sensor.SensorHumedad
 * <p>
 * Descripción: Implementa el sensor de humedad
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 * @see UdsMedidaHum
 */
public class SensorHumedad extends Sensor {
    /** Contador de ids de todos los sensores de este tipo */
    private static int totalId = -1;
    /** Unidades de medida del sensor */
    private UdsMedidaHum medida;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para un sensor de humedad
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param medida           las unidades de medida del sensor
     */
    public SensorHumedad(double offset, LocalDate fechaInstalacion, UdsMedidaHum medida) {
        super(TipoSensor.HUMEDAD.getNombre() + "-" + String.format("%04d", ++totalId), offset,
                fechaInstalacion);
        this.setMedida(medida);
    }

    /**
     * Constructor para un sensor de humedad sin fecha de instalación especificada
     * @param offset    el offset del sensor
     * @param medida    las unidades de medida del sensor
     */
    public SensorHumedad(double offset, UdsMedidaHum medida) {
        super(TipoSensor.HUMEDAD.getNombre() + "-" + String.format("%04d", ++totalId), offset);
        this.setMedida(medida);
    }

    public static int getTotalId() {
        return totalId;
    }

    public static void setTotalId(int newTotalId) {
        SensorHumedad.totalId = newTotalId;
    }

    public UdsMedidaHum getMedida() {
        return medida;
    }

    public void setMedida(UdsMedidaHum newMedida) {
        this.medida = newMedida;
        super.setMin_rango(this.medida.getMinRango());
        super.setMax_rango(this.medida.getMaxRango());
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return super.toString() + "Humedad (" + this.getValorUltimaLectura() + this.medida.getSimbolo() + ") última " +
               "lectura: " + this.getFechaUltimaLectura();
    }
}