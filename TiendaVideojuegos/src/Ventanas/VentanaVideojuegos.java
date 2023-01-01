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
	protected JButton sinFiltros;
	
	protected JLabel filtros;
	
	protected JComboBox<EstadoProducto> estado;
	protected JComboBox<Genero> genero;
	
	protected JComboBox<Integer> fecha;
	
	
	protected DefaultTableModel mDV = new DefaultTableModel(
			new Object [] {"Nombre", "Genero", "Estado","Año","Precio"},0
			);
	protected JTable tV = new JTable(mDV);
	
	protected List<Videojuego> listaVideojuego;
	
	
	
	public VentanaVideojuegos() {
		
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				cargarVideojuegos();
				cargarVideojuegos();
			}
			
			
				
		});
		
		
		 
		Container cp = this.getContentPane();
		
		carrito = new JButton("Carrito");
		atras = new JButton("Atras");
		añadirCarrito = new JButton("Añadir al Carrito");
		filtrar = new JButton("Filtrar");
		sinFiltros = new JButton("Quitar Filtros");
		
		filtros = new JLabel("FILTROS");
		
		estado = new JComboBox<>(EstadoProducto.values());
		genero = new JComboBox<>(Genero.values());
		
		fecha = new JComboBox<>();
		
		for (int i = 1970; i < 2024; i++) {
			fecha.addItem(i);
		};
		
		
		cp.setLayout(new FlowLayout());
		
		cp.add(new JScrollPane(tV));
		
		JPanel JP1 = new JPanel();
		JP1.setLayout(new GridLayout(6,1));
		
		
		
		
		
		JP1.add(filtros);
		JP1.add(genero);
		JP1.add(estado);
		JP1.add(fecha);
		JP1.add(filtrar);
		JP1.add(sinFiltros);
		
		
		cp.add(JP1);
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(carrito, BorderLayout.NORTH);
		JP2.add(añadirCarrito, BorderLayout.CENTER);
		JP2.add(atras, BorderLayout.SOUTH);
		
		cp.add(JP2);
		
		filtrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EstadoProducto ep = EstadoProducto.valueOf(String.valueOf(estado.getSelectedItem()));
				Genero g = Genero.valueOf(String.valueOf(genero.getSelectedItem()));
				Integer a = Integer.parseInt(String.valueOf(fecha.getSelectedItem()));
				filtrarVideojuego(ep, g, a);
			}
		});
		
		carrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				dispose();
				
			}
		});
		
		añadirCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cargarVideojuegos();
				Main.vMP.setVisible(true);
				dispose();
			}
		});
		
		sinFiltros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cargarVideojuegos();
			}
		});
		
		
		this.setVisible(false);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("VENTANA VIDEOJEUGOS");
		
		tV.getDe
		
	}
	
	
	
	protected void cargarVideojuegos() {
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		while (mDV.getRowCount() > 0) {
			mDV.removeRow( 0 );
		}
		
		for (Videojuego videojuego : listaVideojuego) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		}
	}
	
	protected void filtrarVideojuego(EstadoProducto ep, Genero g, Integer a) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getAnyo() == a && videojuego.getEstado() == ep && videojuego.getGenero() == g) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
	}
	
}
