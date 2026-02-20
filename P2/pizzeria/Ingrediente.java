package pizzeria;

/**
 * Esta clase maneja un enum para ingredientes
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: pizzeria.Ingrediente.java
 */
public enum Ingrediente {
    BACON,
    PINNA,
    ATUN,
    EXTRA_DE_QUESO;

    @Override
    public String toString() {
        return this.name();
    }
}

