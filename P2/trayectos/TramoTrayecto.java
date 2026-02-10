package trayectos;

/**
 * Esta clase maneja los trayectos
 * Autor: Alvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: trayectos.TramoTrayecto.java
 */
public abstract class TramoTrayecto {
    private String origen;
    private String destino;

    /**
     * Constructor para un trayecto
     * @param origen el origen del trayecto
     * @param destino el destino del trayecto
     */
    public TramoTrayecto(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "desde " + this.origen + " a " + this.destino + ": ";
    }
}
