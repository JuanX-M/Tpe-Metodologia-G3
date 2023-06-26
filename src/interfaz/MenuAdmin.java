package interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import plataforma.EmpresaTransporte;
import plataforma.Omnibus;
import plataforma.Plataforma;
import plataforma.Viaje;

public class MenuAdmin extends JPanel {
    private JButton btnAgregar;
    private JButton btnMod;
    private JButton btnEliminar;
    private JButton btnSalir;
    private Vista vista;
    private Plataforma p;
    private ViajesTabla tabla;
    
    public MenuAdmin(Vista vista, Plataforma p) {
        this.vista = vista;
        this.p = p;
        
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblPlataforma = new JLabel("Plataforma 9/14");
        lblPlataforma.setFont(new Font("Tahoma", Font.BOLD, 24));
        headerPanel.add(lblPlataforma);

        JPanel centerPanel = new JPanel(new BorderLayout());
        tabla = new ViajesTabla(getViajes());
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnAgregar = new JButton("Agregar");
        btnMod = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnSalir = new JButton("Salir");

        footerPanel.add(btnAgregar);
        footerPanel.add(btnMod);
        footerPanel.add(btnEliminar);
        footerPanel.add(btnSalir);

        add(headerPanel, BorderLayout.NORTH);
        //add(centerPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Agregar listeners a los botones
        btnAgregar.addActionListener(e -> agregarViaje());
        btnMod.addActionListener(e -> actualizarDatos());
        btnEliminar.addActionListener(e -> eliminarViaje());
        btnSalir.addActionListener(e -> salir());
    }

    private ArrayList<Viaje> getViajes() {
        ArrayList<Viaje> viajes = new ArrayList<Viaje>();
        for (EmpresaTransporte empresa : p.getEmpresas())
            for (Omnibus bus : empresa.getOmnibus())
                for (Viaje viaje : bus.getItinerario())
                    viajes.add(viaje);
        return viajes;
    }

    private void agregarViaje() {
        // Aquí debes implementar la lógica para agregar un nuevo viaje a la tabla
        // Puedes mostrar un cuadro de diálogo o una ventana para que el administrador ingrese los datos del nuevo viaje
        // Luego, debes agregar el viaje a la lista y actualizar la tabla
        JOptionPane.showMessageDialog(this, "Funcionalidad de agregar viaje no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void actualizarDatos() {
        // Aquí debes implementar la lógica para modificar un viaje seleccionado de la tabla
        // Obtén el viaje seleccionado de la tabla (puedes usar tabla.getSelectedViaje())
        // Luego, puedes mostrar un cuadro de diálogo o una ventana para que el administrador modifique los datos del viaje
        // Finalmente, actualiza la tabla
        JOptionPane.showMessageDialog(this, "Funcionalidad de modificar viaje no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarViaje() {
        // Aquí debes implementar la lógica para eliminar un viaje seleccionado de la tabla
        // Obtén el viaje seleccionado de la tabla (puedes usar tabla.getSelectedViaje())
        // Luego, elimina el viaje de la lista y actualiza la tabla
        JOptionPane.showMessageDialog(this, "Funcionalidad de eliminar viaje no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salir() {
        // Aquí puedes implementar la lógica para salir del menú de administrador
        // Por ejemplo, puedes volver a la pantalla de inicio de sesión o cerrar la aplicación
        JOptionPane.showMessageDialog(this, "Funcionalidad de salir no implementada.", "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
