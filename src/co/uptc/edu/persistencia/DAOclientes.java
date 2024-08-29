package co.uptc.edu.persistencia;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Cliente;
import co.uptc.edu.persistencia.utilidades.Archivo;

public class DAOclientes {

	private final static String RUTA = "Clientes.cli";

	public ArrayList<Cliente> ObtenerClientes() {
		ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTA);
		ArrayList<Cliente> ListaClientes = new ArrayList<Cliente>();

		for (int i = 1; i < Datos.size(); i++) {
			if(Datos.get(i)!= null) {
				String[] fila = Datos.get(i).split(",");
				Cliente p = new Cliente();
				p.setIdCliente(Integer.parseInt(fila[0]));
				p.setNombre(fila[1]);
				p.setTelefono(fila[2]);
				p.setNumeroVentas(Integer.parseInt(fila[3]));
				ListaClientes.add(p);
			}
		}
		return ListaClientes;
	}
	
	public void modificarCliente(ArrayList<Cliente> clientes) {
		String contenido = "";
		for(int i = 0; i < clientes.size(); i ++) {
			contenido += "\n"+clientes.get(i).getIdCliente()+","+clientes.get(i).getNombre()+","+
					clientes.get(i).getTelefono()+","+clientes.get(i).getNumeroVentas();
		}
		new Archivo().SobreEscribirArchivo("Recursos/" + RUTA, contenido);
	}
	
	public void eliminarCliente(ArrayList<Cliente> clientes) {
		String contenido = "";
		for(int i = 0; i < clientes.size(); i ++) {
				contenido += "\n"+clientes.get(i).getIdCliente()+","+clientes.get(i).getNombre()+","+
						clientes.get(i).getTelefono()+","+clientes.get(i).getNumeroVentas();
			
			
		}
		new Archivo().SobreEscribirArchivo("Recursos/" + RUTA, contenido);
	}
	
	public void agregarCliente(Cliente cli) {
		String contenido = "";
		contenido = "\n"+cli.getIdCliente() + "," + cli.getNombre() +","+ cli.getTelefono() +","+ cli.getNumeroVentas();
		new Archivo().AgregarContenidoArchivo(RUTA,contenido);
	}
}