package sensor;

/**
 * Define las unidades de medida posible para los sensores de humedad
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public enum UdsMedidaHum implements UdsMedida {
    /** La humedad se mide en porcentaje */
    PORCENTAJE("%", 0, 100);
    /** Símbolo que representa a la medida */
    private final String simbolo;
    /** Valor mínimo del rango de valores aceptados */
    private final double minRango;
    /** Valor máximo del rango de valores aceptados */
    private final double maxRango;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para una unidad de temperatura
     * @param minRango valor mínimo del rango de valores aceptados
     * @param maxRango valor máximo del rango de valores aceptados
     */
    UdsMedidaHum(String simbolo, double minRango, double maxRango) {
        this.simbolo = simbolo;
        this.minRango = minRango;
        this.maxRango = maxRango;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Sirve para obtener el valor máximo del rango de valores aceptados de un tipo de temperatura
     * @return el valor máximo del rango de valores aceptados de un tipo de temperatura
     */
    public double getMaxRango() {
        return this.maxRango;
    }

    /**
     * Sirve para obtener el valor mínimo del rango de valores aceptados de un tipo de temperatura
     * @return el valor mínimo del rango de valores aceptados de un tipo de temperatura
     */
    public double getMinRango() {
        return this.minRango;
    }

    /**
     * Obtiene el símbolo de la medida de la enumeración
     * @return el símbolo de la medida de la enumeración
     */
    public String getSimbolo() {
        return this.simbolo;
    }
}