import java.io.IOException;

/**
 * Nombre de la clase: EjemploDeUsoRedSocial
 * <p>
 * Description: Sirve para probar la Red Social
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see RedSocial
 */
public class EjemploDeUsoRedSocial {
    /**
     * Función que comprueba la clase Red Social
     * @param args parámetros de entrada
     */
    public static void main(String[] args) {
        try {
            RedSocial s;
            s = new RedSocial("USUARIOS.txt", "ENLACES.txt", "MENSAJE.txt");
            s.saveRedSocial();
            s = new RedSocial("USUARIOS.txt", "ENLACES.txt", "MENSAJE2.txt");
        } catch (IOException e) {
            System.out.println("Error en archivos");
        }
    }
}

/* Debe producir la siguiente salida:
    Mensaje(Hola!:7) en @carmen
    Mensaje(Hola!:2) en @diego
    Error en archivos */