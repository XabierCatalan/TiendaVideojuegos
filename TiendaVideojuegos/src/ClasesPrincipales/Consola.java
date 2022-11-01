package ClasesPrincipales;

import java.io.Serializable;

public class Consola extends Producto implements Serializable, Pagable {
	protected double precio;
	protected EstadoProducto estado;
	protected Marca marca;
	
	public Consola(int id, String nombre, double precio, EstadoProducto estado, Marca marca) {
		super(id, nombre);
		this.setPrecio(precio);
		this.estado = estado;
		this.marca = marca;
	}
	
	public Consola() {
		super();
		this.precio = 0;
		this.estado = EstadoProducto.PRIMERA_MANO;
		this.marca = Marca.PLAYSTATION;
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

	@Override
	public String toString() {
		return nombre +"(" + marca + ", " + precio + ", " + estado + ")";
	}
	
	
	
	
	
	
	
	
	
	
}
