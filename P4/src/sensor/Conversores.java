package sensor;

import java.io.*;

public interface Conversores {
    double convertir(double valor);
    UdsMedida getMedidaOrigen();
    UdsMedida getMedidaDestino();
}

class CoversorCelsiusKelvin implements Conversores{

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

class ConversorKelvinFahrenheit implements Conversores{

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

class ConversorCelsiusFahrenheit implements Conversores{
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

class ConversorFahrenheitKelvin implements Conversores{

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

class ConversorKelvinCelsius implements Conversores{

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

class ConversorFahrenheitCelsius implements Conversores{
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

class ConversorHectoPascalesPascales implements Conversores{

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

class ConversorHectoPascalesMilibares implements Conversores{

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

class ConversorIdentidad implements Conversores{
    private final UdsMedida unidad;

    public ConversorIdentidad(UdsMedida unidad) {
        this.unidad = unidad;
    }

    @Override
    public double convertir(double valor) {
        return valor; 
    }

    @Override
    public UdsMedida getMedidaOrigen() {
        return unidad;
    }

    @Override
    public UdsMedida getMedidaDestino() {
        return unidad;
    }
}