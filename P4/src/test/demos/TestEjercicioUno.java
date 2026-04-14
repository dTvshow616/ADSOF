package demos;

import alerta.*;
import estacion.EstacionMeteorologica;
import sensor.*;

import java.time.LocalDate;

/**
 * Permite probar los requisitos del apartado 1
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class TestEjercicioUno {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        EstacionMeteorologica estacionMeteo = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        try {
            SensorTemperatura s1 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            s1.setFechaInstalacion(LocalDate.parse("2023-09-01"));
            s1.leerValor(30.5);

            SensorHumedad s2 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            s2.setFechaInstalacion(LocalDate.parse("2024-09-01"));
            s2.leerValor(70.0);

            SensorPresion s3 = estacionMeteo.addSensorPresion(-0.25, UdsMedidaPres.HECTOPASCALES);
            s3.setFechaInstalacion(LocalDate.parse("2025-11-01"));
            s3.leerValor(1013.0);

        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar e) {
            System.out.println("[!]" + e.toString());
        }

        System.out.println(estacionMeteo);
    }
}