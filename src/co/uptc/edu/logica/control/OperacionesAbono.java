package co.uptc.edu.logica.control;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Abono;
import co.uptc.edu.logica.modelo.Aditivo;
import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.logica.modelo.ResiduosOrganicos;
import co.uptc.edu.persistencia.DAOAbono;
import co.uptc.edu.persistencia.DAOAditivos;
import co.uptc.edu.persistencia.DAOResiduosOrganicos;

public class OperacionesAbono {

	/**
	 * Calcula los Kg de abono que se pueden producir en base a la cantidad de
	 * materia orgánica disponible en stock, la cantidad del aditivo que se
	 * seleccionó para fermentar y la capacidad de Litros por kg para fermentar del
	 * aditivo
	 * 
	 * @param aditivoATrabajar        El aditivo que se selecciono para fermentar la
	 *                                materia orgánica
	 * @param cantidadMateriaOrganica El stock de materia orgánica en el sitema
	 * @return Retorna un entero con la cantidad Kg de abono que se pueden producir
	 */
	public double CalcularKiloPosibles(Aditivo aditivoATrabajar, double cantidadMateriaOrganica) {
		int litrosPorKg = aditivoATrabajar.getNumLitrosPorKg();
		double litrosAditDisponibles = aditivoATrabajar.getCantidad();
		double kilosPosibles = (litrosAditDisponibles / litrosPorKg);
		if (kilosPosibles > cantidadMateriaOrganica) {
			kilosPosibles = cantidadMateriaOrganica;
		}
		return kilosPosibles;
	}

	/**
	 * Valida que la cantidad que abono que se desea crear no supere a la cantidad
	 * de Kg posibles
	 * 
	 * @param abonoACrear          La cantidad de abono que se desea crear
	 * @param calcularKiloPosibles Los Kg de abono permitidos
	 * @return Si la canditdad de abono que digito el usuario supera a los Kg
	 *         posibles calculados por el sistema retorna false de lo contrario
	 *         retorna true
	 */
	public boolean ValidarCantidad(int abonoACrear, double calcularKiloPosibles) {
		if (abonoACrear > calcularKiloPosibles) {
			return false;
		}
		return true;
	}

	/**
	 * Actualiza el stock de abono, del aditivo seleccionado y el de materia
	 * organica en base a la cantidad de abono que se produjo
	 * 
	 * @param CantidadEntrante Es la cantidad de abono que se produjo y que se
	 *                         añadirá al stock de abono
	 * @param AditivoATrabajar Es el aditivo que se usó para producir el abono y que
	 *                         tambien se actualizará su stock
	 */
	public void AgregarAbono(int CantidadEntrante, Aditivo AditivoATrabajar) {
		int residuosOrgARestar = CantidadEntrante;
		int abonoASumar = CantidadEntrante;
		int aditivoARestar = CantidadEntrante * AditivoATrabajar.getNumLitrosPorKg();

		ArrayList<Aditivo> aditivos = new DAOAditivos().ObtenerDatosAditivos();
		for (int i = 0; i < aditivos.size(); i++) {
			if (aditivos.get(i).getNombre().equals(AditivoATrabajar.getNombre())) {
				aditivos.get(i).setCantidad(aditivos.get(i).getCantidad() - aditivoARestar);
			}
		}

		ArrayList<Abono> abon = new DAOAbono().ObtenerDatosAbono();
		abon.get(0).setCantidad(abon.get(0).getCantidad() + abonoASumar);

		ArrayList<ResiduosOrganicos> res = new DAOResiduosOrganicos().ObtenerResiduosOrganicos();
		res.get(0).setCantidad(res.get(0).getCantidad() - residuosOrgARestar);

		new DAOAditivos().ModificarAditivo(aditivos);
		new DAOResiduosOrganicos().ModificarResiduosOrganicos(res);
		new DAOAbono().ModificarAbono(abon);
	}

	/**
	 * Se encarga de restar el stock de abono en base al tipo de bulto que se
	 * produjo y su cantidad
	 * 
	 * @param bultoATrabajar Es el bulto que se seleccionó para agregarle stock
	 *                       ademas se conoce la capacidad del bulto para saber
	 *                       cuanto abono restar
	 * @param cantidadBultos Es la cantidad de bultos que se van a crear
	 */
	public void QuitarAbono(Bulto bultoATrabajar, int cantidadBultos) {
		int cantidadARestar = this.CalcularAbonoARestar(bultoATrabajar, cantidadBultos);
		ArrayList<Abono> abon = new DAOAbono().ObtenerDatosAbono();
		abon.get(0).setCantidad(abon.get(0).getCantidad() - cantidadARestar);
		new DAOAbono().ModificarAbono(abon);
	}

	/**
	 * Calcula la cantidad de abono que se va a restar del stock en base a la
	 * capacidad del bulto y a la cantidad de bultos que se produjeron
	 * 
	 * @param bultoATrabajar Es el objeto bulto de donde se va a sacar la capacidad
	 *                       del mismo
	 * @param cantidadBultos Es la cantidad de vultos que se va a producir
	 * @return Retorna un entero que representa la cantidad que se debe restar al
	 *         stock del mismo
	 */
	private int CalcularAbonoARestar(Bulto bultoATrabajar, int cantidadBultos) {
		int res = bultoATrabajar.getVariacionPeso() * cantidadBultos;
		return res;
	}

}
