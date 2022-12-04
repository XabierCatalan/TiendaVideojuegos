package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestVideojuego {
	
	Videojuego videojuego;
	protected int id = 0;
	protected String nombre = "nombre";
	protected Genero genero = Genero.ACCION;
	protected EstadoProducto estado = EstadoProducto.PRIMERA_MANO;
	protected int anyo = 0;
	protected double precio = 0;
	
	@Before
	public void SetUp() {
		videojuego = new Videojuego();
		videojuego.setId(id);
		videojuego.setNombre(nombre);
		videojuego.setGenero(genero);
		videojuego.setEstado(estado);
		videojuego.setAnyo(anyo);
		videojuego.setPrecio(precio);
	}
 
	@Test
	public void testToString() {
		String toString = "Nombre:" + nombre + " Año:" + anyo +" Genero:" + genero + " Estado:" + estado + " Precio:" + precio + "eur";
		System.out.println(toString);
		System.out.println(videojuego.toString());
		assertEquals(videojuego.toString(), toString);
	}

	@Test
	public void testVideojuegoIntStringGeneroEstadoProductoIntDouble() {
		Videojuego newVideojuego = new Videojuego(id,nombre,genero,estado,anyo,precio);
		assertNotNull(newVideojuego);
		assertEquals(newVideojuego.getNombre(), nombre);
		assertEquals(newVideojuego.getId(), id,0);
		assertEquals(newVideojuego.getGenero(), genero);
		assertEquals(newVideojuego.getEstado(), estado);
		assertEquals(newVideojuego.getAnyo(), anyo,0);
		assertEquals(newVideojuego.getPrecio(), precio,0);
	}

	@Test
	public void testVideojuego() {
		Videojuego newVideojuego2 = new Videojuego();
		assertNotNull(newVideojuego2);
		assertEquals(newVideojuego2.getNombre(), "Sin nombre");
		assertEquals(newVideojuego2.getId(), 0,0);
		assertEquals(newVideojuego2.getGenero(), genero.ACCION);
		assertEquals(newVideojuego2.getEstado(), estado.PRIMERA_MANO);
		assertEquals(newVideojuego2.getAnyo(), 0,0);
		assertEquals(newVideojuego2.getPrecio(), 0,0);
		
		
		
	}

	@Test
	public void testGetGenero() {
		assertEquals(videojuego.getGenero(), genero);
	}

	@Test
	public void testSetGenero() {
		Genero newGenero = Genero.ROL;
		assertEquals(videojuego.getGenero(), genero);
		videojuego.setGenero(newGenero);
		assertEquals(videojuego.getGenero(), newGenero);
	}

	@Test
	public void testGetEstado() {
		assertEquals(videojuego.getEstado(), estado);
	}

	@Test
	public void testSetEstado() {
		EstadoProducto newEstado = EstadoProducto.SEGUNDA_MANO;
		assertEquals(videojuego.getEstado(), estado);
		videojuego.setEstado(newEstado);
		assertEquals(videojuego.getEstado(), newEstado);
		
	}

	@Test
	public void testGetAnyo() {
		assertEquals(videojuego.getAnyo(), anyo,0);
	}

	@Test
	public void testSetAnyo() {
		Integer newAnyo = 2;
		assertEquals(videojuego.getAnyo(), anyo,0);
		videojuego.setAnyo(newAnyo);
		assertEquals(videojuego.getAnyo(), newAnyo,0);
	}

	@Test
	public void testSetPrecio() {
		Double newPrecio = 2.0;
		assertEquals(videojuego.getPrecio(), precio,0);
		videojuego.setPrecio(newPrecio);
		assertEquals(videojuego.getPrecio(), newPrecio,0);
	}

	@Test
	public void testGetPrecio() {
		assertEquals(videojuego.getPrecio(), precio,0);
	}

}
