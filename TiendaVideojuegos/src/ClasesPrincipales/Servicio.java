package ClasesPrincipales;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import ClasesPrincipales.Consola;

public class Servicio implements Serializable,Pagable{
	protected Usuario u;
	protected TipoServicio tipo;
	protected Date fecha1;
	protected String Descrip;
	
	
	
	
	
	public Servicio(TipoServicio tipo, Date fecha1, String Descrip, Usuario u) {
		super();
		this.tipo = tipo;
		this.fecha1 = fecha1;
		this.Descrip = Descrip;
		this.u = u;
		
	}

	public Servicio() {
		super();
		this.tipo = TipoServicio.MANTENIMIENTO;
		this.fecha1 = new Date();
		this.Descrip = "";
		this.u = new Usuario();
		
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
	
	public String getDescrip() {
		return Descrip;
	}

	public void setDescrip(String descrip) {
		Descrip = descrip;
	}
	
	

	

	public Usuario getU() {
		return u;
	}

	public void setU (Usuario u) {
		this.u = u;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		return "Email: " + u.getEmail() + " Tipo:" + tipo + ", (" + sdf1.format(fecha1) + ")";
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
