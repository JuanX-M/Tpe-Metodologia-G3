package plataforma;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GestionadorDatos {

    public static void cargarDatos(String filePath, Plataforma p) {
        Map<String, EmpresaTransporte> mapa = new HashMap<>();

        BufferedReader lector = null;

        try {
            lector = new BufferedReader(new FileReader(filePath));
            String linea;

            // Saltar la línea de encabezado
            lector.readLine();

            while ((linea = lector.readLine()) != null) {
                // Dividir la línea utilizando la coma como separador
                String[] datos = linea.split(",");

                String origen = datos[0];
                String destino = datos[1];
                String nombreEmpresa = datos[2];
                int codOmnibus = Integer.parseInt(datos[3]);
                double precio = Double.parseDouble(datos[4]);
                LocalDate fecha = LocalDate.parse(datos[5]);
                LocalTime horaSalida = LocalTime.parse(datos[6]);
                LocalTime horaLlegada = LocalTime.parse(datos[7]);
                String[] intermedios = datos[8].split(";");

                if (!mapa.containsKey(nombreEmpresa))
                    mapa.put(nombreEmpresa, new EmpresaTransporte(nombreEmpresa));

                EmpresaTransporte empresa = mapa.get(nombreEmpresa);
                Omnibus omnibus = empresa.buscarOmnibus(codOmnibus);
                if (omnibus == null) {
                    empresa.addNewOmnibus(codOmnibus);;
                    omnibus = empresa.buscarOmnibus(codOmnibus);
                }

                Viaje viaje = new Viaje(origen, destino, fecha, precio, omnibus, horaLlegada, horaSalida);
                for (String recorrido : intermedios)
                    viaje.addRecorridoIntermedio(recorrido);

                System.out.println(viaje);
                omnibus.agregarItinerario(viaje);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Iterator<String> it = mapa.keySet().iterator();
        while (it.hasNext()) {
            String nombre = it.next();
            p.agregarEmpresa(mapa.get(nombre));
        }
    }

    public static void guardarDatos(String filePath, Plataforma p) {
        BufferedWriter escritor = null;

        try {
            escritor = new BufferedWriter(new FileWriter(filePath));

            // Escribir la línea de encabezado
            escritor.write("Origen,Destino,Nombre Empresa,Cod Omnibus,Precio,Fecha,Hora Salida,Hora Llegada,Intermedios\n");

            for (EmpresaTransporte empresa : p.getEmpresas()) {
                for (Omnibus omnibus : empresa.getOmnibus()) {
                    for (Viaje viaje : omnibus.getItinerario()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(viaje.getOrigen()).append(",");
                        sb.append(viaje.getDestino()).append(",");
                        sb.append(empresa.getNombre()).append(",");
                        sb.append(omnibus.getNum()).append(",");
                        sb.append(viaje.getPrecio()).append(",");
                        sb.append(viaje.getFecha()).append(",");
                        sb.append(viaje.getSalida()).append(",");
                        sb.append(viaje.getLlegada()).append(",");

                        StringBuilder intermedios = new StringBuilder();
                        for (String recorrido : viaje.getRecorridosIntermedios()) {
                            if (intermedios.length() > 0)
                                intermedios.append(";");
                            intermedios.append(recorrido);
                        }
                        sb.append(intermedios.toString());

                        escritor.write(sb.toString());
                        escritor.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (escritor != null) {
                    escritor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
