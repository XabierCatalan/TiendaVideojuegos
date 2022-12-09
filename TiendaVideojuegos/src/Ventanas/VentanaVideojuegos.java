package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ClasesPrincipales.*;

public class VentanaVideojuegos extends JFrame {
	protected JButton carrito;
	protected JButton atras;
	protected JButton añadirCarrito;
	protected JButton filtrar;
	
	protected JLabel filtros;
	
	protected JComboBox<EstadoProducto> estado;
	protected JComboBox<Genero> genero;
	
	protected JTextField fecha;
	
	
	protected DefaultTableModel mDV = new DefaultTableModel(
			new Object [] {"Nombre", "Genero", "Estado","Año","Precio"},0
			);
	protected JTable tV = new JTable(mDV);
	
	protected List<Videojuego> listaVideojuego;
	
	
	
	public VentanaVideojuegos() {
		
		this.cargarVideojuegos();
		 
		Container cp = this.getContentPane();
		
		carrito = new JButton("Carrito");
		atras = new JButton("Atras");
		añadirCarrito = new JButton("Añadir al Carrito");
		filtrar = new JButton("Filtrar");
		
		filtros = new JLabel("FILTROS");
		
		estado = new JComboBox<>(EstadoProducto.values());
		genero = new JComboBox<>(Genero.values());
		
		fecha = new JTextField();
		
		
		
		cp.setLayout(new FlowLayout());
		
		cp.add(new JScrollPane(tV));
		
		JPanel JP1 = new JPanel();
		JP1.setLayout(new GridLayout(6,1));
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(añadirCarrito, BorderLayout.SOUTH);
		
		JP1.add(filtros);
		JP1.add(genero);
		JP1.add(estado);
		JP1.add(fecha);
		JP1.add(filtrar);
		JP1.add(JP2);
		
		cp.add(JP1);
		
		JPanel JP3 = new JPanel();
		JP3.setLayout(new BorderLayout());
		
		JP3.add(carrito, BorderLayout.NORTH);
		JP3.add(añadirCarrito, BorderLayout.CENTER);
		JP3.add(atras, BorderLayout.SOUTH);
		
		cp.add(JP3);
		
		
		this.setVisible(false);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	
	protected void cargarVideojuegos() {
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		for (Videojuego videojuego : listaVideojuego) {
			this.mDV.addRow(new Object[] {videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), videojuego.getPrecio()});
		}
	}
	
}
