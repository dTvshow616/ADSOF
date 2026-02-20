package pizzeria;

/**
 * Esta clase maneja un enum para los tipos de masa
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: pizzeria.Masa.java
 */
public enum Masa {
    FINA(0),
    NORMAL(0),
    GRUESA(5);

    private int recargo;

    /**
     * Constructor para una Masa
     * @param recargo el recargo de la masa
     */
    private Masa(int recargo) {
        this.recargo = recargo;
    }

    @Override
    public String toString() {
        return this.name() + " (+ " + this.recargo + " €)";
    }
}
