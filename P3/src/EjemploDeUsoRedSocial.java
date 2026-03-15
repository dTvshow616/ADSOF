import java.io.*;

/**
 * Nombre de la clase: EjemploDeUsoRedSocial
 * <p>
 * Description: Sirve como main para probar el resto de clases
 * @author Alvaro G.S. & Ana O.R.
 * @version 1.0
 * @see RedSocial
 */
public class EjemploDeUsoRedSocial{
    public static void main(String[] args) {
        try {
            RedSocial s;
            System.out.println("empezando");
            s = new RedSocial("USUARIOS.txt", "ENLACES.txt", "MENSAJE.txt");
            System.out.println("alo");
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