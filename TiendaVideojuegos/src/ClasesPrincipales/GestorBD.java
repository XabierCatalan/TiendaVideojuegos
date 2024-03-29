package ClasesPrincipales;


import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import java.util.Properties;


import javax.swing.JOptionPane;

public class GestorBD {
    private static final Logger LOG = Logger.getLogger(GestorBD.class.getName());
    

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";	
	
	protected static final String DATABASE_FILE_PRODUCTO = "db/databaseproducto.db";
	protected static final String DATABASE_FILE_VIDEOJUEGO = "db/databasevideojuego.db";
	protected static final String DATABASE_FILE_CONSOLA = "db/databaseconsola.db";
	protected static final String DATABASE_FILE_MANDO = "db/databasemando.db";
	protected static final String DATABASE_FILE_USUARIO = "db/databaseusuario.db";
	protected static final String DATABASE_FILE_CARRITO = "db/databasecarrito.db";
	
	
	//Productos_carrito
	protected static final String DATABASE_FILE_PRODUCTOSCARRITO = "db/databaseproductoscarrito.db";
	
	protected static final String CONNECTION_STRING_PRODUCTO = "jdbc:sqlite:" + DATABASE_FILE_PRODUCTO;
	protected static final String CONNECTION_STRING_VIDEOJUEGO = "jdbc:sqlite:" + DATABASE_FILE_VIDEOJUEGO;
	protected static final String CONNECTION_STRING_CONSOLA = "jdbc:sqlite:" + DATABASE_FILE_CONSOLA;
	protected static final String CONNECTION_STRING_MANDO= "jdbc:sqlite:" + DATABASE_FILE_MANDO;
	protected static final String CONNECTION_STRING_USUARIO= "jdbc:sqlite:" + DATABASE_FILE_USUARIO;
	protected static final String CONNECTION_STRING_CARRITO = "jdbc:sqlite:" + DATABASE_FILE_CARRITO;
	
	
	//Productos_carrito
	protected static final String CONNECTION_STRING_PRODUCTOSCARRITO = "jdbc:sqlite:" + DATABASE_FILE_PRODUCTOSCARRITO;
	
	protected static Usuario logedUser = null;

	
	public GestorBD() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public Usuario getLogedUser() {
		return logedUser;
	}
	
	// PRODUCTOS:
	
	public void CrearBBDDProducto() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS PRODUCTO (\n"
					+ " ID_P INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE_P TEXT NOT NULL,\n"
					+ " TP ENUM NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Producto");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDDProducto: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDProducto() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTO);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS PRODUCTO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Producto");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDProducto: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_PRODUCTO));
			System.out.println("- Se ha borrado el fichero de la BBDDProducto");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDProducto: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosProducto(List<Producto> productos) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO PRODUCTO ( NOMBRE_P, TP) VALUES ( '%s', '%s');";
			
			System.out.println("- Insertando productos...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Producto c : productos) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getTp()))) {					
					System.out.println(String.format(" - Producto insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el producto: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDDProducto: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Producto> obtenerDatosProducto() {
		ArrayList<Producto> productos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTO);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM PRODUCTO WHERE ID_P >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Producto producto;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				producto = new Producto();
				producto.setId(rs.getInt("ID_P"));
				producto.setNombre(rs.getString("NOMBRE_P"));
				producto.setTp(TipoProducto.valueOf(rs.getString("TP")));
				
				
				
				//Se inserta cada nuevo cliente en la lista de clientes
				productos.add(producto);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d productos...", productos.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDDProducto: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return productos;
	}
	
	
	
	public void borrarDatosProducto() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTO);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM PRODUCTO;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d producto", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDDProducto: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}
	// VIDEOJUEGOS:
	
	public void CrearBBDDVideojuego() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS VIDEOJUEGO (\n"
					+ " ID_V INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ " GENERO ENUM NOT NULL, \n"
					+ " ESTADOPRODUCTO ENUM NOT NULL,\n"
					+ " ANYO INTEGER NOT NULL,\n"
					+ " PRECIO DOUBLE NOT NULL,\n"
					+ " ID_P INTEGER NOT NULL,\n"
					+ " TP ENUM NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Videojuego");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDVideojuego() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS VIDEOJUEGO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Videojuego");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDVideojuego: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_VIDEOJUEGO));
			System.out.println("- Se ha borrado el fichero de la BBDDVideojuego");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDVideojuego: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosVideojuego(List<Videojuego> videojuegos) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO VIDEOJUEGO ( NOMBRE, GENERO, ESTADOPRODUCTO, ANYO, PRECIO, ID_P, TP) VALUES ( '%s', '%s', '%s', '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando videojuegos...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Videojuego c : videojuegos) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getGenero(), c.getEstado(), c.getAnyo(),  c.getPrecio(), c.getId(), c.getTp()))) {					
					System.out.println(String.format(" - Videojuego insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el videojuego: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDDVideojuego: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Videojuego> obtenerDatosVideojuegos() {
		ArrayList<Videojuego> videojuegos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM VIDEOJUEGO WHERE ID_V >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Videojuego videojuego;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				videojuego= new Videojuego();
				videojuego.setId_v(rs.getInt("ID_V"));
				videojuego.setNombre(rs.getString("NOMBRE"));
				videojuego.setGenero(Genero.valueOf(rs.getString("GENERO")));
				videojuego.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
				videojuego.setAnyo(rs.getInt("ANYO"));
				videojuego.setPrecio(rs.getDouble("PRECIO"));
				videojuego.setId(rs.getInt("ID_P"));
				videojuego.setTp(TipoProducto.valueOf(rs.getString("TP")));
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				videojuegos.add(videojuego);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d videojuegos...", videojuegos.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDDVideojuego: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return videojuegos;
	}
	
public Videojuego buscarVideojuegoPorID_P(int id_P) {
		
		Videojuego v = new Videojuego();
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
			 Statement stmt = con.createStatement()) {
			// Ahorro de tiempo - si está en el mapa no se busca en bd
			//if (mapaUsuarios.containsKey( nick )) {
			//	return mapaUsuarios.get( nick );
			//}
			// Si no lo leemos de base de datos
			
			String sql = "select * from VIDEOJUEGO where ID_P = " + id_P + ";";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			v.setId_v(rs.getInt("ID_V"));
			v.setNombre(rs.getString("NOMBRE"));
			v.setGenero(Genero.valueOf(rs.getString("GENERO")));
			v.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
			if (EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")) == EstadoProducto.SEGUNDA_MANO) {
				double prec = rs.getDouble("PRECIO") * 3;
				v.setPrecio(prec);
			} else {
				v.setPrecio(rs.getDouble("PRECIO"));
			}
			v.setAnyo(rs.getInt("ANYO"));
			
			v.setId(rs.getInt("ID_P"));
			v.setTp(TipoProducto.valueOf(rs.getString("TP")));
			
			
			
			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDDVideojuego: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
		
		return v;
	}
	
	
	
	public void borrarDatosVideojuego() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM VIDEOJUEGO;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d videojuego", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
	
	
	
	
	// MANDOS:
	
	public void CrearBBDDMando() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS MANDO (\n"
					+ " ID_M INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ " ESTADOPRODUCTO ENUM NOT NULL,\n"
					+ " PRECIO DOUBLE NOT NULL,\n"
					+ " MARCA ENUM NOT NULL,\n"
					+ " ID_P INTEGER NOT NULL,\n"
					+ " TP ENUM NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Mando");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDDMando: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDMando() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS MANDO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Mando");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDMando: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_MANDO));
			System.out.println("- Se ha borrado el fichero de la BBDDMando");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDMando: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosMando(List<Mando> mandos) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO MANDO ( NOMBRE, ESTADOPRODUCTO, PRECIO, MARCA, ID_P, TP) VALUES ( '%s', '%s', '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando mandos...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Mando c : mandos) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getEstado(), c.getPrecio(),  c.getMarca(), c.getId(), c.getTp()))) {					
					System.out.println(String.format(" - mando insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el mando: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDDMando: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Mando> obtenerDatosMandos() {
		ArrayList<Mando> mandos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM MANDO WHERE ID_M >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Mando mando;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				mando= new Mando();
				mando.setId_m(rs.getInt("ID_M"));
				mando.setNombre(rs.getString("NOMBRE"));
				mando.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
				mando.setPrecio(rs.getDouble("PRECIO"));
				mando.setMarca(Marca.valueOf(rs.getString("MARCA")));
				mando.setId(rs.getInt("ID_P"));
				mando.setTp(TipoProducto.valueOf(rs.getString("TP")));
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				mandos.add(mando);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d mandos...", mandos.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDDMando: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return mandos;
	}
	
	public Mando buscarMandoPorID_P(int id_P) {
		
		Mando m = new Mando();
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
			 Statement stmt = con.createStatement()) {
			// Ahorro de tiempo - si está en el mapa no se busca en bd
			//if (mapaUsuarios.containsKey( nick )) {
			//	return mapaUsuarios.get( nick );
			//}
			// Si no lo leemos de base de datos
			
			String sql = "select * from MANDO where ID_P = " + id_P + ";";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			m.setId_m(rs.getInt("ID_M"));
			m.setNombre(rs.getString("NOMBRE"));
			m.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
			if (EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")) == EstadoProducto.SEGUNDA_MANO) {
				double prec = rs.getDouble("PRECIO") * 3;
				m.setPrecio(prec);
			} else {
				m.setPrecio(rs.getDouble("PRECIO"));
			}
			m.setMarca(Marca.valueOf(rs.getString("MARCA")));
			m.setId(rs.getInt("ID_P"));
			m.setTp(TipoProducto.valueOf(rs.getString("TP")));
			
			
			
			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDDmando: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
		
		return m;
	}
	
	public void borrarDatosMando() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM MANDO;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d mando", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDDMando: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
	
	// CONSOLAS:
	
	public void CrearBBDDConsola() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS CONSOLA (\n"
					+ " ID_C INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ "ESTADOPRODUCTO ENUM NOT NULL,\n"
					+ "PRECIO DOUBLE NOT NULL,\n"
					+ "MARCA ENUM NOT NULL,\n"
					+ "ID_P INTEGER NOT NULL,\n"
					+ " TP ENUM NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Consola");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDDConsola: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDConsola() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS CONSOLA";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Consola");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDConsola: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_CONSOLA));
			System.out.println("- Se ha borrado el fichero de la BBDDConsola");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDConsola: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosConsola(List<Consola> consolas) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CONSOLA ( NOMBRE, ESTADOPRODUCTO, PRECIO, MARCA, ID_P, TP) VALUES ( '%s', '%s', '%s', '%s','%s', '%s');";
			
			System.out.println("- Insertando consolas...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Consola c : consolas) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getEstado(), c.getPrecio(),  c.getMarca(), c.getId(), c.getTp()))) {					
					System.out.println(String.format(" - consola insertada: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado la consola: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDDConsola: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Consola> obtenerDatosConsolas() {
		ArrayList<Consola> consolas = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CONSOLA WHERE ID_C >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Consola consola;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				consola= new Consola();
				consola.setId_c(rs.getInt("ID_C"));
				consola.setNombre(rs.getString("NOMBRE"));
				consola.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
				consola.setPrecio(rs.getDouble("PRECIO"));
				consola.setMarca(Marca.valueOf(rs.getString("MARCA")));
				consola.setId(rs.getInt("ID_P"));
				consola.setTp(TipoProducto.valueOf(rs.getString("TP")));
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				consolas.add(consola);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d consolas...", consolas.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDDConsola: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return consolas;
	}
	
	public Consola buscarConsolaPorID_P(int id_P) {
		
		Consola c = new Consola();
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
			 Statement stmt = con.createStatement()) {
			// Ahorro de tiempo - si está en el mapa no se busca en bd
			//if (mapaUsuarios.containsKey( nick )) {
			//	return mapaUsuarios.get( nick );
			//}
			// Si no lo leemos de base de datos
			
			String sql = "select * from CONSOLA where ID_P = " + id_P + ";";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			c.setId_c(rs.getInt("ID_C"));
			c.setNombre(rs.getString("NOMBRE"));
			c.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
			if (EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")) == EstadoProducto.SEGUNDA_MANO) {
				double prec = rs.getDouble("PRECIO") * 1.25;
				c.setPrecio(prec);
			} else {
				c.setPrecio(rs.getDouble("PRECIO"));
			}
			c.setMarca(Marca.valueOf(rs.getString("MARCA")));
			c.setId(rs.getInt("ID_P"));
			c.setTp(TipoProducto.valueOf(rs.getString("TP")));
			
			
			
			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDDconsola: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
		
		return c;
	}
	
	public void borrarDatosConsola() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM CONSOLA;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d consola", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDDConsola: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
	
	
	// USUARIOS:
	
	public void CrearBBDDUsuario() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS USUARIO (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ " EMAIL TEXT NOT NULL,\n"
					+ " CONTRASEÑA TEXT NOT NULL,\n"
					+ " TELEFONO TEXT NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Usuario");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDUsuario() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS USUARIO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Usuario");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDUsuario: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_USUARIO));
			System.out.println("- Se ha borrado el fichero de la BBDDUsuario");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDUsuario: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosUsuario(List<Usuario> usuarios) {
		System.out.println("insertarDatosUsuario");
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO USUARIO ( NOMBRE, EMAIL, CONTRASEÑA, TELEFONO) VALUES ( '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando usuarios...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Usuario u : usuarios) {
				if (1 == stmt.executeUpdate(String.format(sql, u.getNombre(), u.getEmail(), u.getContrasenya(), u.getTelefono()))) {					
					System.out.println(String.format(" - Usuario insertado: %s", u.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el usuario: %s", u.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
	}
	
	public ArrayList<Usuario> obtenerDatosUsuario() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM USUARIO WHERE ID >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Usuario usuario;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				usuario= new Usuario();
				usuario.setId(rs.getInt("ID"));
				usuario.setNombre(rs.getString("NOMBRE"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setContrasenya(rs.getString("CONTRASEÑA"));
				usuario.setTelefono(rs.getString("TELEFONO"));
				
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				usuarios.add(usuario);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d consolas...", usuarios.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return usuarios;
	}
	
	public void borrarDatosUsuario() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM USUARIO;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d usuario", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
	
	// CARRITOS:
	
	public void CrearBBDDCarrito() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS CARRITO (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " FECHA DATE NOT NULL,\n"
					+ " ESTADOCARRITO ENUM NOT NULL,\n"
					+ " USUARIO STRING NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Carrito");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDDCarrito: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDCarrito() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS CARRITO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Carrito");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDCarrito: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_CARRITO));
			System.out.println("- Se ha borrado el fichero de la BBDDCarrito");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDCarrito: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosCarrito() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CARRITO (FECHA,ESTADOCARRITO,USUARIO) VALUES ('%s','%s','%s');";
			
			System.out.println("- Insertando carritos...");
			
			try(BufferedReader br = new BufferedReader(new FileReader("Data/carrito.csv"))) {
				StringTokenizer tokenizer;
				String linea = null;
				
				br.readLine();
				
				while ((linea = br.readLine()) != null) {
					tokenizer = new StringTokenizer(linea, ";");
					
					if (1 == stmt.executeUpdate(String.format(sql, tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken()))) {	//no sabemos si hay que poner el string en su valor real				
						System.out.println(" - Carrito insertado:");
					} else {
						System.out.println(" - No se ha insertado el carrito: ");
					}
				}
				
			} catch (Exception e) {
				System.err.println("error al cargar los datos del csvCarrito");
				e.printStackTrace();
			}
			
					
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public void insertarCarritoUnico( String f, String ec, String mail) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CARRITO ( FECHA, ESTADOCARRITO, USUARIO) VALUES ( '%s', '%s', '%s');";
			
			System.out.println("- Insertando carrito...");
			
			
				if (1 == stmt.executeUpdate(String.format(sql, f, ec, mail))) {					
					System.out.println(" - Carrito insertado: ");
				} else {
					System.out.println(" - No se ha insertado el carrito: ");
				}
						
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Carrito> obtenerDatosCarritos() {
		ArrayList<Carrito> carritos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CARRITO WHERE ID >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			Usuario usuario;
			
			Carrito carrito;
			
			
			
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				carrito= new Carrito();
				carrito.setId(rs.getInt("ID"));
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				String fecha = rs.getString("FECHA");
				Date fechaFormateada = sdf.parse(fecha);
				
				
				carrito.setFecha(fechaFormateada);
				carrito.setElementos(ObtenerPagablesPorArrayDeProductos(ObtenerProductosConIDCarrito(rs.getInt("ID"))));
				
						
				carrito.setEstadoCarrito(EstadoCarrito.valueOf(rs.getString("ESTADOCARRITO")));
				
				Usuario u = buscarUsuarioPorEmail(rs.getString("USUARIO"));
				
				
				carrito.setUsuario(u);
			
				
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				carritos.add(carrito);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d carritos...", carritos.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return carritos;
	}
	
	public void borrarDatosCarrito() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "DELETE FROM CARRITO;";			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(String.format("- Se han borrado %d carrito", result));
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
		
	
	//OTROS METODOS
	
	
	// mira si el usuario con el mail y contraseña es valido
	public String iniciarSesion(String mail, String pass) {
		String msg= "iniciarSesion - error no gestionado";
		if("".equals(mail) || "".equals(pass)) {
			msg = "Por favor escribe usuario y contraseña";
			return msg;
		}
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
			Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM USUARIO;";			
			ResultSet rs = stmt.executeQuery(sql);
			boolean encontrado = false;
			while (rs.next()) {			
				// login usando mail y contraseña
				if(mail.equals(rs.getString("EMAIL"))) {
					//identificador de usuario encontrado
					msg = "Contraseña incorrecta";
					String cont = rs.getString("CONTRASEÑA");
					if(pass.equals(rs.getString("CONTRASEÑA"))) {
						GestorLog.info("OK - nombre de usuario y contraseña correctos");
						msg = "OK";
						Usuario u= new Usuario();
						u.setEmail(rs.getString("EMAIL"));
						u.setContrasenya(rs.getString("CONTRASEÑA"));
						u.setId(rs.getInt("ID"));
						u.setNombre(rs.getString("NOMBRE"));
						u.setTelefono(rs.getString("TELEFONO"));
						GestorBD.this.logedUser = u;
						GestorLog.info("usaurio guardado en GestorDB");
					}
					encontrado = true;
					break;//como ha encontrado un usuario sale del while
				}
			}
			if(!encontrado) {
					msg = "El usuario indicado no existe";
					GestorLog.warning(msg);
			}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al consultar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();	
			msg = "error al conectarse a la base de datos";
			GestorLog.warning(msg);
		}	
		return msg;
	}
	

		
	public String crearCuenta(String nombre,String mail, String pass,String tel) {
		
		String msg = "crearCuenta - error no gestionado";
		
		if("".equals(mail) || "".equals(pass) || "".equals(nombre) || "".equals(tel)) {
			msg = "Por favor rellene todos los datos";
			return msg;
		}
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
				Statement stmt = con.createStatement()) {
			
			String sql = "SELECT * FROM USUARIO;";			
			ResultSet rs = stmt.executeQuery(sql);
			boolean encontrado = false;
			while (rs.next()) {
				
				if(mail.equals(rs.getString("EMAIL"))) {
					encontrado = true;
					break;
				}
			}
			
			if(encontrado) {
				msg = "Este email ya tiene una cuenta asociada, por favor intente con otro";
			} else {
				try (Connection con1 = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
						Statement stmt1 = con.createStatement()){
					
					String sql1 = "INSERT INTO USUARIO (NOMBRE,EMAIL,CONTRASEÑA,TELEFONO) VALUES (  '"+nombre+"','"+mail+"','"+pass+"','"+tel+"' );";
					
					
					//con.commit();
					Usuario u= new Usuario();
					u.setEmail(mail);
					u.setContrasenya(pass);
					u.setNombre(nombre);
					u.setTelefono(tel);
					GestorBD.this.logedUser = u;
					
					//stmt.executeUpdate(sql1);
					stmt.executeUpdate(String.format(sql1, u.getNombre(), u.getEmail(), u.getContrasenya(), u.getTelefono()));

					msg="OK";
					
				}catch(Exception ex) {
					System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
					ex.printStackTrace();	
					msg = "error al conectarse a la base de datos";
					GestorLog.warning(msg);
				}
			}
			
			
		}catch (Exception ex) {
			System.err.println(String.format("* Error al buscar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();	
			msg = "error al conectarse a la base de datos";
			GestorLog.warning(msg);
		}
		return msg;
	}
	
	
	
	
	
	
	
	public List<Carrito> buscarCarritosDeUsuario( String emailUsuario ) {
		ArrayList<Carrito> l = new ArrayList<>();
		
		
		//"select ID,FECHA,ESTADOCARRITO,USUARIO from CARRITO where USUARIO = '" + emailUsuario + "'"; EL QUE TENIAMOS
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
			Statement stmt = con.createStatement()) {
			
			String sql = "select * from CARRITO where USUARIO =  '" + emailUsuario + "'";
			
			ResultSet rs = stmt.executeQuery( sql );
			
			Carrito carrito;
			
			while (rs.next()) {
				
				carrito = new Carrito();
				
				int id = rs.getInt("ID");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				String fecha = rs.getString("FECHA");
				System.out.println(fecha);
				Date fechaFormateada = sdf.parse(fecha);
				System.out.println(fechaFormateada);
				
				
	
				ArrayList<Pagable> Pagables = ObtenerPagablesPorArrayDeProductos(ObtenerProductosConIDCarrito(id));
				
				Usuario usuario = buscarUsuarioPorEmail( emailUsuario );
				EstadoCarrito estado = EstadoCarrito.valueOf(rs.getString("ESTADOCARRITO"));
				
				
				
				carrito.setId(id);
				carrito.setFecha(fechaFormateada);
				
				carrito.setElementos(Pagables);
				
				carrito.setUsuario(usuario);
				carrito.setEstadoCarrito(estado);
				
				System.out.println(carrito);
				l.add( carrito );
			}
			rs.close();
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
		System.out.println(l);
		
		return l;
	}
	
	
	public Carrito buscarCarritoPorId(int id , String mail ) {
		
		
		Carrito carrito = new Carrito();
		
		
		//"select ID,FECHA,ESTADOCARRITO,USUARIO from CARRITO where USUARIO = '" + emailUsuario + "'"; EL QUE TENIAMOS
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
			Statement stmt = con.createStatement()) {
			
			String sql = "select * from CARRITO where ID =" + id ;
			
			ResultSet rs = stmt.executeQuery( sql );
			
			
			
			
				
				
				
				int id1 = rs.getInt("ID");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				String fecha = rs.getString("FECHA");
				Date fechaFormateada = sdf.parse(fecha);
				
				
	
				ArrayList<Pagable> Pagables = ObtenerPagablesPorArrayDeProductos(ObtenerProductosConIDCarrito(id));
				
				Usuario usuario = buscarUsuarioPorEmail( mail );
				EstadoCarrito estado = EstadoCarrito.valueOf(rs.getString("ESTADOCARRITO"));
				
				
				
				carrito.setId(id1);
				carrito.setFecha(fechaFormateada);
				
				carrito.setElementos(Pagables);
				
				carrito.setUsuario(usuario);
				carrito.setEstadoCarrito(estado);
				
				
				
			
				rs.close();
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
		
		
		return carrito;
	}
	
	
	public Usuario buscarUsuarioPorEmail(String email) {
		
		Usuario usuario = new Usuario();
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
			 Statement stmt = con.createStatement()) {
			// Ahorro de tiempo - si está en el mapa no se busca en bd
			//if (mapaUsuarios.containsKey( nick )) {
			//	return mapaUsuarios.get( nick );
			//}
			// Si no lo leemos de base de datos
			
			String sql = "select * from USUARIO where EMAIL = '" + email + "'";
			
			ResultSet rs = stmt.executeQuery(sql);
			
				usuario = new Usuario();
				usuario.setId(rs.getInt("ID"));
				usuario.setNombre(rs.getString("NOMBRE"));
				usuario.setEmail(rs.getString("EMAIL"));
				usuario.setContrasenya(rs.getString("CONTRASEÑA"));
				usuario.setTelefono(rs.getString("TELEFONO"));
			
			
			
			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
		
		return usuario;
	}

	
	//ProductosCarrito
	
	public void CrearBBDDProductosCarrito() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTOSCARRITO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS PRODUCTOSCARRITO (\n"
					+ " ID_CP INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " ID_C INTEGER NOT NULL, \n"
					+ " ID_P INTEGER NOT NULL, \n"
					+ " FOREIGN KEY(ID_C) REFERENCES CARRITO(ID) ON DELETE CASCADE, \n"
					+ " FOREIGN KEY(ID_P) REFERENCES PRODUCTO(ID_P) \n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla ProductosCarrito");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDDProductosCarrito: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDProductosCarrito() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTOSCARRITO);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS PRODUCTOSCARRITO";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla ProductosCarrito");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDProductosCarrito: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_PRODUCTOSCARRITO));
			System.out.println("- Se ha borrado el fichero de la BBDDProductosCarrito");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDProductosCarrito: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosProductoCarrito() { 
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTOSCARRITO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO PRODUCTOSCARRITO ( ID_C, ID_P) VALUES ( '%s', '%s');";
			
			System.out.println("- Insertando productos...");
			
			try (BufferedReader BR = new BufferedReader(new FileReader("Data/CarritoProducto.csv"))){
				StringTokenizer tokenizer;
				String linea = null;
				
				BR.readLine();
				
				while ((linea = BR.readLine()) != null) {
					tokenizer = new StringTokenizer(linea, ";");
					
				
					if (1 == stmt.executeUpdate(String.format(sql, tokenizer.nextToken(), tokenizer.nextToken()))) {
						System.out.println(" - ProductoCarrito insertado: ");

					} else {
						System.out.println(" - No se ha insertado el productos_carrito: ");
						}
				}
				
			} catch (Exception ex) {
				System.err.println(String.format("* Error al insertar datos de la BBDDProductosCarrito: %s", ex.getMessage()));
				ex.printStackTrace();		
			}
			
			
					
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDDProductosCarrito: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public void insertarDatosProductoCarritoUnico(Carrito c) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTOSCARRITO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO PRODUCTOSCARRITO ( ID_C, ID_P) VALUES ( '%s', '%s');";
			
			System.out.println("- Insertando productos de un carrito...");
			
			
			for (Pagable p : c.getElementos()) {
				
				Producto prod = (Producto) p;
				
				if (1 == stmt.executeUpdate(String.format(sql, c.getId(), prod.getId()))) {
					System.out.println(" - ProductoCarrito insertado: ");

				} else {
					System.out.println(" - No se ha insertado el productos_carrto: ");
					}
			}
				
			} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDDProductosCarrito: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	
	}
	
	
	
	
	public ArrayList<Producto> ObtenerProductosConIDCarrito(int id) {
		ArrayList<Producto> productos = new ArrayList<>();
		
		// obtener datos de la tabla producto para comparar los id
		ArrayList<Producto> p = obtenerDatosProducto();
		
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_PRODUCTOSCARRITO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "select ID_P from PRODUCTOSCARRITO where ID_C = " + id ;
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id_p = rs.getInt("ID_P");
				
				for (Producto producto : p) {
					if (producto.getId() == id_p) {
						productos.add(producto);
					}
				}
				
				}
			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDProductosCarrito: %s", ex.getMessage()));
			ex.printStackTrace();	
		}
		return productos;
		
	}
	
	public ArrayList<Pagable> ObtenerPagablesPorArrayDeProductos (ArrayList<Producto> productos) {
		ArrayList<Pagable> pagables = new ArrayList<>();
		for (Producto p : productos) {
			if (p.tp == TipoProducto.VIDEOJUEGO) {
				try(Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
					Statement stmt = con.createStatement()) {
					
					Videojuego v = new Videojuego();
					
					String sql = "select * from VIDEOJUEGO where ID_P = ' " + p.getId() + " '";
					ResultSet rs = stmt.executeQuery(sql);
					
					v.setId_v(rs.getInt("ID_V"));
					v.setNombre(rs.getString("NOMBRE"));
					v.setGenero(Genero.valueOf(rs.getString("GENERO")));
					v.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
					v.setAnyo(rs.getInt("ANYO"));
					v.setPrecio(rs.getDouble("PRECIO"));
					v.setId(p.getId());
					v.setTp(TipoProducto.valueOf(rs.getString("TP")));
					
					pagables.add(v);
					
				} catch (Exception ex) {
					System.err.println(String.format("* Error al borrar el archivo de la BBDDProductosCarrito: %s", ex.getMessage()));
					ex.printStackTrace();	
				}
			
				
			} else if (p.tp == TipoProducto.MANDO){
				try(Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
					Statement stmt = con.createStatement()) {
					
					Mando m = new Mando();
					
					String sql = "select * from MANDO where ID_P = ' " + p.getId() + " '";
					ResultSet rs = stmt.executeQuery(sql);
					
					m.setId_m(rs.getInt("ID_M"));
					m.setNombre(rs.getString("NOMBRE"));
					m.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
					m.setPrecio(rs.getDouble("PRECIO"));
					m.setMarca(Marca.valueOf(rs.getString("MARCA")));
					m.setId(p.getId());
					m.setTp(TipoProducto.valueOf(rs.getString("TP")));
					
					pagables.add(m);
					
				} catch (Exception ex) {
					System.err.println(String.format("* Error al borrar el archivo de la BBDDProductosCarrito: %s", ex.getMessage()));
					ex.printStackTrace();	
				} 
					
				
			} else {
				try(Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
					Statement stmt = con.createStatement()) {
					
					Consola c = new Consola();
					
					String sql = "select * from CONSOLA where ID_P = ' " + p.getId() + " '";
					ResultSet rs = stmt.executeQuery(sql);
					
					c.setId_c(rs.getInt("ID_C"));
					c.setNombre(rs.getString("NOMBRE"));
					c.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
					c.setPrecio(rs.getDouble("PRECIO"));
					c.setMarca(Marca.valueOf(rs.getString("MARCA")));
					c.setId(p.getId());
					c.setTp(TipoProducto.valueOf(rs.getString("TP")));
					
					pagables.add(c);
					
				} catch (Exception ex) {
					System.err.println(String.format("* Error al borrar el archivo de la BBDDProductosCarrito: %s", ex.getMessage()));
					ex.printStackTrace();	
				} 
			}
			
			}
		
		return pagables;
		
	}
	
	public void CambiarEstadodeCarritoConId (int id_c, EstadoCarrito estado) {
		
		
		
		if(estado == EstadoCarrito.PREPARACION) {
			try(Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
					Statement stmt = con.createStatement()) {
				
				String sql = "update CARRITO set ESTADOCARRITO = 'LISTO' where ID= " + id_c;   
				
				JOptionPane.showMessageDialog(null, "Se ha cambiado el estado a LISTO");
				
			
			}catch(Exception ex) {
				System.err.println(String.format("* Error al cambiar el estado de carrito a LISTO : %s", ex.getMessage()));
				ex.printStackTrace();
				
			}
			
		}else if(estado == EstadoCarrito.LISTO){
			
			try(Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
					Statement stmt = con.createStatement()) {
				
				String sql = "update CARRITO set ESTADOCARRITO = 'RECOGIDO' where ID= " + id_c;   
				
				JOptionPane.showMessageDialog(null, "Se ha cambiado el estado a RECOGIDO");
				
				
			}catch(Exception ex) {
				System.err.println(String.format("* Error al cambiar el estado de carrito a RECOGIDO : %s", ex.getMessage()));
				ex.printStackTrace();
				
			}
		}else {
			JOptionPane.showMessageDialog(null, "No se puede cambiar el estado de este pedido");
		}
		
		
	
	
	
	
	
	}
	
}




