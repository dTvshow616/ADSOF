package sensor;

/**
 * Define los posibles tipos de sensor y sus rangos de medición
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public enum TipoSensor {
    /** Tipo de sensor Temperatura */
    TEMPERATURA("TEMP"),
    /** Tipo de sensor Humedad */
    HUMEDAD("HUM"),
    /** Tipo de sensor Presión */
    PRESION("PRES");

    /** Nombre que corresponde a un tipo de sensor */
    private final String nombre;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor para un sensor
     * @param nombre el nombre que corresponde a un tipo de sensor
     */
    TipoSensor(String nombre) {
        this.nombre = nombre;
    }

    /*----------------------------------------------- GETTERS & SETTERS ----------------------------------------------*/

    /**
     * Gets nombre.
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /*--------------------------------------------------- TOSTRING ---------------------------------------------------*/

    /**
     * Obtiene el nombre de la enumeración
     * @return el nombre de la enumeración
     */
    @Override
    public String toString() {
        return this.name();
    }
}