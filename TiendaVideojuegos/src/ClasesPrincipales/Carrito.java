package ClasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class Carrito implements Serializable{
	protected Date fecha;
	protected ArrayList<Pagable> elementos;
	protected EstadoCarrito estado;
	protected Usuario usuario;

	
	public Carrito(Date fecha, ArrayList<Pagable> elementos, EstadoCarrito estado, Usuario usuario) {
		super();
		this.setFecha(fecha);
		this.setElementos(elementos);
		this.estado = estado;
		this.usuario = usuario;

	}
	
	public Carrito() {
		super();
		this.fecha = new Date();
		this.elementos = new ArrayList<Pagable>();
		this.estado = EstadoCarrito.PREPARACIÓN;
		this.usuario = new Usuario();
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
	
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArrayList<Pagable> getElementos() {
		return elementos;
	}
	public void setElementos(ArrayList<Pagable> elementos) {
		this.elementos = elementos;
	
	}
	public double getPrecio() {
		double precio = 0;
		
		for (Pagable pagable : elementos) {
			precio += pagable.getPrecio();
		}
		return precio;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return "Pedido de " + usuario.getNombre() + ", " + sdf.format(fecha) + ", " + this.getPrecio() + " euros (" + estado + ")";
	}
	
}

	
	
	
	
	

	

	
	
	


