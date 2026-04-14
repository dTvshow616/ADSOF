package demostradores;

import alerta.SensorYaInstalado;
import estacion.EstacionMeteorologica;
import sensor.*;

import java.time.LocalDate;

/**
 * Permite probar los requisitos del apartado 1
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class TestEjercicioUno {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del demostrador
     */
    public TestEjercicioUno() {
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
            System.out.println("Calibrado: " + estacionMeteo.comprobarCalibracion(s1));
            estacionMeteo.lecturaPuntual(s1, 30.5);
            System.out.println(" ");
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s2 = estacionMeteo.addSensorHumedad(5.0, UdsMedidaHum.PORCENTAJE);
            estacionMeteo.setFechaInstalacionSensor(s2, LocalDate.parse("2024-09-01"));
            System.out.println("Calibrado: " + estacionMeteo.comprobarCalibracion(s2));
            estacionMeteo.lecturaPuntual(s2, 170.0);
            System.out.println("Calibrado: " + estacionMeteo.comprobarCalibracion(s2));
            System.out.println(" ");
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorPresion s3 = estacionMeteo.addSensorPresion(-0.25, UdsMedidaPres.HECTOPASCALES);
            estacionMeteo.setFechaInstalacionSensor(s3, LocalDate.parse("2025-11-01"));
            System.out.println("Calibrado: " + estacionMeteo.comprobarCalibracion(s3));
            estacionMeteo.lecturaPuntual(s3, 1013.0);
            System.out.println(" ");

        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        System.out.println(estacionMeteo);
    }
}