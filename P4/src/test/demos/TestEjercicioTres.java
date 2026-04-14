package demos;

import alerta.ConversorNoValido;
import alerta.SensorYaInstalado;
import conversor.*;
import estacion.EstacionMeteorologica;
import sensor.SensorTemperatura;
import sensor.UdsMedidaTemp;

public class TestEjercicioTres {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        EstacionMeteorologica estacionMeteo = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        try {
            Conversores c1 = new ConversorCelsiusFahrenheit();
            SensorTemperatura s1 = estacionMeteo.addSensorTemperatura(0.0, UdsMedidaTemp.CELSIUS, c1);
            s1.setPorcentajeCambioMax(100000);
            estacionMeteo.lecturaPuntual(s1, 20);
            estacionMeteo.lecturaPuntual(s1, 37);
            estacionMeteo.lecturaPuntual(s1, 80);
            System.out.println(s1.getId());
            System.out.println(s1.getProcesadorDeDatos());

        } catch (SensorYaInstalado | ConversorNoValido e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            Conversores c2 = new ConversorFahrenheitCelsius();
            SensorTemperatura s2 = estacionMeteo.addSensorTemperatura(0.0, UdsMedidaTemp.FAHRENHEIT, c2);
            s2.setPorcentajeCambioMax(100000);
            estacionMeteo.lecturaPuntual(s2, 20);
            estacionMeteo.lecturaPuntual(s2, 37);
            estacionMeteo.lecturaPuntual(s2, 80);
            System.out.println(s2.getId());
            System.out.println(s2.getProcesadorDeDatos());

        } catch (SensorYaInstalado | ConversorNoValido e) {
            System.out.println("[!]" + e.toString());
        }

    }
}