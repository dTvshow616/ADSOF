/**
 * Nombre de la enumeración: UdsMedidaTemp
 * <p>
 * Descripción: Define las unidades de medida posible para los sensores de temperatura
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see SensorTemperatura
 */
public enum UdsMedidaTemp {
    /** La temperatura se mide en Celsius */
    CELSIUS("ºC", -273.15, 1000),
    /** La temperatura se mide en Fahrenheit */
    FAHRENHEIT("ºF", -459.67, 1832),
    /** La temperatura se mide en Kelvin */
    KELVIN("K", 0, 1273.15);
    /** Símbolo que representa a la medida */
    private final String simbolo;
    /** Valor mínimo del rango de valores aceptados */
    private final double minRango;
    /** Valor máximo del rango de valores aceptados */
    private final double maxRango;

    /**
     * Constructor para una unidad de temperatura
     * @param minRango valor mínimo del rango de valores aceptados
     * @param maxRango valor máximo del rango de valores aceptados
     */
    UdsMedidaTemp(String simbolo, double minRango, double maxRango) {
        this.simbolo = simbolo;
        this.minRango = minRango;
        this.maxRango = maxRango;
    }

    /**
     * Sirve para obtener el valor mínimo del rango de valores aceptados de un tipo de temperatura
     * @return el valor mínimo del rango de valores aceptados de un tipo de temperatura
     */
    public double getMinRango() {
        return this.minRango;
    }

    /**
     * Sirve para obtener el valor máximo del rango de valores aceptados de un tipo de temperatura
     * @return el valor máximo del rango de valores aceptados de un tipo de temperatura
     */
    public double getMaxRango() {
        return this.maxRango;
    }

    /**
     * Obtiene el símbolo de la medida de la enumeración
     * @return el símbolo de la medida de la enumeración
     */
    public String getSimbolo() {
        return this.simbolo;
    }
}