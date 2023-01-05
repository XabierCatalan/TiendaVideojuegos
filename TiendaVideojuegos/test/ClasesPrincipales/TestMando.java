package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMando {
	Mando mando;
	protected int id = 8;
	protected String nombre = "nombre";
	protected TipoProducto tp = TipoProducto.MANDO;
	protected EstadoProducto estado = EstadoProducto.PRIMERA_MANO;
	protected double precio = 0;
	protected Marca marca = Marca.NINTENDO;
	protected int id_m = 4;
	
	@Before
	public void SetUp() {
	mando = new Mando();
	mando.setNombre(nombre);
	mando.setId(id);
	mando.setTp(tp);
	mando.setEstado(estado);
	mando.setPrecio(precio);
	mando.setMarca(marca);
	mando.setId_m(id_m);
	
	}

	@Test
	public void testToString() {
		String toString = String.format(nombre +" (" + estado + ") " + marca + " " + "%.2f" + "eur", precio) ;
		System.out.println(toString);
		System.out.println(mando.toString());
		assertEquals(mando.toString(), toString);
	}

	@Test
	public void testMandoIntStringEstadoProductoDoubleMarca() {
		Mando newMando = new Mando(id,nombre,tp,estado,precio,marca,id_m);
		assertNotNull(newMando);
		assertEquals(newMando.getNombre(), nombre);
		assertEquals(newMando.getId(), id,0);
		assertEquals(newMando.getTp(), tp);
		
		assertEquals(newMando.getEstado(), estado);
		assertEquals(newMando.getPrecio(), precio,0);
		assertEquals(newMando.getMarca(), marca);
		assertEquals(newMando.getId_m(), id_m,0);
			
	}

	@Test
	public void testMando() {
		Mando newMando2 = new Mando();
		assertNotNull(newMando2);
		assertEquals(newMando2.getNombre(), "Sin nombre");
		assertEquals(newMando2.getId(), 0,0);
		assertEquals(newMando2.getTp(), TipoProducto.VIDEOJUEGO);
		assertEquals(newMando2.getEstado(), estado.PRIMERA_MANO);
		assertEquals(newMando2.getPrecio(), 0,0);
		assertEquals(newMando2.getMarca(), marca.PLAYSTATION);
		assertEquals(newMando2.getId_m(), 1,0);
	}

	@Test
	public void testGetEstado() {
		assertEquals(mando.getEstado(), estado);
	}

	@Test
	public void testSetEstado() {
		EstadoProducto newEstado = EstadoProducto.SEGUNDA_MANO;
		assertEquals(mando.getEstado(), estado);
		mando.setEstado(newEstado);
		assertEquals(mando.getEstado(), newEstado);
	}

	@Test
	public void testSetPrecio() {
		Double newPrecio = 2.0;
		assertEquals(mando.getPrecio(), precio,0);
		mando.setPrecio(newPrecio);
		assertEquals(mando.getPrecio(), newPrecio,0);
	}

	@Test
	public void testGetPrecio() {
		assertEquals(mando.getPrecio(), precio,0);
	}

	@Test
	public void testGetMarca() {
		assertEquals(mando.getMarca(), marca);
	}

	@Test
	public void testSetMarca() {
		Marca newMarca = Marca.PLAYSTATION;
		assertEquals(mando.getMarca(), marca);
		mando.setMarca(newMarca);
		assertEquals(mando.getMarca(), newMarca);
	}
	
	@Test
	public void testGetId_m() {
		assertEquals(mando.getId_m(), id_m,0);
	}

	@Test
	public void testSetId_m() {
		Integer newId_m = 3;
		assertEquals(mando.getId_m(), id_m,0);
		mando.setId_m(newId_m);
		assertEquals(mando.getId_m(), newId_m,0);
	}

}
