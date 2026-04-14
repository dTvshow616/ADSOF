package formateador;

import java.util.HashMap;
import java.util.List;

public interface IDocumento {
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    List<String> getParrafosSeccionPrincipal();

    String getSeccionPrincipal();

    HashMap<String, List<String>> getSecciones();

    String getTitulo();

}