package Ventanas;

import ClasesPrincipales.GestorBD;
import ClasesPrincipales.TiendaGame;

public class Main {

	static VentanaMain vM = new VentanaMain();
	static VentanaInicioSesion vIS = new VentanaInicioSesion();
	static VentanaCrearCuenta vCC = new VentanaCrearCuenta();
	static VentanaMenuPrincipal vMP = new VentanaMenuPrincipal();
	static VentanaConsolaMandos vCM = new VentanaConsolaMandos();
	static VentanaVideojuegos vV = new VentanaVideojuegos();
	static TiendaGame tg = new TiendaGame();
	static GestorBD bd = new GestorBD();
	
	public static void main(String[] args) {
		vM.setVisible(true);
		
		

	}

}
	