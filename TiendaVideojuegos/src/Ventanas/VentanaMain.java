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
		
		cp.add(new JLabel(new ImageIcon("game.png")), BorderLayout.CENTER);
		
		JPanel abajo = new JPanel();
		
		botonIniciarSesion = new JButton("Iniciar Sesion");
		botonCrearCuenta = new JButton("Crear Nueva Cuenta");
		
		botonIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ventanaInicioSesion.setVisible(true);
				
			}
		});
		
		botonCrearCuenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ventanaCrearCuenta.setVisible(true);
				
			}
		});
		
		abajo.add(botonIniciarSesion);
		abajo.add(botonCrearCuenta);
		
		cp.add(abajo, BorderLayout.SOUTH);
		
		this.setTitle("TiendaGame");
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		VentanaMain v = new VentanaMain();
		v.setVisible(true);
	}

}
