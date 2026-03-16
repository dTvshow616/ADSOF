import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Nombre de la clase: RedSocial
 * <p>
 * Description: Implementa la red social
 * @author Alvaro G.S. & Ana O.R.
 * @version 1.3
 * @see Usuario
 * @see Enlace
 * @see Mensaje
 */
public class RedSocial {
    /* La lista de usuarios perteneciente a la red social */
    HashMap<String, Usuario> usuarios = new HashMap<>();
    /* La lista de usuarios por las que deberá difundirse un mensaje */
    HashMap<Mensaje, ArrayList<Usuario>> usuariosDifusion = new HashMap<>();
    /* Los mensajes  a difundir por la red social */
    ArrayList<Mensaje> mensajes = new ArrayList<>();

    /**
     * Constructor para una red social
     * @param filenameUsuarios nombre del archivo que permite construir la red social completa
     * @param filenameEnlaces  nombre del archivo con la secuencia de usuarios que el mensaje tiene previsto intentar
     *                         visitar durante su difusión
     * @param filenameMensaje  nombre del archivo con el mensaje inicial
     */
    RedSocial(String filenameUsuarios, String filenameEnlaces, String filenameMensaje) throws NullPointerException,
            IOException {
        if (filenameUsuarios == null || filenameEnlaces == null || filenameMensaje == null) {
            throw new NullPointerException("An argument was null");
        }

        try {
            leerUsuarios(filenameUsuarios);
            leerEnlaces(filenameEnlaces);
            leerMensaje(filenameMensaje);

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
                    new BufferedReader(new InputStreamReader(new FileInputStream(".\\P3\\txt\\" + filenameUsuarios)));

            while ((line = buffer.readLine()) != null) {
                words = line.split("[\\t ]+"); // NOTE: Acepta espacios y tabuladores como regex :)
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
                    new BufferedReader(new InputStreamReader(new FileInputStream(".\\P3\\txt\\" + filenameEnlaces)));

            while ((line = buffer.readLine()) != null) {
                words = line.split("[\\t ]+"); // NOTE: Acepta espacios y tabuladores como regex :)
                usuarioOrigen = usuarios.get(words[0]);
                usuarioDestino = usuarios.get(words[1]);
                costePropagacion = Integer.parseInt(words[2]);

                enlace = new Enlace(usuarioOrigen, usuarioDestino, costePropagacion);
                usuarioOrigen.addEnlace(enlace);
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
        Mensaje mensaje;

        try {
            BufferedReader buffer =
                    new BufferedReader(new InputStreamReader(new FileInputStream(".\\P3\\txt\\" + filenameMensaje)));

            if ((line = buffer.readLine()) != null) {
                words = line.split("[\\t ]+"); // NOTE: Acepta espacios y tabuladores como regex :)
                texto = words[0];
                alcanceDisponible = Integer.parseInt(words[1]);
                nombreUsuario = words[2];
                usuarioActual = this.usuarios.get(nombreUsuario);

                mensaje = new Mensaje(texto, alcanceDisponible, usuarioActual);
                mensajes.add(mensaje);
                usuariosDifusion.put(mensaje, new ArrayList<>());

            } else {
                buffer.close();
                return;
            }

            while ((line = buffer.readLine()) != null) {
                words = line.split("[\\t ]+"); // NOTE: Es para que borre los espacios extra detrAs del nombre
                nombreUsuario = words[0];
                this.usuariosDifusion.get(mensaje).add(this.usuarios.get(nombreUsuario));
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    /**
     * Permite añadir usuarios a la red social
     * @param nombreUsuario          el nombre del usuario
     * @param capacidadAmplificacion la capacidad de amplificación del usuario
     * @throws NullPointerException el nombre era null
     */
    public void addUsuario(String nombreUsuario, int capacidadAmplificacion) throws NullPointerException {
        if (!this.usuarios.containsKey(nombreUsuario)) {
            try {
                this.usuarios.put(nombreUsuario, new Usuario(nombreUsuario, capacidadAmplificacion));

            } catch (NullPointerException e) {
                throw new NullPointerException(e.getMessage());
            }
        }
    }

    /**
     * Permite añadir usuarios a la red social sin especificar su capacidad de amplification
     * @param nombreUsuario el nombre del usuario
     * @throws NullPointerException el nombre era null
     */
    public void addUsuario(String nombreUsuario) throws NullPointerException {
        if (!this.usuarios.containsKey(nombreUsuario)) {
            try {
                this.usuarios.put(nombreUsuario, new Usuario(nombreUsuario));
            } catch (NullPointerException e) {
                throw new NullPointerException(e.getMessage());
            }
        }
    }

    /**
     * Permite añadir enlaces a la red social
     * @param nombreUsuarioOrigen  el nombre del origen
     * @param nombreUsuarioDestino el nombre del destino
     * @param coste                el coste del enlace
     * @throws NullPointerException alguno de los nombres era null
     */
    public void addEnlace(String nombreUsuarioOrigen, String nombreUsuarioDestino, int coste) throws NullPointerException {
        try {
            this.usuarios.get(nombreUsuarioOrigen).addEnlace(this.usuarios.get(nombreUsuarioDestino), coste);
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Permite añadir un mensaje a la red social
     * @param texto             el texto del mensaje
     * @param alcanceDisponible el alcance disponible del mensaje
     * @param autor             el autor del mensaje
     * @throws NullPointerException al texto o el autor eran null
     */
    public void addMensaje(String texto, int alcanceDisponible, String autor) throws NullPointerException {
        try {
            mensajes.add(new Mensaje(texto, alcanceDisponible, this.usuarios.get(autor)));
        } catch (NullPointerException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    /**
     * Permite añadir un recorrido de difusión a un mensaje
     * @param texto              el texto del mensaje
     * @param alcanceDisp        el alcance disponible del mensaje
     * @param autor              el autor del mensaje
     * @param nombreUsuariosDest el nombre de todos los usuarios del recorrido
     * @throws NullPointerException el texto o algún nombre era null
     */
    public void addMessageReceivers(String texto, int alcanceDisp, String autor, String... nombreUsuariosDest) throws NullPointerException {
        Mensaje m = new Mensaje(texto, alcanceDisp, this.usuarios.get(autor));

        if (this.mensajes.contains(m)) {
            for (String nombreUsuarioDestino : nombreUsuariosDest) {
                try {
                    this.usuariosDifusion.get(m).add(this.usuarios.get(nombreUsuarioDestino));
                } catch (NullPointerException e) {
                    throw new NullPointerException(e.getMessage());
                }
            }
        }
    }

    /**
     * Permite guardar la información de la red social
     * @throws IOException no se pudieron abrir o escribir los ficheros de guardado
     */
    public void saveRedSocial() throws IOException {
        BufferedWriter buffer;
        try {
            /* Escritura de usuarios_save.txt */
            buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\P3\\txt\\usuarios_save.txt")));
            for (Usuario u : usuarios.values()) {
                buffer.write(u.getNombre() + " " + u.getCapacidadAmplificacion() + "\n");
            }

            buffer.close();
            /* Escritura de enlaces_save.txt */
            buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\P3\\txt\\enlaces_save.txt")));
            for (Usuario u : usuarios.values()) {
                for (int i = 0; i < u.getNumEnlaces(); i++) {
                    Enlace e = u.getEnlace(i);
                    buffer.write(e.getUsuarioOrigen().getNombre() + " " + e.getUsuarioDestino().getNombre() + " " + e.getCoste() + "\n");
                }
            }

            buffer.close();

            /* Escritura de mensajes_save.txt */
            for (int i = 0; i < mensajes.size(); i++) {
                buffer =
                        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".\\P3\\txt\\mensaje_save" + i +
                                ".txt")));
                Mensaje m = mensajes.get(i);
                buffer.write(m.getTexto() + " " + m.getAlcanceInicial() + " " + m.getAutor().getNombre() + "\n");
                for (Usuario u : usuariosDifusion.get(m)) {
                    buffer.write(u.getNombre() + "\n");
                }

                buffer.close();
            }

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }


}