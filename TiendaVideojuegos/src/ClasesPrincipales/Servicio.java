package ClasesPrincipales;

import java.io.Serializable;
import java.util.Date;
import ClasesPrincipales.Consola;

public class Servicio implements Serializable{
	protected TipoServicio servicio;
	protected Date fecha1;
	protected double precio;
	protected Consola consola;
	
	
	public Servicio(TipoServicio servicio, Date fecha1, double precio, Consola consola) {
		super();
		this.servicio = servicio;
		this.setFecha1(fecha1);
		this.setPrecio(precio);
		this.consola = consola;
	}
	
	public Servicio() {
		super();
		this.servicio = TipoServicio.REPARACION;
		this.fecha1 = new Date();
		this.precio = 0;
		this.consola = new Consola();
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
		return precio;
	}

	public void setPrecio(double precio) {
		if (this.servicio == TipoServicio.REPARACION) {
			this.precio = this.consola.getPrecio() / 2.5;
		} else {
			this.precio = this.consola.getPrecio() / 5;
		}
		
	}

	public Consola getConsola() {
		return consola;
	}

	public void setConsola(Consola consola) {
		this.consola = consola;
	}

	@Override
	public String toString() {
		return servicio + ": Consola:" + consola + ", Precio:" + precio + ", Fecha:" + fecha1;
	}
	
	
	
	
	
	
	
	
	

}
