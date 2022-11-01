package ClasesPrincipales;

import java.io.Serializable;

public class Mando extends Producto implements Serializable,Pagable {

	
	protected EstadoProducto estado;
	protected double precio;
	
	public Mando(int id, String nombre, EstadoProducto estado, double precio) {
		super(id, nombre);
		this.estado = estado;
		this.precio = precio;
	}
	
	public Mando() {
		super();
		this.estado = estado.PRIMERA_MANO;
		this.precio = precio;
	}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return nombre + "(" + estado + ")" + precio + "eur";
	}
	
	
	
	
}
