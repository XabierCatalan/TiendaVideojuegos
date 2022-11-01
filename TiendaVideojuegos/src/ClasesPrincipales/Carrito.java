package ClasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;



public class Carrito implements Serializable {
	protected String cliente;
	protected Date fecha;
	protected Estado estado;
	
	
	public Carrito(String cliente, Date fecha, Estado estado) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.estado = estado;
	}
	public Carrito() {
		super();
		this.cliente = "0000 0000 0000 0000";
		this.fecha = new Date();
		this.estado = Estado.PREPARACIÓN;
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


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
	
}

	
	
	
	
	

	

	
	
	


