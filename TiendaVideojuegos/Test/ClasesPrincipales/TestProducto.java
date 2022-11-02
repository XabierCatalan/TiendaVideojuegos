package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestProducto {

	@Test
	public void testProductoIntString() {
		
		
	}

	@Test
	public void testProducto() {
		Producto newProducto = new Producto();
		assertNotNull(newProducto);
		assertEquals(newProducto.getId(), 0,0);
		assertEquals(newProducto.getNombre(), "");
	}

	@Test
	public void testGetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetId() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

}
