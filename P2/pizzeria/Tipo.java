package pizzeria;

/**
 * Esta clase maneja un enum para los tipos de pizza especial
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: pizzeria.Tipo_especial.java
 */
public enum Tipo {
    MARGARITA,
    VEGETARIANA,
    PEPPERONI,
    CUATRO_QUESOS;

    @Override
    public String toString() {
        return this.name();
    }
}