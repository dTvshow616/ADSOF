/**
 * Nombre del enum: TipoSensor
 * <p>
 * Descripción: Define los posibles tipos de sensor y sus rangos de medición
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public enum TipoSensor {
    TEMP, // REVIEW: No sé cómo de bien está esto :(
    HUM,
    PRES;

    /**
     * Obtiene el nombre de la enumeración
     * @return el nombre de la enumeración
     */
    @Override
    public String toString() {
        return this.name();
    }
}