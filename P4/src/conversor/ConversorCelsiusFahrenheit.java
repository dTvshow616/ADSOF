package conversor;

import sensor.*;

public class ConversorCelsiusFahrenheit implements Conversores{
    private CoversorCelsiusKelvin primero;
    private ConversorKelvinFahrenheit segundo;

    @Override
    public double convertir(double valor){
        return segundo.convertir(primero.convertir(valor));
    }

    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaTemp.CELSIUS;
    }

    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaTemp.FAHRENHEIT;
    }

}
