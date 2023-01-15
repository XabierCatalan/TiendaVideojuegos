package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import ClasesPrincipales.*;

public class VentanaPedidosAntiguos extends JFrame{
	
	protected JButton botonAtras;
	
	protected DefaultTableModel mP = new DefaultTableModel (
			new Object[] {"Id", "Fecha","Estado", "Email", "Precio"}, 0
			);
	
	protected JTable tP = new JTable (mP);
	
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
		
		JPanel pArriba = new JPanel();
		pArriba.setLayout(new BorderLayout());
		pArriba.add(botonAtras, BorderLayout.WEST);
		cp.add(pArriba, BorderLayout.NORTH);
		
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
			mP.addRow( new Object[] { carrito.getId(), sdf.format(carrito.getFecha()), carrito.getEstadoCarrito(), carrito.getUsuario().getEmail(), String.format("%.2f €",carrito.getPrecio()) } );
			
		}
	}

}
