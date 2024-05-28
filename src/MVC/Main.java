package MVC;

import Controlador.controlador;
import Modelo.Correo;
import Modelo.Estatura;
import Modelo.Fecha;
import Modelo.Huella;
import Modelo.Peso;
import Modelo.Cliente;
import Modelo.RegistroClientes;


import Vista.VentaMembre;
import Vista.VentanaPrincipal;
import Vista.VentanaRegistrarCliente;

public class Main {

    public static void main(String[] args) {
        try {
            // Suponiendo valores de ejemplo
            Correo correo = new Correo("ejemplo@gmail.com");
            Estatura estatura = new Estatura(175.0); // Ejemplo de estatura en cm
            Fecha fecha = new Fecha(1, 1, 2004); // Ejemplo de fecha de nacimiento
            Huella huella = new Huella(); // Lo mismo para huella
            Peso peso = new Peso(70.0); // Ejemplo de peso en kg

            // Crear una instancia de Cliente con todos los atributos necesarios
            Cliente cliente = new Cliente("Juan", "Pérez", "Masculino", fecha, correo, estatura, peso, huella);

            RegistroClientes regisClientes = new RegistroClientes();

            // Instanciar las vistas
            VentanaPrincipal ventanaPrin = new VentanaPrincipal();
            VentanaRegistrarCliente ventaRegisCliente = new VentanaRegistrarCliente();
            VentaMembre ventaMembre = new VentaMembre();

            // Crear el controlador y pasarle las vistas y modelos
            controlador ctrl = new controlador(ventanaPrin, ventaRegisCliente,ventaMembre, correo, estatura, fecha,huella, peso, cliente, regisClientes);

            // Iniciar la aplicación
            ctrl.iniciar();
            ventanaPrin.setVisible(true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            // Manejar aquí la excepción de un correo inválido, como terminar el programa o pedir otro correo
        }
    }
}
