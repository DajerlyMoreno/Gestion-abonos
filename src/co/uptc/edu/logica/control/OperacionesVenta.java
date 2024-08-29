package co.uptc.edu.logica.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.logica.modelo.Cliente;
import co.uptc.edu.logica.modelo.Factura;
import co.uptc.edu.persistencia.DAOBulto;
import co.uptc.edu.persistencia.DAOclientes;
import co.uptc.edu.persistencia.DAOfactura;
import co.uptc.edu.persistencia.utilidades.Archivo;

public class OperacionesVenta {
	private final static String RUTAcli = "Clientes.cli";

	public ArrayList<String> obtenerNumTelefonicos() {
		ArrayList<String> numTelefonicos = new ArrayList<String>();
		// ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTAcli);
		ArrayList<Cliente> Datos = new DAOclientes().ObtenerClientes();

		for (int i = 0; i < Datos.size(); i++) {
			if (Datos.get(i) != null) {
				// String[] fila = Datos.get(i).split(",");
				numTelefonicos.add(Datos.get(i).getTelefono());

			}
		}

		return numTelefonicos;
	}

	private final static String RUTAbul = "Bultos.efu";

	public ArrayList<String> ObtenerVariacion() {

		ArrayList<String> variacion = new ArrayList<String>();
		// ArrayList<String> Datos = new Archivo().GetInfoContenido(RUTAbul);
		ArrayList<Bulto> Datos = new DAOBulto().ObtenerDatosBultos();
		// ArrayList<String> Datos = new DAOclientes().ObtenerClientes().toString() ;

		for (int i = 0; i < Datos.size(); i++) {
			if (Datos.get(i) != null) {
				// String[] fila = Datos.get(i).split(",");
				variacion.add(Integer.toString(Datos.get(i).getVariacionPeso()));
			}
		}
		return variacion;

	}
	
	
	
	public void AgregarVenta(Cliente clien, ArrayList<Bulto> bultos) {
		Factura fact = new Factura();
		Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoFecha.format(fecha);
    	fact.setBultosAsociados(bultos);
		fact.setCliente(clien);
		fact.setFecha(fechaFormateada);
		fact.setId(new DAOfactura().ObtenerCantidadFacturas()+1);
		fact.setValorTotal(CalcularValorFactura(bultos));
		
		new DAOfactura().agregarFactura(fact);
	}

	private double CalcularValorFactura(ArrayList<Bulto> bultos) {
		double total=0.0;
		for (int i = 0; i < bultos.size(); i++) {
			total= total+bultos.get(i).getPrecioUnitario();
		}
		return total;
	}

}