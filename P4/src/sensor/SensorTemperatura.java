package sensor;

import java.time.LocalDate;

/**
 * Nombre de la clase: sensor.SensorTemperatura
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
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param medida           las unidades de medida del sensor
     */
    public SensorTemperatura(double offset, LocalDate fechaInstalacion, UdsMedidaTemp medida) {
        super(TipoSensor.TEMPERATURA.getNombre() + "-" + String.format("%04d", ++totalId), offset, fechaInstalacion);
        this.medida = medida;
    }

    /**
     * Constructor para un sensor de temperatura sin fecha de instalación especificada
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public SensorTemperatura(double offset, UdsMedidaTemp medida) {
        super(TipoSensor.TEMPERATURA.getNombre() + "-" + String.format("%04d", ++totalId), offset);
        this.medida = medida;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public UdsMedidaTemp getMedida() {
        return medida;
    }

    public void setMedida(UdsMedidaTemp newMedida) {
        this.medida = newMedida;
        super.setMin_rango(this.medida.getMinRango());
        super.setMax_rango(this.medida.getMaxRango());
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return super.toString() + "Temperatura (" + this.getValorUltimaLectura() + this.medida.getSimbolo() + ") " +
               "última " + "lectura: " + this.getFechaUltimaLectura();
    }
}