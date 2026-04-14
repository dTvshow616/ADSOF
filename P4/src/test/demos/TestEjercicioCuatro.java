package demos;

import alerta.*;
import estacion.EstacionMeteorologica;
import sensor.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Permite probar los requisitos del apartado 4
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class TestEjercicioCuatro {
    /*----------------------------------------------------- MISC -----------------------------------------------------*/
    public static void main(String[] args) {
        EstacionMeteorologica estacionMeteo = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        try {
            SensorTemperatura s1 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            s1.setFechaInstalacion(LocalDate.parse("2023-09-01"));
            estacionMeteo.lecturaPuntual(s1, 30.5);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s2 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            s2.setFechaInstalacion(LocalDate.parse("2024-09-01"));
            estacionMeteo.lecturaPuntual(s2, 70.0);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorPresion s3 = estacionMeteo.addSensorPresion(-0.25, UdsMedidaPres.HECTOPASCALES);
            s3.setFechaInstalacion(LocalDate.parse("2025-11-01"));
            estacionMeteo.lecturaPuntual(s3, 1013.0);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorTemperatura s4 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            s4.setFechaInstalacion(LocalDate.parse("2023-09-01"));
            s4.setFechaCaducidad(LocalDateTime.parse("2026-01-01T00:00:00"));
            estacionMeteo.lecturaPuntual(s4, 30.5);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s5 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            s5.setFechaInstalacion(LocalDate.parse("2024-09-01"));
            estacionMeteo.lecturaPuntual(s5, 70.0);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorTemperatura s6 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            s6.setFechaInstalacion(LocalDate.parse("2023-09-01"));
            s6.leerValor(30.5);
        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s7 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            s7.setFechaInstalacion(LocalDate.parse("2024-09-01"));
            s7.leerValor(70.0);
        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar e) {
            System.out.println("[!]" + e.toString());
        }

        estacionMeteo.imprimirEstacion();
    }
}