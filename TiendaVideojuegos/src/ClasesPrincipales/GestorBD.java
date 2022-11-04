package ClasesPrincipales;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class GestorBD {
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE_VIDEOJUEGO = "db/databasevideojuego.db";
	protected static final String DATABASE_FILE_CONSOLA = "db/databaseconsola.db";
	protected static final String DATABASE_FILE_MANDO = "db/databasemando.db";
	protected static final String CONNECTION_STRING_VIDEOJUEGO = "jdbc:sqlite:" + DATABASE_FILE_VIDEOJUEGO;
	protected static final String CONNECTION_STRING_CONSOLA = "jdbc:sqlite:" + DATABASE_FILE_CONSOLA;
	protected static final String CONNECTION_STRING_MANDO= "jdbc:sqlite:" + DATABASE_FILE_MANDO;
	
	public GestorBD() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException ex) {
			System.err.println(String.format("* Error al cargar el driver de BBDD: %s", ex.getMessage()));
			ex.printStackTrace();
		}
	}
	
	public void CrearBBDDVideojuego() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS VIDEOJUEGO (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NAME TEXT NOT NULL,\n"
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
	
	public void CrearBBDDConsola() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS CONSOLA (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NAME TEXT NOT NULL,\n"
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
	
	public void CrearBBDDMando() {
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
			 Statement stmt = con.createStatement()) {
			
			String sql = "CREATE TABLE IF NOT EXISTS MANDO (\n"
					+ " ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
					+ " NAME TEXT NOT NULL,\n"
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
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_VIDEOJUEGO));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
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
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_CONSOLA));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
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
			System.err.println(String.format("* Error al borrar la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();			
		}
		
		try {
			//Se borra el fichero de la BBDD
			Files.delete(Paths.get(DATABASE_FILE_MANDO));
			System.out.println("- Se ha borrado el fichero de la BBDD");
		} catch (Exception ex) {
			System.err.println(String.format("* Error al borrar el archivo de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}
	}
	
	
	public void insertarDatosVideojuego(Videojuego... videojuegos) {
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
	
	public void insertarDatosConsola(Consola... consolas) {
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
	
	public void insertarDatosMando(Mando... mandos) {
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
				videojuego.setGenero(rs.getObject("GENERO",Genero.class));
				videojuego.setEstado(rs.getObject("ESTADOPRODUCTO", EstadoProducto.class));
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
				consola.setEstado(rs.getObject("ESTADOPRODUCTO", EstadoProducto.class));
				consola.setPrecio(rs.getDouble("PRECIO"));
				consola.setMarca(rs.getObject("MARCA", Marca.class));
				
			
				
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
				mando.setEstado(rs.getObject("ESTADOPRODUCTO", EstadoProducto.class));
				mando.setPrecio(rs.getDouble("PRECIO"));
				mando.setMarca(rs.getObject("MARCA", Marca.class));
				
			
				
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
	
	public void actualizarNombreVideojuego(Videojuego videojuego, String newNombre) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_VIDEOJUEGO);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "UPDATE VIDEOJUEGO SET NOMBRE = '%s' WHERE ID = %d;";
	
			
			int result = stmt.executeUpdate(String.format(sql, newNombre, videojuego.getId()));
	
			System.out.println(String.format("- Se ha actulizado %d videojuego", result));
			
			//	System.out.println();
		} catch (Exception ex) {
			System.err.println(String.format("* Error actualizando datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
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
	
	public void actualizarNombreConsola(Consola consola, String newNombre) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_CONSOLA);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "UPDATE CONSOLA SET NOMBRE = '%s' WHERE ID = %d;";
	
			
			int result = stmt.executeUpdate(String.format(sql, newNombre, consola.getId()));
	
			System.out.println(String.format("- Se ha actulizado %d consola", result));
			
			//	System.out.println();
		} catch (Exception ex) {
			System.err.println(String.format("* Error actualizando datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
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
	
	public void actualizarNombreMando(Mando mando, String newNombre) {
		//Se abre la conexiÃ³n y se obtiene el Statement
		try (Connection con = DriverManager.getConnection(CONNECTION_STRING_MANDO);
		     Statement stmt = con.createStatement()) {
			//Se ejecuta la sentencia de borrado de datos
			String sql = "UPDATE MANDO SET NOMBRE = '%s' WHERE ID = %d;";
	
			
			int result = stmt.executeUpdate(String.format(sql, newNombre, mando.getId()));
	
			System.out.println(String.format("- Se ha actulizado %d mando", result));
			
			//	System.out.println();
		} catch (Exception ex) {
			System.err.println(String.format("* Error actualizando datos de la BBDD: %s", ex.getMessage()));
			ex.printStackTrace();						
		}		
	}
	
	
	
	
	

}
