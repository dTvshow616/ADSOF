/**
 * Nombre de la clase: EjemploDeUsoEnlaceSeñuelo
 * <p>
 * Description: Sirve para probar el EnlaceSeñuelo
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Usuario
 * @see Enlace
 * @see EnlaceSenuelo
 */
public class EjemploDeUsoEnlaceSeñuelo{
    /**
     * Función que comprueba la clase EnlaceSeñuelo
     * @param args parámetros de entrada
     */
    public static void main(String args[]){
        Usuario ana = new Usuario("ana");
        Usuario carmen = new Usuario("carmen");
        EnlaceSenuelo es = new EnlaceSenuelo(ana,carmen,10,5,0.5);

        System.out.println(es.costeEspecial());

        System.out.println(es.getUsuarioDestino());
        System.out.println(es.getUsuarioDestino());
        System.out.println(es.getUsuarioDestino());
        System.out.println(es.getUsuarioDestino());        
    }
}