package conversor;

import sensor.*;

public class ConversorKelvinFahrenheit implements Conversores{

    @Override
    public double convertir(double valor){
        return ((valor - 273.15) * (9/5)) + 32 ;
    }

    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaTemp.KELVIN;
    }

    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaTemp.FAHRENHEIT;
    }

}