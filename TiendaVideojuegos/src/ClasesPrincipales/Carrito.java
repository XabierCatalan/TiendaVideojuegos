package ClasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



public class Carrito implements Serializable{
	protected int id;
	protected Date fecha;
	protected ArrayList<Pagable> elementos;
	protected EstadoCarrito estado;
	protected String email;

	
	public Carrito(int id, Date fecha, ArrayList<Pagable> elementos, EstadoCarrito estado, String email) {
		super();
		this.setId(id);
		this.setFecha(fecha);
		this.setElementos(elementos);
		this.estado = estado;
		this.setEmail(email);

	}
	
	public Carrito() {
		super();
		this.id = 0;
		this.fecha = new Date();
		this.elementos = new ArrayList<Pagable>();
		this.estado = EstadoCarrito.PREPARACIÓN;
		this.email = "Sin email";
	}


	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return "Pedido de " + email + ", " + sdf.format(fecha) + ", " + this.getPrecio() + " euros (" + estado + ")   "  + elementos;
	}
	
}

	
	
	
	
	

	

	
	
	


