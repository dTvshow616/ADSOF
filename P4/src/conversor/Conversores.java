package conversor;

import sensor.*;

/**
 * Implementa la interfaz de un conversor
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see UdsMedida
 */
public interface Conversores {
    /**
     * Convierte el valor que esta en una medida x a la medida y
     * @param valor valor que va a convertir
     * @return el valor cambiado a la medida destino
     */
    public double convertir(double valor);
    /**
     * Devuelve la medida origen sobre la que se va a operar
     * @return la medida origen sobre la que se va a operar
     */
    public UdsMedida getMedidaOrigen();
    /**
     * Devuelve la medida a la que se va a transformar el valor
     * @return la medida a la que se va a transformar el valor
     */
    public UdsMedida getMedidaDestino();
}