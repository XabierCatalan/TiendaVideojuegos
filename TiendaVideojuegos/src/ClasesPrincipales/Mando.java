package ClasesPrincipales;

import java.io.Serializable;

public class Mando implements Pagable, Serializable {

	protected String nombre;
	protected EstadoProducto estado;
	
	public Mando(String nombre, EstadoProducto estado) {
		super();
		this.nombre = nombre;
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Mando [nombre=" + nombre + ", estado=" + estado + "]";
	}

	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
