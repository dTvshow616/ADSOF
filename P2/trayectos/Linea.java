package trayectos;

public enum Linea {
    C1("azul claro"),
    C4("azul oscuro"),
    C5("amarilla");

    private String color;

    private Linea(String color) {
        this.color = color;
    }

    @Override /* Quizás hay que borrar el override */
    public String toString() {
        return this.name() + " (" + this.color + ")";
    }
}
