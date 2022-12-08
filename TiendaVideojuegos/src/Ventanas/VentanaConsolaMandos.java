package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import ClasesPrincipales.*;

public class VentanaConsolaMandos extends JFrame {
	
	protected DefaultTableModel mCM = new DefaultTableModel (
			new Object[] { "Nombre", "Marca", "Estado" ,"Precio" }, 0
			);
	
	protected JTable tCM = new JTable ();
	
	public VentanaConsolaMandos () {
		this.setTitle("Consolas y Mandos");
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(false);
		
		JPanel pIzquierda = new JPanel();
		pIzquierda.add( new JScrollPane( tCM ));
		getContentPane().add( pIzquierda, BorderLayout.WEST );
	}

}
