package src;

public class EjemploDeUsoRedSocial {
    public static void main(String[] args) {
        try {
            RedSocial s;
            s = new RedSocial("USUARIOS.txt", "ENLACES.txt", "MENSAJE.txt");
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