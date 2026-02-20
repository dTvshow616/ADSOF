package pizzeria;

/**
 * Esta clase maneja un enum para los tamannos de pizza
 * @author Alvaro Gallego y Ana Olsson
 * @version 1.0
 * Nombre del fichero: pizzeria.Tamanno.java
 */
public enum Tamanno {
    PEQUENNA(3),
    MEDIANA(0),
    GRANDE(6);

    private int recargo;

    /**
     * Constructor para una Tamanno
     * @param recargo el recargo del tamaño
     */
    private Tamanno(int recargo) {
        this.recargo = recargo;
    }

    @Override
    public String toString() {
        return this.name() + " (+ " + this.recargo + " €)";
    }
}
