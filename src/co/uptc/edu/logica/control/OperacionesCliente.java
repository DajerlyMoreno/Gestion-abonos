package co.uptc.edu.logica.control;

import java.util.ArrayList;
import java.util.Random;

import co.uptc.edu.logica.modelo.Cliente;
import co.uptc.edu.persistencia.DAOclientes;

public class OperacionesCliente {
	public void agregarCliente(String nombre, String telefono) {
		
		Random rd = new Random();
		Cliente cli = new Cliente();
		cli.setIdCliente(rd.nextInt(10000,99999));
		cli.setNombre(nombre);
		cli.setTelefono(telefono);
		cli.setNumeroVentas(0);
		new DAOclientes().agregarCliente(cli);
		
	}

	public void editarCliente(String nombre, String telefono, int indice) {
		ArrayList<Cliente> clientes = new DAOclientes().ObtenerClientes();
		clientes.get(indice).setNombre(nombre);
		clientes.get(indice).setTelefono(telefono);
		
		new DAOclientes().modificarCliente(clientes);
	}
	
	public void eliminarCliente(int indice) {
		ArrayList<Cliente> clientes = new DAOclientes().ObtenerClientes();
		clientes.remove(indice);
		new DAOclientes().eliminarCliente(clientes);
	}

	public Cliente obtenerCliente(String telefono) {
		Cliente cli = new Cliente();
		ArrayList<Cliente> clientes = new DAOclientes().ObtenerClientes();
		for(Cliente clie : clientes) {
			if(clie.getTelefono().equalsIgnoreCase(telefono)) {
				
				return clie;
			}
		}
		return cli;
	}
	
}