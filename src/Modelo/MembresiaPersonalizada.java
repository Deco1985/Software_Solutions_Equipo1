package modelo;

public class MembresiaPersonalizada extends Membresia {
    private double precio;

    public MembresiaPersonalizada(double precio) {
        this.precio = precio;
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
