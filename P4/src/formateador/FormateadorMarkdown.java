package formateador;

import sensor.Sensor;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Implementa el formateador de un IDocumento a Markdown
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see Sensor
 */
public class FormateadorMarkdown {
    /** El documento a formatear */
    private final IDocumento doc;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del formateador
     * @param documento el documento a formatear
     */
    public FormateadorMarkdown(IDocumento documento) {
        this.doc = documento;
    }

    /**
     * Permite formatear un IDocumento
     * @param filename el nombre del documento formateado
     * @throws IOException hubo un fallo al manejar el archivo
     */
    public void formatearDocumento(String filename) throws IOException {
        BufferedWriter buffer;

        try {
            buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename + ".md")));

            /* Título */
            buffer.write("# " + doc.getTitulo() + "\n\n");
            /* Sección principal */
            buffer.write("## " + doc.getSeccionPrincipal() + "\n\n");
            /* Párrafos sección principal */
            for (String parrafo : doc.getParrafosSeccionPrincipal()) {
                buffer.write(parrafo + "\n\n");
            }
            /* Secciones */
            Set<String> titulos = doc.getSecciones().keySet();
            for (String titulo : titulos) {
                buffer.write("### " + titulo + "\n\n");
                List<String> parrafos = doc.getSecciones().get(titulo);
                for (String parrafo : parrafos) {
                    buffer.write(parrafo + "\n\n");
                }
            }

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}