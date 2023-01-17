package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ClasesPrincipales.*;

public class VentanaCrearCuenta extends JFrame {
	
	protected JButton Atras;
	protected JButton CrearCuenta;
	protected JLabel Mail;
	protected JLabel Contraseña;
	protected JLabel telf;
	protected JLabel nomb;
	protected JTextField mail1;
	protected JPasswordField contraseña1;
	protected JTextField telf1;
	protected JTextField nomb1;
	protected JLabel NC;
	protected JLabel msgError;
	
	public VentanaCrearCuenta() {
		
		Container cp = this.getContentPane();
		
		Atras = new JButton("Atras");
		CrearCuenta = new JButton("CrearCuenta");
		
		Mail = new JLabel("Mail");
		Contraseña = new JLabel("Contraseña");
		telf = new JLabel("Nº Telefono");
		nomb = new JLabel("Nombre Cuenta");
		NC = new JLabel("NUEVA CUENTA");
		NC.setVerticalAlignment(JLabel.TOP);
		NC.setHorizontalAlignment(JTextField.CENTER);
		msgError = new JLabel("");
		msgError.setHorizontalAlignment(JTextField.CENTER);

		msgError.setForeground(Color.red);
		
		
		mail1 = new JTextField("");
		contraseña1 = new JPasswordField("");
		telf1 = new JTextField("");
		nomb1 = new JTextField("");
		
		cp.setLayout(new BorderLayout());
				
		JPanel JP1 = new JPanel();
		//JP1.setLayout(new FlowLayout());
		JP1.setLayout(new GridLayout(2,1));

		JP1.add(NC);
		JP1.add(msgError);

		cp.add(JP1 , BorderLayout.NORTH);
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new GridLayout(4,4));
		
		JP2.add(Mail);
		JP2.add(mail1);
		JP2.add(Contraseña);
		JP2.add(contraseña1);
		JP2.add(telf);
		JP2.add(telf1);
		JP2.add(nomb);
		JP2.add(nomb1);
		
		cp.add(JP2, BorderLayout.CENTER);
		
		JPanel JP3 = new JPanel();
		JP3.setLayout(new FlowLayout());
		
		JP3.add(Atras);
		JP3.add(CrearCuenta);
		
		cp.add(JP3, BorderLayout.SOUTH);
		
		Atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main.vM.setVisible(true);
				dispose();
			}
		});
		
		CrearCuenta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Meter metodo que cree una cuenta nueva con todo vacio excepto los datos

				
				String mail = mail1.getText();
				String pass = new String(contraseña1.getPassword());
				String tel = telf1.getText(); 
				String nombre = nomb1.getText();
				System.out.println(nombre+mail+pass+tel);
				String msg = Main.bd.crearCuenta(nombre,mail,pass,tel);
				
				if(msg=="OK") {
					GestorLog.fine("usuario nuevo creado");
					Main.vM.setVisible(true);
					Main.vC.c.setUsuario(Main.bd.buscarUsuarioPorEmail(mail));
					System.out.println("se ha metido como usuario del carrito " + Main.bd.buscarUsuarioPorEmail(mail));
					dispose();
					Main.tg.EscribirCSVUsuarios(mail, nombre, pass, tel);
				} else {
					msgError.setText(msg);
					GestorLog.warning(msg);
				}
				

				
			}
		});

		
		this.setResizable(false);
		this.setTitle("CrearCuenta");
		this.setSize(600, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		
	}

}
