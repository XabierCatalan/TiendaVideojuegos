package Ventanas;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ClasesPrincipales.GestorBD;
import ClasesPrincipales.GestorLog;

public class VentanaInicioSesion extends JFrame {
	
	protected JButton Atras;
	protected JButton iniciarSesion;
	protected JLabel Mail;
	protected JLabel Contraseña;
	protected JTextField mail1;
	protected JPasswordField contraseña1;
	protected JLabel IntroDatos;
	
	protected JLabel msgError;
	
	
	
	
	public VentanaInicioSesion() {
		
		
		
		Container cp = this.getContentPane();
		
		Atras = new JButton("Atras");
		iniciarSesion = new JButton("Iniciar Sesion");
		Mail = new JLabel("Mail");
		Contraseña = new JLabel("Contraseña");
		IntroDatos = new JLabel("Introduzca sus Datos");
		mail1 = new JTextField("");
		contraseña1 = new JPasswordField("");
		msgError = new JLabel("");
		msgError.setForeground(Color.red);
		
		BorderLayout BL1 = new BorderLayout();
		
		JPanel JP1 = new JPanel(); //botton atras
		JP1.setLayout(new FlowLayout()); //boton Atras
		
		JPanel JP2 = new JPanel(); //IntroDatos , Mail , mail1, Contraseña, contraseña1
		JP2.setLayout(new GridLayout(2,2));
		
		JPanel JP4 = new JPanel(); //InicioSesion
		JP4.setLayout(new FlowLayout());
		
		JPanel JPError = new JPanel();
		JPError.setLayout(new FlowLayout());
		
		
		
		cp.setLayout(BL1);
		
		add(JP1, BL1.NORTH); //IntroDatos
		JP1.add(IntroDatos); //IntroDatos
		
		add(JP2, BL1.CENTER);  // Mail , mail1, Contraseña, Contraseña1
		
		JP2.add(Mail); //Mail
		JP2.add(mail1); //mail1
		JP2.add(Contraseña); //Contraseña
		JP2.add(contraseña1); //Contraseña1
		
		add(JPError, BL1.NORTH);
		JPError.add(msgError); //error
		
		
		add(JP4, BL1.SOUTH);
		JP4.add(Atras);
		JP4.add(iniciarSesion);
		
		Atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Main.vM.setVisible(true);
				dispose();
				
				
			}
		});
		
		iniciarSesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String inMail = mail1.getText();
				String inPass = new String(contraseña1.getPassword());
				
				
				GestorBD gestorBD = new GestorBD();
				String msg = gestorBD.iniciarSesion(inMail,inPass);
				if(msg=="OK") {
					if(Main.administradores.contains(inMail)) {
						Main.vGP.setVisible(true);
						dispose();
						
					}else {
						Main.vMP.setVisible(true);
						Main.vC.c.setUsuario(Main.bd.buscarUsuarioPorEmail(inMail));
						Main.vPA.listaCarrito = Main.bd.buscarCarritosDeUsuario(inMail);
						System.out.println("Se ha iniciado sesion con la cuenta " + Main.bd.buscarUsuarioPorEmail(inMail));
						dispose();
					}
					
				} else if (msg == "Contraseña incorrecta"){
					
					msgError.setText(msg);
					contraseña1.setText("");
					
				} else if (msg == "El usuario indicado no existe") {
					msgError.setText(msg);
					mail1.setText("");
					contraseña1.setText("");
				}else {
					msgError.setText(msg);
				}
			}
		});
		
		
		
		
		this.setTitle("Ventana Inicio Sesion");
		this.setResizable(false);
		this.setSize(450,150);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		
	}
	

	

	

}
