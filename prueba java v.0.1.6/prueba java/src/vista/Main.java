package vista;

import controlador.Control;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Control control = new Control();
        
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(control);
            ventana.setVisible(true);
        });
    }
}
