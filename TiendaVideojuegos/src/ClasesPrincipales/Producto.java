package ClasesPrincipales;

public class Producto implements Comparable<Producto>{
	protected int id;
	protected String nombre;
	protected TipoProducto tp;
	
	
	
	public Producto(int id, String nombre, TipoProducto tp) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tp = tp;
	}



	public Producto() {
		super();
		this.id = 0;
		this.nombre = "Sin nombre";
		this.tp = TipoProducto.VIDEOJUEGO;
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
	
	public TipoProducto getTp() {
		return tp;
	}

	public void setTp(TipoProducto tp) {
		this.tp = tp;
	}



	@Override
	public String toString() {
		return "Producto " + nombre + "(id: " + id + ")";
	}



	@Override
	public int compareTo(Producto o) {
		// TODO Auto-generated method stub
		return Integer.compare(this.id, o.getId());
	}
	
	
	
	
	
	
}
