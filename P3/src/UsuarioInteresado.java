/**
 * Nombre de la clase: UsuarioInteresado
 * <p>
 * Description: Implementa los usuarios interesados
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.5
 * @see Usuario
 * @see Mensaje
 * @see Enlace
 * @see Exposicion
 */
public class UsuarioInteresado extends Usuario {

    /**
     * Constructor para un usuario interesado
     * @param nombre nombre del usuario
     */
    public UsuarioInteresado(String nombre) {
        super(nombre);
    }

    /**
     * Constructor para un usuario interesado
     * @param nombre                 nombre del usuario
     * @param capacidadAmplificacion Capacidad de amplificar el alcance de un mensaje de un usuario
     */
    public UsuarioInteresado(String nombre, int capacidadAmplificacion) {
        super(nombre, capacidadAmplificacion);
    }

    /**
     * Devuelve el primer enlace que conecte con un usuario de exposición alta o mayor, y si no hay ninguno el primer
     * enlace que tenga el usuario destino que se pide
     * @param destino usuario del que se pide el enlace
     * @return el enlace con el usuario destino, el usuario viral o null si no se encuentra
     */
    @Override
    public Enlace getEnlace(Usuario destino) {
        for (Enlace i : this.enlaces) {
            if (i.getUsuarioDestino().getExposicion().ordinal() >= Exposicion.ALTA.ordinal()) {
                return i;
            }
        }
        return super.getEnlace(destino);
    }


}