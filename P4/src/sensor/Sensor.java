package sensor;

import java.time.*;
import java.util.Random;
import java.util.random.*;

/**
 * Implementa un sensor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public abstract class Sensor {
    /** El tiempo tras el cual caduca la calibración de un sensor */
    private static Period periodoCaducidad = Period.ofDays(365); // NOTE: Valor default
    /** Identificador único del sensor */
    private final String id;
    /** Offset de calibración del sensor */
    private double offset;
    /** Fecha y hora de la última lectura del sensor */
    private LocalDateTime fechaUltimaLectura;
    /* El valor de la última lectura */
    private double valorUltimaLectura;
    /** La calibración del sensor */
    private boolean calibrado;
    /** Valor mínimo del rango de valores aceptados */
    private double minRango;
    /** Valor máximo del rango de valores aceptados */
    private double maxRango;
    /** Suma de todos los valores obtenidos hasta el momento*/
    private double sumaValores;
    /** Cantidad de todos los valores obtenidos hasta el momento*/
    public int cantidadLecturas;
    /** La fecha de instalación del sensor */
    private LocalDate fechaInstalacion;
    /** Tipo de lectura de sensor */
    private TipoLecturaSensor lecturaSensor;
    /** Procesador de datos */
    private ProcesadorDatos procesadorDeDatos;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor de un sensor
     * @param id               el id de sensor
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha de instalación del sensor
     */
    Sensor(String id, double offset, LocalDate fechaInstalacion) {
        this.id = id;
        this.offset = offset;
        this.fechaInstalacion = fechaInstalacion;
        this.calibrado = true;
        this.sumaValores = 0;
        this.cantidadLecturas = 0;
        this.lecturaSensor = TipoLecturaSensor.MINMAX;
    }

    /**
     * Constructor de un sensor
     * @param id               el id de sensor
     * @param offset           el offset del sensor
     * @param fechaInstalacion la fecha de instalación del sensor
     * @param lecturaSensor el tipo de lectura de sensor
     */
    Sensor(String id, double offset, LocalDate fechaInstalacion, TipoLecturaSensor lecturaSensor) {
        this.id = id;
        this.offset = offset;
        this.fechaInstalacion = fechaInstalacion;
        this.calibrado = true;
        this.sumaValores = 0;
        this.cantidadLecturas = 0;
        this.lecturaSensor = lecturaSensor;
    }

    /**
     * Constructor de un sensor sin fecha de instalación definida
     * @param id     el id de sensor
     * @param offset el offset del sensor
     */
    Sensor(String id, double offset) {
        this.id = id;
        this.offset = offset;
        this.fechaInstalacion = LocalDate.now();
        this.calibrado = true;
        this.sumaValores = 0;
        this.cantidadLecturas = 0;
    }

    public boolean isCalibrado() {
        return calibrado;
    }

    /**
     * Comprueba si la calibración del sensor ha caducado
     */
    public void comprobarCaducidad() {
        LocalDate fechaCaducidad = this.fechaInstalacion.plus(periodoCaducidad);
        if (LocalDate.now().isAfter(fechaCaducidad)) {
            this.calibrado = false;
        }
    }

    /**
     * Permite al sensor medir un valor
     * @param valor el valor a medir
     */
    public void leerValor(double valor) {
        this.valorUltimaLectura = valor - this.offset;
        this.cantidadLecturas = this.cantidadLecturas + 1;
        this.sumaValores = this.sumaValores + this.valorUltimaLectura;
        if (this.valorUltimaLectura > maxRango || this.valorUltimaLectura < minRango) {
            /* El sensor se considera no calibrado cuando se realice una lectura fuera de rango */
            this.calibrado = false;
        }
        this.fechaUltimaLectura = LocalDateTime.now();
    }

    /**
     * Simula la lectura por parte de un sensor
     * @param valorConfigurable el valor a medir
     * @return true si todo funciona correctamente, false en caso contrario
     */
    public boolean simulacionLectura(double valorConfigurable){
        Random rand = new Random();
        double valor;
        double porcentaje;
        double media;

        if(this.lecturaSensor == TipoLecturaSensor.MINMAX){
            if(valorConfigurable < 0 || valorConfigurable > 1){
                return false;
            }

            if(rand.nextDouble() < valorConfigurable){
                valor = this.minRango + (rand.nextDouble() * (this.maxRango - this.minRango));
            } else{
                valor = this.maxRango + (rand.nextDouble() * (this.maxRango - this.minRango));
            }
            

        }else if (this.lecturaSensor == TipoLecturaSensor.RANGOCONFIGURABLE) {
            if(valorConfigurable < 0 || valorConfigurable > 100){
                return false;
            } 

            porcentaje = (this.valorUltimaLectura*valorConfigurable)/100;

            valor = (this.valorUltimaLectura - porcentaje) + (rand.nextDouble() * ((this.valorUltimaLectura + porcentaje) - (this.valorUltimaLectura - porcentaje)));
 
        } else if(this.lecturaSensor == TipoLecturaSensor.RANGOMEDIA){
            if(valorConfigurable < 0 || valorConfigurable > 100){
                return false;
            } 

            media = this.sumaValores/this.cantidadLecturas;

            porcentaje = (media*valorConfigurable)/100;

            valor = (media - porcentaje) + (rand.nextDouble() * ((media + porcentaje) - (media - porcentaje)));
        }else{
            return false;
        }

        leerValor(valor);

        return true;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Permite consultar si el sensor está correctamente calibrado
     * @return true si lo está, false si no
     */
    public boolean getCalibrado() {
        comprobarCaducidad();
        return this.calibrado;
    }

    /**
     * Devuelve la fecha de la última lectura
     * @return la fecha de la última lectura
     */
    public LocalDateTime getFechaUltimaLectura() {
        return this.fechaUltimaLectura;
    }

    /**
     * Devuelve el ID del sensor
     * @return el ID del sensor
     */
    public String getId() {
        return this.id;
    }

    /**
     * Devuelve el último valor leído
     * @return el último valor leído
     */
    public double getValorUltimaLectura() {
        return this.valorUltimaLectura;
    }

    /**
     * Establece el valor máximo del rango de valores válidos del sensor
     * @param newRangoMax el valor máximo del rango de valores válidos del sensor
     */
    public void setMaxRango(double newRangoMax) {
        this.maxRango = newRangoMax;
    }

    /**
     * Establece el valor mínimo del rango de valores válidos del sensor
     * @param newRangoMin el valor mínimo del rango de valores válidos del sensor
     */
    public void setMinRango(double newRangoMin) {
        this.minRango = newRangoMin;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        return this.id + " (desde: " + this.fechaInstalacion + "): sensor.Sensor ";
    }
}