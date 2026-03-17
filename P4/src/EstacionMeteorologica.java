import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Nombre de la clase: EstaciónMeteorológica
 * <p>
 * Descripción: Implementa una estación meteorológica
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Sensor
 * @see TipoSensor
 */
public class EstacionMeteorologica {
    /** El conjunto de sensores de la estación */
    HashMap<String, Sensor> sensores = new HashMap<>();
    /** Nombre de la estación */
    String nombre;
    /** Latitud de la ubicación geográfica de la estación */
    double latitud;
    /** Longitud de la ubicación geográfica de la estación */
    double longitud;
    /** Conteo de ids de los sensores */
    int idNuevoSensor;

    /**
     * Constructor para una estación meteorológica
     * @param nombre el nombre de la estación
     * @param latitud la latitud de las coordenadas de la estación
     * @param longitud la longitud de las coordenadas de la estación
     */
    EstacionMeteorologica(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /**
     * Permite añadir sensores a la estación meteorológica
     * @param tipoSensor    el tipo de sensor a crear
     * @param ultimaLectura la última lectura del sensor
     * @param offset        el offset del sensor
     */
    private void addSensor(TipoSensor tipoSensor, LocalDateTime ultimaLectura, double offset) { // DUE: Fecha instal.
        String sensorId = tipoSensor.toString() + "-" + this.idNuevoSensor; // DUE: Debe ser de 4 cifras
        this.idNuevoSensor++;

        if (this.sensores.containsKey(sensorId)) {
            return;
        }

        switch (tipoSensor) {
            case TEMP:
                sensores.put(sensorId, new SensorTemperatura(sensorId, ultimaLectura, offset));
                return;
            case HUM:
                sensores.put(sensorId, new SensorHumedad(sensorId, ultimaLectura, offset));
                return;
            case PRES:
                sensores.put(sensorId, new SensorPresion(sensorId, ultimaLectura, offset));
        }
    }
}