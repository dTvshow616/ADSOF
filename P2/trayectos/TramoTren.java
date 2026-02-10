package trayectos;

public class TramoTren extends TramoTrayecto {
    private Linea linea;
    private int numParadas;

    public TramoTren(String origen, String destino, Linea linea, int numParadas) {
        super(origen, destino);
        this.linea = linea;
        this.numParadas = numParadas;
    }

    /**
     * Calcular el tiempo que se tarda en recorrer un tramo en tren. Este tiempo es igual
     * al número de paradas del tramo, por el tiempo entre dos paradas, que varía según la
     * línea: 5 minutos para la línea C1, 10 para la línea C4, y 30 para la línea C5.
     */
    public double calcularTiempo() {
        switch (this.linea) {
            case Linea.C1:
                return numParadas*5;

            case Linea.C4:
                return numParadas*10;

            case Linea.C5:
                return numParadas*30;

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "En tren de la línea " + this.linea + " " + super.toString();
    }
}
