package co.uptc.edu.persistencia;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Abono;
import co.uptc.edu.persistencia.utilidades.Archivo;

public class DAOAbono {

	private final static String RUTA = "Abono.efu";

	public ArrayList<Abono> ObtenerDatosAbono() {
		ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTA);
		ArrayList<Abono> ListaAbono = new ArrayList<Abono>();

		Abono a = new Abono();
		String[] fila = Datos.get(1).split(", ");
		a.setId(Integer.parseInt(fila[0]));
		a.setCantidad(Integer.parseInt(fila[1]));
		ListaAbono.add(a);

		return ListaAbono;
	}

	public void ModificarAbono(ArrayList<Abono> abon) {
		String contenido = "ID,CANTIDAD";
		for (int i = 0; i < abon.size(); i++) {
			contenido += "\n"+abon.get(i).getId() + ", "+abon.get(i).getCantidad();
		}
		new Archivo().SobreEscribirArchivo("Recursos/" + RUTA, contenido);

	}
}
