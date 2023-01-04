package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


import ClasesPrincipales.*;

public class VentanaCarrito extends JFrame{
	
	protected JButton Atras;
	protected JButton BorrarDelCarro;
	protected JButton ConfirmarPedido;
	
	protected JLabel CARRITO;
	protected JLabel precio;
	
	protected JTextField precio2;
	
	protected JList<Pagable> pagables;
	protected DefaultListModel<Pagable> DLM;



	
	public VentanaCarrito() {
		
		Container cp = this.getContentPane();
		
		Atras = new JButton("Atras");
		BorrarDelCarro = new JButton("BORRAR DEL CARRITO");
		ConfirmarPedido = new JButton("CONFIRMAR PEDIDO");
		
		CARRITO = new JLabel("CARRITO");
		precio = new JLabel("PRECIO:");
		
		precio2 = new JTextField("");
		
		DLM = new DefaultListModel<>();
		
		pagables = new JList<>(DLM);
		
		JPanel JP1 = new JPanel();
		JP1.setLayout(new BorderLayout());
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(Atras , BorderLayout.WEST);
		JP2.add(CARRITO , BorderLayout.CENTER);
		JP1.add(JP2 , BorderLayout.NORTH);
		
		JPanel JP3 = new JPanel();
		JP3.setLayout(new FlowLayout());
		
		JPanel JP4 = new JPanel();
		JP4.setLayout(new BorderLayout());
		
		JPanel JP5 = new JPanel();
		JP5.setLayout(new GridLayout(2,1));
		
		JP5.add(precio);
		JP5.add(precio2);
		
		JP4.add(JP5, BorderLayout.WEST);

		JP3.add(pagables);
		JP3.add(JP4);
		JP1.add(JP3 , BorderLayout.CENTER);
		
		JPanel JP6 = new JPanel();
		JP6.setLayout(new BorderLayout());
		
		JPanel JP7 = new JPanel();
		JP7.setLayout(new BorderLayout());
		
		JP7.add(BorrarDelCarro, BorderLayout.WEST);
		JP7.add(ConfirmarPedido, BorderLayout.EAST);

		
		JP6.add(JP7, BorderLayout.EAST);
		
		JP1.add(JP6, BorderLayout.SOUTH);
		
		cp.add(JP1);
		
		
		this.setTitle("Carrito");
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
	}

}
