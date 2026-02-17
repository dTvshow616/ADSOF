package trayectos;

/**
 * Esta clase maneja los tramos en tren
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: trayectos.TramoTren.java
 */
public class TramoTren extends TramoTrayecto {
    private Linea linea;
    private int numParadas;

    /**
     * Constructor para un tramo en tren
     * @param origen el origen del tramo
     * @param destino el destino del tramo
     * @param linea la linea de tren sobre la que se realiza el tramo
     * @param numParadas el numero de paradas que efectua el tren a lo largo del recorrido
     */
    public TramoTren(String origen, String destino, Linea linea, int numParadas) {
        super(origen, destino);
        this.linea = linea;
        this.numParadas = numParadas;
    }

    /**
     * Calcula el tiempo que se tarda en recorrer un tramo en tren dada su linea y numero de paradas
     * @return la duracion del tramo
     */
    public double tiempo() {
        return switch (this.linea) {
            case C1 -> numParadas * 5;
            case C4 -> numParadas * 10;
            case C5 -> numParadas * 30;
        };
    }

    @Override
    public String toString() {
        return "En tren de la linea " + this.linea + " " + super.toString() + this.tiempo() + " minutos";
    }
}
