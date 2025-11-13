package vista;

import controlador.Control;
import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private Control control;
    private JButton btnRegistrar = new JButton("Registrar Producto");
    private JButton btnVender = new JButton("Vender Producto");
    private JButton btnVerInventario = new JButton("Ver Inventario");
    private JButton btnStockCritico = new JButton("Ver Stock Crítico");

    public VentanaPrincipal(Control control) {
        this.control = control;

        setTitle("Sistema de Inventario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnVender);
        buttonPanel.add(btnVerInventario);
        buttonPanel.add(btnStockCritico);

        panel.add(buttonPanel, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> control.registrarProducto());
        btnVender.addActionListener(e -> control.venderProducto());
        btnVerInventario.addActionListener(e -> control.mostrarInventario());
        btnStockCritico.addActionListener(e -> control.mostrarStockCritico());

        add(panel);
    }
}
