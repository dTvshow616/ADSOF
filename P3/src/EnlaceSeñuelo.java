import java.util.Random;

/**
 * Nombre de la clase: EnlaceSeñuelo
 * <p>
 * Description: Implementa los enlaces señuelo
 * @author Álvaro G.S. & Ana O.R.
 * @version 1.5
 * @see Enlace
 * @see Usuario
 */
public class EnlaceSeñuelo extends Enlace{
    /** Factor a partir del que se calcula el coste especial */
    private int factorCosteExtra;
    /** Probabilidad de devolver mal el usuario destino */
    private double probRetornaOblig;

    /**
     * Constructor para un enlace señuelo
     * @param usuarioOrigen  el usuario de origen del enlace
     * @param usuarioDestino el usuario destino del enlace
     * @param coste          el coste del mensaje
     * @param factorCosteExtra Factor a partir del que se calcula el coste especial
     * @param probRetornaOblig Probabilidad de devolver mal el usuario destino
     */
    public EnlaceSeñuelo(Usuario usuarioOrigen, Usuario usuarioDestino, int coste, int factorCosteExtra, double probRetornaOblig){
        super(usuarioOrigen, usuarioDestino, coste);
        this.factorCosteExtra = factorCosteExtra;
        this.probRetornaOblig = probRetornaOblig;
    }

    /**
     * Devuelve el coste especial multiplicando el coste por el factor de coste extra
     * @return el coste especial del enlace
     */
    public int costeEspecial(){
        return super.coste*this.factorCosteExtra;
    }

    /**
     * Devuelve el usuario destino del enlace aunque hay una probabilidad de que devuelva el usuario origen
     * @return el usuario origen o usuario destino del enlace
     */
    public Usuario getUsuarioDestino(){
        Random rand = new Random();

        if(rand.nextDouble() <= this.probRetornaOblig){
            return super.usuarioOrigen;
        }

        return super.usuarioDestino;
    }

}
