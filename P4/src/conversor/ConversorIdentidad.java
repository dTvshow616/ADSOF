package conversor;

import sensor.*;

public class ConversorIdentidad implements Conversores{
    private final UdsMedida unidad;

    public ConversorIdentidad(UdsMedida unidad) {
        this.unidad = unidad;
    }

    @Override
    public double convertir(double valor) {
        return valor;
    }

    @Override
    public UdsMedida getMedidaOrigen() {
        return unidad;
    }

    @Override
    public UdsMedida getMedidaDestino() {
        return unidad;
    }
}