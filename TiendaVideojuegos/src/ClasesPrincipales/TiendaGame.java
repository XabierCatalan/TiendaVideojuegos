package ClasesPrincipales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
	
	public static List<Videojuego> LeerCSVvideojuego() {
		List<Videojuego> Videojuegos = new ArrayList<>();
		Videojuego v;
		
		try (BufferedReader BR = new BufferedReader(new FileReader("Data/Videojuegos.csv"))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				v = new Videojuego();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				v.setId(0);
				id++;
				v.setNombre(tokenizer.nextToken());
				v.setGenero(Genero.valueOf(tokenizer.nextToken()));
				v.setEstado(EstadoProducto.valueOf(tokenizer.nextToken()));
				v.setAnyo(Integer.parseInt(tokenizer.nextToken()));
				v.setPrecio(Double.parseDouble(tokenizer.nextToken()));
				
				
				Videojuegos.add(v);
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		return Videojuegos;
	}
	
	public static List<Mando> LeerCSVmandos() {
		List<Mando> Mandos = new ArrayList<>();
		Mando m;
		
		try (BufferedReader BR = new BufferedReader(new FileReader("Data/mandos.csv"))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				m = new Mando();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				m.setId(0);
				id++;
				m.setNombre(tokenizer.nextToken());
				m.setEstado(EstadoProducto.valueOf(tokenizer.nextToken()));
				m.setPrecio(Double.parseDouble(tokenizer.nextToken()));
				m.setMarca(Marca.valueOf(tokenizer.nextToken()));
				
				Mandos.add(m);
				
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		return Mandos;
	}
	
	public static List<Consola> LeerCSVconsolas() {
		List<Consola> Consolas = new ArrayList<>();
		Consola c;
		
		try (BufferedReader BR = new BufferedReader(new FileReader("Data/consolas.csv"))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				c = new Consola();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				c.setId(0);
				id++;
				c.setNombre(tokenizer.nextToken());
				c.setEstado(EstadoProducto.valueOf(tokenizer.nextToken()));
				c.setPrecio(Double.parseDouble(tokenizer.nextToken()));
				c.setMarca(Marca.valueOf(tokenizer.nextToken()));
				
				
				Consolas.add(c);
				
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		return Consolas;
	}	
	
	
	public static List<Usuario> LeerCSVUsuarios() {
		List<Usuario> Usuarios = new ArrayList<>();
		Usuario u;
		
		try (BufferedReader BR = new BufferedReader(new FileReader("Data/usuarios.csv"))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				u = new Usuario();
				tokenizer = new StringTokenizer(linea, ";");
				
				tokenizer.nextToken();
				
				u.setId(0);
				id++;
				u.setNombre(tokenizer.nextToken());
				u.setEmail(tokenizer.nextToken());
				u.setContrasenya(tokenizer.nextToken());
				u.setTelefono(tokenizer.nextToken());
				
				
				Usuarios.add(u);
				
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		return Usuarios;
	}
	
	public static List<Carrito> LeerCSVCarritos() {
		List<Carrito> Carritos = new ArrayList<>();
		Carrito c;
		
		try (BufferedReader BR = new BufferedReader(new FileReader("Data/carrito.csv"))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id = 0;
			String fecha;
			
			
			while ((linea = BR.readLine()) != null) {
				c = new Carrito();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				c.setId(0);
				id++;
				fecha = tokenizer.nextToken();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				//c.setFecha(sdf.format(fecha));
				//c.setElementos(tokenizer.nextToken());
				//c.setContrasenya(tokenizer.nextToken());
				//c.setTelefono(tokenizer.nextToken());
				
				
				Carritos.add(c);
				
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		return Carritos;
	}
	
	
	public static void main(String[] args) {
		//GestorBD gestorBD = new GestorBD();
		
		//gestorBD.borrarBBDDVideojuego();
		//gestorBD.borrarBBDDConsola();
		//gestorBD.borrarBBDDMando();
		
		//gestorBD.CrearBBDDVideojuego();
		//gestorBD.CrearBBDDConsola();
		//gestorBD.CrearBBDDMando();
		
		
		//gestorBD.insertarDatosVideojuego(LeerCSVvideojuego());
		//gestorBD.obtenerDatosVideojuegos();
		
		//gestorBD.insertarDatosConsola(LeerCSVconsolas());
		//gestorBD.obtenerDatosConsolas();
		
		//gestorBD.insertarDatosMando(LeerCSVmandos());
		//gestorBD.obtenerDatosMandos();
		
		
		
		//gestorBD.borrarBBDDVideojuego();
		//gestorBD.borrarBBDDConsola();
		//gestorBD.borrarBBDDMando();
		
		
	}
	
	
	
	private static void printVideojuegos(List<Videojuego> videojuegos) {
		if (!videojuegos.isEmpty()) {
			for (Videojuego a : videojuegos) {
				System.out.println(String.format(" - %s", a.toString()));
				}
		}	
	}

	private static void printConsola(List<Consola> consolas) {
		if (!consolas.isEmpty()) {
			for (Consola a : consolas) {
				System.out.println(String.format(" - %s", a.toString()));
				}
		}	
	}
	
	private static void printMando(List<Mando> mandos) {
		if (!mandos.isEmpty()) {
			for (Mando a : mandos) {
				System.out.println(String.format(" - %s", a.toString()));
				}
		}	
	}
	
	
	
	
	
	
	
	
	
	
	 
	
	 

}
