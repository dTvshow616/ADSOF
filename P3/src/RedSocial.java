import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Nombre de la clase: RedSocial
 * <p>
 * Description: Implementa la red social
 * @author Álvaro G.S. & Ana O.R.
 * @version 1.3
 * @see Usuario
 * @see Enlace
 * @see Mensaje
 */
public class RedSocial {
    /* La lista de usuarios perteneciente a la red social */
    HashMap<String, Usuario> usuarios = new HashMap<>();
    /* La lista de usuarios por las que deberá difundirse el mensaje */
    ArrayList<Usuario> usuariosDifusion = new ArrayList<>();
    /* Los enlaces de la red social */
    ArrayList<Enlace> enlaces = new ArrayList<>();
    /* El mensaje a difundir por la red social */
    Mensaje mensaje;

    /**
     * Constructor para una red social
     * @param filenameUsuarios nombre del archivo que permite construir la red social completa
     * @param filenameEnlaces  nombre del archivo con la secuencia de usuarios que el mensaje tiene previsto intentar
     *                         visitar durante su difusión
     * @param filenameMensaje  nombre del archivo con el mensaje inicial
     */
    RedSocial(String filenameUsuarios, String filenameEnlaces, String filenameMensaje) throws IOException {
        try {
            leerUsuarios(filenameUsuarios);
            leerEnlaces(filenameEnlaces);
            leerMensaje(filenameMensaje);

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        this.mensaje.difunde(usuariosDifusion.toArray(new Usuario[0]));
    }

    /**
     * Lee y crea usuarios a partir de un fichero
     * @param filenameUsuarios el nombre del fichero, guardado en la carpeta /txt/
     * @throws IOException fallo en la lectura de dicho fichero
     */
    public void leerUsuarios(String filenameUsuarios) throws IOException {
        String[] words;
        String line, nombreUsuario;
        int capacidadAmplificacion;
        Usuario usuario;

        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(new FileInputStream("txt\\" + filenameUsuarios)));

            while ((line = buffer.readLine()) != null) {
                words = line.split("[ \\t]"); // NOTE: Acepta espacios y tabuladores como regex :)
                nombreUsuario = words[0];
                capacidadAmplificacion = Integer.parseInt(words[1]);
                usuario = new Usuario(nombreUsuario, capacidadAmplificacion);
                usuarios.put(nombreUsuario, usuario);
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Lee y crea enlaces a partir de un fichero
     * @param filenameEnlaces el nombre del fichero, guardado en el directorio /txt/
     * @throws IOException fallo en la lectura de dicho fichero
     */
    public void leerEnlaces(String filenameEnlaces) throws IOException {
        String[] words;
        String line;
        Usuario usuarioOrigen, usuarioDestino;
        int costePropagacion;
        Enlace enlace;

        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(new FileInputStream("txt/" + filenameEnlaces)));

            while ((line = buffer.readLine()) != null) {
                words = line.split("[ \\t]"); // NOTE: Acepta espacios y tabuladores como regex :)
                usuarioOrigen = usuarios.get(words[0]);
                usuarioDestino = usuarios.get(words[1]);
                costePropagacion = Integer.parseInt(words[2]);

                enlace = new Enlace(usuarioOrigen, usuarioDestino, costePropagacion);
                usuarioDestino.addEnlace(enlace);
                enlaces.add(enlace);
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Lee y crea el mensaje a partir de un fichero
     * @param filenameMensaje el nombre del fichero, guardado en el directorio /txt/
     * @throws IOException fallo en la lectura de dicho fichero
     */
    public void leerMensaje(String filenameMensaje) throws IOException {
        String[] words;
        String line;
        String texto;
        int alcanceDisponible;
        Usuario usuarioActual;
        String nombreUsuario;

        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(new FileInputStream("txt/" + filenameMensaje)));

            if ((line = buffer.readLine()) != null) {
                words = line.split("[ \\t]"); // NOTE: Acepta espacios y tabuladores como regex :)
                texto = words[0];
                alcanceDisponible = Integer.parseInt(words[1]);
                nombreUsuario = words[2];
                usuarioActual = this.usuarios.get(nombreUsuario);

                this.mensaje = new Mensaje(texto, alcanceDisponible, usuarioActual);

            } else {
                return;
            }

            while ((line = buffer.readLine()) != null) {
                words = line.split("[ \\t]"); // NOTE: Es para que borre los espacios extra detrás del nombre
                nombreUsuario = words[0];
                usuariosDifusion.add(this.usuarios.get(nombreUsuario));
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }


}