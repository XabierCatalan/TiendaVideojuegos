package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ClasesPrincipales.*;

public class VentanaMain extends JFrame{
	protected JButton botonIniciarSesion;
	protected JButton botonCrearCuenta;
	protected VentanaInicioSesion ventanaInicioSesion;
	protected VentanaCrearCuenta ventanaCrearCuenta;
	
	public VentanaMain() {
		ventanaInicioSesion = new VentanaInicioSesion();
		ventanaCrearCuenta = new VentanaCrearCuenta	();
		
		
		Container cp = this.getContentPane();
		
		cp.add(new JLabel(new ImageIcon("deustea.png")), BorderLayout.CENTER);
		
	}

}
