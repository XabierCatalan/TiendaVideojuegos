package ClasesPrincipales;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestCarrito {
	
	Carrito carrito;
	protected String cliente = "0000 0000 0000 0000";
	protected Date fecha = new Date();
	protected ArrayList<Pagable> elementos = new ArrayList<>();
	protected EstadoCarrito estado = EstadoCarrito.LISTO;
	
	@Before
	public void SetUp() {
		carrito = new Carrito();
		carrito.setCliente(cliente);
		carrito.setFecha(fecha);
		carrito.setElementos(elementos);
		carrito.setEstadoCarrito(estado);
	}
	

	@Test
	public void testCarritoStringDateEstadoCarrito() {
		fail("Not yet implemented");
	}

	@Test
	public void testCarrito() {
		Carrito newCarrito2 = new Carrito();
		assertNotNull(newCarrito2);
		assertEquals(newCarrito2.getCliente(), 0);
	}

	@Test
	public void testGetCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFecha() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetFecha() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEstadoCarrito() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetEstadoCarrito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetElementos() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetElementos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrecio() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
