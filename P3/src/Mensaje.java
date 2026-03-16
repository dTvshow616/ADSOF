/**
 * Nombre de la clase: Mensaje
 * <p>
 * Description: Implementa los mensajes
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 * @see Usuario
 * @see Enlace
 */
public class Mensaje {
    /** El texto del mensaje */
    protected final String texto;
    /** La capacidad inicial del mensaje */
    protected final int alcanceInicial;
    /** La capacidad del mensaje para seguir difundiéndose */
    protected int alcanceDisponible;
    /** El usuario donde se encuentra el mensaje actualmente */
    protected Usuario usuarioActual;
    /** El autor del mensaje */
    protected final Usuario autor;

    /**
     * Constructor de un mensaje
     * @param texto          el texto del mensaje
     * @param alcanceInicial la capacidad del mensaje para seguir difundiéndose
     * @param usuarioActual  el usuario donde se encuentra el mensaje actualmente
     */
    Mensaje(String texto, int alcanceInicial, Usuario usuarioActual) throws NullPointerException {
        if (texto == null) {
            throw new NullPointerException("texto == null");
        }
        if (usuarioActual == null) {
            throw new NullPointerException("usuarioActual == null");
        }

        this.texto = texto;
        this.alcanceInicial = alcanceInicial;
        this.alcanceDisponible = alcanceInicial;
        this.usuarioActual = usuarioActual;
        this.usuarioActual.addMensaje(this);
        this.autor = usuarioActual;
    }

    /**
     * Devuelve el texto del mensaje
     * @return el texto del mensaje
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     * Devuelve el alcance inicial del mensaje
     * @return el alcance inicial del mensaje
     */
    public int getAlcanceInicial() {
        return this.alcanceInicial;
    }

    /**
     * Devuelve el alcance disponible del mensaje
     * @return el alcance disponible del mensaje
     */
    public int getAlcanceDisponible() {
        return this.alcanceDisponible;
    }

    /**
     * Devuelve el usuario actual del mensaje
     * @return el usuario actual del mensaje
     */
    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }

    /**
     * Devuelve el autor del mensaje
     * @return el usuario autor del mensaje
     */
    public Usuario getAutor() {
        return this.autor;
    }

    /**
     * Permite cambiar el alcance disponible del mensaje
     * @param nuevoAlcanceDisponible el nuevo alcance disponible del mensaje
     */
    public void cambiarAlcanceDisponible(int nuevoAlcanceDisponible) {
        this.alcanceDisponible = nuevoAlcanceDisponible;
    }

    /**
     * Permite cambiar el usuario actual del mensaje
     * @param nuevoUsuarioActual el nuevo usuario actual del mensaje
     */
    public void cambiarUsuarioActual(Usuario nuevoUsuarioActual) throws NullPointerException {
        if (nuevoUsuarioActual == null) {
            throw new NullPointerException("usuarioActual == null");
        }

        this.usuarioActual = nuevoUsuarioActual;
    }

    /**
     * Función auxiliar de la función difunde() que devuelve true si y solo si el alcance el mensaje es mayor o igual
     * que el coste real del enlace
     * @param e el enlace
     * @return true si y solo si el alcance el mensaje es mayor o igual que el * coste real del enlace, false si no
     */
    protected boolean puedeDifundirPor(Enlace e) {
        return this.alcanceDisponible >= e.costeReal();
    }

    /**
     * Comprueba si la difusión del mensaje es posible
     * @param u el usuario destino del mensaje
     * @return true si la difusión es posible, false si no
     */
    protected boolean aceptadoPor(Usuario u) {
        return true; // NOTE: por ahora devuelve siempre true
    }

    // REVIEW: comprobar lógica de difusión

    /**
     * Función auxiliar, intenta difundir el mensaje a través de un cierto enlace
     * @param e el enlace mediante el que se intentará difundir el mensaje
     * @return true si el mensaje se pudo difundir, false si no
     */
    public boolean difunde(Enlace e) {
        /* Comprobación de difusión */
        if (e == null || !puedeDifundirPor(e) || !aceptadoPor(e.getUsuarioDestino()) || !e.getUsuarioOrigen().comprobarMensaje(this)) {
            return false;
        }


        /* El usuario actual del mensaje pasa a ser el destino del enlace */
        this.usuarioActual = e.getUsuarioDestino();
        /* Guarda el mensaje en el nuevo usuario del mensaje */
        this.getUsuarioActual().addMensaje(this);
        /* El alcance del mensaje disminuye en el coste real del enlace */
        this.alcanceDisponible = this.alcanceDisponible - e.costeReal();
        /* El alcance vuelve a incrementarse en la cantidad correspondiente a la capacidad de amplificación del
        usuario destino, y entonces el method difunde devolverA true. */
        this.alcanceDisponible = this.alcanceDisponible + this.usuarioActual.getCapacidadAmplificacion();

        return true;
    }

    /**
     * Sobrecarga, intenta difundir el mensaje a través de una red ordenada de usuarios
     * @param usuarios los usuarios de la red, ordenados por orden de difusión
     * @return true si el mensaje se pudo difundir por toda la red, false si no
     */
    public boolean difunde(Usuario... usuarios) {
        boolean huboSaltos = false;
        Enlace e;

        if (usuarios == null) {
            return true;
        }

        for (Usuario usuarioDestino : usuarios) {
            if (usuarioDestino != null) {
                e = this.usuarioActual.getEnlace(usuarioDestino);
                if (!difunde(e)) {
                    huboSaltos = true;
                } else {
                    /* Cada vez que el mensaje consiga propagarse a través de un enlace, el programa deberá mostrar
                    por consola el estado actual del mensaje */
                    System.out.println(this); // NOTE: ¿QuizAs no debería imprimirse siempre?
                }
            }
        }

        return !huboSaltos;
    }

    @Override
    public String toString() {
        return "Mensaje(" + this.texto + ":" + this.alcanceDisponible + ") en @" + this.usuarioActual.getNombre();
    }
}