package conversor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementa un procesador de datos
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public abstract class ProcesadorDatos {
    /** Registro de lecturas */
    private Map<LocalDateTime, Double> registro;
    /** Conversor de valores */
    private Conversores conversor;

/*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/
    public ProcesadorDatos() {
        registro = new HashMap<>();
    }

    public void addregistro(LocalDateTime fechaLectura, double valor) {
        registro.put(fechaLectura, valor);
    }

/*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    public Conversores getConversor() {
        return this.conversor;
    }

}