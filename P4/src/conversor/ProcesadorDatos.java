package conversor;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementa un procesador de datos
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public class ProcesadorDatos {
    private List<LocalDateTime> horaRegistro;
    /** Registro de lecturas */
    private List<Double> registro;
    /** Conversor de valores */
    private Conversores conversor;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del procesador de datos
     * @param conversor Conversor de valores
     */
    public ProcesadorDatos(Conversores conversor) {
        this.horaRegistro = new ArrayList<>();
        this.registro = new ArrayList<>();
        this.conversor = conversor;
    }

    /**
     * Añade un valor al registro del procesador de datos
     * @param fechaLectura cuando se ha leído el último valor
     * @param valor valor que se va a añadir al registro convertido
     */
    public void addRegistro(LocalDateTime fechaLectura, double valor) {
        horaRegistro.add(fechaLectura);
        registro.add(conversor.convertir(valor));
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Devuelve el conversor
     * @return el Conversor
     */
    public Conversores getConversor() {
        return this.conversor;
    }

    /**
     * Devuelve el último registro leído
     * @return el último registro leído
     */
    public double getUltimoRegistro() {
        if (registro.isEmpty()) {
            return Double.NaN;
        }

        return registro.get(registro.size()-1);
    }

    /**
     * Devuelve el valor minimo de registro
     * @return el valor minimo del registro
     */
    public double getMinRegistro(){
        double result = this.conversor.getMedidaDestino().getMaxRango();

        for(double entry : this.registro){
            if(!Double.isNaN(entry) && entry < result){
                result = entry;
            }
        }
        return result;
    }

    /**
     * Devuelve el valor máximo de registro
     * @return el valor máximo del registro
     */
    public double getMaxRegistro(){
        double result = this.conversor.getMedidaDestino().getMinRango();

        for(double entry : this.registro){
            if(!Double.isNaN(entry) && entry > result){
                result = entry;
            }
        }
        return result;
    }

    /**
     * Devuelve la media de registro
     * @return la media del registro
     */
    public double getAverage(){
        double suma = 0;
        int i = 0;

        for(double entry : this.registro){
            if (!Double.isNaN(entry)) {
                suma = suma + entry;
                i++;
            }
        }

        return (suma/i);
    }

        /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/
    @Override
    public String toString() {
        String result;


        result = "(" + this.conversor.getMedidaOrigen().getSimbolo() + ")";

        if(this.conversor instanceof ConversorIdentidad){
            result = result + ": [";
        }else{
            result = result + "con conversor a" + this.conversor.getMedidaDestino().getSimbolo() + ": [";
        }

        for(double entry : this.registro){
            result = result + entry + ", ";
        }

        result = result + "] -- MIN: " + this.getMinRegistro() + "MAX: " + this.getMaxRegistro() + "AVG: " + this.getAverage();

        return result;
    }
}