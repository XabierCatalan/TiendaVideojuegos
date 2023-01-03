package Ventanas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.util.ArrayList;
import java.util.List;

import ClasesPrincipales.*;

public class VentanaConsolaMandos extends JFrame {
	protected JButton botonCarrito;
	protected JButton botonAtras;
	protected JButton botonAnyadirCarrito;
	protected JButton botonFiltrar;
	protected JButton botonSinFiltros;
	
	
	protected JLabel labelFiltros;
	
	protected JComboBox<EstadoProducto> comboEstado;
	protected JComboBox<Marca> comboMarca;
	
	protected DefaultTableModel mCM = new DefaultTableModel (
			new Object[] { "Nombre", "Marca", "Estado" ,"Precio","Tipo Producto" }, 0
			);
	
	protected JTable tCM = new JTable (mCM);
	
	protected List<Consola> listaConsola;
	protected List<Mando> listaMando;
	
	
	
	
	public VentanaConsolaMandos () {
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				cargarTabla();
				cargarTabla();
			}
			
			
				
		});
		
		Container cp = this.getContentPane();
		
		cp.setLayout(new FlowLayout());
		
		botonAnyadirCarrito = new JButton("Añadir al carrito");
		botonAtras = new JButton("Atras");
		botonCarrito = new JButton("Carrito");
		botonFiltrar = new JButton("Filtrar");
		botonSinFiltros = new JButton("Quitar Filtros");
		
		
		labelFiltros = new JLabel("FILTROS");
		
		comboEstado = new JComboBox<>(EstadoProducto.values());
		comboMarca = new JComboBox<>(Marca.values());
		
		this.setTitle("Consolas y Mandos");
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		
		
		
		JPanel pIzquierda = new JPanel();
		pIzquierda.add( new JScrollPane( tCM ));
		cp.add( pIzquierda, BorderLayout.WEST );
		
		
		
		
		JPanel pFiltros = new JPanel();
		pFiltros.setLayout(new GridLayout(6,1));
		
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new BorderLayout());
		
		pFiltros.add(labelFiltros);
		pFiltros.add(comboMarca);
		pFiltros.add(comboEstado);
		pFiltros.add(botonFiltrar);
		pFiltros.add(botonSinFiltros);
		
		
		cp.add(pFiltros);
		
		pBotones.add(botonCarrito, BorderLayout.NORTH);
		pBotones.add(botonAnyadirCarrito, BorderLayout.CENTER);
		pBotones.add(botonAtras, BorderLayout.SOUTH);
		
		cp.add(pBotones);
		
		tCM.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					JLabel l = (Jabel) c;
				// TODO Auto-generated method stub
				return c;
				
			}
			
			
			
		});
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.vMP.setVisible(true);
				dispose();
				
			}
		});
		
		botonSinFiltros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cargarTabla();
			}});
		
		botonCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main.vC.setVisible(true);
				dispose();
			}
		});
		
		botonAnyadirCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		botonFiltrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EstadoProducto ep = EstadoProducto.valueOf(String.valueOf(comboEstado.getSelectedItem()));
				Marca m = Marca.valueOf(String.valueOf(comboMarca.getSelectedItem()));
				filtrarProductos(m , ep);
				
			}
		});
			
				
	}
	
	
	private void cargarTabla() {
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		while (mCM.getRowCount() > 0) {
			mCM.removeRow( 0 );
		}
		
		for (Consola consola : listaConsola) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			}
		}
		for (Mando mando : listaMando) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductos(Marca m,EstadoProducto ep) {
		while (mCM.getRowCount() > 0) {
			mCM.removeRow( 0 );
		}
		
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		List<Consola> listaConsola2 = new ArrayList<>();
		List<Mando> listaMando2 = new ArrayList<>();
		
		for (Consola consola : listaConsola) {
			if (consola.getEstado() == ep && consola.getMarca() == m) {
				listaConsola2.add(consola);
			}
		}
		
		for (Mando mando : listaMando) {
			if (mando.getEstado() == ep && mando.getMarca() == m) {
				listaMando2.add(mando);
			}
		}
		
		for (Consola consola : listaConsola2) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}

}
