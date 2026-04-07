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
    /** Contador de ids de todos los sensores de este tipo */
    private static int totalId = -1;
    /** Unidades de medida del sensor */
    private UdsMedidaPres medida;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para un sensor de presión
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param medida           las unidades de medida del sensor
     */
    SensorPresion(double offset, LocalDate fechaInstalacion, UdsMedidaPres medida) {
        super(TipoSensor.PRESION.getNombre() + "-" + String.format("%04d", ++totalId), offset, fechaInstalacion);
        this.medida = medida;
    }

    /**
     * Constructor para un sensor de presión sin fecha de instalación especificada
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    SensorPresion(double offset, UdsMedidaPres medida) {
        super(TipoSensor.PRESION.getNombre() + "-" + String.format("%04d", ++totalId), offset);
        this.medida = medida;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public UdsMedidaPres getMedida() {
        return medida;
    }

    public void setMedida(UdsMedidaPres newMedida) {
        this.medida = newMedida;
        super.setMin_rango(this.medida.getMinRango());
        super.setMax_rango(this.medida.getMaxRango());
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return super.toString() + "Presión (" + this.getValorUltimaLectura() + this.medida.getSimbolo() + ") última " +
               "lectura: " + this.getFechaUltimaLectura();
    }
}