package ClasesPrincipales;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestServicio {
	
	
	
	Servicio servicio;
	protected TipoServicio tipo = TipoServicio.MANTENIMIENTO;
	protected Date fecha = new Date();
	protected String Descrip = "jnd";
	protected Usuario u = new Usuario(1, "o", "o@gmail", "o.cont", "678866438");
	
	
	@Before
	public void SetUp() {
		servicio = new Servicio();
		servicio.setFecha1(fecha);
		servicio.setTipo(tipo);
		servicio.setDescrip(Descrip);
		servicio.setU(u);
	}

	@Test
	public void testServicioTipoServicioDate() {
		Servicio newServicio = new Servicio(tipo,fecha,Descrip,u);
		assertNotNull(newServicio);
		assertEquals(newServicio.getTipo(), tipo);
		assertEquals(newServicio.getFecha1(), fecha);
		assertEquals(newServicio.getDescrip(), Descrip);
		assertEquals(newServicio.getU(), u);
	}

	@Test
	public void testServicio() {
		Servicio newServicio2 = new Servicio();
		assertNotNull(newServicio2);
		assertEquals(newServicio2.getTipo(), TipoServicio.MANTENIMIENTO);
		assertEquals(newServicio2.getFecha1(), new Date());
		assertEquals(newServicio2.getDescrip(), "");
		//assertEquals(newServicio2.getU(), new Usuario(0,"","","",""));
	}

	@Test
	public void testGetTipo() {
		assertEquals(servicio.getTipo(), tipo);
	}

	@Test
	public void testSetTipo() {
		TipoServicio newTipo = TipoServicio.REPARACION;
		assertEquals(servicio.getTipo(),tipo);
		servicio.setTipo(newTipo);
		assertEquals(servicio.getTipo(), newTipo);
	}

	@Test
	public void testGetFecha1() {
		assertEquals(servicio.getFecha1(), fecha);
	}

	@Test
	public void testSetFecha1() {
		Date newDate = new Date();
		assertEquals(servicio.getFecha1(), fecha);
		servicio.setFecha1(newDate);
		assertEquals(servicio.getFecha1(), newDate);
	}
	
	public void testGetDescrip() {
		assertEquals(servicio.getDescrip(), Descrip);
	}
	
	public void testSetDescrip() {
		String s = "dddd";
		assertEquals(servicio.getDescrip(), Descrip);
		servicio.setDescrip(s);
		assertEquals(servicio.getDescrip(), s);
	}
	
	public void testGetUsuario() {
		assertEquals(servicio.getU(), u);
	}
	
	public void testSetU() {
		Usuario s = new Usuario();
		assertEquals(servicio.getU(), u);
		servicio.setU(u);
		assertEquals(servicio.getU(), s);
	}
	

	@Test
	public void testToString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		
		String toString = "Email: " + u.getEmail() + " Tipo:" + tipo + ", (" + sdf1.format(fecha) + ")";
		System.out.println(toString);
		System.out.println(servicio.toString());
		assertEquals(servicio.toString(), toString);
		
		
	}

	@Test
	public void testGetPrecio() {
		assertEquals(servicio.getPrecio(), 25,0);
	}

}
