package ClasesPrincipales;

import java.io.Serializable;

public class Mando extends Producto implements Serializable,Pagable {

	
	protected EstadoProducto estado;
	protected double precio;
	protected Marca marca;
	
	public Mando(int id, String nombre, EstadoProducto estado, double precio, Marca marca) {
		super(id, nombre);
		this.estado = estado;
		this.precio = precio;
		this.marca = marca;
	}
	
	public Mando() {
		super();
		this.estado = estado.PRIMERA_MANO;
		this.precio = precio;
		this.marca = marca.PLAYSTATION;
	}

	public EstadoProducto getEstado() {
		return estado;
	}

	public void setEstado(EstadoProducto estado) {
		this.estado = estado;
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
	

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return String.format("Nombre:" + nombre + " Estado:" + estado + " Marca:"+ marca + " Precio:" + "%.2f" + "eur", this.getPrecio());
	}
	
	
	
	
}
