package co.uptc.edu.logica.modelo;

public abstract class MateriaPrima {
	protected int Id;
	protected String Nombre;
	protected String Variacion;
	protected Double Cantidad;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getVariacion() {
		return Variacion;
	}
	public void setVariacion(String variacion) {
		Variacion = variacion;
	}
	public Double getCantidad() {
		return Cantidad;
	}
	public void setCantidad(Double cantidad) {
		Cantidad = cantidad;
	}
	
	
}
