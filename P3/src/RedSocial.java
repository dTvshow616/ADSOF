import java.io.File;

public class RedSocial {
    private File usuarios;
    private File mensaje;
    private File enlaces;

    /**
     * Constructor para una red social
     * @param usuarios nombre del archivo que permite construir la red social completa
     * @param enlaces  nombre del archivo con la secuencia de usuarios que el mensaje tiene previsto intentar visitar
     *                 durante su difusión
     * @param mensaje  nombre del archivo con el mensaje inicial
     */
    RedSocial(String filenameUsuarios, String filenameEnlaces, String filenameMensaje) {
        // REVIEW
        this.usuarios = new File(filenameUsuarios);
        this.enlaces = new File(filenameEnlaces);
        this.mensaje = new File(filenameMensaje);
    }

    /* El primer archivo describe los usuarios y cotiene una línea por cada uno de ellos, con su nombre y su capacidad
    de amplificación separados por espacios o tabuladores */
    public short leerUsuarios() {
        // DUE lectura del archivo y construcción de la red social
        return 0;
    }

    /* El segundo archivo detalla los enlaces, también uno por línea, indicando el nombre del usuario origen, el
    nombre del usuario destio y el coste de propagación asociado a ese enlace */
    public short leerEnlaces() {
        // DUE lectura del archivo y creación de enlaces supongo
        return 0;
    }

    /* El tercer archivo especifica el mensaje que se va a difundir: su autor, el alcance inicial y el usuario en el que
    se encuentra al comenzar la simulación. Las líneas restantes de este contienen, en el orden dado, los nombres de los
    usuarios que el mensaje intentará visitar, siguiendo el mecanismo explicado anteriormente en la clase Mensaje */
    public short leerMensaje() {
        // DUE lectura del archivo y llamada a difunde (?)
        return 0;
    }


}