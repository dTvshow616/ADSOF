package sensor;

/**
 * Define las funciones básicas de las Unidades de Medida
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public interface UdsMedida {
/*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/
    /**
     * Obtiene el valor máximo del rango de la unidad
     * @return el valor máximo del rango de la unidad
     */
    public double getMaxRango();

    /**
     * Obtiene el valor mínimo del rango de la unidad
     * @return el valor mínimo del rango de la unidad
     */
    public double getMinRango();

    /**
     * Obtiene el símbolo que representa a la unidad
     * @return el símbolo que representa a la unidad
     */
    public String getSimbolo();
}