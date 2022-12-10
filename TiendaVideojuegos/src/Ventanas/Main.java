package Ventanas;

import ClasesPrincipales.GestorBD;
import ClasesPrincipales.TiendaGame;
import java.util.logging.Logger;

public class Main {

	static TiendaGame tg = new TiendaGame();
	static GestorBD bd = new GestorBD();
	static VentanaMain vM = new VentanaMain();
	static VentanaInicioSesion vIS = new VentanaInicioSesion();
	static VentanaCrearCuenta vCC = new VentanaCrearCuenta();
	static VentanaMenuPrincipal vMP = new VentanaMenuPrincipal();
	static VentanaConsolaMandos vCM = new VentanaConsolaMandos();
	static VentanaVideojuegos vV = new VentanaVideojuegos();
	static VentanaCarrito vC = new VentanaCarrito();
	static VentanaServicio vS = new VentanaServicio();
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    /*
     * NIVELES LOGGER
     * OFF		0	No logging
	 * FATAL	100	The application is unusable. Action needs to be taken immediately.
	 * ERROR	200	An error occurred in the application.
     * WARN		300	Something unexpected—though not necessarily an error—happened and needs to be watched.
     * INFO		400	A normal, expected, relevant event happened.
     * DEBUG	500	Used for debugging purposes
     * TRACE	600	Used for debugging purposes—includes the most detailed information
     */

	
	public static void main(String[] args) {
		
		LOG.info("Programa iniciado");		
		vM.setVisible(true);
		bd.borrarBBDDCarrito();
		bd.borrarBBDDConsola();
		bd.borrarBBDDMando();
		bd.borrarBBDDVideojuego();
		bd.borrarBBDDUsuario();
		
		bd.CrearBBDDCarrito();
		bd.CrearBBDDConsola();
		bd.CrearBBDDMando();
		bd.CrearBBDDUsuario();
		bd.CrearBBDDVideojuego();
		
		bd.insertarDatosConsola(Main.tg.LeerCSVconsolas());
		bd.insertarDatosMando(Main.tg.LeerCSVmandos());
		bd.insertarDatosVideojuego(Main.tg.LeerCSVvideojuego());
		bd.insertarDatosUsuario(Main.tg.LeerCSVUsuarios());
		
		

	}

}
	