/**
 * Nombre de la clase: EjemploDeUsoMensajeControlado
 * <p>
 * Description: Sirve para probar MensajeControlado
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 * @see Usuario
 * @see Enlace
 * @see Mensaje
 * @see MensajeControlado
 */
public class EjemploDeUsoMensajeControlado{
    /**
     * Función que comprueba la clase MensajeControlado
     * @param args parámetros de entrada
     */
    public static void main(String args[]){
        Usuario ana = new Usuario("ana");
        Usuario carmen = new Usuario("carmen");
        MensajeControlado m1 = new MensajeControlado("Hi!",50,ana,10);
        MensajeControlado m2 = new MensajeControlado("Holi!",25,ana,50);
        EnlaceSenuelo es = new EnlaceSenuelo(ana,carmen,10,5,0.5);

        System.out.println(m1.puedeDifundirPor(es));

        System.out.println(m1.aceptadoPor(carmen));
        System.out.println(m2.aceptadoPor(carmen));


    }
}