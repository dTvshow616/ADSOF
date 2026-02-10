package trayectos;

public class MainTrayectos {
    public static void main(String[] args) {
        TramoTrayecto[] trayecto = {
            new TramoAPie("Hotel Puerta del Sol", "Sol Renfe", 1),
            new TramoTren("Sol Renfe", "Cantoblanco Renfe", Linea.C4, 4),
            new TramoAPie("Cantoblanco Renfe", "EPS", 2.6, Ritmo.RAPIDO),
        };

        for (TramoTrayecto tramo: trayecto)
            System.out.println(tramo);
    }
}
