package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import ClasesPrincipales.*;

public class VentanaConsolaMandos extends JFrame {
	
	protected DefaultTableModel mCM = new DefaultTableModel (
			new Object[] { "Nombre", "Marca", "Estado" ,"Precio" }, 0
			);
	
	protected JTable tCM = new JTable (mCM);
	
	protected List<Consola> listaConsola;
	protected List<Mando> listaMando;
	
	
	
	public VentanaConsolaMandos () {
		this.setTitle("Consolas y Mandos");
		this.setSize(1200, 800);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(false);
		
		this.cargarTabla();
		
		JPanel pIzquierda = new JPanel();
		pIzquierda.add( new JScrollPane( tCM ));
		getContentPane().add( pIzquierda, BorderLayout.WEST );
	}
	
	
	private void cargarTabla() {
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		for (Consola consola : listaConsola) {
			mCM.addRow( new Object[] { consola.getNombre(), consola.getMarca(), consola.getEstado(), consola.getPrecio() } );
		}
		for (Mando mando : listaMando) {
			mCM.addRow( new Object[] { mando.getNombre(), mando.getMarca(), mando.getEstado(), mando.getPrecio() } );
		}
		
		
	}

}
