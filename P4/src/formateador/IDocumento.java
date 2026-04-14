package formateador;

import java.util.HashMap;
import java.util.List;

/**
 * The interface Documento.
 */
public interface IDocumento {
    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Obtiene los párrafos de la sección principal
     * @return los párrafos de la sección principal
     */
    List<String> getParrafosSeccionPrincipal();

    /**
     * Obtiene la sección principal
     * @return la sección principal
     */
    String getSeccionPrincipal();

    /**
     * Obtiene las secciones del documento
     * @return las secciones del documento
     */
    HashMap<String, List<String>> getSecciones();

    /**
     * Obtiene el título del documento
     * @return el título del documento
     */
    String getTitulo();

}