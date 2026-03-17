/**
 * Nombre del enum: TipoSensor
 * <p>
 * Descripción: Define los posibles tipos de sensor y sus rangos de medición
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public enum TipoSensor {
    TEMP(-273.15, 1000), // REVIEW: No sé cómo de bien está esto
    HUM(0, 100),
    PRES(300, 1100);

    /** Valor mínimo del rango de valores aceptados */
    private final double minRango;
    /** Valor máximo del rango de valores aceptados */
    private final double maxRango;

    TipoSensor(double minRango, double maxRango) {
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
     * Obtiene el nombre de la enumeración
     * @return el nombre de la enumeración
     */
    @Override
    public String toString() {
        return this.name();
    }
}