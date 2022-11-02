package ClasesPrincipales;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestCarrito {
	
	Carrito carrito;
	protected String cliente = "0000 0000 0000 0001";
	protected Date fecha = new Date();
	protected ArrayList<Pagable> elementos = new ArrayList<Pagable>();
	protected EstadoCarrito estado = EstadoCarrito.LISTO;
	protected double precio = 10;
	
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
		Carrito newCarrito = new Carrito(cliente, fecha, elementos, estado);
		assertNotNull(newCarrito);
		assertEquals(newCarrito.getCliente(), cliente);
		assertEquals(newCarrito.getElementos(), elementos);
		assertEquals(newCarrito.getFecha(), fecha);
		assertEquals(newCarrito.getEstadoCarrito(), estado);
	}

	@Test
	public void testCarrito() {
		Carrito newCarrito2 = new Carrito();
		assertNotNull(newCarrito2);
		assertEquals(newCarrito2.getCliente(), "0000 0000 0000 0000");
		assertEquals(newCarrito2.getElementos(), new ArrayList<Pagable>());
		assertEquals(newCarrito2.getFecha(), new Date());
		assertEquals(newCarrito2.getEstadoCarrito(), EstadoCarrito.PREPARACIÓN);
		
	}

	@Test
	public void testGetCliente() {
		assertEquals(carrito.getCliente(), cliente);
	}

	@Test
	public void testSetCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFecha() {
		assertEquals(carrito.getFecha(), fecha);
	}

	@Test
	public void testSetFecha() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEstadoCarrito() {
		assertEquals(carrito.getEstadoCarrito(), estado);
	}

	@Test
	public void testSetEstadoCarrito() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetElementos() {
		assertEquals(carrito.getElementos(), elementos);
	}

	@Test
	public void testSetElementos() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrecio() {
		assertEquals(carrito.getPrecio(), precio,0);
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
