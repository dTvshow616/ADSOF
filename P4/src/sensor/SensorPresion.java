package sensor;

import conversor.ProcesadorDatos;

/**
 * Nombre de la clase: sensor.SensorPresion
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
     * Constructor para un sensor de presión sin fecha de instalación especificada
     * @param offset            el offset del sensor
     * @param medida            las unidades de medida del sensor
     * @param procesadorDeDatos el procesador de datos
     */
    public SensorPresion(double offset, UdsMedidaPres medida, ProcesadorDatos procesadorDeDatos) {
        super(TipoSensor.PRESION.getNombre() + "-" + String.format("%04d", ++totalId), offset, procesadorDeDatos);
        this.setMedida(medida);
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    @Override
    public String getSimboloMedida() {
        return this.medida.getSimbolo();
    }

    /**
     * Establece la medida del sensor
     * @param newMedida la medida del sensor
     */
    public void setMedida(UdsMedidaPres newMedida) {
        this.medida = newMedida;
        super.setMinRango(this.medida.getMinRango());
        super.setMaxRango(this.medida.getMaxRango());
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return super.toString() + "Presión (" + this.getValorUltimaLectura() + this.medida.getSimbolo() + ") última " +
               "lectura: " + this.getFechaUltimaLectura();
    }
}