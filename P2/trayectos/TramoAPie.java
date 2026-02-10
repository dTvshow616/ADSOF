package trayectos;

/**
 * Esta clase maneja los tramos a pie
 * Autor: Alvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: trayectos.TramoAPie.java
 */
public class TramoAPie extends TramoTrayecto {
    private Ritmo ritmo;
    private double numKilometros;

    /**
     * Constructor para un tramo a pie con ritmo especificado
     * @param origen el origen del tramo
     * @param destino el destino del tramo
     * @param numKilometros la extension del tramo
     * @param ritmo el ritmo al que se hace el tramo
     */
    public TramoAPie(String origen, String destino, double numKilometros, Ritmo ritmo) {
        super(origen, destino);
        this.numKilometros = numKilometros;
        this.ritmo = ritmo;
    }

    /**
     * Constructor para un tramo a pie sin ritmo especificado
     * @param origen el origen del tramo
     * @param destino el destino del tramo
     * @param numKilometros la extension del tramo
     */
    public TramoAPie(String origen, String destino, double numKilometros) {
        this(origen, destino, numKilometros, Ritmo.MODERADO);
    }

    /**
     * Metodo para el calculo de la duracion de un tramo a pie dado su ritmo y extension
     * @return la duracion del tramo
     */
    public double tiempo() {
        return switch (this.ritmo) {
            case SUAVE -> numKilometros * 15;
            case MODERADO -> numKilometros * 10;
            case RAPIDO -> numKilometros * 8;
        };
    }

    @Override
    public String toString() {
        return "A pie " + super.toString() + this.tiempo() + " minutos " +"(ritmo " + this.ritmo + ")";
    }
    
}