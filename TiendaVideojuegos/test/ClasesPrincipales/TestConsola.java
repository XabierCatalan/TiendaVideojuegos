package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestConsola {
	Consola consola;
	protected int id = 0;
	protected String nombre = "nombre";
	protected TipoProducto tp = TipoProducto.CONSOLA;
	protected double precio=0;
	protected EstadoProducto estado = EstadoProducto.PRIMERA_MANO;
	protected Marca marca = Marca.NINTENDO;
	
	@Before
	public void SetUp() {
	consola = new Consola();
	consola.setNombre(nombre);
	consola.setId(id);
	consola.setTp(tp);
	consola.setEstado(estado);
	consola.setPrecio(precio);
	consola.setMarca(marca);
	}
	
	@Test
	public void testToString() {
		String toString = String.format("Nombre:" + nombre +" Estado:" + estado + " Marca:" + marca + " Precio:" + "%.2f" + "eur", precio) ;
		System.out.println(toString);
		System.out.println(consola.toString());
		assertEquals(consola.toString(), toString);
	}

	@Test
	public void testConsolaIntStringDoubleEstadoProductoMarca() {
		Consola newConsola = new Consola(id,nombre,tp,precio,estado,marca);
		assertNotNull(newConsola);
		assertEquals(newConsola.getNombre(), nombre);
		assertEquals(newConsola.getId(), id,0);
		assertEquals(newConsola.getTp(), tp);
		assertEquals(newConsola.getEstado(), estado);
		assertEquals(newConsola.getPrecio(), precio,0);
		assertEquals(newConsola.getMarca(), marca);
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
		assertEquals(newConsola2.getMarca(), marca.PLAYSTATION);	}

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

}
