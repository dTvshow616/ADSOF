package conversor;

import sensor.*;

/**
 * Implementa la clase de un conversor de kelvin a fahrenheit
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 * @see udsMedida
 */
public class ConversorKelvinFahrenheit implements Conversores{

    public ConversorKelvinFahrenheit(){}

    /**
     * Convierte el valor que esta en una medida x a la medida y
     * @param valor valor que va a convertir
     * @return el valor cambiado a la medida destino
     */
    @Override
    public double convertir(double valor){
        return (valor - 273.15) * 9/5 + 32 ;
    }

    /**
     * Devuelve la medida origen sobre la que se va a operar
     * @return la medida origen sobre la que se va a operar
     */
    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaTemp.KELVIN;
    }

    /**
     * Devuelve la medida a la que se va a transformar el valor
     * @return la medida a la que se va a transformar el valor
     */
    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaTemp.FAHRENHEIT;
    }

}