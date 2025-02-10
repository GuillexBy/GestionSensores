package restaurantes.servicio;

public class RestauranteResumen {

	private String id;
	private String nombre;
	private double longitud;
	private double latitud;
	private String gestor;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}	
	public String getGestor() {
		return gestor;
	}
	public void setGestor(String gestor) {
		this.gestor = gestor;
	}
	
	@Override
	public String toString() {
		return "RestauranteResumen [id=" + id + ", nombre=" + nombre + ", longitud=" + longitud + ", latitud=" + latitud
				+ "]";
	}
	
	
}
