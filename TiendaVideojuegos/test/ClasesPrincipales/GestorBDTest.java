package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Test;

public class GestorBDTest {

	@Test
	public void testIniciarSesion() {
		System.out.println("### testIniciarSesion");
		//prueba 1 usuario no existe
		String inMail = "prueba";
		String inPass = "1234";
		GestorBD gestorBD = new GestorBD();
		String msg = gestorBD.iniciarSesion(inMail,inPass);
		System.out.println(msg);
		assertEquals("El usuario indicado no existe", msg);
		
		// prueba 2 usuario correcto
		inMail = "unai.gonzalez@opendeusto.es";
		inPass = "1121";
		msg = gestorBD.iniciarSesion(inMail,inPass);
		assertEquals("OK", msg);

		//prueba 3 usuario guardado en GestorDB
		assertEquals("unai.gonzalez@opendeusto.es", gestorBD.logedUser.getEmail());
	}
	
	@Test
	public void crearCuenta() {
		System.out.println("### crearCuenta");
		// prueba 1 crear usuario
		GestorBD gestorBD = new GestorBD();
		gestorBD.crearCuenta("1234","1234","1234","1234");
		//gestorBD.iniciarSesion(null, null);
		System.out.println(gestorBD.logedUser);
		assertEquals("1234", gestorBD.logedUser.getEmail());
	}

}
