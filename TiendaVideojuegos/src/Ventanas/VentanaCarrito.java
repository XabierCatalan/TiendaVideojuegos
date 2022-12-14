package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import ClasesPrincipales.*;

public class VentanaCarrito extends JFrame{
	
	protected JButton Atras;
	protected JButton BorrarDelCarro;
	protected JButton ConfirmarPedido;
	
	
	protected JLabel precio;
	
	protected JTextField precio2;
	
	protected JList<Pagable> pagables;
	protected DefaultListModel<Pagable> DLM;



	
	public VentanaCarrito() {
		
		Container cp = this.getContentPane();
		
		Atras = new JButton("Atras");
		BorrarDelCarro = new JButton("BORRAR DEL CARRITO");
		ConfirmarPedido = new JButton("CONFIRMAR PEDIDO");
		
		
		precio = new JLabel("PRECIO:");
		
		precio2 = new JTextField("0.0");
		
		DLM = new DefaultListModel<>();
		
		
		
		pagables = new JList<>(DLM);
		pagables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane ScrollPagables = new JScrollPane(pagables);
		
		//PANEL PRINCIPAL JP1
		JPanel JP1 = new JPanel();
		JP1.setLayout(new BorderLayout());
		
		//PARTE DE ARRIBA DEL PANEL PRINCIPAL JP2
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(Atras , BorderLayout.WEST);
		
		JP1.add(JP2 , BorderLayout.NORTH);
		
		//PANEL DE EL CENTRO DEL PANEL PRINCIPAL JP3
		JPanel JP3 = new JPanel();
		JP3.setLayout(new BorderLayout());
		
		// PANEL DE EL CENTRO DEL PANEL DEL CENTRO DEL PANEL PRINCIPAL JP4
		JPanel JP4 = new JPanel();
		JP4.setLayout(new BorderLayout());
		
		// PANEL DE LA IZQ DEL PANEL DE EL CENTRO DEL PANEL DEL CENTRO DEL PANEL PRINCIPAL JP5
		JPanel JP5 = new JPanel();
		JP5.setLayout(new FlowLayout());
		
		JP5.add(precio);
		JP5.add(precio2);
		
		JP4.add(JP5, BorderLayout.WEST);

		JP3.add(ScrollPagables, BorderLayout.WEST);
		JP3.add(JP4, BorderLayout.CENTER);
		JP1.add(JP3 , BorderLayout.CENTER);
		
		//PANEL DE ABAJO DEL PANEL DEL PRINCIPAL JP6
		JPanel JP6 = new JPanel();
		JP6.setLayout(new BorderLayout());
		
		//PANEL DE LA DERECHA DEL PANEL DE ABAJO DEL PANEL DEL CENTRO JP7
		JPanel JP7 = new JPanel();
		JP7.setLayout(new BorderLayout());
		
		JP7.add(BorrarDelCarro, BorderLayout.WEST);
		JP7.add(ConfirmarPedido, BorderLayout.EAST);

		
		JP6.add(JP7, BorderLayout.EAST);
		
		JP1.add(JP6, BorderLayout.SOUTH);
		
		cp.add(JP1);
		
		Atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.vMP.setVisible(true);
				dispose();
				
			}
		});
		
		BorrarDelCarro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Pagable seleccionado = pagables.getSelectedValue();			
				DLM.removeElement(seleccionado);

			}
		});
		
		ConfirmarPedido.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		this.setTitle("Carrito");
		this.setSize(700, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
	}

}
