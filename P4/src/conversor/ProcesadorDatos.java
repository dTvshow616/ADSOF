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
        registro.put(fechaLectura, valor);
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

}