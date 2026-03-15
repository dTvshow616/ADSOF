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
    /* La lista de usuarios por las que deberá difundirse un mensaje */
    HashMap<Mensaje, ArrayList<Usuario>> usuariosDifusion;
    /* Los mensajes  a difundir por la red social */
    ArrayList<Mensaje> mensajes = new ArrayList<>();

    /**
     * Constructor para una red social
     * @param filenameUsuarios nombre del archivo que permite construir la red social completa
     * @param filenameEnlaces  nombre del archivo con la secuencia de usuarios que el mensaje tiene previsto intentar
     *                         visitar durante su difusión
     * @param filenameMensajes nombre del archivo con el mensaje inicial
     */
    RedSocial(String filenameUsuarios, String filenameEnlaces, String filenameMensajes) throws IOException {
        try {
            leerUsuarios(filenameUsuarios);
            leerEnlaces(filenameEnlaces);
            leerMensajes(filenameMensajes);

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        for (Mensaje m : this.mensajes.toArray(new Mensaje[0])) {
            m.difunde(this.usuariosDifusion.get(m).toArray(new Usuario[0]));
        }
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
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Lee y crea los mensajes a partir de un fichero
     * @param filenameMensajes el nombre del fichero, guardado en el directorio /txt/
     * @throws IOException fallo en la lectura de dicho fichero
     */
    public void leerMensajes(String filenameMensajes) throws IOException {
        String[] words;
        String line;
        String primerArgumento;
        int alcanceDisponible;
        Usuario usuarioActual;
        String nombreUsuario;
        Mensaje mensaje = null;
        boolean cabecera = false;

        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(new FileInputStream("txt/" + filenameMensajes)));

            while ((line = buffer.readLine()) != null) {
                words = line.split("[ \\t]"); // NOTE: Acepta espacios y tabuladores como regex :)
                primerArgumento = words[0];
                // REVIEW: Confirmar que detecta y lee varios mensajes en función de si tienen 1 o 3 params
                if (words[1] != null) {
                    alcanceDisponible = Integer.parseInt(words[1]);
                    nombreUsuario = words[2];
                    usuarioActual = this.usuarios.get(nombreUsuario);

                    mensaje = new Mensaje(primerArgumento, alcanceDisponible, usuarioActual);

                    mensajes.add(mensaje);
                }

                if (!cabecera) {
                    this.usuariosDifusion.get(mensaje).add(this.usuarios.get(primerArgumento));
                }
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void addUsuario(String nombreUsuario, int capacidadAmplificacion) {
        if (!this.usuarios.containsKey(nombreUsuario)) {
            this.usuarios.put(nombreUsuario, new Usuario(nombreUsuario, capacidadAmplificacion));
        }
    }

    public void addUsuario(String nombreUsuario) {
        if (!this.usuarios.containsKey(nombreUsuario)) {
            this.usuarios.put(nombreUsuario, new Usuario(nombreUsuario));
        }
    }

    public void addEnlace(String nombreUsuarioOrigen, String nombreUsuarioDestino, int coste) {
        this.usuarios.get(nombreUsuarioOrigen).addEnlace(this.usuarios.get(nombreUsuarioDestino), coste);
    }

    public void addMensaje(String texto, int alcanceDisponible, String autor) {
        mensajes.add(new Mensaje(texto, alcanceDisponible, this.usuarios.get(autor)));
    }

    public void addMessageReceivers(String texto, int alcanceDisp, String autor, String... nombreUsuariosDest) {
        Mensaje m = new Mensaje(texto, alcanceDisp, this.usuarios.get(autor));

        if (this.mensajes.contains(m)) {
            for (String nombreUsuarioDestino : nombreUsuariosDest) {
                this.usuariosDifusion.get(m).add(this.usuarios.get(nombreUsuarioDestino));
            }
        }
    }

    public void saveRedSocial() {
        /* Escritura de usuarios_save.txt */
        /* Escritura de enlaces_save.txt */
        /* Escritura de mensajes_save.txt */
        // DUE: Hacer esto
    }


}