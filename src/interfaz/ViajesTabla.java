package interfaz;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import plataforma.Viaje;

public class ViajesTabla extends JTable {
    private DefaultTableModel model;
    HashMap<Integer, Viaje> viajes = new HashMap<Integer,Viaje>();;
    
    
    public ViajesTabla(ArrayList<Viaje> viajes) {
        model = new DefaultTableModel();
        model.addColumn("Empresa");
        model.addColumn("codOmnibus");
        model.addColumn("Origen");
        model.addColumn("Destino");
        model.addColumn("Fecha");
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
        getColumnModel().getColumn(4).setPreferredWidth(150);
        getColumnModel().getColumn(5).setPreferredWidth(150);
        getColumnModel().getColumn(6).setPreferredWidth(150);
        getColumnModel().getColumn(7).setPreferredWidth(400);
    }

    public Viaje getViaje(int numFila)
    {
    	Integer num = Integer.valueOf(numFila);
    	if (this.viajes.containsKey(num))
    		return this.viajes.get(num);
    	return null;
    }
   
    public void actualizar(ArrayList<Viaje> viajes) {
        // Clear the existing table data
        model.setRowCount(0);
        this.viajes.clear();

        // Add the new data to the table
        for (int i = 0; i < viajes.size(); i++) {
			Viaje viaje = viajes.get(i);
			this.viajes.put(Integer.valueOf(i), viaje);
            String empresa = viaje.getOmnibus().getNombreEmpresa();
            String origen = viaje.getOrigen();
            String destino = viaje.getDestino();
            String horaLlegada = viaje.getLlegada().toString();
            int codOmnibus = viaje.getOmnibus().getNum();
            String fecha = viaje.getFecha().toString();
            String horaSalida = viaje.getSalida().toString();
            double precio = viaje.getPrecio();
            String recorridosIntermedios = viaje.getRecorridosIntermedios().toString();
            model.addRow(new Object[]{empresa, codOmnibus, origen, destino, fecha, horaLlegada, horaSalida, precio, recorridosIntermedios});
        }
    }
}
