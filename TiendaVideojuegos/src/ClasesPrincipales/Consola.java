package ClasesPrincipales;

import java.io.Serializable;

public class Consola extends Producto implements Serializable, Pagable {
	protected double precio;
	protected EstadoProducto estado;
	protected Marca marca;
	protected int id_c;
	
	public Consola(int id, String nombre, TipoProducto tp, double precio, EstadoProducto estado, Marca marca, int id_c) {
		super(id, nombre, tp);
		this.setPrecio(precio);
		this.estado = estado;
		this.marca = marca;
		this.id_c= id_c;
	}
	
	public Consola() {
		super();
		this.precio = 0;
		this.estado = EstadoProducto.PRIMERA_MANO;
		this.marca = Marca.PLAYSTATION;
		this.id_c = 1;
	}

	public double getPrecio() {
		if (this.estado == EstadoProducto.SEGUNDA_MANO) {
			return precio / 1.25;
		}
		return precio;
	}

	public void setPrecio(double precio) {
		if (precio >= 0) {
		this.precio = precio;
	}}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	
	public int getId_c() {
		return id_c;
	}

	public void setId_c(int id_c) {
		this.id_c = id_c;
	}

	@Override
	public String toString() {
		return String.format(nombre +" (" + estado + ") " + marca + " " + "%.2f" + "eur", this.getPrecio()) ;
	}
	
	
	
	
	
	
	
	
	
	
}
