package co.uptc.edu.logica.control;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Abono;
import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.persistencia.DAOAbono;
import co.uptc.edu.persistencia.DAOBulto;

public class OperacionesBulto {

	public void AgregarBulto(String variacionPeso, String precioUnit) {
		Bulto bul = new Bulto();
		bul.setStock(0);
		bul.setVariacionPeso(Integer.parseInt(variacionPeso));
		bul.setPrecioUnitario(Double.parseDouble(precioUnit.replaceAll("$", "")));
		new DAOBulto().AgregarBulto(bul);
	}

	public void ModificarBulto(String precioUnitario, String variacionPeso, int indice) {
		ArrayList<Bulto> bul = new DAOBulto().ObtenerDatosBultos();
		bul.get(indice).setPrecioUnitario(Double.parseDouble(precioUnitario.replaceAll("$", "")));
		bul.get(indice).setVariacionPeso(Integer.parseInt(variacionPeso));
	
		new DAOBulto().ModificarBulto(bul);
	}
	
	public void ModificarBulto(int nuevoStock, int indice) {
		ArrayList<Bulto> bul = new DAOBulto().ObtenerDatosBultos();
		bul.get(indice).setStock(bul.get(indice).getStock()+nuevoStock);
	
		new DAOBulto().ModificarBulto(bul);
	}
	
	public void EliminarBulto(int indice) {
		ArrayList<Bulto> Bultos = new DAOBulto().ObtenerDatosBultos();
		Bultos.remove(indice);
		new DAOBulto().ModificarBulto(Bultos);
	}

	public Double CalcularBultosPosibles(Bulto bultoATrabajar) {
		ArrayList<Abono> abono = new DAOAbono().ObtenerDatosAbono();
		int cantAbono = abono.get(0).getCantidad();
		int variacion = bultoATrabajar.getVariacionPeso();
		Double BultosPosibles = (double) (cantAbono/variacion);
		return BultosPosibles;
	}
	
	public Bulto obtenerBulto(String variacionPeso) {
		Bulto bul = new Bulto();
		ArrayList<Bulto> bultos = new DAOBulto().ObtenerDatosBultos();
		for(Bulto bulto : bultos) {
			if(bulto.getVariacionPeso() == Integer.parseInt(variacionPeso)) {
				System.out.println(bulto.getPrecioUnitario());
				return bulto;
			}
		}
		return bul;
	}

}
