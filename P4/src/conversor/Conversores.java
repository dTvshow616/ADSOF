package conversor;

import sensor.*;

public interface Conversores {
    double convertir(double valor);
    UdsMedida getMedidaOrigen();
    UdsMedida getMedidaDestino();
}
