/**
 * Nombre de la clase: Enlace
 * <p>
 * Description: Implementa los enlaces
 * @author Álvaro G.S. & Ana O.R.
 * @version 1.1
 * @see Usuario
 */
public class Enlace {
    /** La suma de los costes de todos los enlaces creados */
    private static int sumaCostes;
    /** El usuario origen del enlace */
    private final Usuario usuarioOrigen;
    /** El usuario destino del enlace */
    private Usuario usuarioDestino;
    /** EL coste del enlace */
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
        /* Si se intentase dar a un enlace un coste menor o igual que cero, se tratará como si el coste fuese 1 */
        if (coste <= 0) {
            this.coste = 1;
        } else {
            this.coste = coste;
        }
        sumaCostes = this.obtenerSumaCostes() + this.coste;
    }

    /**
     * Constructor para un enlace sin coste especificado
     * @param usuarioOrigen  el usuario de origen del enlace
     * @param usuarioDestino el usuario destino del enlace
     */
    Enlace(Usuario usuarioOrigen, Usuario usuarioDestino) {
        this(usuarioOrigen, usuarioDestino, 1);
    }

    /**
     * Devuelve la suma de los costes de DUEs los enlaces creados
     * @return la suma de los costes de DUEs los enlaces creados
     */
    public int obtenerSumaCostes() {
        return sumaCostes;
    }

    /**
     * Devuelve el usuario origen del enlace
     * @return el usuario origen del enlace
     */
    public Usuario obtenerUsuarioOrigen() {
        return this.usuarioOrigen;
    }

    /**
     * Devuelve el usuario destino del enlace
     * @return el usuario origen del enlace
     */
    public Usuario obtenerUsuarioDestino() {
        return this.usuarioDestino;
    }

    /**
     * Devuelve el coste del enlace
     * @return el coste del enlace
     */
    public int obtenerCoste() {
        return this.coste;
    }

    /**
     * Devuelve 0 (solo los enlaces especiales definidos en el futuro tendrán coste especial distinto de cero
     * @return el coste especial del enlace
     */
    public int costeEspecial() {
        return 0; // DUE Cambiar en futuros apartados
    }

    /**
     * Calcula el coste real de un enlace como la suma del coste del enlace más su coste especial
     * @return el coste real del enlace
     */
    public int costeReal() {
        return this.coste + this.costeEspecial();
    }

    /**
     * Permite cambiar el destino de un cierto enlace
     * @param nuevoUsuario el nuevo destino del enlace
     * @param nuevoCoste   el nuevo coste del enlace
     */
    public void cambiarDestino(Usuario nuevoUsuario, int nuevoCoste) {
        this.usuarioDestino = nuevoUsuario;
        if (nuevoCoste <= 0) {
            this.coste = 1;
        } else {
            this.coste = nuevoCoste;
        }
    }

    @Override
    public java.lang.String toString() {
        return "(" + this.usuarioDestino.toString() + "--" + this.coste + "-->" + this.usuarioDestino.toString() + ")";
    }
}