package ClasesPrincipales;

import java.io.Serializable;

public class Videojuego extends Producto implements Serializable,Pagable {
	
	protected int año;
	protected double precio;
	

	@Override
	public double getPrecio() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
