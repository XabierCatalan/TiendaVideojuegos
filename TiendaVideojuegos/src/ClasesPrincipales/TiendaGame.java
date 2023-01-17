package ClasesPrincipales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.StringTokenizer;

import Ventanas.Main;
import Ventanas.VentanaPedidosAntiguos;

public class TiendaGame implements Serializable{
	
	protected ArrayList<Carrito> carritos;
	protected ArrayList<Producto> productos;
	protected static Properties properties;
	
	
	public TiendaGame(ArrayList<Carrito> carritos, ArrayList<Producto> productos) {
		super();
		this.carritos = carritos;
		this.productos = productos;
		
	}
	
	public TiendaGame() {
		super();
		this.carritos = new ArrayList<Carrito>();
		this.productos = new ArrayList<Producto>();
		
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

	

	@Override
	public String toString() {
		return "TiendaGame [carritos=" + carritos + ", productos=" + productos + "]";
	}
	
	//Leer Properties
	public static void LeerProperties() {
		properties = new Properties();
		try {
			FileInputStream entrada = new FileInputStream("general.properties");
			
			properties.load(entrada);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	// GUARDAR DATOS Y CARGAR DATOS
	
	public static List<Producto> LeerCSVProductos() {
		List<Producto> productos = new ArrayList<>();
		Producto p;
		
		try (BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("producto")))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id = 0;
			
			
			
			while ((linea = BR.readLine()) != null) {
				p = new Producto();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				p.setId(id);
				id++;
				p.setNombre(tokenizer.nextToken());
				p.setTp(TipoProducto.valueOf(tokenizer.nextToken()));
				
				
				productos.add(p);
				System.err.println(p);
				
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		return productos;
	}
	
	public static List<Videojuego> LeerCSVvideojuego() {
		List<Videojuego> Videojuegos = new ArrayList<>();
		Videojuego v;
		
		try (BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("videojuego")))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id_v = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				v = new Videojuego();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				v.setId_v(id_v);
				id_v++;
				v.setNombre(tokenizer.nextToken());
				v.setGenero(Genero.valueOf(tokenizer.nextToken()));
				v.setEstado(EstadoProducto.valueOf(tokenizer.nextToken()));
				v.setAnyo(Integer.parseInt(tokenizer.nextToken()));
				v.setPrecio(Double.parseDouble(tokenizer.nextToken()));
				v.setId(Integer.parseInt(tokenizer.nextToken()));
				v.setTp(TipoProducto.valueOf(tokenizer.nextToken()));
				
				
				Videojuegos.add(v);
				System.err.println(v);
			
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
		
		try (BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("mando")))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id_m = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				m = new Mando();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				m.setId_m(id_m);
				id_m++;
				m.setNombre(tokenizer.nextToken());
				m.setEstado(EstadoProducto.valueOf(tokenizer.nextToken()));
				m.setPrecio(Double.parseDouble(tokenizer.nextToken()));
				m.setMarca(Marca.valueOf(tokenizer.nextToken()));
				m.setId(Integer.parseInt(tokenizer.nextToken()));
				m.setTp(TipoProducto.valueOf(tokenizer.nextToken()));
				
				Mandos.add(m);
				System.err.println(m);
				
			
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
		
		try (BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("consola")))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id_c = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				c = new Consola();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				c.setId_c(id_c);
				id_c++;
				c.setNombre(tokenizer.nextToken());
				c.setEstado(EstadoProducto.valueOf(tokenizer.nextToken()));
				c.setPrecio(Double.parseDouble(tokenizer.nextToken()));
				c.setMarca(Marca.valueOf(tokenizer.nextToken()));
				c.setId(Integer.parseInt(tokenizer.nextToken()));
				c.setTp(TipoProducto.valueOf(tokenizer.nextToken()));
				
				
				Consolas.add(c);
				System.err.println(c);
				
			
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
		
		try (BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("usuario")))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			int id = 0;
			
			
			while ((linea = BR.readLine()) != null) {
				u = new Usuario();
				tokenizer = new StringTokenizer(linea, ";");
				
				
				u.setId(id);
				id++;
				u.setNombre(tokenizer.nextToken());
				u.setEmail(tokenizer.nextToken());
				u.setContrasenya(tokenizer.nextToken());
				u.setTelefono(tokenizer.nextToken());
				
				
				Usuarios.add(u);
				System.err.println(u);
				
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		return Usuarios;
	}
	
	public static void EscribirCSVUsuarios(String mail, String nombre, String contraseña, String tel) {
		
		try (BufferedWriter BW = new BufferedWriter(new FileWriter(properties.getProperty("usuario"),true));
			BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("usuario")))) {
			
				BW.write(nombre + ";" + mail + ";" + contraseña + ";" + tel);
				BW.newLine();
				
				BW.flush();
			
			
			
			
		}catch(Exception e) {
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
	}
	
	public static void EscribirCarrito(Carrito c) {
		
		try (BufferedWriter BW = new BufferedWriter(new FileWriter(properties.getProperty("carrito"),true));
			BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("carrito")))) {
			
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
				BW.write(sdf.format(c.getFecha()) + ";" + c.getEstadoCarrito() + ";" + c.getUsuario().getEmail() );
				BW.newLine();
				
				BW.flush();
			
			
			
			
		}catch(Exception e) {
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
	}
	
	public static void AlterarCSVCarrito(Carrito carrito,ArrayList<Carrito> carritos) {
		
		try (BufferedWriter BW = new BufferedWriter(new FileWriter(properties.getProperty("carrito")));
			BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("carrito")))) {
			
				Map<Integer, Carrito> todos = new HashMap<>();
				ArrayList<Carrito> car = new ArrayList<>();
				
				for (Carrito c : carritos) {
					todos.putIfAbsent(c.getId(), c);
				}
				
				
				
				car.addAll(todos.values());
				
				
			
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				StringTokenizer tokenizer;
				String linea = null;
				int cont = 0;
				
				BW.write("FECHA;ESTADOCARRITO;EMAIL");
				BW.newLine();
				
				
					
				for (Carrito c : car ) {
					
					
					if(c.getId() == carrito.getId()) {
						
						if(c.getEstadoCarrito() == EstadoCarrito.PREPARACION) {
							BW.write(sdf.format(c.getFecha()) + ";" + String.valueOf(EstadoCarrito.LISTO) + ";" + c.getUsuario().getEmail());
							BW.newLine();
							
						}else if(c.getEstadoCarrito() == EstadoCarrito.LISTO) {
							BW.write(sdf.format(c.getFecha()) + ";" + String.valueOf(EstadoCarrito.RECOGIDO) + ";" + c.getUsuario().getEmail());
							BW.newLine();
							
						}else if(c.getEstadoCarrito() == EstadoCarrito.RECOGIDO){
							BW.write(sdf.format(c.getFecha()) + ";" + String.valueOf(c.getEstadoCarrito()) + ";" + c.getUsuario().getEmail());
							BW.newLine();
						}
						
					}else {
						BW.write(sdf.format(c.getFecha()) + ";" + String.valueOf(c.getEstadoCarrito()) + ";" + c.getUsuario().getEmail());
						BW.newLine();
					}
					
				
					
				}
				
				BW.flush();
				
			
			
			
			
		}catch(Exception e) {
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
	}
	
	public static void EscribirCarritoProducto(Carrito c) {
		
		try (BufferedWriter BW = new BufferedWriter(new FileWriter(properties.getProperty("carritoProducto"),true));
			BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("carritoProducto")))) {
			
				for (Pagable pagable : c.getElementos()) {
					Producto p  = (Producto) pagable;
					BW.write(c.getId() + ";" + p.getId());
					BW.newLine();
					
				}
			
				
				BW.flush();
			
			
			
			
		}catch(Exception e) {
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
	}
	
	
	public static List<String> LeerCSVAdministradores() {
		List<String> administradores = new ArrayList<>();
		
		try (BufferedReader BR = new BufferedReader(new FileReader(properties.getProperty("admin")))) {
			StringTokenizer tokenizer;
			String linea = null;
			int numLinea = 0;
			
			BR.readLine();
			
			
			
			
			while ((linea = BR.readLine()) != null) {
				
				tokenizer = new StringTokenizer(linea, ";");
				
				administradores.add(tokenizer.nextToken());
				
			
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(String.format("Error en la Tienda Game: %s", e.getMessage()));
			e.printStackTrace();
		}
		
		
		
		return administradores;
		
	}
	
	
	

	
	public static void main(String[] args) {
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	
	 

}
