package test;

import java.time.LocalDate;

import alerta.*;
import estacion.EstacionMeteorologica;
import sensor.*;
import conversor.*;


public class TestEjercicioTres {
        public static void main(String[] args) {
        EstacionMeteorologica estacionMeteo = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        try{
            Conversores c1 = new ConversorCelsiusFahrenheit();
            SensorTemperatura s1 = estacionMeteo.addSensorTemperatura(0.0, UdsMedidaTemp.CELSIUS, c1);
            s1.setPorcentajeCambioMax(100000);
            s1.leerValor(20);
            s1.leerValor(37);
            s1.leerValor(80);
            System.out.println(s1.getId());
            System.out.println(s1.getProcesadorDeDatos());

        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar | ConversorNoValido e) {
            System.out.println("[!]" + e.toString());
        }       

        try{
            Conversores c2 = new ConversorFahrenheitCelsius();
            SensorTemperatura s2 = estacionMeteo.addSensorTemperatura(0.0, UdsMedidaTemp.FAHRENHEIT, c2);
            s2.setPorcentajeCambioMax(100000);
            s2.leerValor(20);
            s2.leerValor(37);
            s2.leerValor(80);
            System.out.println(s2.getId());
            System.out.println(s2.getProcesadorDeDatos());

        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar | ConversorNoValido e) {
            System.out.println("[!]" + e.toString());
        }    


    }
}
