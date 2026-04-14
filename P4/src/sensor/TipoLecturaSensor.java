package sensor;

/**
 * Define los tipos de lectura que puede poseer un sensor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public enum TipoLecturaSensor {
    /**
     * Rango establecido
     */
    MINMAX,
    /**
     * Rango configurable
     */
    RANGOCONFIGURABLE,
    /**
     * Rango cercano a la media
     */
    RANGOMEDIA;
}