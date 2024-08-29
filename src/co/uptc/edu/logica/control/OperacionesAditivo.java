package co.uptc.edu.logica.control;

import java.util.ArrayList;
import java.util.Iterator;

import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.persistencia.DAOAditivos;

public class OperacionesAditivo {

	/**
	 * Se encarga de organizara los datos de un nuevo aditivo para que se guarden en
	 * el archivo plano de aditivos
	 * 
	 * @param Nombre El nombre del nuevo aditivo
	 * @param PorcConcentr El porcentaje de concentración del nuevo aditivo
	 * @param LitroXKilo Los Litros por Kg a fermentar del nuevo aditivo
	 */
	public void AgregarAditivo(String Nombre, String PorcConcentr, String LitroXKilo) {
		Aditivo ad = new Aditivo();
		ad.setId(new DAOAditivos().ObtenerTamano() + 1);
		ad.setNombre(Nombre);
		ad.setPorcentajeConcentracion(Integer.parseInt(PorcConcentr.replaceFirst("%", "")));
		ad.setNumLitrosPorKg(Integer.parseInt(LitroXKilo));
		ad.setVariacion("L");
		ad.setCantidad(0.0);
		new DAOAditivos().AgregarAditivo(ad);
	}

	/**
	 * Modifica los datos de un aditivo existente en el archivo plano de aditivos
	 * @param nombre
	 * @param litroXKilo
	 * @param porcConc
	 * @param indice
	 */
	public void ModificarAditivo(String nombre, String litroXKilo, String porcConc, int indice) {
		ArrayList<Aditivo> adit = new DAOAditivos().ObtenerDatosAditivos();
		adit.get(indice).setNombre(nombre);
		adit.get(indice).setNumLitrosPorKg(Integer.parseInt(litroXKilo));
		adit.get(indice).setPorcentajeConcentracion(Integer.parseInt(porcConc.replaceAll("%", "")));

		new DAOAditivos().ModificarAditivo(adit);
	}

	public void EliminarAditivo(int indice) {
		ArrayList<Aditivo> Aditivos = new DAOAditivos().ObtenerDatosAditivos();
		Aditivos.remove(indice);
		new DAOAditivos().ModificarAditivo(Aditivos);
	}

	public void AnadirAditivo(Double cantidadEntrante, int Indice) {
		ArrayList<Aditivo> adit = new DAOAditivos().ObtenerDatosAditivos();
		adit.get(Indice).setCantidad(cantidadEntrante);

		new DAOAditivos().ModificarAditivo(adit);
	}

	public boolean BuscarAditivo(String nombreAditivo) {
		ArrayList<Aditivo> Aditivos = new DAOAditivos().ObtenerDatosAditivos();
		for (int i = 0; i < Aditivos.size(); i++) {
			if (Aditivos.get(i).getNombre().equals(nombreAditivo)) {
				return true;
			}
		}
		return false;
	}

	public Aditivo TraerAditivo(String nombreAditivo) {
		ArrayList<Aditivo> Aditivos = new DAOAditivos().ObtenerDatosAditivos();
		Aditivo ad = new Aditivo();
		for (int i = 0; i < Aditivos.size(); i++) {
			if (Aditivos.get(i).getNombre().equals(nombreAditivo)) {
				ad = Aditivos.get(i);
				return ad;
			}
		}
		return ad;
	}

	public Aditivo DevolverAditivo(String nombreAditivo) {
		ArrayList<Aditivo> Aditivos = new DAOAditivos().ObtenerDatosAditivos();
		Aditivo ad = new Aditivo();
		for (int i = 0; i < Aditivos.size(); i++) {
			if (Aditivos.get(i).getNombre().equals(nombreAditivo)) {
				ad = Aditivos.get(i);
			}
		}
		return ad;
	}
}
