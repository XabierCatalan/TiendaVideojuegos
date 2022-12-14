package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class VentanaMain extends JFrame{
	protected JButton botonIniciarSesion;
	protected JButton botonCrearCuenta;
	protected JButton botonEntrarTienda;
	
	public VentanaMain() {
		
		//Main.bd.insertarDatosCarrito(Main.tg.LeerCSVCarritos());

		VentanaMain.this.setResizable(false);
		Container cp = this.getContentPane();
		ImageIcon imageIcon = new ImageIcon("game.png");
		Image image = new ImageIcon("game.png").getImage();
		Image newImg = image.getScaledInstance(500, 215, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newImg);
		cp.add(new JLabel(imageIcon), BorderLayout.NORTH);
		
		JPanel abajo = new JPanel();
		JPanel abajo2 = new JPanel();
		
		botonIniciarSesion = new JButton("Iniciar Sesion");
		botonCrearCuenta = new JButton("Crear Nueva Cuenta");
		botonEntrarTienda = new JButton("Entar en la tienda");
		
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
		
		botonEntrarTienda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.vMP.setVisible(true);
				VentanaMain.this.setVisible(false);
			}
		});
		
		abajo.add(botonIniciarSesion);
		abajo.add(botonCrearCuenta);
		abajo2.add(botonEntrarTienda);
		
		cp.add(abajo, BorderLayout.CENTER);
		cp.add(abajo2, BorderLayout.SOUTH);
		
		this.setTitle("TiendaGame");
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	

}
