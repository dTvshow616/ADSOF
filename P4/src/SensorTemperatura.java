import java.time.LocalDateTime;

/**
 * Nombre de la clase: SensorTemperatura
 * <p>
 * Descripción: Implementa el sensor de temperatura
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Sensor
 * @see UnidadTemperatura
 */
public class SensorTemperatura extends Sensor {
    private UnidadTemperatura udTemp;

    SensorTemperatura(String id, LocalDateTime ultimaLectura, double offset) {
        super(id, ultimaLectura, offset, TipoSensor.TEMP.getMinRango(), TipoSensor.TEMP.getMaxRango());
    }
}