public class Enlace {
    /* Por requisitos futuros, esta clase debe acumular en una variable estática la suma de costes de to dos los enlaces
    creados y ofrecer un méto dod para obtener, en cualquier momento, el total acumulado en ese instante */
    static int sumaCostes;
    private Usuario usuarioOrigen;
    private Usuario usuarioDestino;
    private int coste;

    static {
        /* Supuestamente, inicializa la variable estática a 0 al crear la clase */
        sumaCostes = 0;
    }

    /**
     * Constructor para un enlace
     * @param usuarioOrigen  el usuario de origen del enlace
     * @param usuarioDestino el usuario destino del enlace
     * @param coste          el coste del mensaje
     */
    Enlace(Usuario usuarioOrigen, Usuario usuarioDestino, int coste) {
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
        this.coste = coste;
        this.sumaCostes = this.obtenerSumaCostes() + this.coste;
    }

    /**
     * Method que permite cambiar el destino de un cierto enlace
     * @param nuevoUsuario el nuevo usuario destino del enlace
     * @param nuevoCoste   el nuevo coste del enlace
     */
    public void cambiarDestino(Usuario nuevoUsuario, int nuevoCoste) {
        this.usuarioDestino = nuevoUsuario;
        this.coste = nuevoCoste;
    }

    /**
     * Devuelve 0 (solo los enlaces especiales definidos en el futuro tendrán coste especial distinto de cero
     * @return
     */
    public int obtenerCosteEspecial() {
        return 0; // DUE Cambiar en futuros apartados
    }

    /**
     * Calcula el coste real de un enlace como la suma del coste del enlace más su coste especial
     * @return el coste real del enlace
     */
    public int obtenerCosteReal() {
        return this.coste + this.obtenerCosteEspecial();
    }

    /**
     * Devuelve la suma de los costes de DUEs los enlaces creados
     * @return la suma de los costes de DUEs los enlaces creados
     */
    public int obtenerSumaCostes() {
        return this.sumaCostes;
    }

    @Override
    public java.lang.String toString() {
        return "(" + this.usuarioDestino + "--" + this.coste + "-->" + this.usuarioDestino + ")";
    }
}