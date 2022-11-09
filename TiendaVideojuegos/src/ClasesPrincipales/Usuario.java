package ClasesPrincipales;

public class Usuario {
	protected int id;
	protected String nombre;
	protected String email;
	protected String contraseña;
	protected String telefono;
	
	
	public Usuario(int id,String nombre, String email, String contraseña, String telefono) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.contraseña = contraseña;
		this.telefono = telefono;
	}
	
	public Usuario() {
		super();
		this.id = 0;
		this.nombre = "Sin nombre";
		this.email = "Sin email";
		this.contraseña = "Sin contraseña";
		this.telefono = "000000000";
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "Usuario:" + nombre + ", email:" + email + ", contraseña:" + contraseña + ", telefono:"
				+ telefono ;
	}
	
	
	
	

}
