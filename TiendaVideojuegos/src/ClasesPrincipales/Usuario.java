package ClasesPrincipales;

public class Usuario {
	protected int id;
	protected String nombre;
	protected String email;
	protected String contrasenya;
	protected String telefono;
	
	
	public Usuario(int id,String nombre, String email, String contrasenya, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.contrasenya = contrasenya;
		this.telefono = telefono;
	}
	
	public Usuario() {
		super();
		this.id = 0;
		this.nombre = "";
		this.email = "";
		this.contrasenya = "";
		this.telefono = "";
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contraseña) {
		this.contrasenya = contraseña;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return nombre + ";" + email + ";" + contrasenya + ";" + telefono ;
	}
	
	public boolean equals(Object o) {
		if(o != null && o instanceof Usuario) {
			return this.email.equals(((Usuario)o).email);
		}
		return false;
		
	}
	
	
	

}
