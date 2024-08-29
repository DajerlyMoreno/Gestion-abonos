package co.uptc.edu.persistencia;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.persistencia.utilidades.Archivo;

public class DAOAditivos {

	private final static String RUTA = "Aditivos.efu";

	public ArrayList<Aditivo> ObtenerDatosAditivos() {

		ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTA);
		ArrayList<Aditivo> ListaAditivos = new ArrayList<Aditivo>();

		for (int i = 1; i < Datos.size(); i++) {
			Aditivo a = new Aditivo();
			String[] fila = Datos.get(i).split(", ");
			if (fila.length != 0) {
				a.setId(Integer.parseInt(fila[0]));
				a.setNombre(fila[1]);
				a.setVariacion(fila[2]);
				a.setPorcentajeConcentracion(Integer.parseInt(fila[3].replaceAll("%", "")));
				a.setNumLitrosPorKg(Integer.parseInt(fila[4]));
				a.setCantidad(Double.parseDouble(fila[5]));

				ListaAditivos.add(a);
			}
		}
		return ListaAditivos;

	}

	public int ObtenerTamano() {
		ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTA);
		int aux = 0;
		for (int i = 1; i < Datos.size(); i++) {

			aux++;
		}
		return aux;
	}

	public void AgregarAditivo(Aditivo ad) {
		String contenido = "";
		contenido ="\n"+ad.getId() + ", " + ad.getNombre() + ", " + ad.getVariacion() + ", "
				+ ad.getPorcentajeConcentracion() + "%" + ", " + ad.getNumLitrosPorKg() + ", " + ad.getCantidad();
		new Archivo().AgregarContenidoArchivo(RUTA, contenido);
	}

	public void ModificarAditivo(ArrayList<Aditivo> adit) {
		String contenido = "ID,NOMBRE,VARIACION,PORCENTAJECONCENTRACION,NUMLITROSPORKILO,CANTIDAD";
		for (int i = 0; i < adit.size(); i++) {
			contenido += "\n"+adit.get(i).getId() + ", " + adit.get(i).getNombre() + ", " + adit.get(i).getVariacion() + ", "
					+ adit.get(i).getPorcentajeConcentracion() + "%, " + adit.get(i).getNumLitrosPorKg() + ", "
					+ adit.get(i).getCantidad();
		}
		new Archivo().SobreEscribirArchivo("Recursos/" + RUTA, contenido);

	}

	

}
