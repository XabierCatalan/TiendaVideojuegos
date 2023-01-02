package ClasesPrincipales;

import java.io.Serializable;

public class Videojuego extends Producto implements Serializable,Pagable {
	protected Genero genero;
	protected EstadoProducto estado;
	protected int anyo;
	protected double precio;
	protected int id_v;
	
	
	

	public Videojuego(int id, String nombre, TipoProducto tp, Genero genero, EstadoProducto estado, int anyo, double precio, int id_v) {
		super(id, nombre,tp);
		this.genero = genero;
		this.estado = estado;
		this.setAnyo(anyo);
		this.setPrecio(precio);
		this.id_v = id_v;
	}
	
	public Videojuego() {
		super();
		this.genero = genero.ACCION;
		this.estado = estado.PRIMERA_MANO;
		this.setAnyo(0);
		this.setPrecio(0);
		this.id_v = 1;
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
		return precio / 3;
		
		}else {
			return precio;
		}
	}
	
	
	public int getId_v() {
		return id_v;
	}

	public void setId_v(int id_v) {
		this.id_v = id_v;
	}

	@Override
	public String toString() {
		return String.format("Nombre:" + nombre + " Año:" + anyo +" Genero:" + genero + " Estado:" + estado + " Precio:" + "%.2f" + "eur", this.getPrecio());
	}

	


	
		
}
