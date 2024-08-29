package co.uptc.edu.logica.modelo;

import java.util.ArrayList;

public class Factura {
	private int id;
	private String fecha;
	private Cliente cliente;
	private ArrayList<Bulto> bultosAsociados;
	private double valorTotal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Bulto> getBultosAsociados() {
		return bultosAsociados;
	}
	public void setBultosAsociados(ArrayList<Bulto> bultosAsociados) {
		this.bultosAsociados = bultosAsociados;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	

}