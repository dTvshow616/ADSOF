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
    public ProcesadorDatos(Conversores conversor) {
        registro = new HashMap<>();
        this.conversor = conversor;
    }

    public void addregistro(LocalDateTime fechaLectura, double valor) {
        registro.put(fechaLectura, conversor.convertir(valor));
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
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

    public double getMinRegistro(){
        double result = this.conversor.getMedidaDestino().getMaxRango();

        for(Map.Entry<LocalDateTime,Double> entry : this.registro.entrySet()){
            if(entry.getValue() < result){
                result = entry.getValue();
            }
        }
        return result;
    }

    public double getMaxRegistro(){
        double result = this.conversor.getMedidaDestino().getMinRango();

        for(Map.Entry<LocalDateTime,Double> entry : this.registro.entrySet()){
            if(entry.getValue() > result){
                result = entry.getValue();
            }
        }
        return result;
    }

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