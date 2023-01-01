package ClasesPrincipales;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

public class GestorBD {
    private static final Logger LOG = Logger.getLogger(GestorBD.class.getName());

	protected static final String DRIVER_NAME = "org.sqlite.JDBC";	
	
	protected static final String DATABASE_FILE_PRODUCTO = "db/databaseproducto.db";
	protected static final String DATABASE_FILE_VIDEOJUEGO = "db/databasevideojuego.db";
	protected static final String DATABASE_FILE_CONSOLA = "db/databaseconsola.db";
	protected static final String DATABASE_FILE_MANDO = "db/databasemando.db";
	protected static final String DATABASE_FILE_USUARIO = "db/databaseusuario.db";
	protected static final String DATABASE_FILE_CARRITO = "db/databasecarrito.db";
	protected static final String DATABASE_FILE_SERVICIO = "db/databaseservicio.db";
	
	protected static final String CONNECTION_STRING_PRODUCTO = "jdbc:sqlite:" + DATABASE_FILE_PRODUCTO;
	protected static final String CONNECTION_STRING_VIDEOJUEGO = "jdbc:sqlite:" + DATABASE_FILE_VIDEOJUEGO;
	protected static final String CONNECTION_STRING_CONSOLA = "jdbc:sqlite:" + DATABASE_FILE_CONSOLA;
	protected static final String CONNECTION_STRING_MANDO= "jdbc:sqlite:" + DATABASE_FILE_MANDO;
	protected static final String CONNECTION_STRING_USUARIO= "jdbc:sqlite:" + DATABASE_FILE_USUARIO;
	protected static final String CONNECTION_STRING_CARRITO = "jdbc:sqlite:" + DATABASE_FILE_CARRITO;
	protected static final String CONNECTION_STRING_SERVICIO = "jdbc:sqlite:" + DATABASE_FILE_SERVICIO;
	
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
			String sql = "INSERT INTO PRODUCTO ( NOMBRE, TP) VALUES ( '%s', '%s');";
			
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
			String sql = "SELECT * FROM PRODUCTO WHERE ID >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Producto producto;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				producto = new Producto();
				producto.setId(rs.getInt("ID"));
				producto.setNombre(rs.getString("NOMBRE"));
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
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ " GENERO ENUM NOT NULL, \n"
					+ "ESTADOPRODUCTO ENUM NOT NULL,\n"
					+ "ANYO INTEGER NOT NULL,\n"
					+ "PRECIO DOUBLE NOT NULL\n"
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
			String sql = "INSERT INTO VIDEOJUEGO ( NOMBRE, GENERO, ESTADOPRODUCTO, ANYO, PRECIO) VALUES ( '%s', '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando videojuegos...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Videojuego c : videojuegos) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getGenero(), c.getEstado(), c.getAnyo(),  c.getPrecio()))) {					
					System.out.println(String.format(" - Videojuego insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el videojuego: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Videojuego> obtenerDatosVideojuegos() {
		ArrayList<Videojuego> videojuegos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM VIDEOJUEGO WHERE ID >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Videojuego videojuego;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				videojuego= new Videojuego();
				videojuego.setId(rs.getInt("ID"));
				videojuego.setNombre(rs.getString("NOMBRE"));
				videojuego.setGenero(Genero.valueOf(rs.getString("GENERO")));
				videojuego.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
				videojuego.setAnyo(rs.getInt("ANYO"));
				videojuego.setPrecio(rs.getDouble("PRECIO"));
				
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				videojuegos.add(videojuego);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d videojuegos...", videojuegos.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return videojuegos;
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
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ "ESTADOPRODUCTO ENUM NOT NULL,\n"
					+ "PRECIO DOUBLE NOT NULL,\n"
					+ "MARCA ENUM NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Mando");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
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
			String sql = "INSERT INTO MANDO ( NOMBRE, ESTADOPRODUCTO, PRECIO, MARCA) VALUES ( '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando mandos...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Mando c : mandos) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getEstado(), c.getPrecio(),  c.getMarca()))) {					
					System.out.println(String.format(" - mando insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el mando: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Mando> obtenerDatosMandos() {
		ArrayList<Mando> mandos = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM MANDO WHERE ID >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Mando mando;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				mando= new Mando();
				mando.setId(rs.getInt("ID"));
				mando.setNombre(rs.getString("NOMBRE"));
				mando.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
				mando.setPrecio(rs.getDouble("PRECIO"));
				mando.setMarca(Marca.valueOf(rs.getString("MARCA")));
				
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				mandos.add(mando);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d mandos...", mandos.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return mandos;
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
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
	
	// CONSOLAS:
	
	public void CrearBBDDConsola() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS CONSOLA (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NOMBRE TEXT NOT NULL,\n"
					+ "ESTADOPRODUCTO ENUM NOT NULL,\n"
					+ "PRECIO DOUBLE NOT NULL,\n"
					+ "MARCA ENUM NOT NULL\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla Consola");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
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
			String sql = "INSERT INTO CONSOLA ( NOMBRE, ESTADOPRODUCTO, PRECIO, MARCA) VALUES ( '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando consolas...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Consola c : consolas) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getNombre(), c.getEstado(), c.getPrecio(),  c.getMarca()))) {					
					System.out.println(String.format(" - consola insertada: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado la consola: %s", c.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public ArrayList<Consola> obtenerDatosConsolas() {
		ArrayList<Consola> consolas = new ArrayList<>();
		
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
		     Statement stmt = con.createStatement()) {
			String sql = "SELECT * FROM CONSOLA WHERE ID >= 0";
	//		System.out.println(sql);
			
			//Se ejecuta la sentencia y se obtiene el ResultSet con los resutlados
			ResultSet rs = stmt.executeQuery(sql);		
			
			
			Consola consola;
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				consola= new Consola();
				consola.setId(rs.getInt("ID"));
				consola.setNombre(rs.getString("NOMBRE"));
				consola.setEstado(EstadoProducto.valueOf(rs.getString("ESTADOPRODUCTO")));
				consola.setPrecio(rs.getDouble("PRECIO"));
				consola.setMarca(Marca.valueOf(rs.getString("MARCA")));
				
			
				
				//Se inserta cada nuevo cliente en la lista de clientes
				consolas.add(consola);
			}
			
			//Se cierra el ResultSet
			rs.close();
			
			System.out.println(String.format("- Se han recuperado %d consolas...", consolas.size()));			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al obtener datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
		
		return consolas;
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
			System.err.println(String.format("* Error al borrar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}	
	
	
	// USUARIOS:
	
	public void CrearBBDDUsuario() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS USUARIO (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \\n"
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
	// SERVICIOS:
	
	public void CrearBBDDServicio() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_SERVICIO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS SERVICIO (\n"
					+ " TEXT EMAIL PRIMARY KEY , \n"
					+ " TIPOSERVICIO ENUM NOT NULL,\n"
					+ " FECHA DATE NOT NULL, \n"
					+ " DESCRIP TEXT\n"
					+ ");";
					
			if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha creado la tabla SERVICIO");}
		}catch (Exception ex) {
			System.err.println(String.format("* Error al crear la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void borrarBBDDServicio() {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_SERVICIO);
		     Statement stmt = con.createStatement()) {
			
	        String sql = "DROP TABLE IF EXISTS servicio";
			
	        //Se ejecuta la sentencia de creación de la tabla Estudiantes
	        if (!stmt.execute(sql)) {
	        	System.out.println("- Se ha borrado la tabla Servicio");
	        }
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar la BBDDServicio: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_SERVICIO));
			System.out.println("- Se ha borrado el fichero de la BBDDServicio");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDDServicio: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	public void insertarDatosServicioCSV(List<Servicio> servicio) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_SERVICIO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO SERVICIO ( USUARIO, TIPO, FECHA, DESCRIP) VALUES ( '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando servicios...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Servicio s : servicio) {
				if (1 == stmt.executeUpdate(String.format(sql, s.getU(), s.getTipo(), s.getFecha1(), s.getDescrip()))) {					
					System.out.println(String.format(" - Usuario insertado: %s", s.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el servicio: %s", s.toString()));
				}
			}			
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}				
	}
	
	public void insertarDatosServicio(Servicio s) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_SERVICIO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO SERVICIO ( USUARIO, TIPO, FECHA, DESCRIP) VALUES ( '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando servicios...");
			
			//Se recorren los clientes y se insertan uno a uno
			
			stmt.executeUpdate(String.format(sql, s.getU(), s.getTipo(), s.getFecha1(), s.getDescrip()));					
			System.out.println(String.format(" - Usuario insertado: %s", s.toString()));
							
		} catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
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
					+ " ELEMENTOS ARRAYLIST<PAGABLE> NOT NULL, \n"
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
	
	public void insertarDatosCarrito(List<Carrito> carritos) {
		//Se abre la conexión y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CARRITO);
		     Statement stmt = con.createStatement()) {
			//Se define la plantilla de la sentencia SQL
			String sql = "INSERT INTO CARRITO ( FECHA, ELEMENTOS, ESTADOCARRITO, USUARIO) VALUES ( '%s', '%s', '%s', '%s');";
			
			System.out.println("- Insertando carritos...");
			
			//Se recorren los clientes y se insertan uno a uno
			for (Carrito c : carritos) {
				if (1 == stmt.executeUpdate(String.format(sql, c.getFecha(), c.getElementos(), c.getEstadoCarrito(), c.getUsuario()))) {					
					System.out.println(String.format(" - Carrito insertado: %s", c.toString()));
				} else {
					System.out.println(String.format(" - No se ha insertado el carrito: %s", c.toString()));
				}
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
			StringTokenizer tokenizer;
			Carrito carrito;
			String elementos;
			ArrayList<Pagable> p;
			
			
			//Se recorre el ResultSet y se crean objetos Cliente
			while (rs.next()) {
				carrito= new Carrito();
				carrito.setId(rs.getInt("ID"));
				carrito.setFecha(rs.getDate("FECHA"));
				carrito.setUsuario(rs.get("USUARIO"));
				
				//elementos = rs.getString("ELEMENTOS");
				//p = new ArrayList<Pagable>();
				//String[] elementos2 = elementos.split(", ");
				//for (String e : elementos2) {
				//	p.add(null);
				//}
				
				
				
				
				carrito.setEstadoCarrito(EstadoCarrito.valueOf(rs.getString("ESTADOCARRITO")));
				
				String u = rs.getString("USUARIO");
				tokenizer = new StringTokenizer(u,";");
				usuario= new Usuario();
				usuario.setId(Integer.parseInt(tokenizer.nextToken()));
				usuario.setNombre(tokenizer.nextToken());
				usuario.setEmail(tokenizer.nextToken());
				usuario.setContrasenya(tokenizer.nextToken());
				usuario.setTelefono(tokenizer.nextToken());
				
				carrito.setEmail(usuario);
			
				
			
				
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
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_USUARIO);
			Statement stmt = con.createStatement()) {
			String sql = "INSERT INTO USUARIO (NOMBRE,EMAIL,CONTRASEÑA,TELEFONO) VALUES (  "+nombre+","+mail+","+pass+","+tel+" );";
			
			stmt.executeUpdate(sql);
			//con.commit();
			Usuario u= new Usuario();
			u.setEmail(			mail);
			u.setContrasenya(	pass);
			u.setNombre(		nombre);
			u.setTelefono(		tel);
			GestorBD.this.logedUser = u;

			msg="OK";
			
		}catch (Exception ex) {
			System.err.println(String.format("* Error al insertar datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();	
			msg = "error al conectarse a la base de datos";
			GestorLog.warning(msg);
		}
		return msg;
	}
	
	
	
	public List<Carrito> buscarCarritosDeUsuario( String emailUsuario ) {
		ArrayList<Carrito> l = new ArrayList<>();
		String sql = "select ID,FECHA,ESTADOCARRITO,USUARIO from CARRITO where USUARIO = '" + emailUsuario + "'";
		try {
			log( Level.INFO, "Lanzada consulta a base de datos: " + sql, null );
			ResultSet rs = statement.executeQuery( sql );
			while (rs.next()) {
				int id = rs.getInt("ID");
				Date fecha = rs.getDate("FECHA");
	
				
				Usuario usuario = buscarUsuarioPorEmail( emailUsuario );
				
				
				Foto foto = new Foto( codigo, usuario, categoria, fecha, ruta );
				l.add( carrito );
			}
			rs.close();
		} catch (SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en búsqueda de base de datos: " + sql, e );
		}
		return l;
	}
	
	
	public Usuario buscarUsuarioPorEmail(String email) {
		String sql = "select * from usuario where nick = '" + nick + "'";
		try {
			// Ahorro de tiempo - si está en el mapa no se busca en bd
			if (mapaUsuarios.containsKey( nick )) {
				return mapaUsuarios.get( nick );
			}
			// Si no lo leemos de base de datos
			log( Level.INFO, "Lanzada consulta a base de datos: " + sql, null );
			ResultSet rs = statement.executeQuery( sql );
			if (rs.next()) {
				Usuario usuario = new Usuario(
					rs.getInt("codigo"), rs.getInt("nivel"), rs.getString("nick"), rs.getInt("numFotos") );
				rs.close();
				mapaUsuarios.put( usuario.getNick(), usuario );
				return usuario;
			} else {
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			lastError = e;
			log( Level.SEVERE, "Error en búsqueda de base de datos: " + sql, e );
			return null;
		}
	}

}




