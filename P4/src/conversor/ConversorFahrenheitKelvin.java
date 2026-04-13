package conversor;

import sensor.*;

public class ConversorFahrenheitKelvin implements Conversores{

    @Override
    public double convertir(double valor){
        return ((valor - 32) * (5/9)) + 273.15;
    }

    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaTemp.FAHRENHEIT;
    }

    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaTemp.KELVIN;
    }

}
