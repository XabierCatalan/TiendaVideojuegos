package Ventanas;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ClasesPrincipales.*;

public class VentanaInicioSesion extends JFrame {
	
	protected JButton Atras;
	protected JButton iniciarSesion;
	protected JLabel Mail;
	protected JLabel Contraseña;
	protected JTextField mail1;
	protected JTextField contraseña1;
	protected JLabel IntroDatos;
	
	public VentanaInicioSesion() {
		
		Container cp = this.getContentPane();
		
		Atras = new JButton("Atras");
		iniciarSesion = new JButton("Iniciar Sesion");
		Mail = new JLabel("Mail");
		Contraseña = new JLabel("Contraseña");
		IntroDatos = new JLabel("IntroDatos");
		mail1 = new JTextField("");
		contraseña1 = new JTextField("");
		
		
		BorderLayout BL1 = new BorderLayout();
		
		JPanel JP1 = new JPanel(); //botton atras
		JP1.setLayout(new FlowLayout()); //boton Atras
		
		JPanel JP2 = new JPanel(); //IntroDatos , Mail , mail1, Contraseña, contraseña1
		JP2.setLayout(new GridLayout(2,1));
		
		JPanel JP3 = new JPanel(); //Mail , mail1, Contraseña, contraseña1
		JP3.setLayout(new GridLayout(2,2));
		
		JPanel JP4 = new JPanel(); //InicioSesion
		JP4.setLayout(new FlowLayout());
		
		JPanel JP5 = new JPanel(); //relleno
		JPanel JP6 = new JPanel(); // relleno
		
		cp.setLayout(BL1);
		
		add(JP1, BL1.NORTH); //boton Atras
		JP1.add(Atras); //boton Atras
		
		add(JP2, BL1.CENTER); //IntroDatos , Mail , mail1, Contraseña, Contraseña1
		JP2.add(IntroDatos); //IntroDatos
		JP3.add(Mail); //Mail
		JP3.add(mail1); //mail1
		JP3.add(Contraseña); //Contraseña
		JP3.add(contraseña1); //Contraseña1
		JP2.add(JP3);
		
		add(JP4, BL1.SOUTH);
		JP4.add(iniciarSesion);
		
		add(JP5, BL1.EAST);
		add(JP5, BL1.WEST);
		
		
		
		
		this.setTitle("Ventana Inicio Sesion");
		this.setResizable(true);
		this.setSize(600,600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
	}
	

	

	

}
