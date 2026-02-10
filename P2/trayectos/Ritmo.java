package trayectos;

/**
 * Esta clase maneja un enum para los del desplazamiento a pie
 * Autor: Alvaro Gallego y Ana Olsson
 * Version: 1.0
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

