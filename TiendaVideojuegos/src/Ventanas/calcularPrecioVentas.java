package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ClasesPrincipales.*;

public class calcularPrecioVentas extends JFrame{
		protected JButton atras;
		protected JSlider slider;
		protected JTextField textovalor;
	
	public calcularPrecioVentas() {
		
		Container cp = this.getContentPane();
		
		atras = new JButton("Atras");
		
		
		
		slider = new JSlider(0,1000,500);
		slider.setMajorTickSpacing(200);
		slider.setMinorTickSpacing(50);
		
		slider.setPaintTicks(true);
		
		slider.setFont(new Font("Serif", Font.ITALIC, 12));
		
		slider.setPaintLabels(true);
		
		textovalor = new JTextField(String.valueOf(slider.getValue()));
		textovalor.setFont(new Font("Serif", Font.ITALIC, 12));
		textovalor.setEditable(false);
		
	
		
		JPanel JP = new JPanel();
		JP.setLayout(new BorderLayout());
		JP.add(atras, BorderLayout.NORTH);
		JP.add(slider, BorderLayout.CENTER);
		JP.add(textovalor, BorderLayout.SOUTH);
		
		cp.add(JP);
		
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
		
		
		
		this.setVisible(false);
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Calcular Precio Ventas");
		this.setResizable(false);
	}
}
