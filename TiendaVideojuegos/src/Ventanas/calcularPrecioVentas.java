package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ClasesPrincipales.*;

public class calcularPrecioVentas extends JFrame{
		protected JButton atras;
		protected JButton buscar;

		
		protected JSlider slider;
		
		protected JTextField textovalor;
		
		
		protected DefaultListModel<List<Producto>> DLM;
		protected JList<List<Producto>> Lista;
	
	public calcularPrecioVentas() {
		
		Container cp = this.getContentPane();
		
		atras = new JButton("Atras");
		buscar = new JButton("Buscar");
		
		
		slider = new JSlider(0,1000,500);
		slider.setMajorTickSpacing(200);
		slider.setMinorTickSpacing(50);
		
		slider.setPaintTicks(true);
		
		slider.setFont(new Font("Serif", Font.ITALIC, 12));
		
		slider.setPaintLabels(true);
		
		textovalor = new JTextField(String.valueOf(slider.getValue()));
		textovalor.setFont(new Font("Serif", Font.ITALIC, 12));
		textovalor.setEditable(false);
		
		DLM = new DefaultListModel<>();
		Lista = new JList<>(DLM);
		Lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane ScrollPane = new JScrollPane(Lista);
		
	
		//el panel principal de la ventana
		JPanel JP1 = new JPanel();
		
		JP1.setLayout(new BorderLayout());
		
		//panel de arriba
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(atras, BorderLayout.WEST);
		
		//panel izq
		JPanel JP3 = new JPanel();
		JP3.setLayout(new BorderLayout());
		
		JP3.add(slider, BorderLayout.CENTER);
		JP3.add(textovalor, BorderLayout.SOUTH);
		
		//PANEL DE ABAJO
		JPanel JP4 = new JPanel();
		JP4.setLayout(new BorderLayout());
		JP4.add(buscar,BorderLayout.EAST);
		
		
		JPanel JP5 = new JPanel();
		JP5.setLayout(new FlowLayout());
		JP5.add(ScrollPane);
		
		
		JP1.add(JP2, BorderLayout.NORTH);
		JP1.add(JP3, BorderLayout.WEST);
		JP1.add(JP4, BorderLayout.SOUTH);
		JP1.add(JP5, BorderLayout.CENTER);
		cp.add(JP1);
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.vMP.setVisible(true);
				dispose();
				
			}
		});
		
		slider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				textovalor.setText(String.valueOf(slider.getValue()));
				
			}
		});
		
		buscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Producto> productos = Main.bd.obtenerDatosProducto();
				System.out.println(productos);
				List<List<Producto>> p = combinacionesPorPrecio(productos, java.lang.Double.parseDouble(textovalor.getText()));
				for (List<Producto> list : p) {
					DLM.addElement(list);
				}
				
			}
		});
		
		
		
		this.setVisible(false);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Calcular Precio Ventas");
		this.setResizable(false);
	}
	
	public static void comb (List<List<Producto>> productosFinal, List<Producto> productos, 
			double dinerico, double actual, List<Producto> temporal ) {
		
		if (dinerico < 0) {
			return;
		} else if (actual > dinerico) {
			temporal.remove(temporal.size()-1);
			temporal.sort((Producto p1, Producto p2) -> Integer.compare(p1.getId(), p2.getId()));
			if (!productosFinal.contains(temporal)) {
				productosFinal.add(temporal);
			}
		} else {
			for (Producto producto : productos) {
				temporal.add(producto);
				
				
				
				if(producto.getTp() == TipoProducto.VIDEOJUEGO) {
					List<Videojuego> lista = Main.bd.obtenerDatosVideojuegos();
					for (Videojuego v : lista) {
						if (v.getId() == producto.getId()) {
							comb(productosFinal, productos, dinerico, actual + v.getPrecio(), temporal);
						}
					}
					//System.out.println(v);
					//comb(productosFinal, productos, dinerico, actual + v.getPrecio(), temporal);
					
					
				} else if (producto.getTp() == TipoProducto.CONSOLA) {
					List<Consola> lista = Main.bd.obtenerDatosConsolas();
					for (Consola c : lista) {
						if (c.getId() == producto.getId()) {
							comb(productosFinal, productos, dinerico, actual + c.getPrecio(), temporal);
						}
					}
					//comb(productosFinal, productos, dinerico, actual + c.getPrecio(), temporal);
					
					
					
				} else {
					List<Mando> lista = Main.bd.obtenerDatosMandos();
					for (Mando m : lista) {
						if (m.getId() == producto.getId()) {
							comb(productosFinal, productos, dinerico, actual + m.getPrecio(), temporal);
						}
					}
					//comb(productosFinal, productos, dinerico, actual + m.getPrecio(), temporal);
					
					
					
				}
				
				temporal.remove(temporal.size()-1);
			}
		}
		
	}
	
	public static List<List<Producto>> combinacionesPorPrecio (List<Producto> productos, double dinerico) {
		List<List<Producto>> resultado = new ArrayList<>();
		
		double actual = 0;
		
		comb(resultado, productos, dinerico, actual, new ArrayList<>());
		
		return resultado;
	}
}
