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
	
	
	@Before
	public void SetUp() {
		servicio = new Servicio();
		servicio.setFecha1(fecha);
		servicio.setTipo(tipo);
	}

	@Test
	public void testServicioTipoServicioDate() {
		Servicio newServicio = new Servicio(tipo,fecha);
		assertNotNull(newServicio);
		assertEquals(newServicio.getTipo(), tipo);
		assertEquals(newServicio.getFecha1(), fecha);
	}

	@Test
	public void testServicio() {
		Servicio newServicio2 = new Servicio();
		assertNotNull(newServicio2);
		assertEquals(newServicio2.getTipo(), TipoServicio.MANTENIMIENTO);
		assertEquals(newServicio2.getFecha1(), new Date());
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

	@Test
	public void testToString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		
		String toString = "Tipo= " + tipo + ", (" + sdf1.format(fecha) + ")";
		System.out.println(toString);
		System.out.println(servicio.toString());
		assertEquals(servicio.toString(), toString);
		
		
	}

	@Test
	public void testGetPrecio() {
		assertEquals(servicio.getPrecio(), 25,0);
	}

}
