package vista;

import controlador.ControladorMembresias;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de la ventana
        VentanaMembresias ventanaMembresias = new VentanaMembresias();

        // Crear una instancia del controlador y pasarle la vista
        ControladorMembresias controladorMembresias = new ControladorMembresias(ventanaMembresias);

        // Hacer visible la ventana
        ventanaMembresias.setVisible(true);
    }
}
