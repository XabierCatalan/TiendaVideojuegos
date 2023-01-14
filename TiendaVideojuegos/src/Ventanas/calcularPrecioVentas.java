package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.*;


import ClasesPrincipales.*;

public class calcularPrecioVentas extends JFrame{
		protected JButton atras;
	
	public calcularPrecioVentas() {
		
		Container cp = this.getContentPane();
		
		atras = new JButton("Atras");
		
		JPanel JP = new JPanel();
		JP.add(atras);
		
		cp.add(JP);
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.vMP.setVisible(true);
				dispose();
				
			}
		});
		
		this.setVisible(false);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Calcular Pracio Ventas");
		this.setResizable(false);
	}
}
