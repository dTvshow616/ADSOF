package alerta;

/**
 * Implementa la excepción para un conversor no válido
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class ConversorNoValido extends Exception {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para esta excepción
     */
    public ConversorNoValido() {
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Define el mensaje de la excepción
     * @return descripción de la excepción
     */
    @Override
    public String toString() {
        return "El conversor no es valido";
    }

}