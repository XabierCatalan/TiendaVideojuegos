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
	protected Date fecha = new Date();
	protected ArrayList<Pagable> elementos = new ArrayList<Pagable>();
	protected EstadoCarrito estado = EstadoCarrito.LISTO;
	protected Usuario usuario = new Usuario(1,"xabi","xabier.catalan@opendeusto.es","1234","111111111");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	@Before
	public void SetUp() {
		carrito = new Carrito();
		carrito.setFecha(fecha);
		carrito.setElementos(elementos);
		carrito.setEstadoCarrito(estado);
		carrito.setUsuario(usuario);
		
	}
	

	@Test
	public void testCarritoStringDateEstadoCarrito() {
		Carrito newCarrito = new Carrito(fecha, elementos, estado, usuario);
		assertNotNull(newCarrito);
		assertEquals(newCarrito.getUsuario(), usuario);
		assertEquals(newCarrito.getElementos(), elementos);
		assertEquals(newCarrito.getFecha(), fecha);
		assertEquals(newCarrito.getEstadoCarrito(), estado);
	}

	@Test
	public void testCarrito() {
		Carrito newCarrito2 = new Carrito();
		assertNotNull(newCarrito2);
		
		assertEquals(newCarrito2.getElementos(), new ArrayList<Pagable>());
		assertEquals(newCarrito2.getFecha(), new Date());
		assertEquals(newCarrito2.getEstadoCarrito(), EstadoCarrito.PREPARACIÓN);
		assertEquals(newCarrito2.getUsuario(), new Usuario(0,"Sin nombre","Sin email", "Sin contrasenya", "000000000"));
		
	}

	@Test
	public void testGetUsuario() {
		assertEquals(carrito.getUsuario(), usuario);
	}

	@Test
	public void testSetUsuario() {
		Usuario newUsuario = new Usuario(2,"oscar","o.perez@opendeusto.es","4321","666666666");
		assertEquals(carrito.getUsuario(), usuario);
		carrito.setUsuario(newUsuario);
		assertEquals(carrito.getUsuario(), newUsuario);
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
		elementos.add(mando);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String toString = "Pedido de " + usuario.getNombre() + ", " + sdf.format(fecha) + ", " + carrito.getPrecio() + " euros (" + estado + ")";
		System.out.println(toString);
		System.out.println(carrito.toString());
		assertEquals(carrito.toString(), toString);
	}

}
