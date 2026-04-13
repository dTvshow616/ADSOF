package conversor;

import sensor.*;

public class CoversorCelsiusKelvin implements Conversores{

    @Override
    public double convertir(double valor){
        return valor + 273.15;
    }

    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaTemp.CELSIUS;
    }

    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaTemp.KELVIN;
    }


}
