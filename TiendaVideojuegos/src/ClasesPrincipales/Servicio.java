package ClasesPrincipales;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import ClasesPrincipales.Consola;

public class Servicio implements Serializable,Pagable{
	protected TipoServicio tipo;
	protected Date fecha1;
	
	
	
	
	public Servicio(TipoServicio tipo, Date fecha1) {
		super();
		this.tipo = tipo;
		this.fecha1 = fecha1;
		
	}

	public Servicio() {
		super();
		this.tipo = TipoServicio.MANTENIMIENTO;
		this.fecha1 = new Date();
		
	}

	public TipoServicio getTipo() {
		return tipo;
	}

	public void setTipo(TipoServicio tipo) {
		this.tipo = tipo;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		return "Tipo= " + tipo + ", (" + sdf1.format(fecha1) + ")";
	}

	@Override
	public double getPrecio() {
		if(tipo.equals(TipoServicio.MANTENIMIENTO)) {
			int precio = 25;
			return precio;
		}else {
			int precio = 50;
			return precio;
		}
		
	}
	
	
	
	
	
	
	

	
	

	
	
	
	
	
	
	
	
	
	

}
