package interfaz;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import plataforma.Viaje;

import java.time.LocalTime;
import java.util.ArrayList;

public class ViajesTabla extends JTable {
    private DefaultTableModel model;

    public ViajesTabla(ArrayList<Viaje> viajes) {
        model = new DefaultTableModel();
        model.addColumn("Empresa");
        model.addColumn("Hora de Llegada");
        model.addColumn("Hora de Salida");
        model.addColumn("Precio");
        model.addColumn("Recorridos Intermedios");

        actualizar(viajes);

        setModel(model);
        setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        getColumnModel().getColumn(0).setPreferredWidth(150);
        getColumnModel().getColumn(1).setPreferredWidth(150);
        getColumnModel().getColumn(2).setPreferredWidth(150);
        getColumnModel().getColumn(3).setPreferredWidth(150);
        getColumnModel().getColumn(4).setPreferredWidth(400);
    }

    public void actualizar(ArrayList<Viaje> viajes) {
        // Clear the existing table data
        model.setRowCount(0);

        // Add the new data to the table
        for (Viaje viaje : viajes) {
			//System.out.println(viaje);
            String empresa = viaje.getOmnibus().getNombreEmpresa();
            String horaLlegada = viaje.getLlegada().toString();
            String horaSalida = viaje.getSalida().toString();
            double precio = viaje.getPrecio();
            String recorridosIntermedios = viaje.getRecorridosIntermedios().toString();

            model.addRow(new Object[]{empresa, horaLlegada, horaSalida, precio, recorridosIntermedios});
        }
    }
}
