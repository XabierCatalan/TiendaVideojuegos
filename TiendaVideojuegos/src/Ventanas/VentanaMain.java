package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ClasesPrincipales.*;

public class VentanaMain extends JFrame{
	protected JButton botonIniciarSesion;
	protected JButton botonCrearCuenta;
	
	
	
	public VentanaMain() {
		
		
		
		Container cp = this.getContentPane();
		
		cp.add(new JLabel(new ImageIcon("game.png")), BorderLayout.CENTER);
		
		JPanel abajo = new JPanel();
		
		botonIniciarSesion = new JButton("Iniciar Sesion");
		botonCrearCuenta = new JButton("Crear Nueva Cuenta");
		
		botonIniciarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.vIS.setVisible(true);
				VentanaMain.this.setVisible(false);
				
			}
		});
		
		botonCrearCuenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.vCC.setVisible(true);
				VentanaMain.this.setVisible(false);
				
			}
		});
		
		abajo.add(botonIniciarSesion);
		abajo.add(botonCrearCuenta);
		
		cp.add(abajo, BorderLayout.SOUTH);
		
		this.setTitle("TiendaGame");
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	

}
