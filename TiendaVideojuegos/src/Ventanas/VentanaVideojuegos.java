package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ClasesPrincipales.*;

public class VentanaVideojuegos extends JFrame {
	protected JButton carrito;
	protected JButton atras;
	protected JButton añadirCarrito;
	
	protected JLabel filtros;
	
	protected JComboBox<EstadoProducto> estado;
	protected JComboBox<Genero> genero;
	
	protected JTextField fecha;
	
	protected JTable videojuegos;
	
	public VentanaVideojuegos() {
		 
		Container cp = this.getContentPane();
		
		carrito = new JButton("Carrito");
		atras = new JButton("Atras");
		añadirCarrito = new JButton("Añadir al Carrito");
		
		filtros = new JLabel("FILTROS");
		
		estado = new JComboBox<>();
		genero = new JComboBox<>();
		
		fecha = new JTextField();
		
		videojuegos = new JTable();
		
		cp.setLayout(new FlowLayout());
		
		cp.add(videojuegos);
		
		JPanel JP1 = new JPanel();
		JP1.setLayout(new GridLayout(5,1));
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(añadirCarrito, BorderLayout.SOUTH);
		
		JP1.add(filtros);
		JP1.add(genero);
		JP1.add(estado);
		JP1.add(fecha);
		JP1.add(JP2);
		
		cp.add(JP1);
		
		JPanel JP3 = new JPanel();
		JP3.setLayout(new BorderLayout());
		
		JP3.add(carrito, BorderLayout.NORTH);
		JP3.add(atras, BorderLayout.SOUTH);
		
		cp.add(JP3);
		
		
		this.setVisible(false);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
}
