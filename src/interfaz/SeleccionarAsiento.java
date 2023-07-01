package interfaz;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import plataforma.Viaje;

public class SeleccionarAsiento extends JPanel {
    private JButton comprarButton;
    private JComboBox<String> asientosComboBox;

    public SeleccionarAsiento(Viaje v) {
        // Configuración del panel
        setLayout(new FlowLayout());
        
        // Crear y configurar el combo box de asientos
        asientosComboBox = new JComboBox<>();
        asientosComboBox.setPreferredSize(new Dimension(100, 30));
        add(asientosComboBox);
        
        // Crear y configurar el botón de compra
        comprarButton = new JButton("Comprar");
        comprarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String asientoSeleccionado = (String) asientosComboBox.getSelectedItem();
                // Aquí puedes realizar la lógica para llevar al usuario a la compra del asiento seleccionado
                // Puedes abrir una nueva ventana, mostrar una página web, etc.
                System.out.println("Asiento seleccionado: " + asientoSeleccionado);
            }
        });
        add(comprarButton);
    }
}
