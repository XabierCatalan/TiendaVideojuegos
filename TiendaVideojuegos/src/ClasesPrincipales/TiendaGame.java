package ClasesPrincipales;

import java.io.Serializable;
import java.util.ArrayList;

public class TiendaGame implements Serializable{
	
	protected ArrayList<Carrito> carritos;
	protected ArrayList<Producto> productos;
	protected ArrayList<Servicio> servicios;
	
	public TiendaGame(ArrayList<Carrito> carritos, ArrayList<Producto> productos, ArrayList<Servicio> servicios) {
		super();
		this.carritos = carritos;
		this.productos = productos;
		this.servicios = servicios;
	}
	
	public TiendaGame() {
		super();
		this.carritos = new ArrayList<Carrito>();
		this.productos = new ArrayList<Producto>();
		this.servicios = new ArrayList<Servicio>();
	}

	public ArrayList<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(ArrayList<Carrito> carritos) {
		if (carritos != null) {
			this.carritos = carritos;
		}
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		if (carritos != null) {
			this.productos = productos;
		}
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		if (servicios != null) {
			this.servicios = servicios;
		}
	}

	@Override
	public String toString() {
		return "TiendaGame [carritos=" + carritos + ", productos=" + productos + ", servicios=" + servicios + "]";
	}
	
	
	
	
	
	
	
	
	 
	
	 

}
