package Modelo;

public class Estatura {
    private double centimetros; // Almacena la estatura en centímetros

    public Estatura(double centimetros) {
        this.centimetros = centimetros;
    }

    // Getter y Setter
    public double getCentimetros() {
        return centimetros;
    }

    public void setCentimetros(double centimetros) {
        this.centimetros = centimetros;
    }

    // Método para obtener la estatura en metros
    public double getMetros() {
        return centimetros / 100;
    }

    // Método para establecer la estatura en metros
    public void setMetros(double metros) {
        this.centimetros = metros * 100;
    }
}
