package demos;

import alerta.*;
import estacion.EstacionMeteorologica;
import formateador.FormateadorHtml;
import formateador.FormateadorMarkdown;
import sensor.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Permite probar los requisitos del apartado 5
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class TestEjercicioCinco {
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

            SensorTemperatura s4 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            s4.setFechaInstalacion(LocalDate.parse("2023-09-01"));
            s4.setFechaCaducidad(LocalDateTime.parse("2026-01-01T00:00:00"));
            s4.leerValor(30.5);

            SensorHumedad s5 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            s5.setFechaInstalacion(LocalDate.parse("2024-09-01"));
            s5.leerValor(70.0);

            SensorTemperatura s6 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            s6.setFechaInstalacion(LocalDate.parse("2023-09-01"));
            s6.leerValor(30.5);

            SensorHumedad s7 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            s7.setFechaInstalacion(LocalDate.parse("2024-09-01"));
            s7.leerValor(70.0);

        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar e) {
            System.out.println("[!]" + e.toString());
        }

        FormateadorHtml f1 = new FormateadorHtml(estacionMeteo);
        try {
            f1.formatearDocumento("testCinco");
        } catch (IOException e) {
            System.out.println("[!] " + e.toString());
        }

        FormateadorMarkdown f2 = new FormateadorMarkdown(estacionMeteo);
        try {
            f2.formatearDocumento("testCinco");
        } catch (IOException e) {
            System.out.println("[!] " + e.toString());
        }
    }
}