package ClasesPrincipales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import ClasesPrincipales.Consola;

public class Servicio implements Serializable, Pagable{
	protected TipoServicio servicio;
	protected Date fecha1;
	protected double precio;
	protected ArrayList<Arreglable> arreglar;
	
	
	public Servicio(TipoServicio servicio, Date fecha1, double precio, ArrayList<Arreglable> arreglar) {
		super();
		this.servicio = servicio;
		this.fecha1 = fecha1;
		this.precio = precio;
		this.arreglar = arreglar;
	}
	
	public Servicio() {
		super();
		this.servicio = TipoServicio.MANTENIMIENTO;
		this.fecha1 = new Date();
		this.precio = 0;
		this.arreglar = new ArrayList<Arreglable>();
	}

	public TipoServicio getServicio() {
		return servicio;
	}

	public void setServicio(TipoServicio servicio) {
		this.servicio = servicio;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public double getPrecio() {
		if (this.servicio == TipoServicio.REPARACION) {
			for (Arreglable a : arreglar) {
				double p;
				p =+ a.getPrecio();
				precio = p / 2.5;
				}
		} else if(this.servicio == TipoServicio.MANTENIMIENTO){
			for (Arreglable a : arreglar) {
				double p;
				p =+ a.getPrecio();
				precio = p / 5;
			}
	
		}
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
		
	}

	public ArrayList<Arreglable> getArreglar() {
		return arreglar;
	}

	public void setArreglar(ArrayList<Arreglable> arreglar) {
		this.arreglar = arreglar;
	}

	@Override
	public String toString() {
		return "Tipo de Servicio: " + servicio + ", Arreglamos/Mantenimiento: " + arreglar + ", con un precio total: " + precio + ", en la fecha" + fecha1;
	}

	
	

	
	
	
	
	
	
	
	
	
	

}
