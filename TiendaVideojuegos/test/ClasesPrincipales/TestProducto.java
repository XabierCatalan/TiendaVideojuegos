package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestProducto {
	Producto producto;
 protected String nombre = "nombre1";
 protected int id = 0;
 protected TipoProducto tp = TipoProducto.CONSOLA;
 
 @Before
 public void SetUp() {
	 producto = new Producto();
	 producto.setId(id);
	 producto.setNombre(nombre);
	 producto.setTp(tp);
 }
	@Test
	public void testProductoIntString() {
		Producto newProducto = new Producto(id, nombre, tp);
		assertNotNull(newProducto);
		assertEquals(newProducto.getId(), id,0);
		assertEquals(newProducto.getNombre(), nombre);
		assertEquals(newProducto.getTp(), tp);
	}

	@Test
	public void testProducto() {
		Producto newProducto2 = new Producto();
		assertNotNull(newProducto2);
		assertEquals(newProducto2.getNombre(), "Sin nombre");
		assertEquals(newProducto2.getId(), 0,0);
		assertEquals(newProducto2.getTp(), TipoProducto.VIDEOJUEGO);
		
	}

	@Test
	public void testGetId() {
		assertEquals(producto.getId(), id,0);
	}

	@Test
	public void testSetId() {
		Integer newId = 1;
		assertEquals(producto.getId(), id,0);
		producto.setId(newId);
		assertEquals(producto.getId(), newId,0);
	}

	@Test
	public void testGetNombre() {
		assertEquals(producto.getNombre(), nombre);
	}

	@Test
	public void testSetNombre() {
		String newNombre = "nombre2";
		assertEquals(producto.getNombre(), nombre);
		producto.setNombre(newNombre);
		assertEquals(producto.getNombre(), newNombre);
	}
	
	@Test
	public void testGetTp() {
		assertEquals(producto.getTp(), tp);
	}
	
	@Test
	public void testSetTp() {
		TipoProducto newTipo = TipoProducto.MANDO;
		assertEquals(producto.getTp(),tp);
		producto.setTp(newTipo);
		assertEquals(producto.getTp(), newTipo);
	}
	

	@Test
	public void testToString() {
		String toString = "Producto " + nombre + "(id: " + id + ")";
		System.out.println(toString);
		System.out.println(producto.toString());
		assertEquals(producto.toString(), toString);
	}

}
