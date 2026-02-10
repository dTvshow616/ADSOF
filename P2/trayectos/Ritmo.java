package trayectos;

public enum Ritmo {
    SUAVE,
    MODERADO,
    RAPIDO;

    @Override /* Quizás hay que borrar el override */
    public String toString() {
        return this.name();
    }
}

