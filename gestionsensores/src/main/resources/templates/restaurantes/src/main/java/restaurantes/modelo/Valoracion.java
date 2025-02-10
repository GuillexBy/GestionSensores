package restaurantes.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Valoracion {

	@JsonAlias({ "Correo", "correo" })
	private String correo;
	@JsonAlias({ "Fecha", "fecha" })
	private String fecha;
	@JsonAlias({ "Calificacion", "calificacion" })
	private int calificacion;
	@JsonAlias({ "Comentario", "comentario" })
	private String comentario;
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public int getCalificacion() {
		return calificacion;
	}
	
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
