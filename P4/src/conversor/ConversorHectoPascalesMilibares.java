package conversor;

import sensor.*;

public class ConversorHectoPascalesMilibares implements Conversores{

    @Override
    public double convertir(double valor){
        return valor;
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
