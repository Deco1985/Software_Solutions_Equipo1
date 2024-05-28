package controlador;

import modelo.*;
import vista.VentanaMembresias;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorMembresias extends Sujeto implements ActionListener {
    private VentanaMembresias ventanaMembresias;
    private List<Membresia> membresias;
    private EstrategiaMembresia estrategiaMembresia;

    public ControladorMembresias(VentanaMembresias ventanaMembresias) {
        this.ventanaMembresias = ventanaMembresias;
        this.membresias = new ArrayList<>();
        this.estrategiaMembresia = new EstrategiaPrecio();

        this.ventanaMembresias.agregarListener(this);
        this.ventanaMembresias.agregarTipoMembresiaListener(e -> llenarFechasAutomaticamente());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaMembresias.btnAgregar) {
            agregarMembresia();
        } else if (e.getSource() == ventanaMembresias.btnActualizar) {
            actualizarMembresia();
        } else if (e.getSource() == ventanaMembresias.btnRegistrarNueva) {
            registrarNuevaMembresia();
        }
    }

    private void agregarMembresia() {
    try {
        // Obtener los datos de la interfaz de usuario
        String nombreCliente = ventanaMembresias.txtNombreCliente.getText();
        String tipoMembresia = ventanaMembresias.cmbTipoMembresia.getSelectedItem().toString();
        LocalDate fechaInicio = LocalDate.parse(ventanaMembresias.txtFechaInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fechaExpiracion = LocalDate.parse(ventanaMembresias.txtFechaExpiracion.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String estado = ventanaMembresias.cmbEstado.getSelectedItem().toString();

        // Crear la membresía
        Membresia membresia;
        if ("Personalizada".equals(tipoMembresia)) {
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la membresía personalizada:"));
            membresia = FabricaMembresias.crearMembresia(tipoMembresia, precio);
        } else {
            membresia = FabricaMembresias.crearMembresia(tipoMembresia);
        }
        membresia.setNombreCliente(nombreCliente);
        membresia.setFechaInicio(fechaInicio);
        membresia.setFechaExpiracion(fechaExpiracion);
        membresia.setEstado(estado);
        membresia.setTipo(tipoMembresia);

        // Agregar la membresía a la lista y a la tabla
        membresias.add(membresia);
        double precio = estrategiaMembresia.calcularPrecio(membresia);
        ventanaMembresias.agregarMembresiaATabla(membresia, precio);
        notificarObservadores(membresia);
        
        JOptionPane.showMessageDialog(ventanaMembresias, "Membresía agregada con éxito. Precio: " + precio);
    } catch (DateTimeParseException ex) {
        JOptionPane.showMessageDialog(ventanaMembresias, "Error en las fechas: " + ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(ventanaMembresias, "Error agregando membresía: " + ex.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
    }
}


    private void actualizarMembresia() {
        try {
            String nombreCliente = ventanaMembresias.txtNombreCliente.getText();
            String tipoMembresia = ventanaMembresias.cmbTipoMembresia.getSelectedItem().toString();
            LocalDate fechaInicio = LocalDate.parse(ventanaMembresias.txtFechaInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaExpiracion = LocalDate.parse(ventanaMembresias.txtFechaExpiracion.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String estado = ventanaMembresias.cmbEstado.getSelectedItem().toString();

            for (Membresia membresia : membresias) {
                if (membresia.getNombreCliente().equals(nombreCliente)) {
                    membresia.setTipo(tipoMembresia);
                    membresia.setFechaInicio(fechaInicio);
                    membresia.setFechaExpiracion(fechaExpiracion);
                    membresia.setEstado(estado);

                    double precio = estrategiaMembresia.calcularPrecio(membresia);
                    JOptionPane.showMessageDialog(ventanaMembresias, "Membresía actualizada con éxito. Precio: " + precio);

                    ventanaMembresias.actualizarMembresiaEnTabla(membresia);
                    notificarObservadores(membresia);
                    return;
                }
            }

            JOptionPane.showMessageDialog(ventanaMembresias, "Membresía no encontrada para actualizar.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(ventanaMembresias, "Error en las fechas: " + ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ventanaMembresias, "Error actualizando membresía: " + ex.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void registrarNuevaMembresia() {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del nuevo tipo de membresía:");
            String fechaInicioStr = JOptionPane.showInputDialog("Ingrese la fecha de inicio (dd/MM/yyyy):");
            String fechaExpiracionStr = JOptionPane.showInputDialog("Ingrese la fecha de expiración (dd/MM/yyyy):");
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la membresía:"));

            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaExpiracion = LocalDate.parse(fechaExpiracionStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Registrar un nuevo tipo de membresía
            FabricaMembresias.registrarMembresia(nombre, MembresiaPersonalizada.class);
            ventanaMembresias.cmbTipoMembresia.addItem(nombre);

            // Crear la nueva membresía personalizada con los detalles proporcionados
            Membresia nuevaMembresia = new MembresiaPersonalizada(precio);
            nuevaMembresia.setTipo(nombre);
            nuevaMembresia.setFechaInicio(fechaInicio);
            nuevaMembresia.setFechaExpiracion(fechaExpiracion);
            nuevaMembresia.setPrecio(precio);

            JOptionPane.showMessageDialog(ventanaMembresias, "Nuevo tipo de membresía registrado con éxito.");
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(ventanaMembresias, "Error en las fechas: " + ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventanaMembresias, "Error en el precio: " + ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ventanaMembresias, "Error registrando nueva membresía: " + ex.getMessage(), "Error General", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarFechasAutomaticamente() {
        String tipoMembresia = ventanaMembresias.cmbTipoMembresia.getSelectedItem().toString();
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaExpiracion;

        switch (tipoMembresia) {
            case "Día":
                fechaExpiracion = fechaInicio.plusDays(1);
                break;
            case "Semana":
                fechaExpiracion = fechaInicio.plusWeeks(1);
                break;
            case "Mes":
                fechaExpiracion = fechaInicio.plusMonths(1);
                break;
            default:
                ventanaMembresias.txtFechaInicio.setEditable(true);
                ventanaMembresias.txtFechaExpiracion.setEditable(true);
                return;
        }

        ventanaMembresias.txtFechaInicio.setText(fechaInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        ventanaMembresias.txtFechaExpiracion.setText(fechaExpiracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        ventanaMembresias.txtFechaInicio.setEditable(false);
        ventanaMembresias.txtFechaExpiracion.setEditable(false);
    }
}
