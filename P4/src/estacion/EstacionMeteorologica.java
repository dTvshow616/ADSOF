package estacion;

import alerta.*;
import conversor.*;
import formateador.IDocumento;
import sensor.*;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

/**
 * Implementa una estación meteorológica
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 * @see ProcesadorDatos
 */
public class EstacionMeteorologica implements IDocumento {
    /** Nombre de la estación */
    private final String nombre;
    /** Latitud de la ubicación geográfica de la estación */
    private final double latitud;
    /** Longitud de la ubicación geográfica de la estación */
    private final double longitud;
    /** Lista de sensores que deben excluirse de las mediciones */
    private List<Sensor> sensoresExcluidos = new ArrayList<>();
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
    private HashMap<Exception, Sensor> alertas = new HashMap<>();
    /** Fecha en la que se realizó la última lectura */
    private LocalDateTime fechaUtimaLectura = LocalDateTime.now();
    /** Periodicidad de las lecturas periódicas */
    private Period periodicidadLecturas;
    /** Número de lecturas que se han realizado */
    private int numLecturas;
    /** Número máximo de lecturas que se pueden realizar */
    private int numMaxLecturas;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para una estación meteorológica
     * @param nombre   el nombre de la estación
     * @param latitud  la latitud de las coordenadas de la estación
     * @param longitud la longitud de las coordenadas de la estación
     */
    public EstacionMeteorologica(String nombre, double latitud, double longitud) {
        // DUE: Añadir el resto de parámetros relevantes
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Permite hacer una lectura puntual sobre todos los sensores
     */
    public void lecturaSimultanea() {
        for (Sensor sensor : sensores.values()) {
            if (!sensoresExcluidos.contains(sensor)) {
                try {
                    sensor.simulacionLectura();
                } catch (SensorSinCalibrar e1) {
                    System.out.println(e1.toString());
                    this.sensoresExcluidos.add(sensor);
                    this.alertas.put(e1, sensor);
                } catch (CambioBruscoLectura e2) {
                    System.out.println(e2.toString());
                    this.alertas.put(e2, sensor);
                }
            }
        }
    }

    /**
     * Permite medir un cierto valor con un sensor en concreto
     * @param sensor el sensor deseado
     * @param valor  el valor inicial de la medición
     */
    public void lecturaPuntual(Sensor sensor, double valor) {
        if (!sensoresExcluidos.contains(sensor)) {
            try {
                sensor.leerValor(valor);
            } catch (SensorSinCalibrar e1) {
                System.out.println(e1.toString());
                this.sensoresExcluidos.add(sensor);
                this.alertas.put(e1, sensor);
            } catch (CambioBruscoLectura e2) {
                System.out.println(e2.toString());
                this.alertas.put(e2, sensor);
            }
        }
    }

    /**
     * Permite simular la lectura de un sensor en concreto
     * @param sensor el sensor deseado
     */
    public void simulacionPuntual(Sensor sensor, double valorConfigurable) {
        if (!sensoresExcluidos.contains(sensor)) {
            try {
                sensor.simulacionLectura(valorConfigurable);
            } catch (SensorSinCalibrar e1) {
                System.out.println(e1.toString());
                this.sensoresExcluidos.add(sensor);
                this.alertas.put(e1, sensor);
            } catch (CambioBruscoLectura e2) {
                System.out.println(e2.toString());
                this.alertas.put(e2, sensor);
            }
        }
    }

    /**
     * Permite calibrar un sensor ajustando su offset
     * @param id          el id del sensor
     * @param nuevoOffset el nuevo offset del sensor
     */
    public void calibrarSensor(String id, double nuevoOffset) {
        Sensor sensor = sensores.get(id);

        sensor.setOffset(nuevoOffset);
        sensor.setCalibrado(true);
        for (Exception alerta : this.alertas.keySet()) {
            if (alertas.get(alerta) == sensor) {
                alertas.remove(alerta);
            }
        }

        sensoresExcluidos.remove(sensor);
    }

    /**
     * Permite hacer una lectura periódica sobre todos los sensores
     */
    public void lecturaPeriodica() {
        if ((this.numLecturas < this.numMaxLecturas) &&
            (this.fechaUtimaLectura.plus(this.periodicidadLecturas).isBefore(LocalDateTime.now()))) {
            this.fechaUtimaLectura = LocalDateTime.now();
            lecturaSimultanea();
            this.numLecturas++;
        }
    }

    /**
     * Permite que se añada un sensor de humedad a la estación meteorológica sin especificar la fecha de instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public SensorHumedad addSensorHumedad(double offset, UdsMedidaHum medida) throws SensorYaInstalado {
        Conversores a = new ConversorIdentidad(UdsMedidaHum.PORCENTAJE);
        ProcesadorDatos procesadorDeDatos = new ProcesadorDatos(a);

        SensorHumedad sensorHumedad = new SensorHumedad(offset, medida, procesadorDeDatos);
        if (!this.sensoresHumedad.containsKey(sensorHumedad.getId())) {
            this.sensores.put(sensorHumedad.getId(), sensorHumedad);
            this.sensoresHumedad.put(sensorHumedad.getId(), sensorHumedad);
            this.procesadores.put(sensorHumedad, sensorHumedad.getProcesadorDeDatos());
            return sensorHumedad;
        } else {
            throw new SensorYaInstalado(sensorHumedad, this.sensoresHumedad.get(sensorHumedad.getId()));
        }
    }

    /**
     * Permite que se añada un sensor de humedad a la estación meteorológica sin especificar la fecha de instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public SensorHumedad addSensorHumedad(double offset, UdsMedidaHum medida, Conversores conversor)
            throws SensorYaInstalado, ConversorNoValido {
        if (conversor.getMedidaOrigen() != medida) {
            throw new ConversorNoValido();
        }

        ProcesadorDatos procesadorDeDatos = new ProcesadorDatos(conversor);

        SensorHumedad sensorHumedad = new SensorHumedad(offset, medida, procesadorDeDatos);
        if (!this.sensoresHumedad.containsKey(sensorHumedad.getId())) {
            this.sensores.put(sensorHumedad.getId(), sensorHumedad);
            this.sensoresHumedad.put(sensorHumedad.getId(), sensorHumedad);
            this.procesadores.put(sensorHumedad, sensorHumedad.getProcesadorDeDatos());
            return sensorHumedad;
        } else {
            throw new SensorYaInstalado(sensorHumedad, this.sensoresHumedad.get(sensorHumedad.getId()));
        }
    }

    /**
     * Permite que se añada un sensor de presión a la estación meteorológica sin especificar la fecha de instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public SensorPresion addSensorPresion(double offset, UdsMedidaPres medida) throws SensorYaInstalado {
        Conversores a = new ConversorIdentidad(UdsMedidaPres.HECTOPASCALES);
        ProcesadorDatos procesadorDeDatos = new ProcesadorDatos(a);

        SensorPresion sensorPresion = new SensorPresion(offset, medida, procesadorDeDatos);
        if (!this.sensoresPresion.containsKey(sensorPresion.getId())) {
            this.sensores.put(sensorPresion.getId(), sensorPresion);
            this.sensoresPresion.put(sensorPresion.getId(), sensorPresion);
            this.procesadores.put(sensorPresion, sensorPresion.getProcesadorDeDatos());
            return sensorPresion;
        } else {
            throw new SensorYaInstalado(sensorPresion, this.sensoresPresion.get(sensorPresion.getId()));
        }
    }

    /**
     * Permite que se añada un sensor de presión a la estación meteorológica sin especificar la fecha de instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public SensorPresion addSensorPresion(double offset, UdsMedidaPres medida, Conversores conversor)
            throws SensorYaInstalado, ConversorNoValido {
        if (conversor.getMedidaOrigen() != medida) {
            throw new ConversorNoValido();
        }

        ProcesadorDatos procesadorDeDatos = new ProcesadorDatos(conversor);

        SensorPresion sensorPresion = new SensorPresion(offset, medida, procesadorDeDatos);
        if (!this.sensoresPresion.containsKey(sensorPresion.getId())) {
            this.sensores.put(sensorPresion.getId(), sensorPresion);
            this.sensoresPresion.put(sensorPresion.getId(), sensorPresion);
            this.procesadores.put(sensorPresion, sensorPresion.getProcesadorDeDatos());
            return sensorPresion;
        } else {
            throw new SensorYaInstalado(sensorPresion, this.sensoresPresion.get(sensorPresion.getId()));
        }
    }

    /**
     * Permite que se añada un sensor de temperatura a la estación meteorológica sin especificar la fecha de
     * instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public SensorTemperatura addSensorTemperatura(double offset, UdsMedidaTemp medida) throws SensorYaInstalado {
        Conversores a = new ConversorIdentidad(UdsMedidaTemp.CELSIUS);
        ProcesadorDatos procesadorDeDatos = new ProcesadorDatos(a);

        SensorTemperatura sensorTemperatura = new SensorTemperatura(offset, medida, procesadorDeDatos);
        if (!this.sensoresTemperatura.containsKey(sensorTemperatura.getId())) {
            this.sensores.put(sensorTemperatura.getId(), sensorTemperatura);
            this.sensoresTemperatura.put(sensorTemperatura.getId(), sensorTemperatura);
            this.procesadores.put(sensorTemperatura, sensorTemperatura.getProcesadorDeDatos());
            return sensorTemperatura;
        } else {
            throw new SensorYaInstalado(sensorTemperatura, this.sensoresTemperatura.get(sensorTemperatura.getId()));
        }
    }

    /**
     * Permite que se añada un sensor de temperatura a la estación meteorológica sin especificar la fecha de
     * instalación
     * @param offset el offset del sensor
     * @param medida las unidades de medida del sensor
     */
    public SensorTemperatura addSensorTemperatura(double offset, UdsMedidaTemp medida, Conversores conversor)
            throws SensorYaInstalado, ConversorNoValido {
        if (conversor.getMedidaOrigen() != medida) {
            throw new ConversorNoValido();
        }

        ProcesadorDatos procesadorDeDatos = new ProcesadorDatos(conversor);

        SensorTemperatura sensorTemperatura = new SensorTemperatura(offset, medida, procesadorDeDatos);
        if (!this.sensoresTemperatura.containsKey(sensorTemperatura.getId())) {
            this.sensores.put(sensorTemperatura.getId(), sensorTemperatura);
            this.sensoresTemperatura.put(sensorTemperatura.getId(), sensorTemperatura);
            this.procesadores.put(sensorTemperatura, sensorTemperatura.getProcesadorDeDatos());
            return sensorTemperatura;
        } else {
            throw new SensorYaInstalado(sensorTemperatura, this.sensoresTemperatura.get(sensorTemperatura.getId()));
        }
    }

    /**
     * Permite imprimir la información de la estación meteorológica
     */
    public void imprimirEstacion() {
        System.out.println("Estación Meteorológica: " + this.nombre);
        System.out.println("Ubicación: " + this.latitud + ", " + this.longitud);
        System.out.println(
                "--------------------------------------------------------------------------------------------------\n");

        System.out.println("Sensores instalados: " + this.sensores.size());
        System.out.println("Última lectura: " + this.fechaUtimaLectura);

        for (Sensor sensor : this.sensores.values()) {
            if (!sensoresExcluidos.contains(sensor)) {
                System.out.println(sensor.toString());
                System.out.println("-> " + this.procesadores.get(sensor));
            }
        }

        System.out.println(" ");

        if (!this.alertas.isEmpty()) {
            System.out.println("Alertas activas: " + this.alertas.size());
        }
        for (Exception alerta : this.alertas.keySet()) {
            System.out.println("- " + alerta.toString());
        }
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    public LocalDateTime getFechaUtimaLectura() {
        return fechaUtimaLectura;
    }

    public void setFechaUtimaLectura(LocalDateTime newFechaUtimaLectura) {
        this.fechaUtimaLectura = newFechaUtimaLectura;
    }

    public double getLatitud() {
        return latitud;
    }

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

    public double getLongitud() {
        return longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumLecturas() {
        return numLecturas;
    }

    public void setNumLecturas(int newNumLecturas) {
        this.numLecturas = newNumLecturas;
    }

    public int getNumMaxLecturas() {
        return numMaxLecturas;
    }

    public void setNumMaxLecturas(int newNumMaxLecturas) {
        this.numMaxLecturas = newNumMaxLecturas;
    }

    @Override
    public List<String> getParrafosSeccionPrincipal() {
        List<String> parrafosSeccionPrincipal = new ArrayList<>();

        parrafosSeccionPrincipal.add("Ubicación: " + this.latitud + ", " + this.longitud);
        parrafosSeccionPrincipal.add("Sensores instalados: " + this.sensores.size());
        parrafosSeccionPrincipal.add("Alertas activas: " + this.alertas.size());

        return parrafosSeccionPrincipal;
    }

    public Period getPeriodicidadLecturas() {
        return periodicidadLecturas;
    }

    public void setPeriodicidadLecturas(Period newPeriodicidadLecturas) {
        this.periodicidadLecturas = newPeriodicidadLecturas;
    }

    public HashMap<Sensor, ProcesadorDatos> getProcesadores() {
        return procesadores;
    }

    public void setProcesadores(HashMap<Sensor, ProcesadorDatos> newProcesadores) {
        this.procesadores = newProcesadores;
    }

    @Override
    public String getSeccionPrincipal() {
        return this.nombre;
    }

    @Override
    public HashMap<String, List<String>> getSecciones() {
        HashMap<String, List<String>> secciones = new HashMap<>();

        List<String> listaSensores = new ArrayList<>();
        for (Sensor sensor : this.sensores.values()) {
            if (!sensoresExcluidos.contains(sensor)) {
                listaSensores.add(sensor.toString());
            }
        }

        secciones.put("Sensores activos", listaSensores);

        List<String> listaAlertas = new ArrayList<>();
        for (Exception alerta : this.alertas.keySet()) {
            listaAlertas.add(alerta.toString());
        }

        secciones.put("Alertas activas: " + this.alertas.size(), listaAlertas);

        return secciones;
    }

    /**
     * Permite obtener un sensor a través de su Id
     * @param desiredId el id del sensor deseado
     * @return el sensor deseado
     */
    public Sensor getSensorFromId(String desiredId) {
        return this.sensores.get(desiredId);
    }

    public HashMap<String, Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(HashMap<String, Sensor> newSensores) {
        this.sensores = newSensores;
    }

    public List<Sensor> getSensoresExcluidos() {
        return sensoresExcluidos;
    }

    public void setSensoresExcluidos(List<Sensor> newSensoresExcluidos) {
        this.sensoresExcluidos = newSensoresExcluidos;
    }

    public HashMap<String, SensorHumedad> getSensoresHumedad() {
        return sensoresHumedad;
    }

    public void setSensoresHumedad(HashMap<String, SensorHumedad> newSensoresHumedad) {
        this.sensoresHumedad = newSensoresHumedad;
    }

    public HashMap<String, SensorPresion> getSensoresPresion() {
        return sensoresPresion;
    }

    public void setSensoresPresion(HashMap<String, SensorPresion> newSensoresPresion) {
        this.sensoresPresion = newSensoresPresion;
    }

    public HashMap<String, SensorTemperatura> getSensoresTemperatura() {
        return sensoresTemperatura;
    }

    public void setSensoresTemperatura(HashMap<String, SensorTemperatura> newSensoresTemperatura) {
        this.sensoresTemperatura = newSensoresTemperatura;
    }

    @Override
    public String getTitulo() {
        return "Estación Meteorológica: " + this.nombre;
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

        int counter = 0;

        for (Sensor sensor : this.sensoresPresion.values()) {
            counter++;
            if (counter < sensoresPresion.size()) {
                sb.append(sensor.toString()).append(",\n");
            } else {
                sb.append(sensor.toString());
            }
        }

        sb.append("]");

        return sb.toString();
    }
}