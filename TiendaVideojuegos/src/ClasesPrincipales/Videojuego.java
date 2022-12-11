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
		this.genero = genero.ACCION;
		this.estado = estado.PRIMERA_MANO;
		this.setAnyo(0);
		this.setPrecio(0);
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
		if (anyo >= 0) {
			this.anyo = anyo;
		}
	}

	public void setPrecio(double precio) {
		if (precio >= 0) {
			this.precio = precio;
		}
	}
	
	@Override
	public double getPrecio() {
	if(this.getEstado()== estado.SEGUNDA_MANO) {
		return precio ;
		
	}else {
		return precio;
	}
}
	
	
	@Override
	public String toString() {
		return "Nombre:" + nombre + " Año:" + anyo +" Genero:" + genero + " Estado:" + estado + " Precio:" + precio + "eur";
	}

	


	
		
}
