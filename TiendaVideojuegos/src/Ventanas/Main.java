package Ventanas;

import java.util.logging.Logger;

import ClasesPrincipales.GestorBD;
import ClasesPrincipales.GestorLog;
import ClasesPrincipales.TiendaGame;

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
	static VentanaGestionPedidos vGP = new VentanaGestionPedidos();
    
	public static void main(String[] args) {
		
		GestorLog.fine("##### Comienza el programa #####");
		
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
	