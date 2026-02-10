package trayectos;

/**
 * Incluir una clase para los tramos a pie, que guarde el número de kilómetros del tramo. El tiempo que
 * se tarda en recorrer un tramo a pie es igual al número de kilómetros del tramo, por el tiempo de recorrer
 * un kilómetro: 15 minutos si se va a ritmo suave, 10 si el ritmo es moderado, y 8 si es rápido. Si no se 
 * indica un ritmo, se asumirá por defecto un ritmo moderado. Al mostrar un tramo a pie, se debe mostrar
 * también el ritmo al que se camina.
 */
public class TramoAPie extends TramoTrayecto {
    private Ritmo ritmo;
    private double numKilometros;

    public TramoAPie(String origen, String destino, double numKilometros, Ritmo ritmo) {
        super(origen, destino);
        this.numKilometros = numKilometros;
        this.ritmo = ritmo;
    }

    public TramoAPie(String origen, String destino, double numKilometros) {
        this(origen, destino, numKilometros, Ritmo.MODERADO);
    }

    public double calcularTiempo() {
        switch (this.ritmo) {
            case Ritmo.SUAVE:
                return numKilometros*15;

            case Ritmo.MODERADO:
                return numKilometros*10;

            case Ritmo.RAPIDO:
                return numKilometros*8;

            default:
                throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "A pie " + super.toString() + "(ritmo " + this.ritmo + ")";
    }
    
}