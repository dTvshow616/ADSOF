package test;

import java.time.LocalDate;

import alerta.*;
import estacion.EstacionMeteorologica;
import sensor.*;

public class TestEjercicioDos {
    public static void main(String[] args) {
        EstacionMeteorologica estacionMeteo = new EstacionMeteorologica("Madrid Centro", 40.4168, -3.7038);

        try{
            SensorTemperatura s1 = estacionMeteo.addSensorTemperatura(0.0, UdsMedidaTemp.CELSIUS);
            s1.setPorcentajeCambioMax(100000);
            s1.setLecturaSensor(TipoLecturaSensor.MINMAX);
            s1.simulacionLectura(1);
            System.out.println(s1);
            s1.simulacionLectura(1);
            System.out.println(s1);
            s1.simulacionLectura(1.4);
            System.out.println(s1);
            s1.simulacionLectura(0);
            System.out.println(s1);
        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar e) {
            System.out.println("[!]" + e.toString());
        }

        try{
            SensorHumedad s2 = estacionMeteo.addSensorHumedad(0, UdsMedidaHum.PORCENTAJE);
            s2.setPorcentajeCambioMax(100000);
            s2.setLecturaSensor(TipoLecturaSensor.RANGOCONFIGURABLE);
            s2.setValorUltimaLectura(50);
            s2.simulacionLectura(0);
            System.out.println(s2);
            s2.simulacionLectura(25);
            System.out.println(s2);
            s2.simulacionLectura(25);
            System.out.println(s2);
        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar e) {
            System.out.println("[!]" + e.toString());
        }           

        try{
            SensorPresion s3 = estacionMeteo.addSensorPresion(0, UdsMedidaPres.HECTOPASCALES);
            s3.setPorcentajeCambioMax(100000);
            s3.setLecturaSensor(TipoLecturaSensor.RANGOMEDIA);
            s3.leerValor(500);
            s3.simulacionLectura(0);
            System.out.println(s3);
            s3.simulacionLectura(25);
            System.out.println(s3);
            s3.simulacionLectura(25);
            System.out.println(s3);
        } catch (SensorYaInstalado | CambioBruscoLectura | SensorSinCalibrar e) {
            System.out.println("[!]" + e.toString());
        }  

    }   
}
