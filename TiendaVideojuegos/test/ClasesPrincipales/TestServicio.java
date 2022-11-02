package ClasesPrincipales;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestServicio {

	Servicio servicio;
	
	protected TipoServicio tipo = TipoServicio.REPARACION;
	protected Date fecha1 = new Date();
	protected double precio = 0;
	protected ArrayList<Arreglable> arreglar = new ArrayList<Arreglable>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Before
	public void SetUp() {
		servicio = new Servicio();
		servicio.setServicio(tipo);;
		servicio.setFecha1(fecha1);
		servicio.setPrecio(precio);
		servicio.setArreglar(arreglar);
		
		
	}
	
	@Test
	public void testServicioTipoServicioDateDoubleArrayListOfArreglable() {
		Servicio newServicio = new Servicio(tipo, fecha1, precio, arreglar);
		assertNotNull(newServicio);
		assertEquals(newServicio.getServicio(), tipo);
		assertEquals(newServicio.getFecha1(), fecha1);
		assertEquals(newServicio.getPrecio(), precio,0);
		assertEquals(newServicio.getArreglar(), arreglar);
	}

	@Test
	public void testServicio() {
		Servicio newServicio2 = new Servicio();
		assertNotNull(newServicio2);
		assertEquals(newServicio2.getServicio(), TipoServicio.MANTENIMIENTO);
		assertEquals(newServicio2.getFecha1(), new Date());
		assertEquals(newServicio2.getPrecio(), 0);
		assertEquals(newServicio2.getArreglar(), new ArrayList<Arreglable>());
	}

	@Test
	public void testGetServicio() {
		assertEquals(servicio.getServicio(), tipo);
	}

	@Test
	public void testSetServicio() {
		TipoServicio newTipo = TipoServicio.MANTENIMIENTO;
		assertEquals(servicio.getServicio(), tipo);
		servicio.setServicio(newTipo);
		assertEquals(servicio.getServicio(), newTipo);
	}

	@Test
	public void testGetFecha1() {
		assertEquals(servicio.getFecha1(), fecha1);
	}

	@Test
	public void testSetFecha1() {
		Date newDate = new Date();
		assertEquals(servicio.getFecha1(), fecha1);
		servicio.setFecha1(newDate);
		assertEquals(servicio.getFecha1(), newDate);
	}

	@Test
	public void testGetPrecio() {
		assertEquals(servicio.getPrecio(), precio);
	}

	@Test
	public void testSetPrecio() {
		double newPrecio = 0;
		assertEquals(servicio.getPrecio(),precio);
		servicio.setPrecio(newPrecio);
		assertEquals(servicio.getPrecio(), newPrecio);
	}

	@Test
	public void testGetArreglar() {
		assertEquals(servicio.getArreglar(), arreglar);
	}

	@Test
	public void testSetArreglar() {
		ArrayList<Arreglable> newArreglar = new ArrayList<Arreglable>();
		assertEquals(servicio.getArreglar(), arreglar);
		servicio.setArreglar(newArreglar);
		assertEquals(servicio.getArreglar(), newArreglar);
	}

	
	@Test
	public void testToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String toString = "El servicio " + tipo + ", " + sdf.format(fecha1) + ", " + servicio.getPrecio() + " euros (" + arreglar + ")";
		System.out.println(toString);
		System.out.println(servicio.toString());
		assertEquals(servicio.toString(), toString);
	}

}
