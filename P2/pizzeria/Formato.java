package pizzeria;

/**
 * Esta clase maneja un enum para los formatos
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: pizzeria.Formato.java
 */
public enum Formato {
    ESPECIAL,
    CONFIGURABLE,
    POR_MITADES;

    @Override
    public String toString() {
        return this.name();
    }
}

