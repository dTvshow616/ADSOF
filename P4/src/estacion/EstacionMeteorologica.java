package estacion;

import alerta.SensorYaInstalado;
import conversor.ProcesadorDatos;
import sensor.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementa una estación meteorológica
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 */
public class EstacionMeteorologica {
    /** Lista de sensores que deben excluirse de las mediciones */
    private List<Sensor> sensoresExcluidos = new ArrayList<>();
    /** Nombre de la estación */
    private String nombre;
    /** Latitud de la ubicación geográfica de la estación */
    private double latitud;
    /** Longitud de la ubicación geográfica de la estación */
    private double longitud;
    /** El conjunto de todos los sensores de la estación */
    private HashMap<String, Sensor> sensores = new HashMap<>();
    /** El conjunto de procesadores de datos correspondientes a los sensores */
    private HashMap<Sensor, ProcesadorDatos> procesadores = new HashMap<>();
    /** El conjunto de sensores de humedad de la estación */
    private HashMap<String, SensorHumedad> sensoresHumedad = new HashMap<>();
    /** El conjunto de sensores de presión de la estación */
    private HashMap<String, SensorPresion> sensoresPresion = new HashMap<>();
    /** El conjunto de sensores de temperatura de la estación */
    private HashMap<String, SensorTemperatura> sensoresTemperatura = new HashMap<>();
    /** Histórico de alertas de la estación */
    private HashMap<Sensor, String> alertas = new HashMap<>();
    /** Fecha en la que se realizó la última lectura */
    private LocalDateTime fechaUtimaLectura = LocalDateTime.now();

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para una estación meteorológica
     * @param nombre   el nombre de la estación
     * @param latitud  la latitud de las coordenadas de la estación
     * @param longitud la longitud de las coordenadas de la estación
     */
    public EstacionMeteorologica(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /**
     * Permite que se añada un sensor de humedad a la estación meteorológica
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha en la que se instaló el sensor
     * @param medida           las unidades de medida del sensor
     */
    public void addSensorHumedad(double offset, LocalDateTime fechaInstalacion, UdsMedidaHum medida)
            throws SensorYaInstalado {
        SensorHumedad sensorHumedad = new SensorHumedad(offset, fechaInstalacion, medida);
        if (!this.sensoresHumedad.containsKey(sensorHumedad.getId())) {
            this.sensoresHumedad.put(sensorHumedad.getId(), sensorHumedad);
            this.sensores.put(sensorHumedad.getId(), sensorHumedad);
            this.procesadores.put(sensorHumedad, sensorHumedad.getProcesadorDeDatos());
        } else {
            throw new SensorYaInstalado(sensorHumedad);
        }
    }

    /**
     * Permite que se añada un sensor de presión a la estación meteorológica
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha en la que se instaló el sensor
     * @param medida           las unidades de medida del sensor
     */
    public void addSensorPresion(double offset, LocalDateTime fechaInstalacion, UdsMedidaPres medida)
            throws SensorYaInstalado {
        SensorPresion sensorPresion = new SensorPresion(offset, fechaInstalacion, medida);
        if (!this.sensoresPresion.containsKey(sensorPresion.getId())) {
            this.sensoresPresion.put(sensorPresion.getId(), sensorPresion);
            this.sensores.put(sensorPresion.getId(), sensorPresion);
            this.procesadores.put(sensorPresion, sensorPresion.getProcesadorDeDatos());
        } else {
            throw new SensorYaInstalado(sensorPresion);
        }
    }

    /**
     * Permite que se añada un sensor de temperatura a la estación meteorológica
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha en la que se instaló el sensor
     * @param medida           las unidades de medida del sensor
     */
    public void addSensorTemperatura(double offset, LocalDateTime fechaInstalacion, UdsMedidaTemp medida)
            throws SensorYaInstalado {
        SensorTemperatura sensorTemperatura = new SensorTemperatura(offset, fechaInstalacion, medida);
        if (!this.sensoresTemperatura.containsKey(sensorTemperatura.getId())) {
            this.sensoresTemperatura.put(sensorTemperatura.getId(), sensorTemperatura);
            this.sensores.put(sensorTemperatura.getId(), sensorTemperatura);
            this.procesadores.put(sensorTemperatura, sensorTemperatura.getProcesadorDeDatos());
        } else {
            throw new SensorYaInstalado(sensorTemperatura);
        }
    }

    /**
     * Permite que se añada un sensor de humedad a la estación meteorológica sin especificar la fecha de instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public void addSensorHumedad(double offset, UdsMedidaHum medida) throws SensorYaInstalado {
        SensorHumedad sensorHumedad = new SensorHumedad(offset, medida);
        if (!this.sensoresHumedad.containsKey(sensorHumedad.getId())) {
            this.sensoresHumedad.put(sensorHumedad.getId(), sensorHumedad);
            this.procesadores.put(sensorHumedad, sensorHumedad.getProcesadorDeDatos());
        } else {
            throw new SensorYaInstalado(sensorHumedad);
        }
    }

    /**
     * Permite que se añada un sensor de presión a la estación meteorológica sin especificar la fecha de instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public void addSensorPresion(double offset, UdsMedidaPres medida) throws SensorYaInstalado {
        SensorPresion sensorPresion = new SensorPresion(offset, medida);
        if (!this.sensoresPresion.containsKey(sensorPresion.getId())) {
            this.sensoresPresion.put(sensorPresion.getId(), sensorPresion);
            this.procesadores.put(sensorPresion, sensorPresion.getProcesadorDeDatos());
        } else {
            throw new SensorYaInstalado(sensorPresion);
        }
    }

    /**
     * Permite que se añada un sensor de temperatura a la estación meteorológica sin especificar la fecha de
     * instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public void addSensorTemperatura(double offset, UdsMedidaTemp medida) throws SensorYaInstalado {
        SensorTemperatura sensorTemperatura = new SensorTemperatura(offset, medida);
        if (!this.sensoresTemperatura.containsKey(sensorTemperatura.getId())) {
            this.sensoresTemperatura.put(sensorTemperatura.getId(), sensorTemperatura);
            this.procesadores.put(sensorTemperatura, sensorTemperatura.getProcesadorDeDatos());
        } else {
            throw new SensorYaInstalado(sensorTemperatura);
        }
    }

    /**
     * Permite imprimir la información de la estación meteorológica
     */
    public void imprimirEstacion() {
        System.out.println("Estación Meteorológica :" + this.nombre);
        System.out.println("Ubicación: " + this.latitud + "," + this.longitud);
        System.out.println(
                "--------------------------------------------------------------------------------------------------");

        System.out.println("Sensores instalados: " + this.sensores.size());
        System.out.println("Última lectura: " + this.fechaUtimaLectura);

        System.out.println("\n");

        for (Sensor sensor : this.sensores.values()) {
            System.out.println(sensor.toString());
        }

        System.out.println("\n");

        for (String alerta : this.alertas.values()) {
            System.out.println(alerta);
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Permite obtener todos los sensores registrados
     * @return un listado con todos los sensores registrados
     */
    public List<Sensor> getListSensores() {
        return new ArrayList<>(this.sensores.values());
    }

    /**
     * Permite obtener todos los sensores de tipo Humedad
     * @return un listado con todos los sensores de tipo Humedad
     */
    public List<SensorHumedad> getListSensoresHumedad() {
        return new ArrayList<>(this.sensoresHumedad.values());
    }

    /**
     * Permite obtener todos los sensores de tipo Presión
     * @return un listado con todos los sensores de tipo Presión
     */
    public List<SensorPresion> getListSensoresPresion() {
        return new ArrayList<>(this.sensoresPresion.values());
    }

    /**
     * Permite obtener todos los sensores de tipo Temperatura
     * @return un listado con todos los sensores de tipo Temperatura
     */
    public List<SensorTemperatura> getListSensoresTemperatura() {
        return new ArrayList<>(this.sensoresTemperatura.values());
    }

    /**
     * Permite obtener un sensor a través de su Id
     * @param desiredId el id del sensor deseado
     * @return el sensor deseado
     */
    public Sensor getSensorFromId(String desiredId) {
        return this.sensores.get(desiredId);
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