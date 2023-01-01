package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import ClasesPrincipales.*;

import javax.swing.JFrame;

public class VentanaServicio extends JFrame{
	protected JButton atras;
	protected JButton enviarSol;
	
	protected JLabel Descrip;
	protected JLabel fecha;
	
	protected JComboBox<TipoServicio> tipo;
	
	protected JTextField descrip;
	protected JTextField fecha2;

	
	
	public VentanaServicio() {
		
		Container cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		
		atras = new JButton("Atras");
		enviarSol = new JButton("Enviar Solicitud");
		
		Descrip = new JLabel("Descripcion:");
		fecha = new JLabel("Fecha (dd/mm/yyyy):");
		
		tipo = new JComboBox<>(TipoServicio.values());
		
		descrip = new JTextField();
		fecha2 = new JTextField();
		
		JPanel JP1 = new JPanel();
		JP1.setLayout( new BorderLayout());
		
		JP1.add(atras, BorderLayout.WEST);
		
		cp.add(JP1, BorderLayout.NORTH);
		
		JPanel JP2 = new JPanel();
		JP2.setLayout( new BorderLayout());
		
		JPanel JP3 = new JPanel();
		JP3.setLayout( new GridLayout(3,1));
		
		JP3.add(tipo);
		JP3.add(Descrip);
		JP3.add(descrip);
		
		JP2.add(JP3, BorderLayout.WEST);
		
		JPanel JP4 = new JPanel();
		JP4.setLayout( new GridLayout(2,1));
		
		JP4.add(fecha);
		JP4.add(fecha2);
		
		JP2.add(JP4, BorderLayout.EAST);

		cp.add(JP2, BorderLayout.CENTER);
		
		JPanel JP5 = new JPanel();
		JP5.setLayout(new BorderLayout());
		
		JP5.add(enviarSol, BorderLayout.EAST);
		
		cp.add(JP5, BorderLayout.SOUTH);
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.vMP.setVisible(true);
				dispose();
				
			}
		});
		
		
		
		

		this.setVisible(false);
		this.setSize(500, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("GESTION REPARACIONES");
	}

}
