package ClasesPrincipales;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TiendaGame implements Serializable{
	
	protected ArrayList<Carrito> carritos;
	protected ArrayList<Producto> productos;
	protected ArrayList<Servicio> servicios;
	
	public TiendaGame(ArrayList<Carrito> carritos, ArrayList<Producto> productos, ArrayList<Servicio> servicios) {
		super();
		this.carritos = carritos;
		this.productos = productos;
		this.servicios = servicios;
	}
	
	public TiendaGame() {
		super();
		this.carritos = new ArrayList<Carrito>();
		this.productos = new ArrayList<Producto>();
		this.servicios = new ArrayList<Servicio>();
	}

	public ArrayList<Carrito> getCarritos() {
		return carritos;
	}

	public void setCarritos(ArrayList<Carrito> carritos) {
		if (carritos != null) {
			this.carritos = carritos;
		}
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		if (carritos != null) {
			this.productos = productos;
		}
	}

	public ArrayList<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(ArrayList<Servicio> servicios) {
		if (servicios != null) {
			this.servicios = servicios;
		}
	}

	@Override
	public String toString() {
		return "TiendaGame [carritos=" + carritos + ", productos=" + productos + ", servicios=" + servicios + "]";
	}
	
	// GUARDAR DATOS Y CARGAR DATOS
	
	public void guardarDatosVideojuegoCSV() {

		try {
			PrintWriter pw = new PrintWriter("carritos.csv");
			for (Carrito a : carritos ) {
				pw.println(a.getCliente()
						+";"+a.getFecha()
						+";"+a.getElementos()
						+";"+a.getEstadoCarrito()
						+";");
							
			}
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos CSV.");
		}
	}
	
	public void guardarDatosProducotosCSV() {

		try {
			PrintWriter pw = new PrintWriter("productos.csv");
			for (Producto a : productos ) {
				pw.println(a.getId()
						+";"+a.getNombre()
						+";");
							
			}
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos CSV.");
		}
	}
	
	public void guardarDatosServiciosCSV() {

		try {
			PrintWriter pw = new PrintWriter("servicios.csv");
			for (Servicio a : servicios ) {
				pw.println(a.getTipo()
						+";"+a.getFecha1()
						+";");
							
			}
			
			pw.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Error al guardar datos CSV.");
		}
	}
	
	public static void main(String[] args) {
		GestorBD gestorBD = new GestorBD();
		
		gestorBD.CrearBBDDVideojuego();
		gestorBD.CrearBBDDConsola();
		gestorBD.CrearBBDDMando();
		
		ArrayList<Videojuego> videojuegos = initVideojuego();
		gestorBD.insertarDatosVideojuego(videojuegos.toArray(new Videojuego[videojuegos.size()]));
		
		ArrayList<Consola> consolas = initConsola();
		gestorBD.insertarDatosConsola(consolas.toArray(new Consola[consolas.size()]));
		
		ArrayList<Mando> mandos = initMando();
		gestorBD.insertarDatosMando(mandos.toArray(new Mando[mandos.size()]));
		
		videojuegos = gestorBD.obtenerDatosVideojuegos();
		printVideojuegos(videojuegos);
		
		consolas = gestorBD.obtenerDatosConsolas();
		printConsola(consolas);
		
		mandos = gestorBD.obtenerDatosMandos();
		printMando(mandos);
		
		gestorBD.borrarBBDDVideojuego();
		gestorBD.borrarBBDDConsola();
		gestorBD.borrarBBDDMando();
		
		
		
	}
	
	
	private static void printVideojuegos(ArrayList<Videojuego> videojuegos) {
		if (!videojuegos.isEmpty()) {
			for (Videojuego a : videojuegos) {
				System.out.println(String.format(" - %s", a.toString()));
				}
		}	
	}

	private static void printConsola(ArrayList<Consola> consolas) {
		if (!consolas.isEmpty()) {
			for (Consola a : consolas) {
				System.out.println(String.format(" - %s", a.toString()));
				}
		}	
	}
	
	private static void printMando(ArrayList<Mando> mandos) {
		if (!mandos.isEmpty()) {
			for (Mando a : mandos) {
				System.out.println(String.format(" - %s", a.toString()));
				}
		}	
	}
	
	public static ArrayList<Videojuego> initVideojuego() {
		ArrayList<Videojuego> videojuegos = new ArrayList<Videojuego>();
		
		Videojuego videojuego = new Videojuego();
		videojuego.setNombre("GTAV");
		videojuego.setAnyo(2003);
		videojuego.setEstado(EstadoProducto.PRIMERA_MANO);
		videojuego.setPrecio(65);
		
		videojuego = new Videojuego();
		videojuego.setNombre("Pokemon");
		videojuego.setAnyo(2007);
		videojuego.setEstado(EstadoProducto.SEGUNDA_MANO);
		videojuego.setPrecio(45);
		
		return videojuegos;
		
	}
	
	public static ArrayList<Consola> initConsola() {
		ArrayList<Consola> consolas = new ArrayList<Consola>();
		
		Consola consola = new Consola();
		consola.setNombre("PS5");
		consola.setMarca(Marca.PLAYSTATION);
		consola.setEstado(EstadoProducto.PRIMERA_MANO);
		consola.setPrecio(400);
	
		
		consola = new Consola();
		consola.setNombre("XBOX ONE");
		consola.setMarca(Marca.XBOX);
		consola.setEstado(EstadoProducto.SEGUNDA_MANO);
		consola.setPrecio(200);
		
		return consolas;
		
	}
	
	public static ArrayList<Mando> initMando() {
		ArrayList<Mando> mandos = new ArrayList<Mando>();
		
		Mando mando = new Mando();
		mando.setNombre("DualShock");
		mando.setMarca(Marca.PLAYSTATION);
		mando.setEstado(EstadoProducto.PRIMERA_MANO);
		mando.setPrecio(35);
		
		mando = new Mando();
		mando.setNombre("NintendoPad");
		mando.setMarca(Marca.NINTENDO);
		mando.setEstado(EstadoProducto.SEGUNDA_MANO);
		mando.setPrecio(20);
		
		return mandos;
		
	
	
	}
	
	
	
	
	
	
	
	
	 
	
	 

}
