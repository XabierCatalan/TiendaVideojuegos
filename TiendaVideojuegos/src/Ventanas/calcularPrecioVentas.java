package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ClasesPrincipales.*;

public class calcularPrecioVentas extends JFrame{
		protected JButton atras;
		protected JButton buscar;

		
		protected JSlider slider;
		
		protected JTextField textovalor;
		
		
		protected DefaultListModel<Pagable> DLM;
		protected JList<Pagable> Lista;
	
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
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		this.setVisible(false);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Calcular Precio Ventas");
		this.setResizable(false);
	}
	
	public ArrayList<ArrayList<Producto>> PosiblesComprasPorPrecio (double precioFinal, double PrecioActual, 
			ArrayList<Producto> productos, ArrayList<ArrayList<Producto>> productosFinal) {
		
		if (PrecioActual > precioFinal) {
			productosFinal.remove(productosFinal.size()-1);
			return productosFinal;
		} else {
			
			for (Producto producto : productos) {
				if (producto.getTp() == TipoProducto.VIDEOJUEGO) {
					Videojuego v = (Videojuego) producto;
					
					Double pA = PrecioActual + v.getPrecio();
					
				
					
					
					

				} else if (producto.getTp() == TipoProducto.CONSOLA) {
					
				} else {
					
				}
				
			}
			
		}
		

	return null;	
	}
}
