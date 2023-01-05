package ClasesPrincipales;

import java.io.Serializable;

public class Mando extends Producto implements Serializable,Pagable {

	
	protected EstadoProducto estado;
	protected double precio;
	protected Marca marca;
	protected int id_m;
	
	public Mando(int id, String nombre, TipoProducto tp, EstadoProducto estado, double precio, Marca marca, int id_m) {
		super(id, nombre, tp);
		this.estado = estado;
		this.precio = precio;
		this.marca = marca;
		this.id_m = id_m;
	}
	
	public Mando() {
		super();
		this.estado = estado.PRIMERA_MANO;
		this.precio = precio;
		this.marca = marca.PLAYSTATION;
		this.id_m = 1;
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
	
	
	public int getId_m() {
		return id_m;
	}

	public void setId_m(int id_m) {
		this.id_m = id_m;
	}

	@Override
	public String toString() {
		return String.format(nombre + " (" + estado + ") "+ marca + " " + "%.2f" + "eur", this.getPrecio());
	}
	
	
	
	
}
