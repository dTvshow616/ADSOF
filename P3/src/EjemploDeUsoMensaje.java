/**
 * Nombre de la clase: EjemploDeUsoMensaje
 * <p>
 * Description: Sirve para probar Mensaje
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see RedSocial
 */
public class EjemploDeUsoMensaje {
    /**
     * Función que comprueba la clase Mensaje
     * @param args parámetros de entrada
     */
    public static void main(String[] args) {
        Usuario ana = new Usuario("ana", 1);
        Usuario luis = new Usuario("luis", 5);
        Usuario carmen = new Usuario("carmen");
        Usuario sebas = new Usuario("sebas");
        Usuario manolo = new Usuario("manolo");
        Mensaje m = new Mensaje("Hi!", 50, ana);

        ana.addEnlace(new Enlace(ana, luis, 68));
        ana.addEnlace(carmen, 33);
        System.out.println(m);

        carmen.addEnlace(manolo, 1);
        m.difunde(luis, carmen, manolo);
        System.out.println(m);

        m.difunde(manolo);
        System.out.println(m);

        sebas.addEnlace(new Enlace(sebas, luis, 11));
        m.difunde(sebas.getEnlace(luis));
        System.out.println(m);
    }
}