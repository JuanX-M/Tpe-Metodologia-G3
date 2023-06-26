package plataforma;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AdminApp{

	private Plataforma p;
	
	public AdminApp(Plataforma p) {
		this.p = p;
		// TODO Auto-generated constructor stub
	}

	public void alta(Viaje viaje, int nroOmnibus, String nombreEmpresa)
	{
		EmpresaTransporte empresa = this.p.buscarEmpresa(nombreEmpresa);
		if (empresa == null)
		{
			empresa = new EmpresaTransporte(nombreEmpresa);
			this.p.agregarEmpresa(empresa);
			empresa.addNewOmnibus(nroOmnibus);
		}
		Omnibus omnibus = empresa.buscarOmnibus(nroOmnibus);
		if (omnibus == null)
		{
			empresa.addNewOmnibus(nroOmnibus);
			omnibus = empresa.buscarOmnibus(nroOmnibus);
		}	
		omnibus.agregarItinerario(viaje);
		
		this.p.escribirDatos();
		//this.p.cargarDatos();
	}
	
	public void baja(Viaje viaje)
	{
		Omnibus omnibus = viaje.getOmnibus();
		omnibus.eliminarViaje(viaje);
		assert omnibus.getItinerario().contains(viaje);
		this.p.escribirDatos();
	}
	
	
	public void actualizar()
	{
		
	}
}
