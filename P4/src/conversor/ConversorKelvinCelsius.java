package conversor;

import sensor.*;

public class ConversorKelvinCelsius implements Conversores{

    @Override
    public double convertir(double valor){
        return valor - 273.15;
    }

    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaTemp.KELVIN;
    }

    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaTemp.CELSIUS;
    }

}
