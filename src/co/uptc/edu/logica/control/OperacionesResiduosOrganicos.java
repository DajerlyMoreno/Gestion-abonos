package co.uptc.edu.logica.control;

import java.util.ArrayList;

import co.uptc.edu.logica.modelo.ResiduosOrganicos;
import co.uptc.edu.persistencia.DAOResiduosOrganicos;


public class OperacionesResiduosOrganicos {

	public void AgregarMateriaOrganica(Double cantidadEntrante, int residuosDesprciables) {
		ArrayList<ResiduosOrganicos> res = new DAOResiduosOrganicos().ObtenerResiduosOrganicos();
		res.get(0).setCantidad(res.get(0).getCantidad()+cantidadEntrante);
		
		new DAOResiduosOrganicos().ModificarResiduosOrganicos(res);
		
	}
	
	
	public boolean ValidarDatos(Double cantidadEntrante, int residuosDesprciables) {
		if (residuosDesprciables<=cantidadEntrante) {
			return true;
		}else {
			return false;
		}
		
	}

	

}
