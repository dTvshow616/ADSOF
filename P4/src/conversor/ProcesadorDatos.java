package conversor;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementa un procesador de datos
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public class ProcesadorDatos {
    /** Registro de lecturas */
    private Map<LocalDateTime, Double> registro;
    /** Conversor de valores */
    private Conversores conversor;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del procesador de datos
     * @param conversor Conversor de valores
     */
    public ProcesadorDatos(Conversores conversor) {
        registro = new HashMap<>();
        this.conversor = conversor;
    }

    /**
     * Añade un valor al registro del procesador de datos
     * @param fechaLectura cuando se ha leido el ultimo valor
     * @param valor valor que se va a añadir al registro convertido
     */
    public void addregistro(LocalDateTime fechaLectura, double valor) {
        registro.put(fechaLectura, conversor.convertir(valor));
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
        return registro.get(Collections.max(registro.keySet()));
    }

    /**
     * Devuelve el valor minimo de registro
     * @return el valor minimo del registro
     */
    public double getMinRegistro(){
        double result = this.conversor.getMedidaDestino().getMaxRango();

        for(Map.Entry<LocalDateTime,Double> entry : this.registro.entrySet()){
            if(entry.getValue() < result){
                result = entry.getValue();
            }
        }
        return result;
    }

    /**
     * Devuelve el valor maximo de registro
     * @return el valor maximo del registro
     */
    public double getMaxRegistro(){
        double result = this.conversor.getMedidaDestino().getMinRango();

        for(Map.Entry<LocalDateTime,Double> entry : this.registro.entrySet()){
            if(entry.getValue() > result){
                result = entry.getValue();
            }
        }
        return result;
    }

    /**
     * Devuelve la media de registro
     * @return la media del registro
     */
    public double getaverage(){
        double suma = 0;
        int i = 0;

        for(Map.Entry<LocalDateTime,Double> entry : this.registro.entrySet()){
            suma = suma + entry.getValue();
            i++;
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

        for(Map.Entry<LocalDateTime,Double> entry : this.registro.entrySet()){
            result = result + entry.getValue() + ", ";
        }

        result = result + "] -- MIN: " + this.getMinRegistro() + "MAX: " + this.getMaxRegistro() + "AVG: " + this.getaverage(); 

        return result;
    }
}