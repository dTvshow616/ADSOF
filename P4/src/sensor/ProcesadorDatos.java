package sensor;

import java.util.Map;
import java.util.HashMap;
import java.time.*;

/**
 * Implementa un procesador de datos
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public abstract class ProcesadorDatos {
    /** Registro de lecturas*/
    private Map<LocalDateTime,Double> registro;
    /** Conversor de valores*/
    private Conversores conversor;

    public ProcesadorDatos(){
        registro = new HashMap<>();
    }

    public void addregistro(LocalDateTime fechaLectura, double valor){
        registro.put(fechaLectura, valor);
    }

    public Conversores getConversor(){
        return this.conversor;
    }

}
