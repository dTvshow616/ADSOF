import java.io.*; // NOTE: Para la lectura de ficheros
import java.util.HashMap;

/**
 * Nombre de la clase: RedSocial
 * <p>
 * Description: Implementa la red social
 * @author Álvaro G.S. & Ana O.R.
 * @version 1.3
 * @see Usuario
 * @see Enlace
 */
public class RedSocial {
    HashMap<String, Usuario> usuarios = new HashMap<>();
    Enlace[] enlaces = new Enlace[]{};
    private int num_enlaces;

    /**
     * Constructor para una red social
     * @param filenameUsuarios nombre del archivo que permite construir la red social completa
     * @param filenameEnlaces  nombre del archivo con la secuencia de usuarios que el mensaje tiene previsto intentar
     *                         visitar durante su difusión
     * @param filenameMensaje  nombre del archivo con el mensaje inicial
     */
    RedSocial(String filenameUsuarios, String filenameEnlaces, String filenameMensaje) throws IOException {
        // REVIEW
        try {
            leerUsuarios(filenameUsuarios);
            leerEnlaces(filenameEnlaces);
            leerMensaje(filenameMensaje);
        } catch (IOException e1) {
            throw new IOException(e1.getMessage());
        }
    }

    /* El primer archivo describe los usuarios y contiene una línea por cada uno de ellos, con su nombre y su capacidad
    de amplificación separados por espacios o tabuladores */
    public void leerUsuarios(String filenameUsuarios) throws IOException {
        // DUE lectura del archivo y construcción de la red social
        String[] words;
        String line, nombreUsuario;
        int capacidadAmplificacion;
        Usuario usuario;

        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(new FileInputStream("txt\\" + filenameUsuarios)));

            while ((line = buffer.readLine()) != null) {
                words = line.split("[ \\t]"); // NOTE: Acepta tanto espacios como tabuladores como regex :)
                nombreUsuario = words[0];
                capacidadAmplificacion = Integer.parseInt(words[1]);
                usuario = new Usuario(nombreUsuario, capacidadAmplificacion); // DUE: Implementarlo en Usuario
                usuarios.put(nombreUsuario, usuario);
            }

            buffer.close();

        } catch (IOException e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* El segundo archivo detalla los enlaces, también uno por línea, indicando el nombre del usuario origen, el
    nombre del usuario destino y el coste de propagación asociado a ese enlace */
    public void leerEnlaces(String filenameEnlaces) {
        // DUE lectura del archivo y creación de enlaces supongo
        String line;
        Usuario usuarioOrigen, usuarioDestino;
        int costePropagacion;
        Enlace enlace;

        String[] words;
        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(new FileInputStream("txt/" + filenameEnlaces)));
            try {
                while ((line = buffer.readLine()) != null) {
                    words = line.split(" "); // REVIEW: ¿También debería aceptar tabuladores como separadores?
                    usuarioOrigen = usuarios.get(words[0]);
                    usuarioDestino = usuarios.get(words[1]);
                    costePropagacion = Integer.parseInt(words[2]);

                    enlace = new Enlace(usuarioOrigen, usuarioDestino, costePropagacion);
                    enlaces[num_enlaces] = enlace;
                    num_enlaces++;
                }

                buffer.close();

            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        } catch (FileNotFoundException e1) {
            throw new RuntimeException(e1);
        }
    }

    /* El tercer archivo especifica el mensaje que se va a difundir: su autor, el alcance inicial y el usuario en el que
    se encuentra al comenzar la simulación. Las líneas restantes de este contienen, en el orden dado, los nombres de los
    usuarios que el mensaje intentará visitar, siguiendo el mecanismo explicado anteriormente en la clase Mensaje */
    public void leerMensaje(String filenameMensaje) {
        // DUE lectura del archivo y llamada a la función difunde (¿?)
    }


}