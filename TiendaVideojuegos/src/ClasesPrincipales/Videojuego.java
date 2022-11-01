package ClasesPrincipales;

import java.io.Serializable;

public class Videojuego extends Producto implements Serializable,Pagable {
	protected Genero genero;
	protected EstadoProducto estado;
	protected int año;
	protected double precio;
	
	
	

	public Videojuego(int id, String nombre, Genero genero, EstadoProducto estado, int año, double precio) {
		super(id, nombre);
		this.genero = genero;
		this.estado = estado;
		this.año = año;
		this.precio = precio;
	}
	
	public Videojuego() {
		super();
		this.genero = genero;
		this.estado = estado;
		this.año = año;
		this.precio = precio;
	}
	
	

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return nombre + "(" + genero + ") (" + estado + ")" + precio + "eur";
	}
	
	
	
}
