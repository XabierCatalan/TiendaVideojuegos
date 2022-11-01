package ClasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;



public class Carrito implements Serializable {
	protected String cliente;
	protected Date fecha;
	protected EstadoCarrito estado;
	
	
	public Carrito(String cliente, Date fecha, EstadoCarrito estado) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.estado = estado;
	}
	public Carrito() {
		super();
		this.cliente = "0000 0000 0000 0000";
		this.fecha = new Date();
		this.estado = EstadoCarrito.PREPARACIÓN;
	}


	public String getCliente() {
		return cliente;
	}


	public void setCliente(String cliente) {
		this.cliente = cliente;
	}


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public EstadoCarrito getEstadoCarrito() {
		return estado;
	}


	public void setEstadoCarrito(EstadoCarrito estado) {
		this.estado = estado;
	}
	
	
	
}

	
	
	
	
	

	

	
	
	


