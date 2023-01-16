package Ventanas;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;


import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import ClasesPrincipales.*;

public class VentanaVideojuegos extends JFrame {
	protected JButton carrito;
	protected JButton atras;
	
	protected JButton sinFiltros;
	
	protected JLabel filtros;
	
	protected JComboBox<String> estado;
	protected JComboBox<String> genero;
	
	protected JComboBox<String> fecha;
	
	
	protected DefaultTableModel mDV = new DefaultTableModel(
			new Object [] {"ID", "Nombre", "Genero", "Estado","Año","Precio"," "},0
			) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	protected JTable tV = new JTable(mDV);
	
	protected List<Videojuego> listaVideojuego;
	
	
	
	public VentanaVideojuegos() {
		
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				cargarVideojuegos();
				cargarVideojuegos();
			}
			
			
				
		});
		
		
		 
		Container cp = this.getContentPane();
		
		carrito = new JButton("Carrito");
		atras = new JButton("Atras");
		
		sinFiltros = new JButton("Quitar Filtros");
		
		filtros = new JLabel("FILTROS");
		
		estado = new JComboBox<>();
		estado.addItem("SIN FILTROS");
		for (EstadoProducto ep : EstadoProducto.values()) {
			
			estado.addItem(ep.toString());
			
		}
		
		genero = new JComboBox<>();
		genero.addItem("SIN FILTROS");
		for (Genero gen : Genero.values()) {
			
			genero.addItem(gen.toString());
			
		}
		

		
		
	
		
		fecha = new JComboBox<>();
		fecha.addItem("ELIGE UN AÑO");
		
		for (int i = 1970; i < 2024; i++) {
			fecha.addItem(String.valueOf(i));
		};
		
		
		// para definir el campo elegido por defecto
		genero.setSelectedItem("SIN FILTROS");
		estado.setSelectedItem("SIN FILTROS");
		fecha.setSelectedItem("ELIGE UN AÑO");
		
		tV.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// TODO Auto-generated method stub
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				JLabel l = (JLabel) c;
				
				if (column == 6) {
					l.setIcon(new ImageIcon("mas.png"));
					l.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
				} else {
					l.setIcon(null);
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
		
		
		
		tV.getTableHeader().setReorderingAllowed(false);
		
		
		tV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int fila = tV.rowAtPoint(e.getPoint());
				int columna = tV.columnAtPoint(e.getPoint());
				
				
				if (columna == 6) {
					int id_p = (int) tV.getValueAt(fila, 0);
					
					Videojuego v = Main.bd.buscarVideojuegoPorID_P(id_p);
					
					Main.vC.DLM.addElement(v);

					System.out.println("Se ha añadido al carrito el videojuego");
					JOptionPane.showMessageDialog(null, "Se ha añadido al carrito el videojuego");
					
					//System.out.println(Main.vC.DLM);
					
//					for (int i = 0; i < Main.vC.DLM.getSize(); i++) {
//						Videojuego v =  Main.vC.DLM.getElementAt(i);
//						double x   Main.vC.DLM.getElementAt(i);
//					}
					
					double precio = Double.parseDouble(Main.vC.precio2.getText());  
					double newPrecio = precio + v.getPrecio();
					String p = String.valueOf(newPrecio);
					Main.vC.precio2.setText(p);
					
						
					}
				}
				
				
				
				
			}
		);
		
		tV.setRowHeight(20); // determina la altura de las celdas
		
		tV.getColumnModel().getColumn(0).setPreferredWidth(35);
		tV.getColumnModel().getColumn(1).setPreferredWidth(90);
		tV.getColumnModel().getColumn(2).setPreferredWidth(95);
		tV.getColumnModel().getColumn(3).setPreferredWidth(130);
		tV.getColumnModel().getColumn(4).setPreferredWidth(55);
		tV.getColumnModel().getColumn(5).setPreferredWidth(55);
		tV.getColumnModel().getColumn(6).setPreferredWidth(35);
		
		
		
		
		//filtros automaticos
		
		estado.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filtrarTodo();
			}
		});
		
		genero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filtrarTodo();
				
			}
		});
		
		fecha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				filtrarTodo();
				
			}
		});
		
		
			
	
			
		cp.setLayout(new FlowLayout());
		
		cp.add(new JScrollPane(tV));
		
		JPanel JP1 = new JPanel();
		JP1.setLayout(new GridLayout(6,1));
		
		
		
		
		
		JP1.add(filtros);
		JP1.add(genero);
		JP1.add(estado);
		JP1.add(fecha);
		JP1.add(sinFiltros);
		
		
		cp.add(JP1);
		
		JPanel JP2 = new JPanel();
		JP2.setLayout(new BorderLayout());
		
		JP2.add(carrito, BorderLayout.NORTH);
		
		JP2.add(atras, BorderLayout.SOUTH);
		
		cp.add(JP2);
		
		
		
		
		
		carrito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main.vC.setVisible(true);
				dispose();
				
			}
		});
		
		
		
		atras.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Main.vMP.setVisible(true);
				dispose();
			}
		});
		
		sinFiltros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				quitarFiltros();
			}
		});
		
		
		this.setVisible(false);
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("VENTANA VIDEOJEUGOS");
		this.setResizable(false);
		
		
		
	}
	
	
	
	protected void cargarVideojuegos() {
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		while (mDV.getRowCount() > 0) {
			mDV.removeRow( 0 );
		}
		
		for (Videojuego videojuego : listaVideojuego) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId(), videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId(), videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		}
	}
	
	protected void filtrarVideojuego(EstadoProducto ep, Genero g, Integer a) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getAnyo() == a && videojuego.getEstado() == ep && videojuego.getGenero() == g) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
	}
	
	protected void filtrarVideojuegoPorEstado(EstadoProducto ep) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getEstado() == ep) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
		
	}
	
	protected void filtrarVideojuegoPorGenero(Genero g) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getGenero() == g) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
		
	}
	
	protected void filtrarVideojuegoPorAnyo(Integer a) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getAnyo() == a) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
		
	}
	
	protected void filtrarVideojuegoPorEstadoYGenero(EstadoProducto ep, Genero g) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getEstado() == ep && videojuego.getGenero() == g) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
		
	}
	
	protected void filtrarVideojuegoPorEstadoYAnyo(EstadoProducto ep, Integer a) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getEstado() == ep && videojuego.getAnyo() == a ) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
		
	}
	
	protected void filtrarVideojuegoPorGeneroYAnyo(Genero g, Integer a) {
		this.mDV.setRowCount(0);
		
		listaVideojuego = Main.bd.obtenerDatosVideojuegos();
		
		List<Videojuego> listaVideojuego2 = new ArrayList<>();
		
		for (Videojuego videojuego : listaVideojuego) {
			if (videojuego.getGenero() == g && videojuego.getAnyo() == a ) {
				listaVideojuego2.add(videojuego);
			}
		}
		
		for (Videojuego videojuego : listaVideojuego2) {
			if(videojuego.getEstado()== EstadoProducto.PRIMERA_MANO) {
				this.mDV.addRow(new Object[] {videojuego.getId_v(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio())});
			}else {
				this.mDV.addRow(new Object[] {videojuego.getId_v(),videojuego.getNombre(), videojuego.getGenero(), videojuego.getEstado(), videojuego.getAnyo(), String.format("%.2f",videojuego.getPrecio() * 3)});
			}
		
		}
		
		
	}
	
	protected void filtrarTodo() {
		if (estado.getSelectedItem() == "SIN FILTROS" && genero.getSelectedItem() == "SIN FILTROS" && fecha.getSelectedItem() == "ELIGE UN AÑO") {
			cargarVideojuegos();
		}else if(genero.getSelectedItem() == "SIN FILTROS" && fecha.getSelectedItem() == "ELIGE UN AÑO") {
			for (EstadoProducto ep : EstadoProducto.values()) {
				if (estado.getSelectedItem() == ep.toString()) {
					filtrarVideojuegoPorEstado(ep);
				}
			}
		
		
		}else if(estado.getSelectedItem() == "SIN FILTROS" && fecha.getSelectedItem() == "ELIGE UN AÑO") {
			for (Genero g : Genero.values()) {
				if (genero.getSelectedItem() == g.toString()) {
					filtrarVideojuegoPorGenero(g);
				}
			}
			
		}else if(estado.getSelectedItem() == "SIN FILTROS" && genero.getSelectedItem() == "SIN FILTROS") {
			for (int i = 1970; i < 2024; i++) {
				Integer a = Integer.parseInt(String.valueOf(fecha.getSelectedItem()));
				if (a == i) {
					filtrarVideojuegoPorAnyo(i);
				}
			}
			
		}else if(fecha.getSelectedItem() == "ELIGE UN AÑO") {
			for (EstadoProducto ep : EstadoProducto.values()) {
				for (Genero g : Genero.values()) {
					if (estado.getSelectedItem() == ep.toString() && genero.getSelectedItem() == g.toString()) {
						filtrarVideojuegoPorEstadoYGenero(ep, g);
					}
				}
				
			}
			
		}else if(genero.getSelectedItem() == "SIN FILTROS") {
			for (EstadoProducto ep : EstadoProducto.values()) {
				for (int i = 1970; i < 2024; i++) {
					Integer a = Integer.parseInt(String.valueOf(fecha.getSelectedItem()));
					if (a == i && estado.getSelectedItem() == ep.toString()) {
						filtrarVideojuegoPorEstadoYAnyo(ep, a);
					}
				}
			}
			
		}else if(estado.getSelectedItem() == "SIN FILTROS") {
			for (Genero g : Genero.values()) {
				for (int i = 1970; i < 2024; i++) {
					Integer a = Integer.parseInt(String.valueOf(fecha.getSelectedItem()));
					if (a == i && genero.getSelectedItem() == g.toString()) {
						filtrarVideojuegoPorGeneroYAnyo(g, a);
					}
				}
			}
			
		}else {
			for (EstadoProducto ep : EstadoProducto.values()) {
				for (Genero g : Genero.values()) {
					for (int i = 1970; i < 2024; i++) {
						Integer a = Integer.parseInt(String.valueOf(fecha.getSelectedItem()));
						if (a == i && genero.getSelectedItem() == g.toString() && estado.getSelectedItem() == ep.toString()) {
							filtrarVideojuego(ep, g, a);
						}
					}
				}
			}
			
			
		}
		
		
	}
	
	
	protected void quitarFiltros() {
		
		estado.setSelectedItem("SIN FILTROS");
		genero.setSelectedItem("SIN FILTROS");
		fecha.setSelectedItem("ELIGE UN AÑO");
	}



	



	
		
}
	
	
	
	
	
	
	

