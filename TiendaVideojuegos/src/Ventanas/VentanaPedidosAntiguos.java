package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ClasesPrincipales.*;

public class VentanaPedidosAntiguos extends JFrame{
	
	protected JButton botonAtras;
	
	protected DefaultTableModel mP = new DefaultTableModel (
			new Object[] {"Id", "Fecha","Estado", "Email", "Precio"}, 0
			){
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	protected JTable tP = new JTable (mP);
	protected JLabel lblFecha;
	
	protected JList<Pagable> pagables;
	protected DefaultListModel<Pagable> modeloPagables;
	
	protected List<Carrito> listaCarrito;
	
	public VentanaPedidosAntiguos() {
		
		this.setVisible(false);
		this.setSize(1000, 550);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Pedidos Antiguos");
		this.setResizable(false);
		
		Container cp = this.getContentPane();
		
		JPanel principal = new JPanel();
		principal.setLayout(new BorderLayout());
		
		cp.add(principal);
		
		JPanel centro = new JPanel();
		centro.setLayout(new FlowLayout());
		principal.add(centro);
		
		JPanel pIzquierda = new JPanel();
		pIzquierda.add( new JScrollPane( tP ), BorderLayout.CENTER );
		centro.add(pIzquierda, BorderLayout.WEST);
		
		
		modeloPagables = new DefaultListModel<>();
		pagables = new JList<>(modeloPagables);
		pagables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane ScrollPagables = new JScrollPane(pagables);
		
		JPanel pDerecha = new JPanel();
		pDerecha.add(ScrollPagables);
		centro.add(pDerecha, BorderLayout.EAST);
		
		botonAtras = new JButton("Atras");
		
		botonAtras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main.vMP.setVisible(true);
				dispose();
			}
		});
		
		SimpleDateFormat sdfF = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
		lblFecha = new JLabel("");
		
		JPanel pArriba = new JPanel();
		pArriba.setLayout(new BorderLayout());
		pArriba.add(lblFecha, BorderLayout.EAST);
		pArriba.add(botonAtras, BorderLayout.WEST);
		cp.add(pArriba, BorderLayout.NORTH);
		
		
		Runnable r1 = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {	
					Calendar hoy = new GregorianCalendar();
										
					lblFecha.setText( hoy.get(hoy.DATE) + "/" + (hoy.get(hoy.MONTH)+1) + "/" + hoy.get(hoy.YEAR)  + "    " + hoy.get(hoy.HOUR_OF_DAY) + ":" + hoy.get(hoy.MINUTE) + ":" + hoy.get(hoy.SECOND));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		};
		Thread t1 = new Thread(r1);
		t1.start();
		
		tP.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
					Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
					JLabel l = (JLabel) c;
					
					EstadoCarrito estado = (EstadoCarrito) table.getValueAt(row, 2);
					
					if (estado == EstadoCarrito.PREPARACION) {
						l.setBackground(Color.GREEN);
						l.setForeground(Color.BLACK);	
						
					} else if (estado == EstadoCarrito.LISTO){
						l.setBackground(Color.YELLOW);
						l.setForeground(Color.BLACK);	
						
					} else if (estado == EstadoCarrito.RECOGIDO) {
						l.setBackground(Color.RED);
						l.setForeground(Color.WHITE);	
						
					}
					
					if(isSelected == true){
						int id = (int) table.getValueAt(row, 0);
						String mail = (String) table.getValueAt(row, 3);
						
						modeloPagables.removeAllElements();
						for (Pagable pagable : Main.bd.buscarCarritoPorId(id, mail).getElementos()) {
//							modeloPagables.addElement(pagable);
							Producto p = (Producto) pagable;
							if(p.getTp() == TipoProducto.MANDO) {
								
								Mando m = (Mando) p;
								if(m.getEstado() == EstadoProducto.SEGUNDA_MANO) {
									m.setPrecio(m.getPrecio() * 3 * 3);
									
									Pagable pa = (Pagable) m;
									
									modeloPagables.addElement(pa);
									
								}else {
									Pagable pa = (Pagable) m;
									modeloPagables.addElement(pa);
								}
								
							}else if(p.getTp() == TipoProducto.CONSOLA) {
								
								Consola co = (Consola) p;
								
								if(co.getEstado() == EstadoProducto.SEGUNDA_MANO) {
									co.setPrecio(co.getPrecio() * 1.25 * 1.25);
									
									Pagable pa = (Pagable) co;
									modeloPagables.addElement(pa);
								}else {
									Pagable pa = (Pagable) co;
									modeloPagables.addElement(pa);
								}
							}else if(p.getTp() == TipoProducto.VIDEOJUEGO){
								
								Videojuego v = (Videojuego) p;
								
								if(v.getEstado() == EstadoProducto.SEGUNDA_MANO) {
									v.setPrecio(v.getPrecio() * 3 * 3);
									
									Pagable pa = (Pagable) v;
									modeloPagables.addElement(pa);
								}else {
									Pagable pa = (Pagable) v;
									modeloPagables.addElement(pa);
								}
							
						}
						
						
					}
					
					
					
					
						
					}
					return c;
			}		
		});
		
		
		tP.getTableHeader().setReorderingAllowed(false);
		
		
		tP.getColumnModel().getColumn(0).setPreferredWidth(35);
		tP.getColumnModel().getColumn(1).setPreferredWidth(65);
		tP.getColumnModel().getColumn(2).setPreferredWidth(90);
		tP.getColumnModel().getColumn(3).setPreferredWidth(130);
		tP.getColumnModel().getColumn(4).setPreferredWidth(50);
		
		
		
		
	}
	
	
	protected void cargarTabla(String mail) {
		
		listaCarrito = Main.bd.buscarCarritosDeUsuario(mail);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		while (mP.getRowCount() > 0) {
			mP.removeRow( 0 );
		}
		
		for (Carrito carrito : listaCarrito) {
			ArrayList<Pagable> pag = new ArrayList<>();
			for (Pagable pagable : carrito.getElementos()) {
				Producto p = (Producto) pagable;
				if(p.getTp() == TipoProducto.MANDO) {
					
					Mando m = (Mando) p;
					if(m.getEstado() == EstadoProducto.SEGUNDA_MANO) {
						m.setPrecio(m.getPrecio() * 3 * 3);
						
						Pagable pa = (Pagable) m;
						
						pag.add(pa);
						
					}else {
						Pagable pa = (Pagable) m;
						pag.add(pa);
					}
					
				}else if(p.getTp() == TipoProducto.CONSOLA) {
					
					Consola c = (Consola) p;
					
					if(c.getEstado() == EstadoProducto.SEGUNDA_MANO) {
						c.setPrecio(c.getPrecio() * 1.25 * 1.25);
						
						Pagable pa = (Pagable) c;
						pag.add(pa);
					}else {
						Pagable pa = (Pagable) c;
						pag.add(pa);
					}
				}else if(p.getTp() == TipoProducto.VIDEOJUEGO){
					
					Videojuego v = (Videojuego) p;
					
					if(v.getEstado() == EstadoProducto.SEGUNDA_MANO) {
						v.setPrecio(v.getPrecio() * 3 * 3);
						
						Pagable pa = (Pagable) v;
						pag.add(pa);
					}else {
						Pagable pa = (Pagable) v;
						pag.add(pa);
					}
				}
			
				
			}
			carrito.setElementos(pag);
			
			mP.addRow( new Object[] { carrito.getId(), sdf.format(carrito.getFecha()), carrito.getEstadoCarrito(), carrito.getUsuario().getEmail(), String.format("%.2f €",carrito.getPrecio()) } );
			
		}
	}

}
