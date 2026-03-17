import java.time.LocalDateTime;

/**
 * Nombre de la clase: SensorHumedad
 * <p>
 * Descripción: Implementa el sensor de humedad
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Sensor
 */
public class SensorHumedad extends Sensor {
    SensorHumedad(String id, LocalDateTime ultimaLectura, double offset) {
        super(id, ultimaLectura, offset, TipoSensor.HUM.getMinRango(), TipoSensor.HUM.getMaxRango());
    }
}