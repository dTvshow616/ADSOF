package demostradores;

import alerta.SensorYaInstalado;
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

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del demostrador
     */
    public TestEjercicioCuatro() {
    }


    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Main del test
     * @param args argumentos estándar del main de Java, no se usan
     */
    public static void main(String[] args) {
        EstacionMeteorologica estacionMeteo = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        try {
            SensorTemperatura s1 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            estacionMeteo.setFechaInstalacionSensor(s1, LocalDate.parse("2023-09-01"));
            estacionMeteo.lecturaPuntual(s1, 30.5);
            estacionMeteo.lecturaPuntual(s1, 32.5);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s2 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            estacionMeteo.setFechaInstalacionSensor(s2, LocalDate.parse("2024-09-01"));
            estacionMeteo.lecturaPuntual(s2, 70.0);
            estacionMeteo.lecturaPuntual(s2, 80.0);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorPresion s3 = estacionMeteo.addSensorPresion(-0.25, UdsMedidaPres.HECTOPASCALES);
            estacionMeteo.setFechaInstalacionSensor(s3, LocalDate.parse("2025-11-01"));
            estacionMeteo.lecturaPuntual(s3, 1013.0);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorTemperatura s4 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            estacionMeteo.setFechaInstalacionSensor(s4, LocalDate.parse("2023-09-01"));
            estacionMeteo.setFechaCaducidadSensor(s4, LocalDateTime.parse("2026-01-01T00:00:00"));
            estacionMeteo.lecturaPuntual(s4, 30.5);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s5 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            estacionMeteo.setFechaInstalacionSensor(s5, LocalDate.parse("2024-09-01"));
            estacionMeteo.lecturaPuntual(s5, 70.0);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorTemperatura s6 = estacionMeteo.addSensorTemperatura(10.0, UdsMedidaTemp.CELSIUS);
            estacionMeteo.setFechaInstalacionSensor(s6, LocalDate.parse("2023-09-01"));
            estacionMeteo.lecturaPuntual(s6, 30.5);
            estacionMeteo.lecturaPuntual(s6, 68.5);
            estacionMeteo.calibrarSensor(s6, 3.0);
            estacionMeteo.configurarPorcentajeBrusco(s6, 100);
            estacionMeteo.lecturaPuntual(s6, 105.5);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s7 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            estacionMeteo.setFechaInstalacionSensor(s7, LocalDate.parse("2024-09-01"));
            System.out.println("Calibrado: " + estacionMeteo.comprobarCalibracion(s7));
            estacionMeteo.lecturaPuntual(s7, 70.0);
            estacionMeteo.lecturaPuntual(s7, 170.0);
            System.out.println("Calibrado: " + estacionMeteo.comprobarCalibracion(s7));
            estacionMeteo.imprimirEstacion();

            estacionMeteo.calibrarSensor(s7, 2);
            System.out.println("Calibrado: " + estacionMeteo.comprobarCalibracion(s7));
            estacionMeteo.imprimirEstacion();
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }
    }
}