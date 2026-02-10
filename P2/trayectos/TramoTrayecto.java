package trayectos;

public abstract class TramoTrayecto {
    private String origen;
    private String destino;

    public TramoTrayecto(String origen, String destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public double tiempo() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "desde " + this.origen + " a " + this.destino + ": " + this.tiempo() + " minutos";
    }
}
