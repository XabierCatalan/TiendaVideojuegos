package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestVideojuego {
	
	Videojuego videojuego;
	protected int id = 12;
	protected String nombre = "nombre";
	protected TipoProducto tp = TipoProducto.VIDEOJUEGO;
	protected Genero genero = Genero.ACCION;
	protected EstadoProducto estado = EstadoProducto.PRIMERA_MANO;
	protected int anyo = 0;
	protected double precio = 0;
	protected int id_v = 7;
	
	@Before
	public void SetUp() {
		videojuego = new Videojuego();
		videojuego.setId(id);
		videojuego.setNombre(nombre);
		videojuego.setTp(tp);
		videojuego.setGenero(genero);
		videojuego.setEstado(estado);
		videojuego.setAnyo(anyo);
		videojuego.setPrecio(precio);
		videojuego.setId_v(id_v);
	}
 
	@Test
	public void testToString() {
		String toString = String.format("Nombre:" + nombre + " Año:" + anyo +" Genero:" + genero + " Estado:" + estado + " Precio:" + "%.2f" + "eur", precio);
		System.out.println(toString);
		System.out.println(videojuego.toString());
		assertEquals(videojuego.toString(), toString);
	}

	@Test
	public void testVideojuegoIntStringGeneroEstadoProductoIntDouble() {
		Videojuego newVideojuego = new Videojuego(id,nombre,tp,genero,estado,anyo,precio,id_v);
		assertNotNull(newVideojuego);
		assertEquals(newVideojuego.getNombre(), nombre);
		assertEquals(newVideojuego.getId(), id,0);
		assertEquals(newVideojuego.getTp(), tp);
		assertEquals(newVideojuego.getGenero(), genero);
		assertEquals(newVideojuego.getEstado(), estado);
		assertEquals(newVideojuego.getAnyo(), anyo,0);
		assertEquals(newVideojuego.getPrecio(), precio,0);
		assertEquals(newVideojuego.getId_v(), id_v,0);
	}

	@Test
	public void testVideojuego() {
		Videojuego newVideojuego2 = new Videojuego();
		assertNotNull(newVideojuego2);
		assertEquals(newVideojuego2.getNombre(), "Sin nombre");
		assertEquals(newVideojuego2.getId(), 0,0);
		assertEquals(newVideojuego2.getTp(), TipoProducto.VIDEOJUEGO);
		assertEquals(newVideojuego2.getGenero(), genero.ACCION);
		assertEquals(newVideojuego2.getEstado(), estado.PRIMERA_MANO);
		assertEquals(newVideojuego2.getAnyo(), 0,0);
		assertEquals(newVideojuego2.getPrecio(), 0,0);
		assertEquals(newVideojuego2.getId_v(), 1,0);
		
		
		
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
	
	@Test
	public void testGetId_v() {
		assertEquals(videojuego.getId_v(), id_v,0);
	}

	@Test
	public void testSetId_v() {
		Integer newId_v = 3;
		assertEquals(videojuego.getId_v(), id_v,0);
		videojuego.setId_v(newId_v);
		assertEquals(videojuego.getId_v(), newId_v,0);
	}

}
