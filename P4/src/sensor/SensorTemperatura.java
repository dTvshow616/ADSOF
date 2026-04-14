package sensor;

import conversor.ProcesadorDatos;

import java.time.temporal.ChronoUnit;

/**
 * Implementa el sensor de temperatura
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 * @see UdsMedidaTemp
 */
public class SensorTemperatura extends Sensor {
    /** Contador de ids de todos los sensores de este tipo */
    private static int totalId = 0;
    /** Unidades de medida del sensor */
    private UdsMedidaTemp medida;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para un sensor de temperatura sin fecha de instalación especificada
     * @param offset            el offset del sensor
     * @param medida            las unidades de medida del sensor
     * @param procesadorDeDatos el procesador de datos
     */
    public SensorTemperatura(double offset, UdsMedidaTemp medida, ProcesadorDatos procesadorDeDatos) {
        super(TipoSensor.TEMPERATURA.getNombre() + "-" + String.format("%04d", ++totalId), offset, procesadorDeDatos);
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
    public void setMedida(UdsMedidaTemp newMedida) {
        this.medida = newMedida;
        super.setMinRango(this.medida.getMinRango());
        super.setMaxRango(this.medida.getMaxRango());
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return super.toString() + "Temperatura (" + this.getValorUltimaLectura() + this.medida.getSimbolo() + ") " +
               "última " + "lectura: " + this.getFechaUltimaLectura().truncatedTo(ChronoUnit.SECONDS);
    }
}