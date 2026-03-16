/**
 * Nombre de la clase: MensajeControlado
 * <p>
 * Description: Implementa los mensajes controlados
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 * @see Mensaje
 * @see Usuario
 * @see Enlace
 */
public class MensajeControlado extends Mensaje {
    /* La rigidez que tiene el mensaje para evitar su sobreexposición*/
    private int rigidez;

    /**
     * Constructor de un mensaje controlado
     * @param texto             el texto del mensaje
     * @param alcanceDisponible la capacidad del mensaje para seguir difundiéndose
     * @param usuarioActual     el usuario donde se encuentra el mensaje actualmente
     * @param rigidez
     */
    MensajeControlado(String texto, int alcanceDisponible, Usuario usuarioActual, int rigidez) {
        super(texto, alcanceDisponible, usuarioActual);
        this.rigidez = rigidez;
    }

    /**
     * Función auxiliar de la función difunde() que devuelve true si y solo si el alcance el mensaje es mayor o igual
     * que el coste real del enlace y el enlace no es un señuelo
     * @param e el enlace
     * @return true si y solo si el alcance el mensaje es mayor o igual que el * coste real del enlace, false si no o si
     * el enlace es un señuelo
     */
    protected boolean puedeDifundirPor(Enlace e) {
        if (e instanceof EnlaceSenuelo) {
            return false;
        }

        return super.puedeDifundirPor(e);
    }

    /**
     * Comprueba si la difusión del mensaje es posible, asegurándose de que el usuario destino no es demasiado viral
     * @param u el usuario destino del mensaje
     * @return true si la difusión es posible, false si no
     */
    protected boolean aceptadoPor(Usuario u) {

        if ((u.getExposicion() == Exposicion.OCULTA)) {
            return super.aceptadoPor(u);
        } else if ((u.getExposicion() == Exposicion.BAJA) && (this.rigidez >= 5)) {
            return super.aceptadoPor(u);
        } else if ((u.getExposicion() == Exposicion.MEDIA) && (this.rigidez >= 10)) {
            return super.aceptadoPor(u);
        } else if ((u.getExposicion() == Exposicion.ALTA) && (this.rigidez >= 20)) {
            return super.aceptadoPor(u);
        } else if ((u.getExposicion() == Exposicion.VIRAL) && (this.rigidez >= 50)) {
            return super.aceptadoPor(u);
        }

        return false;
    }
}