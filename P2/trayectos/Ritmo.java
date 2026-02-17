package trayectos;

/**
 * Esta clase maneja un enum para los del desplazamiento a pie
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: trayectos.Ritmo.java
 */
public enum Ritmo {
    SUAVE,
    MODERADO,
    RAPIDO;

    @Override
    public String toString() {
        return this.name();
    }
}

