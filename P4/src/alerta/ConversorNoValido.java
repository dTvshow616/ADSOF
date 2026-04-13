package alerta;


public class ConversorNoValido extends Exception{

    /**
     * Constructor para esta excepción
     */
    public ConversorNoValido() {}

    /**
     * Define el mensaje de la excepción
     * @return descripción de la excepción
     */
    @Override
    public String toString() {
        return "El conversor no es valido";
    }
    
}
