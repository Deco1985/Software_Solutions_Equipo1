package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import modelo.Membresia;

public class VentanaMembresias extends JFrame {

    public JTextField txtNombreCliente;
    public JComboBox<String> cmbTipoMembresia;
    public JTextField txtFechaInicio;
    public JTextField txtFechaExpiracion;
    public JComboBox<String> cmbEstado;
    public JButton btnActualizar;
    public JButton btnAgregar;
    public JTable tablaMembresias;
    private DefaultTableModel modeloTabla;

    private static final Map<String, Integer> duracionesMembresias;

    static {
        duracionesMembresias = new HashMap<>();
        duracionesMembresias.put("Día", 1);
        duracionesMembresias.put("Semana", 7);
        duracionesMembresias.put("Mes", 30);
    }

    public VentanaMembresias() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Membresías");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(7, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNombreCliente = new JLabel("Nombre del Cliente:");
        txtNombreCliente = new JTextField();

        JLabel lblTipoMembresia = new JLabel("Tipo de Membresía:");
        cmbTipoMembresia = new JComboBox<>(new String[]{"Día", "Semana", "Mes", "Personalizada"});
        cmbTipoMembresia.addActionListener(e -> actualizarFechaExpiracion());

        JLabel lblFechaInicio = new JLabel("Fecha de Inicio (dd/MM/yyyy):");
        txtFechaInicio = new JTextField();
        txtFechaInicio.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        txtFechaInicio.setEditable(false);

        JLabel lblFechaExpiracion = new JLabel("Fecha de Expiración (dd/MM/yyyy):");
        txtFechaExpiracion = new JTextField();
        txtFechaExpiracion.setEditable(false);

        JLabel lblEstado = new JLabel("Estado:");
        cmbEstado = new JComboBox<>(new String[]{"Activa", "Expirada"});

        btnActualizar = new JButton("Actualizar Membresía");
        btnAgregar = new JButton("Agregar Membresía");

        panelFormulario.add(lblNombreCliente);
        panelFormulario.add(txtNombreCliente);

        panelFormulario.add(lblTipoMembresia);
        panelFormulario.add(cmbTipoMembresia);

        panelFormulario.add(lblFechaInicio);
        panelFormulario.add(txtFechaInicio);

        panelFormulario.add(lblFechaExpiracion);
        panelFormulario.add(txtFechaExpiracion);

        panelFormulario.add(lblEstado);
        panelFormulario.add(cmbEstado);

        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnActualizar);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre del Cliente");
        modeloTabla.addColumn("Tipo de Membresía");
        modeloTabla.addColumn("Fecha de Inicio");
        modeloTabla.addColumn("Fecha de Expiración");
        modeloTabla.addColumn("Estado");

        tablaMembresias = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMembresias);

        panel.add(panelFormulario, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    private void actualizarFechaExpiracion() {
        String tipo = (String) cmbTipoMembresia.getSelectedItem();
        if (tipo != null) {
            if (!tipo.equals("Personalizada")) {
                int dias = duracionesMembresias.getOrDefault(tipo, 0);
                LocalDate fechaInicio = LocalDate.now();
                LocalDate fechaExpiracion = fechaInicio.plusDays(dias);
                txtFechaExpiracion.setText(fechaExpiracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                txtFechaExpiracion.setEditable(false);
            } else {
                txtFechaExpiracion.setText("");
                txtFechaExpiracion.setEditable(true);
            }
        }
    }

    public void agregarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
        btnAgregar.addActionListener(listener);
    }

    public void agregarMembresiaATabla(Membresia membresia) {
        modeloTabla.addRow(new Object[]{
            membresia.getNombreCliente(),
            membresia.getTipo(),
            membresia.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            membresia.getFechaExpiracion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
            membresia.getEstado()
        });
    }

    public void actualizarMembresiaEnTabla(Membresia membresia) {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 0).equals(membresia.getNombreCliente())) {
                modeloTabla.setValueAt(membresia.getTipo(), i, 1);
                modeloTabla.setValueAt(membresia.getFechaInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), i, 2);
                modeloTabla.setValueAt(membresia.getFechaExpiracion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), i, 3);
                modeloTabla.setValueAt(membresia.getEstado(), i, 4);
            }
        }
    }
}
