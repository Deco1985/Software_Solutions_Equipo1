package modelo;

public class EstrategiaPrecio implements EstrategiaMembresia {
    @Override
    public double calcularPrecio(Membresia membresia) {
        return membresia.getPrecio();
    }
}
