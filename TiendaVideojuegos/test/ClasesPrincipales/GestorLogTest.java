package ClasesPrincipales;

import static org.junit.Assert.*;

import org.junit.Test;

public class GestorLogTest {

	@Test
	public void test() {
		GestorLog.fine("## Test de GestorLog ##");
		GestorLog.fine("log fine");
		GestorLog.info("log info");
		GestorLog.warning("log warning");
		// fine info se imprimen en negro en system.out
		// warning se imprime en rojo
		// todos los test se escriben en el fichero default.log
		assertTrue(true);
	}

}
