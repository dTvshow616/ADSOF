/**
 * Nombre del enum: UnidadTemperatura
 * <p>
 * Descripción: Define las unidades de temperatura y sus rangos de medición
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public enum UnidadMedida {
    CELSIUS("ºC", -273.15, 1000),
    FAHRENHEIT("ºF", -459.67, 1832),
    KELVIN("K", 0, 1273.15),
    PORCENTAJE("%", 0, 100),
    HECTOPASCALES("hPa", 300, 1100);

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
    UnidadMedida(String simbolo, double minRango, double maxRango) {
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