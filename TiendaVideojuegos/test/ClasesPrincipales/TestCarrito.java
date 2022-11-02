package ClasesPrincipales;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestCarrito {
	protected Mando mando =new Mando(1,"mando",EstadoProducto.PRIMERA_MANO,5,Marca.PLAYSTATION);
	
	Carrito carrito;
	protected String cliente = "0000 0000 0000 0001";
	protected Date fecha = new Date();
	protected ArrayList<Pagable> elementos = new ArrayList<Pagable>();
	protected EstadoCarrito estado = EstadoCarrito.LISTO;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
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
		String newCliente = "0000 0000 0000 0003";
		assertEquals(carrito.getCliente(), cliente);
		carrito.setCliente(newCliente);
		assertEquals(carrito.getCliente(), newCliente);
	}

	@Test
	public void testGetFecha() {
		assertEquals(carrito.getFecha(), fecha);
	}

	@Test
	public void testSetFecha() {
		Date newDate = new Date();
		assertEquals(carrito.getFecha(), fecha);
		carrito.setFecha(newDate);
		assertEquals(carrito.getFecha(), newDate);
	}

	@Test
	public void testGetEstadoCarrito() {
		assertEquals(carrito.getEstadoCarrito(), estado);
	}

	@Test
	public void testSetEstadoCarrito() {
		EstadoCarrito newEstado = EstadoCarrito.RECOGIDO;
		assertEquals(carrito.getEstadoCarrito(),estado);
		carrito.setEstadoCarrito(newEstado);
		assertEquals(carrito.getEstadoCarrito(), newEstado);
	}

	@Test
	public void testGetElementos() {
		assertEquals(carrito.getElementos(), elementos);
	}

	@Test
	public void testSetElementos() {
		ArrayList<Pagable> newElementos = new ArrayList<Pagable>();
		assertEquals(carrito.getElementos(), elementos);
		carrito.setElementos(newElementos);
		assertEquals(carrito.getElementos(), newElementos);
	}

	@Test
	public void testGetPrecio() {
		elementos.add(mando);
		assertEquals(carrito.getPrecio(), 5,0 );
	}

	@Test
	public void testToString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String toString = "";
		System.out.println(toString);
		System.out.println(carrito.toString());
		assertEquals(carrito.toString(), toString);
	}

}
