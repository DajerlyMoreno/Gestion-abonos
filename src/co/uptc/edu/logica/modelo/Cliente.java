package co.uptc.edu.logica.modelo;

import java.util.ArrayList;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String telefono;
	private int numeroVentas;
	private ArrayList<String> ventas;
	
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getNumeroVentas() {
		return numeroVentas;
	}
	public void setNumeroVentas(int numeroVentas) {
		this.numeroVentas = numeroVentas;
	}
	public ArrayList<String> getVentas() {
		return ventas;
	}
	public void setVentas(ArrayList<String> ventas) {
		this.ventas = ventas;
	}
	
}