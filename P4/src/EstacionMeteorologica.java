import java.time.LocalDate;
import java.util.HashMap;

/**
 * Nombre de la clase: EstaciónMeteorológica
 * <p>
 * Descripción: Implementa una estación meteorológica
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 */
public class EstacionMeteorologica {
    /** El conjunto de sensores de humedad de la estación */
    HashMap<String, SensorHumedad> sensoresHumedad = new HashMap<>();
    /** El conjunto de sensores de presion de la estación */
    HashMap<String, SensorPresion> sensoresPresion = new HashMap<>();
    /** El conjunto de sensores de temperatura de la estación */
    HashMap<String, SensorTemperatura> sensoresTemperatura = new HashMap<>();
    /** Nombre de la estación */
    String nombre;
    /** Latitud de la ubicación geográfica de la estación */
    double latitud;
    /** Longitud de la ubicación geográfica de la estación */
    double longitud;
    /** Conteo de ids de los sensores */
    int idNuevoSensor;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para una estación meteorológica
     * @param nombre   el nombre de la estación
     * @param latitud  la latitud de las coordenadas de la estación
     * @param longitud la longitud de las coordenadas de la estación
     */
    EstacionMeteorologica(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public void addSensorHumedad(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                                 UdsMedidaHum medida) {
        SensorHumedad sensorHumedad = new SensorHumedad(offset, min_rango, max_rango, fechaInstalacion, medida);
        if (!this.sensoresHumedad.containsKey(sensorHumedad.getId())) {
            this.sensoresHumedad.put(sensorHumedad.getId(), sensorHumedad);
        }
    }

    public void addSensorPresion(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                                 UdsMedidaPres medida) {
        SensorPresion sensorPresion = new SensorPresion(offset, min_rango, max_rango, fechaInstalacion, medida);
        if (!this.sensoresPresion.containsKey(sensorPresion.getId())) {
            this.sensoresPresion.put(sensorPresion.getId(), sensorPresion);
        }
    }

    public void addSensorTemperatura(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                                     UdsMedidaTemp medida) {
        SensorTemperatura sensorTemperatura =
                new SensorTemperatura(offset, min_rango, max_rango, fechaInstalacion, medida);
        if (!this.sensoresTemperatura.containsKey(sensorTemperatura.getId())) {
            this.sensoresTemperatura.put(sensorTemperatura.getId(), sensorTemperatura);
        }
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Sensor sensor : this.sensoresTemperatura.values()) {
            sb.append(sensor.toString()).append(",\n");
        }
        for (Sensor sensor : this.sensoresHumedad.values()) {
            sb.append(sensor.toString()).append(",\n");
        }
        for (Sensor sensor : this.sensoresPresion.values()) {
            sb.append(sensor.toString()).append(",\n");
        }

        sb.append("]");

        return sb.toString();
    }
}