package demostradores;

import alerta.SensorYaInstalado;
import estacion.EstacionMeteorologica;
import sensor.*;

/**
 * Permite probar los requisitos del apartado 2
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.0
 */
public class TestEjercicioDos {

    /*------------------------------------------------- CONSTRUCTOR --------------------------------------------------*/

    /**
     * Constructor del demostrador
     */
    public TestEjercicioDos() {
    }


    /*----------------------------------------------------- MISC -----------------------------------------------------*/

    /**
     * Main del test
     * @param args argumentos estándar del main de Java, no se usan
     */
    public static void main(String[] args) {
        EstacionMeteorologica estacionMeteo = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        try {
            SensorTemperatura s1 = estacionMeteo.addSensorTemperatura(0.0, UdsMedidaTemp.CELSIUS);
            s1.setPorcentajeCambioMax(100000);
            s1.setLecturaSensor(TipoLecturaSensor.MINMAX);
            estacionMeteo.simulacionPuntual(s1, 1);
            System.out.println(s1);
            estacionMeteo.simulacionPuntual(s1, 1);
            System.out.println(s1);
            estacionMeteo.simulacionPuntual(s1, 1.4);
            System.out.println(s1);
            estacionMeteo.simulacionPuntual(s1, 0);
            System.out.println(s1);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorHumedad s2 = estacionMeteo.addSensorHumedad(0, UdsMedidaHum.PORCENTAJE);
            s2.setPorcentajeCambioMax(100000);
            s2.setLecturaSensor(TipoLecturaSensor.RANGOCONFIGURABLE);
            s2.setValorUltimaLectura(50);
            estacionMeteo.simulacionPuntual(s2, 0);
            System.out.println(s2);
            estacionMeteo.simulacionPuntual(s2, 25);
            System.out.println(s2);
            estacionMeteo.simulacionPuntual(s2, 25);
            System.out.println(s2);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

        try {
            SensorPresion s3 = estacionMeteo.addSensorPresion(0, UdsMedidaPres.HECTOPASCALES);
            s3.setPorcentajeCambioMax(100000);
            s3.setLecturaSensor(TipoLecturaSensor.RANGOMEDIA);
            estacionMeteo.lecturaPuntual(s3, 500);
            estacionMeteo.simulacionPuntual(s3, 0);
            System.out.println(s3);
            estacionMeteo.simulacionPuntual(s3, 25);
            System.out.println(s3);
            estacionMeteo.simulacionPuntual(s3, 25);
            System.out.println(s3);
        } catch (SensorYaInstalado e) {
            System.out.println("[!]" + e.toString());
        }

    }
}