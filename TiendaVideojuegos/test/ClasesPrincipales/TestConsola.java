package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestConsola {
	Consola consola;
	protected int id = 10;
	protected String nombre = "nombre";
	protected TipoProducto tp = TipoProducto.CONSOLA;
	protected double precio=0;
	protected EstadoProducto estado = EstadoProducto.PRIMERA_MANO;
	protected Marca marca = Marca.NINTENDO;
	protected int id_c = 1;
	
	@Before
	public void SetUp() {
	consola = new Consola();
	consola.setNombre(nombre);
	consola.setId(id);
	consola.setTp(tp);
	consola.setEstado(estado);
	consola.setPrecio(precio);
	consola.setMarca(marca);
	consola.setId_c(id_c);
	}
	
	@Test
	public void testToString() {
		String toString = String.format(nombre +" (" + estado + ") " + marca + " " + "%.2f" + "eur", precio) ;
		System.out.println(toString);
		System.out.println(consola.toString());
		assertEquals(consola.toString(), toString);
	}

	@Test
	public void testConsolaIntStringDoubleEstadoProductoMarca() {
		Consola newConsola = new Consola(id,nombre,tp,precio,estado,marca,id_c);
		assertNotNull(newConsola);
		assertEquals(newConsola.getNombre(), nombre);
		assertEquals(newConsola.getId(), id,0);
		assertEquals(newConsola.getTp(), tp);
		assertEquals(newConsola.getEstado(), estado);
		assertEquals(newConsola.getPrecio(), precio,0);
		assertEquals(newConsola.getMarca(), marca);
		assertEquals(newConsola.getId_c(), id_c,0);
				}

	@Test
	public void testConsola() {
		Consola newConsola2 = new Consola();
		assertNotNull(newConsola2);
		assertEquals(newConsola2.getNombre(), "Sin nombre");
		assertEquals(newConsola2.getId(), 0,0);
		assertEquals(newConsola2.getTp(), TipoProducto.VIDEOJUEGO);
		assertEquals(newConsola2.getEstado(), estado.PRIMERA_MANO);
		assertEquals(newConsola2.getPrecio(), 0,0);
		assertEquals(newConsola2.getMarca(), marca.PLAYSTATION);
		assertEquals(newConsola2.getId_c(), 1,0);
		}

	@Test
	public void testGetPrecio() {
		assertEquals(consola.getPrecio(), precio,0);
	}

	@Test
	public void testSetPrecio() {
		Double newPrecio = 2.0;
		assertEquals(consola.getPrecio(), precio,0);
		consola.setPrecio(newPrecio);
		assertEquals(consola.getPrecio(), newPrecio,0);	}

	@Test
	public void testGetEstado() {
		assertEquals(consola.getEstado(), estado);
	}

	@Test
	public void testSetEstado() {
		EstadoProducto newEstado = EstadoProducto.SEGUNDA_MANO;
		assertEquals(consola.getEstado(), estado);
		consola.setEstado(newEstado);
		assertEquals(consola.getEstado(), newEstado);	}

	@Test
	public void testGetMarca() {
		assertEquals(consola.getMarca(), marca);
	}

	@Test
	public void testSetMarca() {
		Marca newMarca = Marca.PLAYSTATION;
		assertEquals(consola.getMarca(), marca);
		consola.setMarca(newMarca);
		assertEquals(consola.getMarca(), newMarca);	}
	
	@Test
	public void testGetId_c() {
		assertEquals(consola.getId_c(), id_c,0);
	}

	@Test
	public void testSetId_c() {
		Integer newId_c = 3;
		assertEquals(consola.getId_c(), id_c,0);
		consola.setId_c(newId_c);
		assertEquals(consola.getId_c(), newId_c,0);
	}
	
	

}


