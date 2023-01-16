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
		
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(atras, BorderLayout.NORTH);
		JP2.add(slider, BorderLayout.CENTER);
		JP2.add(textovalor, BorderLayout.SOUTH);
		JP2.add(buscar,BorderLayout.EAST);
		
		JPanel JP3 = new JPanel();
		JP3.setLayout(new FlowLayout());
		JP3.add(ScrollPane);
		
		
		JP1.add(JP2, BorderLayout.WEST);
		JP1.add(JP3, BorderLayout.CENTER);
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
//				for (List<Producto> list : p) {
//					DLM.addElement(list);
//				}
				
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
					Videojuego v = Main.bd.buscarVideojuegoPorID_P(producto.getId());
					System.out.println(v);
					//comb(productosFinal, productos, dinerico, actual + v.getPrecio(), temporal);
					//temporal.remove(temporal.size()-1);
					
				} else if (producto.getTp() == TipoProducto.CONSOLA) {
					Consola c = Main.bd.buscarConsolaPorID_P(producto.getId());
					System.out.println(c);
					//comb(productosFinal, productos, dinerico, actual + c.getPrecio(), temporal);
					//temporal.remove(temporal.size()-1);
					
					
				} else {
					Mando m = Main.bd.buscarMandoPorID_P(producto.getId());
					System.out.println(m);
					//comb(productosFinal, productos, dinerico, actual + m.getPrecio(), temporal);
					//temporal.remove(temporal.size()-1);
					
					
				}
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
