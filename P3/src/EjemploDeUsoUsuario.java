/**
 * Nombre de la clase: EjemploDeUsoUsuario
 * <p>
 * Description: Sirve para probar Usuario
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Usuario
 * @see Mensaje
 * @see Enlace
 */
public class EjemploDeUsoUsuario{
    /**
     * Función que comprueba la clase Usuario
     * @param args parámetros de entrada
     */
    public static void main(String args[]){
        Usuario ana = new Usuario("ana", 1);
        Usuario carmen = new Usuario("carmen");
        Usuario luis = new Usuario("luis",3);

        System.out.println(ana);

        Mensaje m = new Mensaje("Hi!", 50, ana);

        System.out.println(carmen);

        if(ana.comprobarMensaje(m) == true){
            System.out.println("Mensaje en el usuario");
        }else{
            System.out.println("Mensaje no en el usuario");
        }

        System.out.println(ana);

        ana.addEnlace(new Enlace(ana, luis, 68));
        ana.addEnlace(carmen, 33);
        System.out.println(ana);
        
    }
}