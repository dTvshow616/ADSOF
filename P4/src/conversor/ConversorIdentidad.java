package conversor;

import sensor.*;

/**
 * Implementa la clase de un conversor que cambia el valor a si mismo
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see UdsMedida
 */
public class ConversorIdentidad implements Conversores{
    /** la medida que va a ser tanto el origen como el destino */
    private final UdsMedida unidad;

    /**
     * Constructor de un conversor identidad
     * @param unidad la medida que va a ser tanto el origen como el destino
     */
    public ConversorIdentidad(UdsMedida unidad) {
        this.unidad = unidad;
    }

    /**
     * Convierte el valor que esta en una medida x a la medida y
     * @param valor valor que va a convertir
     * @return el valor cambiado a la medida destino
     */
    @Override
    public double convertir(double valor) {
        return valor;
    }

    /**
     * Devuelve la medida origen sobre la que se va a operar
     * @return la medida origen sobre la que se va a operar
     */
    @Override
    public UdsMedida getMedidaOrigen() {
        return unidad;
    }

    /**
     * Devuelve la medida a la que se va a transformar el valor
     * @return la medida a la que se va a transformar el valor
     */
    @Override
    public UdsMedida getMedidaDestino() {
        return unidad;
    }
}