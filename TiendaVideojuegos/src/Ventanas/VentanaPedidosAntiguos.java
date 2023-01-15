package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ClasesPrincipales.*;

public class VentanaPedidosAntiguos extends JFrame{
	
	protected DefaultTableModel mP = new DefaultTableModel (
			new Object[] {"Id", "Fecha","Estado", "Email", "Precio"}, 0
			);
	
	protected JTable tP = new JTable (mP);
	
	protected JList<Pagable> pagables;
	protected DefaultListModel<Pagable> modeloPagables;
	
	public VentanaPedidosAntiguos() {
		
		this.setVisible(false);
		this.setSize(1000, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Pedidos Antiguos");
		this.setResizable(false);
		
		Container cp = this.getContentPane();
		
		cp.setLayout(new FlowLayout());
		
		JPanel pIzquierda = new JPanel();
		pIzquierda.add( new JScrollPane( tP ), BorderLayout.CENTER );
		cp.add(pIzquierda, BorderLayout.WEST);
		
		
		modeloPagables = new DefaultListModel<>();
		pagables = new JList<>(modeloPagables);
		pagables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane ScrollPagables = new JScrollPane(pagables);
		
		JPanel pDerecha = new JPanel();
		pDerecha.add(ScrollPagables,BorderLayout.EAST);
		cp.add(pDerecha);
		
		
		
		
	}

}
