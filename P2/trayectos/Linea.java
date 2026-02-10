package trayectos;

/**
 * Esta clase maneja un enum para las lineas del Cercanias
 * Autor: Alvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: trayectos.Linea.java
 */
public enum Linea {
    C1("azul claro"),
    C4("azul oscuro"),
    C5("amarilla");

    private String color;

    /**
     * Constructor para una Linea
     * @param color el color de la linea
     */
    private Linea(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.name() + " (" + this.color + ")";
    }
}
