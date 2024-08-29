package co.uptc.edu.persistencia;

import java.io.File;
import java.util.ArrayList;

import co.uptc.edu.logica.modelo.Bulto;
import co.uptc.edu.logica.modelo.Cliente;
import co.uptc.edu.logica.modelo.Factura;
import co.uptc.edu.persistencia.utilidades.Archivo;

public class DAOfactura {

	public Factura ObtenerFactura(Factura fact) {
		ArrayList<String> Datos = new Archivo().GetInfoContenido(
				"/facturas/" + fact.getId() + fact.getCliente().getNombre() + fact.getCliente().getTelefono());
		Factura factura = new Factura();

		factura.setId(Integer.parseInt(Datos.get(0)));
		factura.setFecha(Datos.get(1));

		String cliente[] = Datos.get(2).split(",");
		Cliente cli = new Cliente();
		cli.setIdCliente(Integer.parseInt(cliente[0]));
		cli.setNombre(cliente[1]);
		cli.setTelefono(cliente[2]);

		ArrayList<Bulto> bultosA = new ArrayList<Bulto>();
		String bultos[] = Datos.get(3).split(",");
		for (int i = 0; i < bultos.length; i++) {
			String[] detallesBulto = bultos[1].split(";");
			Bulto bulto = new Bulto();
			bulto.setVariacionPeso(Integer.parseInt(detallesBulto[0]));
			bulto.setPrecioUnitario(Double.parseDouble(detallesBulto[1]));
			bulto.setStock(Integer.parseInt(detallesBulto[2]));
			bultosA.add(bulto);
		}

		factura.setCliente(cli);
		factura.setBultosAsociados(bultosA);
		factura.setValorTotal(Double.parseDouble(Datos.get(4)));

		return factura;
	}

	public int ObtenerCantidadFacturas() {
		String carpeta = "/facturas/";
		File dir = new File(carpeta);
		int numArchivos;
		File[] archivos = dir.listFiles();
		if (archivos==null) {
			 numArchivos=1;
		}else {
			numArchivos = archivos.length;
		}
		
		return numArchivos;
	}

	public void agregarFactura(Factura fac) {
		String contenido = "";
		contenido = fac.getId() + "\n" + fac.getFecha() + "\n" + fac.getCliente().getIdCliente() + ","
				+ fac.getCliente().getNombre() + "," + fac.getCliente().getTelefono() + "\n";
		for (int i = 0; i < fac.getBultosAsociados().size(); i++) {
			contenido += fac.getBultosAsociados().get(i).getVariacionPeso() + ";"
					+ fac.getBultosAsociados().get(i).getPrecioUnitario() + ";"
					+ fac.getBultosAsociados().get(i).getStock() + ",";
		}
		contenido = contenido.substring(0, contenido.length() - 1);
		contenido += "\n" + fac.getValorTotal();
		new Archivo().SobreEscribirArchivo(
				"Recursos/facturas/" + fac.getId() + fac.getCliente().getNombre() + fac.getCliente().getTelefono(),
				contenido);

	}
}