import java.time.LocalDateTime;

/**
 * Nombre de la clase: SensorPresion
 * <p>
 * Descripción: Implementa el sensor de presión
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Sensor
 */
public class SensorPresion extends Sensor {
    SensorPresion(String id, LocalDateTime ultimaLectura, double offset) {
        super(id, ultimaLectura, offset, TipoSensor.PRES.getMinRango(), TipoSensor.PRES.getMaxRango());
    }
}