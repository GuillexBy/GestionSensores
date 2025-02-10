package opiniones.servicio;

public class OpinionResumen {

	private String id;
	private String nombre;
	private String longitud;
	private String latitud;
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
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	@Override
	public String toString() {
		return "RestauranteResumen [id=" + id + ", nombre=" + nombre + ", longitud=" + longitud + ", latitud=" + latitud
				+ "]";
	}
	
	
}
