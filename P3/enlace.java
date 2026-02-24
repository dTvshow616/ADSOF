public class Enlace {
    private Usuario usuarioOrigen;
    private Usuario ususarioDestino;
    private int coste;

    // Por requisitos futuros, esta clase debe acumular en una variable estática la suma de costes de to dos los enlaces
    // creados y ofrecer un méto dod para obtener, en cualquier momento, el total acumulado en ese instante

    /**
     * Constructor para un enlace
     * @param usuarioOrigen el usuario de origen del enlace
     * @param ususarioDestino el usuario destino del enlace
     * @param coste el coste del mensaje
     */
    Enlace(Usuario usuarioOrigen, Usuario ususarioDestino, int coste) {
        this.usuarioOrigen = usuarioOrigen;
        this.ususarioDestino = ususarioDestino;
        this.coste = coste;
    }

    /**
     * Mét odo que permite cambiar el destino de un cierto enlace
     * @param nuevoUsuario el nuevo usuario destino del enlace
     * @param nuevoCoste el nuevo coste del enlace
     */
    cambiarDestino(Usuario nuevoUsuario, int nuevoCoste) {
        this.ususarioDestino = nuevoUsuario;
        this.coste = nuevoCoste;
    }

    /**
     * Devuelve 0 (solo los enlaces especiales definidos en el futuro tendrán coste especial distinto de cero
     * @return
     */
    int costeEspecial() {
        return 0;
    }

    /**
     * Calcula el coste real de un enlace como la suma del coste del enlace más su coste especial
     * @return el coste real del enlace
     */
    int costeReal() {
        return this.coste; + this.costeReal();
    }

    @Override
    public java.lang.String toString() {
        return "(" + this.ususarioDestino + "--" + this.coste + "-->" + this.ususarioDestino + ")";
    }
}