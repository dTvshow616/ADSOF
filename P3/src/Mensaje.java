package src;

public class Mensaje {
    private String author;
    private int alcanceDisponible; /* Capacidad para seguir difundiéndose */
    private Usuario usuarioActual;

    /**
     * Constructor para un mensaje
     * @param author            el autor de un mensaje
     * @param alcanceDisponible el alcance disponible de un mensaje
     * @param usuarioActual     el usuario actual de un mensaje
     */
    Mensaje(String author, int alcanceDisponible, Usuario usuarioActual) {
        this.author = author;
        this.alcanceDisponible = alcanceDisponible;
        this.usuarioActual = usuarioActual;
    }

    /**
     * Devuelve el autor del mensaje
     * @return el autor del mensaje
     */
    public String obtenerAuthor() {
        return this.author;
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
     * Función que intende difundir el mensaje a través de un cierto enlace
     * @param e el enlace mediante el que se intentará difundir el mensaje
     * @return true si el mensaje se pudo difundir, false si no
     */
    public boolean difunde(Enlace e) {
        /* Este method intentará difundir el mensaje siguiendo el enlace dado. La difusión será posible solo si: existe
        realmente el enlace desde el usuario actual del mensaje; el mensaje disponible de más alcance (o igual) que
        el coste real del enlace (ver Apartado 1); y el usuario destino puede aceptar el mensaje según restricciones
        especiales que aparecerán en extensiones posteriores. */
        // ? Tiene que haber una mejor forma de poner estos ifs
        if (e == null) {
            return false;
        }

        if (e.obtenerUsuarioOrigen() == e.obtenerUsuarioDestino()) {
            return true;
        }

        if (this.alcanceDisponible >= e.obtenerCosteReal()) {
            return false;
        }

        /* El usuario actual del mensaje pasa a ser el destino del enlace */
        this.usuarioActual = e.obtenerUsuarioDestino();
        /* El alcance del mensaje disminuye en el coste real del enlace */
        this.alcanceDisponible = this.alcanceDisponible - e.obtenerCosteReal();
        /* El alcance vuelve a incrementarse en la cantidad correspondiente a la capacidad de amplificación del usuario
        destino, y entonces el method difunde devolverá true. */
        // REVIEW las funciones de usuario
        this.alcanceDisponible = this.alcanceDisponible + this.usuarioActual.obtenerCapacidadAmplificacion();

        return true;
    }

    /* Debe sobrecargar el method difunde para que reciba como argumentos un número variable de usuarios que el
    mensaje tiene previsto visitar en ese order. El method aplicará iterativamente difunde(Enlace) si el enlace
    hacia el siguiente usuario existe y puede difundirse, el mensaje se traslada (difunde) allí, pero si no
    existe el enlace, o el alcance no es suficiente, o el destino no lo acepta, se intenta directamente con el
    siguiente usuario en la lista, sin detener la difusión por completo. */

    /**
     * Función que intenta difundir el mensaje a través de una red ordenada de usuarios
     * @param usuarios los usuarios de la red, ordenados por orden de difusión
     * @return true si el mensaje se pudo difundir por toda la red, false si no
     */
    public boolean difunde(Usuario[] usuarios) {
        Usuario usuarioOrigen;
        Enlace e;

        boolean huboSaltos = false;

        if (usuarios == null) {
            return true;
        }

        usuarioOrigen = usuarios[0];

        for (Usuario usuarioDestino : usuarios) {
            /* Llamada al otro difunde */
            if (difunde(e) == false) { // ! Sacar el enlace entre usuarioOrigen y usuarioDestino
                huboSaltos = true;
            } else {
                usuarioOrigen = usuarioDestino;
            }
        }

        /* El method devolverá true solo si el mensaje ha podido correctamente en tout los saltos en los que realmente
        se haya realizado una transmisión. Si al menos una vez se ha tenido que "saltar" un usuario porque no había
        camino posible o no había alcance suficiente, el retorno será false, incluso si el mensaje logra llegar a
        otros usuarios posteriores.

        Por ejemplo, una llamada difunde(uA, uB, uC, uD, uE, uF) podría provocar que el mensaje solo visite uA, uC y
        uE, si, por ejemplo: no existía enlace de uA a uB, si existía de uA a uC, en uC el mensaje no tenía alcance
        suficiente para llegar a uD, sí tenía para llegar a uE, pero no para llegar a uF. En ese caso, el retorno
        sería false */ // DUE borrar este comentario
        return !huboSaltos;
    }

    @Override
    public String toString() {
        return "Mensaje(m:" + this.alcanceDisponible + ") en" + this.usuarioActual;
    }
}