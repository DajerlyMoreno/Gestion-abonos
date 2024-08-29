package co.uptc.edu.persistencia;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.persistencia.utilidades.Archivo;

public class DAOBulto {
	private final static String RUTA = "Bultos.efu";
	
	public ArrayList<Bulto> ObtenerDatosBultos(){
		
		ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTA);
		ArrayList<Bulto> ListaBultos = new ArrayList<Bulto>();
		
		for (int i = 1; i < Datos.size(); i++) {
			Bulto b = new Bulto();
			String[] fila = Datos.get(i).split(", ");
			if (fila.length != 0) {
				b.setVariacionPeso(Integer.parseInt(fila[0]));
				b.setStock(Integer.parseInt(fila[1]));
				b.setPrecioUnitario(Double.parseDouble(fila[2]));
				ListaBultos.add(b);
			}
		}
		return ListaBultos;
		
	}

	public void AgregarBulto(Bulto bul) {
		String contenido = "";
		contenido = "\n"+bul.getVariacionPeso() + ", " + bul.getStock() + ", " + bul.getPrecioUnitario();
		new Archivo().AgregarContenidoArchivo(RUTA, contenido);
	
	}

	public void ModificarBulto(ArrayList<Bulto> bul) {
		String contenido = "VARIACION,STOCK,PRECIOUNITARIO";
		for (int i = 0; i < bul.size(); i++) {
			contenido += "\n"+bul.get(i).getVariacionPeso() + ", " +bul.get(i).getStock() + ", "+bul.get(i).getPrecioUnitario();
		}
		new Archivo().SobreEscribirArchivo("Recursos/" + RUTA, contenido);
		
	}
}
