package co.uptc.edu.persistencia;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.logica.modelo.ResiduosOrganicos;
import co.uptc.edu.persistencia.utilidades.Archivo;

public class DAOResiduosOrganicos {

	private final static String RUTA = "ResiduosOrganicos.efu";

	public ArrayList<ResiduosOrganicos> ObtenerResiduosOrganicos() {

		ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTA);
		ArrayList<ResiduosOrganicos> ListaResiduosOrganicos = new ArrayList<ResiduosOrganicos>();

		ResiduosOrganicos r = new ResiduosOrganicos();
		String[] fila = Datos.get(1).split(", ");

		r.setId(Integer.parseInt(fila[0]));
		r.setNombre(fila[1]);
		r.setVariacion(fila[3]);
		r.setCantidadResiduosDespreciables(Integer.parseInt(fila[4]));
		r.setCantidad(Double.parseDouble(fila[2]) - r.getCantidadResiduosDespreciables());
		ListaResiduosOrganicos.add(r);

		return ListaResiduosOrganicos;
	}

	public void ModificarResiduosOrganicos(ArrayList<ResiduosOrganicos> res) {
		String contenido = "ID,NOMBRE,CANTIDAD,VARIACION,RESIDUOSDESPRESIABLES";
		for (int i = 0; i < res.size(); i++) {
			contenido += "\n" + res.get(i).getId() + ", " + res.get(i).getNombre() + ", " + res.get(i).getCantidad()
					+ ", " + res.get(i).getVariacion() + ", "+res.get(i).getCantidadResiduosDespreciables();
		}
		new Archivo().SobreEscribirArchivo("Recursos/" + RUTA, contenido);

	}
}
