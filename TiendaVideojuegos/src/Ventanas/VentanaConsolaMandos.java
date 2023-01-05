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
	protected JButton botonSinFiltros;
	
	
	protected JLabel labelFiltros;
	
	protected JComboBox<String> estado;
	protected JComboBox<String> marca;
	protected JComboBox<String> tipoProducto;
	
	protected DefaultTableModel mCM = new DefaultTableModel (
			new Object[] { "Id","Nombre", "Marca", "Estado" ,"Precio","Tipo", "" }, 0
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
		
		botonAtras = new JButton("Atras");
		botonCarrito = new JButton("Carrito");
		botonSinFiltros = new JButton("Quitar Filtros");
		
		
		labelFiltros = new JLabel("FILTROS");
		
		estado = new JComboBox<>();
		estado.addItem("SIN FILTROS");
		for (EstadoProducto ep : EstadoProducto.values()) {
			
			estado.addItem(ep.toString());
			
		}
		
		marca = new JComboBox<>();
		marca.addItem("SIN FILTROS");
		for (Marca m : Marca.values()) {
			
			marca.addItem(m.toString());
			
		}
		
		tipoProducto = new JComboBox<>();
		tipoProducto.addItem("SIN FILTROS");
		for (TipoProducto tp : TipoProducto.values()) {
			if (tp == TipoProducto.VIDEOJUEGO) {
				
			}else {
				tipoProducto.addItem(tp.toString());
			}
			
			
		}
		
		// para definir el campo elegido por defecto
		
		estado.setSelectedItem("SIN FILTROS");
		marca.setSelectedItem("SIN FILTROS");
		tipoProducto.setSelectedItem("SIN FILTROS");
		
		//filtros automaticos
		
		estado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filtrarTodo();
			}
		});
		
		marca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filtrarTodo();
			}
		});
		
		tipoProducto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filtrarTodo();
			}
		});
		
		this.setTitle("Consolas y Mandos");
		this.setSize(900, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		
		
		
		JPanel pIzquierda = new JPanel();
		pIzquierda.add( new JScrollPane( tCM ), BorderLayout.CENTER );
		cp.add(pIzquierda, BorderLayout.WEST);
		
		
		
		
		JPanel pFiltros = new JPanel();
		pFiltros.setLayout(new GridLayout(6,1));
		
		JPanel pBotones = new JPanel();
		pBotones.setLayout(new BorderLayout());
		
		pFiltros.add(labelFiltros);
		pFiltros.add(marca);
		pFiltros.add(estado);
		pFiltros.add(tipoProducto);
		pFiltros.add(botonSinFiltros);
		
		
		cp.add(pFiltros);
		
		pBotones.add(botonCarrito, BorderLayout.NORTH);
		pBotones.add(botonAtras, BorderLayout.SOUTH);
		
		cp.add(pBotones);
		
		tCM.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					JLabel l = (JLabel) c;
					JLabel h = (JLabel) c;
					
					
					Marca marcas = (Marca) table.getValueAt(row, 2);
					
					
					
					// cambiar a fotos de marcas
					
					if (marcas == Marca.NINTENDO && column == 2 ) {
						l.setIcon(new ImageIcon("NINTENDO.png"));
					} else if (marcas == Marca.XBOX && column == 2) {
						l.setIcon(new ImageIcon("XBOX1.png"));
					} else if (marcas == Marca.PLAYSTATION && column == 2) {
						l.setIcon(new ImageIcon("PS.png"));
					} else {
						l.setIcon(null);
					}
					
					// boton de añadir al carrito
					
					if (column == 6) {
						l.setIcon(new ImageIcon("mas.png"));
						l.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
						
					}
					
					
					if (isSelected == true && column != 6) {
						l.setBackground(Color.BLUE);
						l.setForeground(Color.WHITE);
					} else {
						l.setBackground(Color.WHITE);
						l.setForeground(Color.BLACK);
						
					}
					
					
					
				return c;
				
			}
			
			
			
		});
		
		tCM.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tCM.rowAtPoint(e.getPoint());
				int columna = tCM.columnAtPoint(e.getPoint());
				
				if(columna == 6) {
					// funciones del boton
					if (tCM.getValueAt(fila, 5) == TipoProducto.CONSOLA) {
						int id_p = (int) tCM.getValueAt(fila, 0);
						Main.vC.DLM.addElement(Main.bd.buscarConsolaPorID_P(id_p));
						System.out.println("Se ha añadido una consola al carrito");
						JOptionPane.showMessageDialog(null, "Se ha añadido al carrito la consola");
						
					} else if (tCM.getValueAt(fila, 5) == TipoProducto.MANDO) {
						int id_p = (int) tCM.getValueAt(fila, 0);
						Main.vC.DLM.addElement(Main.bd.buscarMandoPorID_P(id_p));
						JOptionPane.showMessageDialog(null, "Se ha añadido al carrito el mando");						
					}
				}
				
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
				quitarFiltros();
			}});
		
		botonCarrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main.vC.setVisible(true);
				dispose();
			}
		});
		
		
		
		// Ajustes de la tabla tCM
		tCM.getTableHeader().setReorderingAllowed(false); // no permite que se cambien de orden las columnas
		
		// determina la altura de las celdas
		tCM.setRowHeight(20); 
		
		// determina la ancgura de cada columna
		
		
		
		
		tCM.getColumnModel().getColumn(0).setPreferredWidth(35);
		tCM.getColumnModel().getColumn(1).setPreferredWidth(140);
		tCM.getColumnModel().getColumn(2).setPreferredWidth(95);
		tCM.getColumnModel().getColumn(3).setPreferredWidth(120);
		tCM.getColumnModel().getColumn(4).setPreferredWidth(55);
		tCM.getColumnModel().getColumn(5).setPreferredWidth(85);
		tCM.getColumnModel().getColumn(6).setPreferredWidth(35);
		
		
		
		
			
				
	}
	
	
	private void cargarTabla() {
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		while (mCM.getRowCount() > 0) {
			mCM.removeRow( 0 );
		}
		
		for (Consola consola : listaConsola) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			}
		}
		for (Mando mando : listaMando) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductosPorMarcaYEstado(Marca m,EstadoProducto ep) {
		this.mCM.setRowCount(0);
		
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
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductosPorMarcaYTp(Marca m,TipoProducto tp) {
		this.mCM.setRowCount(0);
		
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		List<Consola> listaConsola2 = new ArrayList<>();
		List<Mando> listaMando2 = new ArrayList<>();
		
		for (Consola consola : listaConsola) {
			if (consola.getTp() == tp && consola.getMarca() == m) {
				listaConsola2.add(consola);
			}
		}
		
		for (Mando mando : listaMando) {
			if (mando.getTp() == tp && mando.getMarca() == m) {
				listaMando2.add(mando);
			}
		}
		
		for (Consola consola : listaConsola2) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductosPorTpYEstado(TipoProducto tp,EstadoProducto ep) {
		this.mCM.setRowCount(0);
		
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		List<Consola> listaConsola2 = new ArrayList<>();
		List<Mando> listaMando2 = new ArrayList<>();
		
		for (Consola consola : listaConsola) {
			if (consola.getEstado() == ep && consola.getTp() == tp) {
				listaConsola2.add(consola);
			}
		}
		
		for (Mando mando : listaMando) {
			if (mando.getEstado() == ep && mando.getTp() == tp) {
				listaMando2.add(mando);
			}
		}
		
		for (Consola consola : listaConsola2) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductosPorMarca(Marca m) {
		this.mCM.setRowCount(0);
		
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		List<Consola> listaConsola2 = new ArrayList<>();
		List<Mando> listaMando2 = new ArrayList<>();
		
		for (Consola consola : listaConsola) {
			if (consola.getMarca() == m) {
				listaConsola2.add(consola);
			}
		}
		
		for (Mando mando : listaMando) {
			if (mando.getMarca() == m) {
				listaMando2.add(mando);
			}
		}
		
		for (Consola consola : listaConsola2) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductosPorEstado(EstadoProducto ep) {
		this.mCM.setRowCount(0);
		
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		List<Consola> listaConsola2 = new ArrayList<>();
		List<Mando> listaMando2 = new ArrayList<>();
		
		for (Consola consola : listaConsola) {
			if (consola.getEstado() == ep) {
				listaConsola2.add(consola);
			}
		}
		
		for (Mando mando : listaMando) {
			if (mando.getEstado() == ep) {
				listaMando2.add(mando);
			}
		}
		
		for (Consola consola : listaConsola2) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductosPorTp(TipoProducto tp) {
		this.mCM.setRowCount(0);
		
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		List<Consola> listaConsola2 = new ArrayList<>();
		List<Mando> listaMando2 = new ArrayList<>();
		
		for (Consola consola : listaConsola) {
			if (consola.getTp() == tp) {
				listaConsola2.add(consola);
			}
		}
		
		for (Mando mando : listaMando) {
			if (mando.getTp() == tp) {
				listaMando2.add(mando);
			}
		}
		
		for (Consola consola : listaConsola2) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	private void filtrarProductos(Marca m, EstadoProducto ep, TipoProducto tp) {
		this.mCM.setRowCount(0);
		
		listaConsola = Main.bd.obtenerDatosConsolas();
		listaMando = Main.bd.obtenerDatosMandos();
		
		List<Consola> listaConsola2 = new ArrayList<>();
		List<Mando> listaMando2 = new ArrayList<>();
		
		for (Consola consola : listaConsola) {
			if (consola.getMarca() == m && consola.getEstado() == ep && consola.getTp() == tp) {
				listaConsola2.add(consola);
			}
		}
		
		for (Mando mando : listaMando) {
			if (mando.getMarca() == m && mando.getEstado() == ep && mando.getTp() == tp) {
				listaMando2.add(mando);
			}
		}
		
		for (Consola consola : listaConsola2) {
			if(consola.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio()), consola.getTp() } );
			}else {
				mCM.addRow( new Object[] { consola.getId(), consola.getNombre(), consola.getMarca(), consola.getEstado(), String.format("%.2f",consola.getPrecio() * 1.25), consola.getTp() } );
			} 
		}
		for (Mando mando : listaMando2) {
			if(mando.getEstado()==EstadoProducto.PRIMERA_MANO) {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio()), mando.getTp()  } );
			}else {
				mCM.addRow( new Object[] { mando.getId(), mando.getNombre(), mando.getMarca(), mando.getEstado(), String.format("%.2f",mando.getPrecio() * 3), mando.getTp() } );
			}
		}
		
		
	}
	
	protected void filtrarTodo() {
		if (estado.getSelectedItem() == "SIN FILTROS" && marca.getSelectedItem() == "SIN FILTROS" && tipoProducto.getSelectedItem() == "SIN FILTROS") {
			cargarTabla();
		}else if(marca.getSelectedItem() == "SIN FILTROS" && tipoProducto.getSelectedItem() == "SIN FILTROS") {
			for (EstadoProducto ep : EstadoProducto.values()) {
				if (estado.getSelectedItem() == ep.toString()) {
					filtrarProductosPorEstado(ep);;
				}
			}
		
		
		}else if(estado.getSelectedItem() == "SIN FILTROS" && tipoProducto.getSelectedItem() == "SIN FILTROS") {
			for (Marca m : Marca.values()) {
				if (marca.getSelectedItem() == m.toString()) {
					filtrarProductosPorMarca(m);
				}
			}
			
		}else if(estado.getSelectedItem() == "SIN FILTROS" && marca.getSelectedItem() == "SIN FILTROS") {
			for (TipoProducto tp : TipoProducto.values()) {
				if (tipoProducto.getSelectedItem() == tp.toString()) {
					filtrarProductosPorTp(tp);
				}
			}
			
		}else if(tipoProducto.getSelectedItem() == "SIN FILTROS") {
			for (EstadoProducto ep : EstadoProducto.values()) {
				for (Marca m : Marca.values()) {
					if (estado.getSelectedItem() == ep.toString() && marca.getSelectedItem() == m.toString()) {
						filtrarProductosPorMarcaYEstado(m, ep);
					}
				}
				
			}
			
		}else if(marca.getSelectedItem() == "SIN FILTROS") {
			for (EstadoProducto ep : EstadoProducto.values()) {
				for (TipoProducto tp : TipoProducto.values()) {
					if (estado.getSelectedItem() == ep.toString() && tipoProducto.getSelectedItem() == tp.toString()) {
						filtrarProductosPorTpYEstado(tp, ep);
					}
				}
				
			}
			
		}else if(estado.getSelectedItem() == "SIN FILTROS") {
			for (Marca m : Marca.values()) {
				for (TipoProducto tp : TipoProducto.values()) {
					if (marca.getSelectedItem() == m.toString() && tipoProducto.getSelectedItem() == tp.toString()) {
						filtrarProductosPorMarcaYTp(m, tp);
					}
				}
				
			}
			
		}else {
			for (EstadoProducto ep : EstadoProducto.values()) {
				for (Marca m : Marca.values()) {
					for (TipoProducto tp : TipoProducto.values()) {
						if (tipoProducto.getSelectedItem() == tp.toString() && marca.getSelectedItem() == m.toString() && estado.getSelectedItem() == ep.toString()) {
							filtrarProductos(m, ep, tp);
						}
					}
				}
			}
			
			
		}
		
		
	}
	protected void quitarFiltros() {
		
		estado.setSelectedItem("SIN FILTROS");
		marca.setSelectedItem("SIN FILTROS");
		tipoProducto.setSelectedItem("SIN FILTROS");
		
	}
	
	

}
