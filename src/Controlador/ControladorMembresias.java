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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ventanaMembresias.btnAgregar) {
            agregarMembresia();
        } else if (e.getSource() == ventanaMembresias.btnActualizar) {
            actualizarMembresia();
        }
    }

    private void agregarMembresia() {
        try {
            String nombreCliente = ventanaMembresias.txtNombreCliente.getText();
            String tipoMembresia = ventanaMembresias.cmbTipoMembresia.getSelectedItem().toString();
            LocalDate fechaInicio = LocalDate.parse(ventanaMembresias.txtFechaInicio.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaExpiracion = LocalDate.parse(ventanaMembresias.txtFechaExpiracion.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String estado = ventanaMembresias.cmbEstado.getSelectedItem().toString();

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

            membresias.add(membresia);

            double precio = estrategiaMembresia.calcularPrecio(membresia);
            JOptionPane.showMessageDialog(ventanaMembresias, "Membresía agregada con éxito. Precio: " + precio);

            ventanaMembresias.agregarMembresiaATabla(membresia);
            notificarObservadores(membresia);
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
}
