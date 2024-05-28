package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import Modelo.Correo;
import Modelo.Estatura;
import Modelo.Fecha;
import Modelo.Huella;
import Modelo.Peso;
import Modelo.Cliente;
import Modelo.RegistroClientes;
import Modelo.Membresia;


import Vista.VentaMembre;
import Vista.VentanaPrincipal;
import Vista.VentanaRegistrarCliente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Controlador de la aplicación MVC que maneja las interacciones entre la vista y el modelo.
 */
public class controlador implements ActionListener {

    private VentanaPrincipal ventanaPrin;
    private VentanaRegistrarCliente ventaRegisCliente;
    private VentaMembre ventaMembresias;
    
    
    private Correo correo;
    private Estatura estatura;
    private Fecha fecha;
    private Huella huella;
    private Peso peso;
    private Cliente cliente;
    private RegistroClientes regisClientes;
    
     // Nueva colección de membresías
    private List<Membresia> membresias;

    public controlador(VentanaPrincipal ventanaPrin, VentanaRegistrarCliente ventaRegisCliente,VentaMembre ventaMembresias, Correo correo, Estatura estatura, Fecha fecha, Huella huella, Peso peso, Cliente cliente, RegistroClientes regisClientes) {
        this.ventanaPrin = ventanaPrin;
        this.ventaRegisCliente = ventaRegisCliente;
        this.ventaMembresias = ventaMembresias;
        this.correo = correo;
        this.estatura = estatura;
        this.fecha = fecha;
        this.huella = huella;
        this.peso = peso;
        this.cliente = cliente;
        this.regisClientes = regisClientes;
        this.membresias = new ArrayList<>();  // Inicializar la colección de membresías

        // Configurar los listeners para los botones y otros componentes de la interfaz
        
        this.ventanaPrin.btnClientes.addActionListener(this);//Boton Clientes del Menú principal
        this.ventaRegisCliente.BtnRegistrar.addActionListener(this);//Boton de REGISTRAR en la ventana Clientes
        this.ventanaPrin.btnMembresias.addActionListener(this);//Boton Membresias de la ventana Principal
        this.ventaMembresias.btnActualizar.addActionListener(this); // Botón Actualizar Membresía en la ventana de membresías
    }

    /**
     * Método para inicializar el controlador y configurar las vistas.
     */
    public void iniciar() {
        // Configurar la ventana principal
        ventanaPrin.setDefaultCloseOperation(VentanaPrincipal.EXIT_ON_CLOSE);
        ventanaPrin.setLocationRelativeTo(null); // Centrar la ventana principal en la pantalla
        ventanaPrin.setVisible(true); // Hacer visible la ventana principal

        // Configuraciones adicionales si son necesarias
        ventaRegisCliente.setDefaultCloseOperation(VentanaRegistrarCliente.HIDE_ON_CLOSE);
        ventaRegisCliente.setLocationRelativeTo(null);//Centrar la pantalla de registrar clientes
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaPrin.btnClientes) {
            // Mostrar la ventana de registro de clientes cuando se presiona el botón de clientes
            ventaRegisCliente.setVisible(true);
        } else if (e.getSource() == ventaRegisCliente.BtnRegistrar) {
            // Intentar registrar un nuevo cliente cuando se presiona el botón Registrar
            registrarNuevoCliente();
        } else if (e.getSource() == ventanaPrin.btnMembresias) {
            // Mostrar la ventana de Membresias de clientes cuando se presiona el botón de Membresias
            ventaMembresias.setVisible(true);
        } else if (e.getSource() == ventaMembresias.btnActualizar) {
            // Actualizar membresía cuando se presiona el botón Actualizar
            actualizarMembresia();
        }
    }

/**
 * Método para registrar un nuevo cliente usando los datos del formulario en VentanaRegistrarCliente.
 */
private void registrarNuevoCliente() {
    try {
        // Recoger datos de los campos de entrada en la ventana de registro
        String nombres = ventaRegisCliente.txtNombres.getText();
        String apellidos = ventaRegisCliente.txtApellidos.getText();
        String correo = ventaRegisCliente.txtCorreo.getText();
        double estatura = Double.parseDouble(ventaRegisCliente.txtEstatura.getText());
        double peso = Double.parseDouble(ventaRegisCliente.txtPeso.getText());
        int dia = Integer.parseInt(ventaRegisCliente.txtDiaNacimiento.getText());
        int mes = Integer.parseInt(ventaRegisCliente.txtMesNacimiento.getText());
        int anio = Integer.parseInt(ventaRegisCliente.txtAnioNacimiento.getText());

        Fecha fechaNacimiento = new Fecha(dia, mes, anio);
        Correo correoElectronico = new Correo(correo);

        
        
        // Más campos según sea necesario

        // Crear instancia de Cliente (asumiendo que tienes un constructor adecuado o métodos set)
        Cliente nuevoCliente = new Cliente(nombres, apellidos, correo, new Fecha(dia,mes,anio), new Correo(correo), new Estatura(estatura), new Peso(peso), new Huella());
        
        // Agregar el nuevo cliente al registro de clientes
        regisClientes.agregarCliente(nuevoCliente);
        
        // Actualizar la tabla de la interfaz
        Object[] datosCliente = new Object[] {
            
            nuevoCliente.getNombres(),
            nuevoCliente.getApellidos(),
            nuevoCliente.getCorreoElectronico(),// Asumiendo que Correo tiene un método getValor()
            nuevoCliente.getFechaDeNacimiento(), // Asumiendo que Fecha tiene un método toString adecuado
            nuevoCliente.getEstatura(), // Asumiendo que Estatura tiene un método getValor()
            nuevoCliente.getPeso(), // Asumiendo que Peso tiene un método getValor()
        };
        ((DefaultTableModel) ventaRegisCliente.TablaClientes.getModel()).addRow(datosCliente);
        
        
        
        // Mensaje de confirmación
        JOptionPane.showMessageDialog(ventaRegisCliente, "Cliente registrado con éxito.");
        ventaRegisCliente.dispose(); // Opcional: cerrar la ventana de registro después de registrar
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(ventaRegisCliente, "Error en los datos numéricos: " + ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(ventaRegisCliente, "Error registrando cliente: " + ex.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
    }
}


/**
     * Método para actualizar la membresía usando los datos del formulario en VentaMembre.
     */
    private void actualizarMembresia() {
        try {
            // Recoger datos de los campos de entrada en la ventana de membresías
            String nombreCliente = ventaMembresias.txtNombreCliente.getText();
            String tipoMembresia = ventaMembresias.cmbTipoMembresia.getSelectedItem().toString();
            LocalDate fechaInicio = LocalDate.parse(ventaMembresias.txtFechaInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaExpiracion = LocalDate.parse(ventaMembresias.txtFechaExpiracion.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String estado = ventaMembresias.cmbEstado.getSelectedItem().toString();

            // Crear instancia de Membresia
            Membresia membresia = new Membresia(nombreCliente, tipoMembresia, fechaInicio, fechaExpiracion, estado);
            
            // Agregar la membresía a la lista (o actualizar si ya existe)
            membresias.add(membresia); // Esto se puede cambiar según la lógica de actualización

            // Mensaje de confirmación
            JOptionPane.showMessageDialog(ventaMembresias, "Membresía actualizada con éxito.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(ventaMembresias, "Error en las fechas: " + ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ventaMembresias, "Error actualizando membresía: " + ex.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }









}
