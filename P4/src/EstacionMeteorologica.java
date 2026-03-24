import java.time.LocalDate;
import java.util.HashMap;

/**
 * Nombre de la clase: EstaciónMeteorológica
 * <p>
 * Descripción: Implementa una estación meteorológica
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Sensor
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
        //RELLENAR
    }

    public void addSensorPresion(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                                 UdsMedidaHum medida) {
        //RELLENAR
    }

    public void addSensorTemperatura(double offset, double min_rango, double max_rango, LocalDate fechaInstalacion,
                                     UdsMedidaHum medida) {
        //RELLENAR
    }
}