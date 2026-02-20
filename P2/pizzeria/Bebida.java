package pizzeria;

/**
 * Esta clase maneja un enum para las bebidas
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: pizzeria.Bebida.java
 */
public enum Bebida {
    REFRESCO_DE_COLA(0),
    BOTELLA_DE_AGUA(0),
    CERVEZA_SIN_ALCOHOL(0);

    private int precio;

    /**
     * Constructor para una Bebida
     * @param precio el precio de la Bebida
     */
    private Bebida(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return this.name() + " (" + this.precio + " €)";
    }
}
