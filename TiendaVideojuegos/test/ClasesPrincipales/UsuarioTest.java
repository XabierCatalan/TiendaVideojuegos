package ClasesPrincipales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	Usuario usuario;
	protected int id = 1;
	protected String nombre = "nombre";
	protected String email = "email";
	protected String contrasenya = "contrasenya";
	protected String telefono = "111111111";
	
	@Before
	public void SetUp() {
		usuario = new Usuario();
		usuario.setId(id);
		usuario.setNombre(nombre);
		usuario.setEmail(email);
		usuario.setContrasenya(contrasenya);
		usuario.setTelefono(telefono);
		
	}
	
	@Test
	public void testGetId() {
		assertEquals(usuario.getId(), id,0);
	}

	@Test
	public void testSetId() {
		Integer newId = 1;
		assertEquals(usuario.getId(), id,0);
		usuario.setId(newId);
		assertEquals(usuario.getId(), newId,0);
	}

	@Test
	public void testUsuarioStringStringStringString() {
		Usuario newUsuario = new Usuario(id,nombre, email, contrasenya, telefono);
		assertNotNull(newUsuario);
		assertEquals(newUsuario.getNombre(), nombre);
		assertEquals(newUsuario.getEmail(), email);
		assertEquals(newUsuario.getContrasenya(), contrasenya);
		assertEquals(newUsuario.getTelefono(), telefono);
	}

	@Test
	public void testUsuario() {
		Usuario newUsuario = new Usuario();
		assertNotNull(newUsuario);
		assertEquals(newUsuario.getNombre(), "");
		assertEquals(newUsuario.getEmail(), "");
		assertEquals(newUsuario.getContrasenya(), "");
		assertEquals(newUsuario.getTelefono(), "");


	}

	@Test
	public void testGetNombre() {
		assertEquals(usuario.getNombre(), nombre);
	}

	@Test
	public void testSetNombre() {
		String newNombre = "nombre2";
		assertEquals(usuario.getNombre(), nombre);
		usuario.setNombre(newNombre);
		assertEquals(usuario.getNombre(), newNombre);

	}

	@Test
	public void testGetEmail() {
		assertEquals(usuario.getEmail(), email);
	}

	@Test
	public void testSetEmail() {
		String newEmail = "email2";
		assertEquals(usuario.getEmail(), email);
		usuario.setEmail(newEmail);
		assertEquals(usuario.getEmail(), newEmail);
	}

	@Test
	public void testGetContrasenya() {
		assertEquals(usuario.getContrasenya(), contrasenya);
	}

	@Test
	public void testSetContrasenya() {
		String newContrasenya = "contrasenya2";
		assertEquals(usuario.getContrasenya(), contrasenya);
		usuario.setContrasenya(newContrasenya);
		assertEquals(usuario.getContrasenya(), newContrasenya);
	}

	@Test
	public void testGetTelefono() {
		assertEquals(usuario.getTelefono(), telefono);
	}

	@Test
	public void testSetTelefono() {
		String newTelefono = "999999999";
		assertEquals(usuario.getTelefono(), telefono);
		usuario.setTelefono(newTelefono);
		assertEquals(usuario.getTelefono(), newTelefono);
	}

	@Test
	public void testToString() {
		String toString = nombre + ";" + email + ";" + contrasenya + ";" + telefono ;
		System.out.println(toString);
		System.out.println(usuario.toString());
		assertEquals(usuario.toString(), toString);

	}

}
