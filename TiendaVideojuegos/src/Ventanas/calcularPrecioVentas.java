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
		
		
		slider = new JSlider(0,500,250);
		slider.setMajorTickSpacing(100);
		slider.setMinorTickSpacing(25);
		
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
		JP5.setLayout(new BorderLayout());
		JP5.add(ScrollPane, BorderLayout.CENTER);
		
		
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
				DLM.removeAllElements();
				List<Producto> productos = Main.bd.obtenerDatosProducto();
				//System.out.println(productos);
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
	
//	public static void comb (List<List<Producto>> productosFinal, List<Producto> productos, 
//			double dinerico, List<Producto> temporal ) {
//		
//		//Si el dinero del que disponemos en negativo se para 
//		
//		 if (0 > dinerico) {
//			if (temporal.size() > 0) {
//				temporal.remove(temporal.size()-1);
//
//				temporal.sort((Producto p1, Producto p2) -> Integer.compare(p1.getId(), p2.getId()));
//				
//				if (!productosFinal.contains(temporal)) {
//					productosFinal.add(temporal);
//				}
//			}
//			
//			
//		} else {
//			for (Producto p : productos) {
//				
//				if (!temporal.contains(p)) {
//				
//				temporal.add(p);
//				
//				if (p.getTp() == TipoProducto.MANDO) {
//					Mando m = Main.bd.buscarMandoPorID_P(p.getId());
//					comb(productosFinal, productos, dinerico - m.getPrecio(), temporal);
//				}   else if (p.getTp() == TipoProducto.CONSOLA) {
//					Consola c = Main.bd.buscarConsolaPorID_P(p.getId());
//					comb(productosFinal, productos, dinerico - c.getPrecio(), temporal);
//				} else if ((p.getTp() == TipoProducto.VIDEOJUEGO)){
//					Videojuego v = Main.bd.buscarVideojuegoPorID_P(p.getId());
//					comb(productosFinal, productos, dinerico - v.getPrecio(), temporal);
//				}
//				//System.out.println(temporal);
//				///System.out.println(temporal.size());
//				
//				if (temporal.size() != 0) {
//					System.out.println(temporal);
//				temporal.remove(temporal.size()-1);
//				} else {
//					System.out.println("hola");
//				}
//			}
//		}
//			
//	}
//		
//		
//		
//	}
	
	public static void comb (List<List<Producto>> productosFinal, List<Producto> productos, 
			double dinerico, double devuelta, List<Producto> temporal ) {
		if (dinerico < 0) {
			return;
		} else if(dinerico < devuelta) {
	    	temporal.sort((Producto o1, Producto o2) -> Integer.compare(o1.getId(), o2.getId()));
	    	if (!productosFinal.contains(temporal)) {
	        	System.out.println(temporal);
	            productosFinal.add(new ArrayList<>(temporal));        	
	    	}
	    	
		} else {
			for (Producto p : productos) {
				temporal.add(p);
				
				if (p.getTp() == TipoProducto.MANDO) {
					Mando m = Main.bd.buscarMandoPorID_P(p.getId());
		    		comb(productosFinal, productos, dinerico - m.getPrecio(), devuelta, temporal);

					
				} else if (p.getTp() == TipoProducto.CONSOLA) {
					Consola c = Main.bd.buscarConsolaPorID_P(p.getId());
		    		comb(productosFinal, productos, dinerico - c.getPrecio(), devuelta, temporal);
					
				} else if (p.getTp() == TipoProducto.VIDEOJUEGO) {
					Videojuego v = Main.bd.buscarVideojuegoPorID_P(p.getId());
		    		comb(productosFinal, productos, dinerico - v.getPrecio(), devuelta, temporal);
		    		
				}
				
				temporal.remove(temporal.size()-1);
			}
			
		}
	
	}
	
	
	public static List<List<Producto>> combinacionesPorPrecio (List<Producto> productos, double dinerico) {
		List<List<Producto>> resultado = new ArrayList<>();
		
		double devuelta = dinerico - (dinerico / 3);
		
		comb(resultado, productos, dinerico, devuelta, new ArrayList<Producto>());
		
		return resultado;
	}
}



///**
// * Crea una lista de listas de productos que se pueden comparar dada una 
// * cantidad máxima de dinero y una cantidad máxima de sobrante. 
// * @param result List<List<Producto>> listas de productos que se pueden comprar.
// * @param elementos List<Producto> con los productos que se pueden comprar.
// * @param disponible double con el importe restante por gastar.
// * @param sobranteMax double con el importe sobrante máximo.
// * @param temp List<Producto> lista temporal de productos que se pueden comprar.
// */
//private static void combinaciones(List<List<Producto>> result, 
//								  List<Producto> elementos, 
//								  double disponible,
//								  double sobranteMax,
//								  List<Producto> temp) {
//    // Caso base. Si el importe disponible es negativo se detiene la recursividad
//	if (disponible < 0) {
//    	return;
//    // Caso base. Si el importe disponible es menor que el sobrante máximo
//    } else if (disponible < sobranteMax) {
//    	//Se reordena la lista temporal de productos para evitar combinaciones equivalentes
//    	//Para reordenar se crea un Comparator de productos con una expresión lambda.
//    	temp.sort((Producto o1, Producto o2) -> Integer.compare(o1.getId(), o2.getId()));
//    	//Se añade la lista temporal si no se había añadido previamente.
//    	if (!result.contains(temp)) {
//        	//Se añade la lista temporal a la lista de resultados
//            result.add(new ArrayList<>(temp));        	
//    	}
//    } else {
//        // Caso recursivo. Por cada elemento        	
//    	for(Producto e : elementos) {
//    		//Se añade el elemento a la lista temporal
//    		temp.add(e);
//    		//Se realiza la invocación recursiva en la que se va decrementado el importe disponible
//    		combinaciones(result, elementos, disponible-e.getPrecio(), sobranteMax, temp);
//    		//Se elimina el último de la lista temporal
//    		temp.remove(temp.size()-1);
//    	}
//    }
//}

///**
// * Genera las combinaciones de productos que se pueden comprar con un importe máximo y un resto máximo.
// * @param elementos List<Producto> con los productos que se pueden comprar.
// * @param disponible double con el importe máximo a gastar.
// * @param sobranteMax double con el importe máximo que puede sobrar al configurar las compras.
// * @return List<List<Producto>> listas de productos que se pueden comprar con el importe dado.
// */
//public static List<List<Producto>> combinaciones(List<Producto> elementos, double disponible, double sobranteMax) {
//	//Se inicializa la lista de combinaciones que se devolverá como resultado.
//	List<List<Producto>> result = new ArrayList<>();
//	//Se invoca al método recursivo
//	combinaciones(result, elementos, disponible, sobranteMax, new ArrayList<>());
//	//Se devuelve el resultado.
//	return result;
//}

