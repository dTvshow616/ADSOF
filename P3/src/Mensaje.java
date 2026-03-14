/**
 * Nombre de la clase: Mensaje
 * <p>
 * Description: Implementa los mensajes
 * @author Álvaro G.S. & Ana O.R.
 * @version 1.3
 * @see Usuario
 * @see Enlace
 */
public class Mensaje {
    /* El texto del mensaje */
    private String texto;
    /** La capacidad del mensaje para seguir difundiéndose */
    private int alcanceDisponible;
    /** El usuario donde se encuentra el mensaje actualmente */
    private Usuario usuarioActual;

    /**
     * Constructor de un mensaje
     * @param texto             el texto del mensaje
     * @param alcanceDisponible la capacidad del mensaje para seguir difundiéndose
     * @param autor             el nombre del autor del mensaje
     * @param usuarioActual     el usuario donde se encuentra el mensaje actualmente
     */
    Mensaje(String texto, int alcanceDisponible, String autor, Usuario usuarioActual) {
        this.texto = texto;
        this.alcanceDisponible = alcanceDisponible;
        this.usuarioActual = usuarioActual;
    }

    /* ------------------------------------------------- LOS GETTERS ------------------------------------------------ */

    /**
     * Devuelve el texto del mensaje
     * @return el texto del mensaje
     */
    public String obtenerTexto() {
        return this.texto;
    }

    /**
     * Devuelve el alcance disponible del mensaje
     * @return el alcance disponible del mensaje
     */
    public int obtenerAlcanceDisponible() {
        return this.alcanceDisponible;
    }

    /**
     * Devuelve el usuario actual del mensaje
     * @return el usuario actual del mensaje
     */
    public Usuario obtenerUsuarioActual() {
        return this.usuarioActual;
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
    public void cambiarUsuarioActual(Usuario nuevoUsuarioActual) {
        this.usuarioActual = nuevoUsuarioActual;
    }

    /* ---------------------------------------------- COSAS DEL DIFUNDE --------------------------------------------- */

    /**
     * Función auxiliar de la función difunde() que devuelve true si y solo si el alcance el mensaje es mayor o igual
     * que el coste real del enlace
     * @param e el enlace
     * @return true si y solo si el alcance el mensaje es mayor o igual que el * coste real del enlace, false si no
     */
    private boolean puedeDifundirPor(Enlace e) {
        return this.alcanceDisponible >= e.costeReal();
    }

    /**
     * Comprueba si la difusión del mensaje es posible
     * @param u el usuario destino del mensaje
     * @return true si la difusión es posible, false si no
     */
    private boolean aceptadoPor(Usuario u) {
        return true; // NOTE: por ahora devuelve siempre true
    }

    // REVIEW: ver si la lógica de difunde tiene sentido

    /**
     * Función auxiliar, intenta difundir el mensaje a través de un cierto enlace
     * @param e el enlace mediante el que se intentará difundir el mensaje
     * @return true si el mensaje se pudo difundir, false si no
     */
    private boolean difunde(Enlace e) {
        /* Comprobación de difusión */
        if (e == null || !puedeDifundirPor(e) || !aceptadoPor(e.obtenerUsuarioDestino())) {
            return false;
        }

        /* El usuario actual del mensaje pasa a ser el destino del enlace */
        this.usuarioActual = e.obtenerUsuarioDestino();
        /* El alcance del mensaje disminuye en el coste real del enlace */
        this.alcanceDisponible = this.alcanceDisponible - e.costeReal();
        /* El alcance vuelve a incrementarse en la cantidad correspondiente a la capacidad de amplificación del
        usuario destino, y entonces el method difunde devolverá true. */
        this.alcanceDisponible = this.alcanceDisponible + this.usuarioActual.obtenerCapacidadAmplificacion();

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
                e = this.usuarioActual.obtenerEnlace(usuarioDestino); // DUE: Implemetar esto en Usuario
                if (!difunde(e)) {
                    huboSaltos = true;
                }
            }
        }

        return !huboSaltos;
    }

    @Override
    public String toString() {
        return "Mensaje(m:" + this.alcanceDisponible + ") en" + this.usuarioActual.toString();
    }
}