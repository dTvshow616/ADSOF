public class Mensaje {
    private String author;
    private int alcanceDisponible; // Capacidad para seguir difundiéndose
    private Usuario usuarioActual;

    /**
     * Constructor para un mensaje
     * @param author el autor de un mensaje
     * @param alcanceDisponible el alcance disponible de un mensaje
     * @param usuarioActual el usuario actual de un mensaje
     */
    Mensaje(String author, int alcanceDisponible, Usuario usuarioActual) {
        this.author = author;
        this.alcanceDisponible = alcanceDisponible;
        this.usuarioActual = usuarioActual;
    }

    // los getters

    /**
     * Devuelve el autor del mensaje
     */
    mensajeGetAuthor(){
        return this.author;
    }

    /**
     * Devuelve el alance disponible del mensaje
     */
    mensajeGetAlcanceDisponible(){
        return this.alcanceDisponible;
    }

    /**
     * Devuelve el usuario actual del mensaje
     */
    mensajeGetUsuarioActual(){
        return this.usuarioActual;
    }

    /**
     * Función que intenda difundir el mensaje a través de un cierto enlace
     * @param e el enlace mediante el que se intentará difundir el mensaje
     * @return true si el mensaje se pudo difundir, false si no
     */
    boolean difunde(Enlace e) {
        // Este méto do intentará difundir el mensaje siguiendo el enlace dado. La difusión será posible solo si: existe
        // realmente el enlace desde el usuario actual del mensaje; el mensaje disponible de más alcance (o igual) que
        // el coste real del enlace (ver Apartado 1); y el usuario destino puede aceptar el mensaje según restricciones
        // especiales que aparecerán en extensiones posteriores.

        if (e == null || this.alcanceDisponible >= e.costeReal() ) {
            return false;
        }

        // Si la difusión es posible: el usuario actual del mensaje pasa a ser el destino del enlace, el alcance del
        // mensaje disminuye en el coste real del enlace, el alcance vuelve a incrementarse en la cantidad
        // correspondiente a la capacidad de amplificación del usuario destino, y entonces el méto do difunde devolverá
        // true.

        // Debe sobrecargar el méto do difunde para que reciba como argumentos un número variable de usuarios que el
        // mensaje tiene previsto visistar en ese order. El méto do aplicará iterativamente difunde(Enlace) si el enlace
        // hacia el siguiente usuario existe y puede difundirse, el mensaje se traslada (difunde) allí, pero si no
        // existe el enlace, o el alcance no es suficiente, o el destino no lo acepta, se intenta directamente con el
        // siguiente usuario en la lista, sin detener la difusión por completo.

        // El méto do devolverá true solo si el mensaje ha podido correctamente en to dos los saltos en los que realmente
        // se haya realizado una transmisisón. Si al menos una vez se ha tenido que "saltar" un usuario porque no había
        // camino posible o no había alcance suficiente, el retorno será false, incluso si el mensaje logra llegar a
        // otros usuarios posteriores.

        // Por ejemplo, una llamada difunde(uA, uB, uC, uD, uE, uF) podría provocar que el mensaje solo visite uA, uC y
        // uE, si, por ejemplo: no existía enlace de uA a uB, si existía de uA a uC, en uC el mensaje no tenía alcance
        // suficiente para llegar a uD, sí tenía para llegar a uE, pero no para llegar a uF. En ese caso, el retorno
        // sería false
    }

    @Override
    public String toString(){
        return "Mensaje(m:" + this.alcanceDisponible + ") en" + this.usuarioActual;
    }
}