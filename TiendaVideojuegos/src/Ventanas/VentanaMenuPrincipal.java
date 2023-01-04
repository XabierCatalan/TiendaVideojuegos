package Ventanas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ClasesPrincipales.GestorBD;

public class VentanaMenuPrincipal extends JFrame{
	
	protected JLabel mn;
	
	protected JButton comVideojuego;
	protected JButton comConsola;
	protected JButton calcPrecioVentas;
	protected JButton gestReparaciones;
	
	protected JButton atras;
	protected JButton carrito;
	
	public VentanaMenuPrincipal() {
		
		Container cp = this.getContentPane();
		
		mn = new JLabel("MENU PRINCIPAL");
		
		comVideojuego = new JButton("COMPRAR VIDEOJUEGOS");
		comConsola = new JButton("COMPRAR CONSOLAS Y MANDOS");
		calcPrecioVentas = new JButton("CALCULAR PRECIO VENTAS");
		gestReparaciones = new JButton("GESTION DE REPARACIONES");
		
		atras = new JButton("Atras");
		carrito = new JButton("Carrito");
		
		cp.setLayout(new BorderLayout());
		
		JPanel JP1 = new JPanel();
		JP1.setLayout(new BorderLayout());
		
		JPanel JP3 = new JPanel();
		JP3.setLayout(new FlowLayout());
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new GridLayout(5,1));
		
		
		
		JP1.add(carrito, BorderLayout.EAST);
		JP1.add(atras, BorderLayout.WEST);
		
		JP3.add(mn);
		
		JP2.add(JP3);
		JP2.add(comVideojuego);
		JP2.add(comConsola);
		JP2.add(gestReparaciones);
		JP2.add(calcPrecioVentas);
		
		cp.add(JP1 , BorderLayout.NORTH);
		cp.add(JP2 , BorderLayout.CENTER);
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GestorBD ges = new GestorBD();
				System.out.println(ges.getLogedUser());
				Main.vM.setVisible(true);
				dispose();
				
			}
		});
		
		
		carrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.vC.setVisible(true);
				dispose();
			}
		});
		
		
		
		comVideojuego.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Main.vV.setVisible(true);
				Main.vV.genero.setSelectedItem("SIN FILTROS");
				Main.vV.estado.setSelectedItem("SIN FILTROS");
				Main.vV.fecha.setSelectedItem("ELIGE UN AÑO");
				dispose();
			}
		});
		
		
		
		comConsola.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Main.vCM.setVisible(true);
				Main.vCM.estado.setSelectedItem("SIN FILTROS");
				Main.vCM.marca.setSelectedItem("SIN FILTROS");
				Main.vCM.tipoProducto.setSelectedItem("SIN FILTROS");
				dispose();
			}
		});
		
		
		
		gestReparaciones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				Main.vS.setVisible(true);
				dispose();
			}
		});
		
		
		
		calcPrecioVentas.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				dispose();
			}
		});
		
		
		
		
		
		this.setSize(250,250);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
	}
	
	


}
