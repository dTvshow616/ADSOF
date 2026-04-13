package conversor;

import sensor.*;

public class ConversorFahrenheitCelsius implements Conversores{
    private ConversorFahrenheitKelvin primero;
    private ConversorKelvinCelsius segundo;

    @Override
    public double convertir(double valor){
        return segundo.convertir(primero.convertir(valor));
    }

    @Override
    public UdsMedida getMedidaOrigen(){
        return UdsMedidaTemp.FAHRENHEIT;
    }

    @Override
    public UdsMedida getMedidaDestino(){
        return UdsMedidaTemp.CELSIUS;
    }
}
