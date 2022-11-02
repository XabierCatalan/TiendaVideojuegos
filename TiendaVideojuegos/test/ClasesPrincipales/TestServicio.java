package ClasesPrincipales;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestServicio {
	
	
	
	Servicio servicio;
	protected TipoServicio tipo = TipoServicio.MANTENIMIENTO;
	protected Date fecha = new Date();
	
	
	
	public void SetUp() {
		servicio = new Servicio();
		servicio.setTipo(tipo);
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
		
		
		
	}

	@Test
	public void testGetPrecio() {
		fail("Not yet implemented");
	}

}
