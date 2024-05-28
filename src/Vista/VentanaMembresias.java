package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import javax.swing.table.DefaultTableModel;
import modelo.Membresia;

public class VentanaMembresias extends JFrame {

    public JTextField txtNombreCliente;
    public JComboBox<String> cmbTipoMembresia;
    public JTextField txtFechaInicio;
    public JTextField txtFechaExpiracion;
    public JComboBox<String> cmbEstado;
    public JButton btnActualizar;
    public JButton btnAgregar;
    public JButton btnRegistrarNueva;
    private JTable tablaMembresias;
    private DefaultTableModel modeloTabla;

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
        panel.setLayout(new GridLayout(8, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNombreCliente = new JLabel("Nombre del Cliente:");
        txtNombreCliente = new JTextField();

        JLabel lblTipoMembresia = new JLabel("Tipo de Membresía:");
        cmbTipoMembresia = new JComboBox<>(new String[]{"Día", "Semana", "Mes", "Personalizada"});

        JLabel lblFechaInicio = new JLabel("Fecha de Inicio (dd/MM/yyyy):");
        txtFechaInicio = new JTextField();

        JLabel lblFechaExpiracion = new JLabel("Fecha de Expiración (dd/MM/yyyy):");
        txtFechaExpiracion = new JTextField();

        JLabel lblEstado = new JLabel("Estado:");
        cmbEstado = new JComboBox<>(new String[]{"Activa", "Expirada"});

        btnActualizar = new JButton("Actualizar Membresía");
        btnAgregar = new JButton("Agregar Membresía");
        btnRegistrarNueva = new JButton("Registrar Nueva Membresía");

        panel.add(lblNombreCliente);
        panel.add(txtNombreCliente);

        panel.add(lblTipoMembresia);
        panel.add(cmbTipoMembresia);

        panel.add(lblFechaInicio);
        panel.add(txtFechaInicio);

        panel.add(lblFechaExpiracion);
        panel.add(txtFechaExpiracion);

        panel.add(lblEstado);
        panel.add(cmbEstado);

        panel.add(btnAgregar);
        panel.add(btnActualizar);
        panel.add(btnRegistrarNueva);

        add(panel, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cliente");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Fecha Inicio");
        modeloTabla.addColumn("Fecha Expiración");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Precio");

        tablaMembresias = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMembresias);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void agregarListener(ActionListener listener) {
        btnActualizar.addActionListener(listener);
        btnAgregar.addActionListener(listener);
        btnRegistrarNueva.addActionListener(listener);
    }

    public void agregarTipoMembresiaListener(ItemListener listener) {
        cmbTipoMembresia.addItemListener(listener);
    }

  public void agregarMembresiaATabla(Membresia membresia, double precio) {
    modeloTabla.addRow(new Object[]{
        membresia.getNombreCliente(),
        membresia.getTipo(),
        membresia.getFechaInicio().toString(),
        membresia.getFechaExpiracion().toString(),
        membresia.getEstado(),
        precio  // Mostrar el precio en la tabla
    });
}


    public void actualizarMembresiaEnTabla(Membresia membresia) {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (modeloTabla.getValueAt(i, 0).equals(membresia.getNombreCliente())) {
                modeloTabla.setValueAt(membresia.getTipo(), i, 1);
                modeloTabla.setValueAt(membresia.getFechaInicio().toString(), i, 2);
                modeloTabla.setValueAt(membresia.getFechaExpiracion().toString(), i, 3);
                modeloTabla.setValueAt(membresia.getEstado(), i, 4);
                modeloTabla.setValueAt(membresia.getPrecio(), i, 5);
            }
        }
    }
}
