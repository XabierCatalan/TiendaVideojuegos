package ClasesPrincipales;

import java.io.Serializable;

public class Videojuego extends Producto implements Serializable,Pagable {
	protected Genero genero;
	protected EstadoProducto estado;
	protected int anyo;
	protected double precio;
	
	
	

	public Videojuego(int id, String nombre, Genero genero, EstadoProducto estado, int anyo, double precio) {
		super(id, nombre);
		this.genero = genero;
		this.estado = estado;
		this.setAnyo(anyo);
		this.setPrecio(precio);
	}
	
	public Videojuego() {
		super();
		this.genero = genero;
		this.estado = estado;
		this.anyo = anyo;
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

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
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
