package ClasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class Carrito implements Serializable {
	protected String cliente;
	protected Date fecha;
	protected ArrayList<Pagable> elementos;
	protected EstadoCarrito estado;
	
	
	public Carrito(String cliente, Date fecha, EstadoCarrito estado) {
		super();
		this.cliente = cliente;
		this.fecha = fecha;
		this.Elementos(elementos);
		this.estado = estado;
	
	}
	public Carrito() {
		super();
		this.cliente = "0000 0000 0000 0000";
		this.fecha = new Date();
		this.elementos = new ArrayList<Pagable>();
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
		if (fecha != null) {
			this.fecha = fecha;
		}
	}	


	public EstadoCarrito getEstadoCarrito() {
		return estado;
	}


	public void setEstadoCarrito(EstadoCarrito estado) {
		this.estado = estado;
	}
	
	
	private void Elementos(ArrayList<Pagable> elementos2) {
		// TODO Auto-generated method stub
	
	}
	public ArrayList<Pagable> getElementos() {
		return elementos;
	}
	public void setElementos(ArrayList<Pagable> elementos) {
		this.elementos = elementos;
	
	}
}

	
	
	
	
	

	

	
	
	

