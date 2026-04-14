package formateador;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Implementa el formateador de un IDocumento a HTML
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public class FormateadorHtml {
    /** El documento a formatear */
    private final IDocumento doc;

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del formateador
     * @param documento el documento a formatear
     */
    public FormateadorHtml(IDocumento documento) {
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
            buffer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename + ".html")));

            buffer.write("<!DOCTYPE html>\n");
            buffer.write("<html lang=\"es\">\n");
            /* Título */
            buffer.write("<head>\n");
            buffer.write("\t<title>" + doc.getTitulo() + "</title>\"\n");
            buffer.write("</head>\n");
            /* Sección principal */
            buffer.write("<body>\n");
            buffer.write("<h1>" + doc.getSeccionPrincipal() + "</h1>\n");
            /* Párrafos sección principal */
            for (String parrafo : doc.getParrafosSeccionPrincipal()) {
                buffer.write("<p>" + parrafo + "</p>\n");
            }
            /* Secciones */
            Set<String> titulos = doc.getSecciones().keySet();
            for (String titulo : titulos) {
                buffer.write("<p>" + titulo + "</p>\n");
                buffer.write("<u1>\n");
                List<String> parrafos = doc.getSecciones().get(titulo);
                for (String parrafo : parrafos) {
                    buffer.write("<li>" + parrafo + "</li>\n");
                }
                buffer.write("</u1>\n");
            }
            buffer.write("</body>\n");
            buffer.write("</html>\n");

            buffer.close();

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}