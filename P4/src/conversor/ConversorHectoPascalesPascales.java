package conversor;

import sensor.*;

public class ConversorHectoPascalesPascales implements Conversores{

    @Override
    public double convertir(double valor){
        return valor * 100;
    }

    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaPres.HECTOPASCALES;
    }

    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaPres.HECTOPASCALES;
    }

}
