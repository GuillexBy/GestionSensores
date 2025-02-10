package restaurantes.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ResumenOpinion {

	@JsonAlias({ "numeroValoraciones", "numValoraciones" })
	private int numValoraciones;
	private double calificacionMedia;
	private String id;

	public int getNumValoraciones() {
		return numValoraciones;
	}

	public void setNumValoraciones(int numValoraciones) {
		this.numValoraciones = numValoraciones;
	}

	public double getCalificacionMedia() {
		return calificacionMedia;
	}

	public void setCalificacionMedia(double calificacionMedia) {
		this.calificacionMedia = calificacionMedia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ResumenValoracion [numValoraciones=" + numValoraciones + ", calificacionMedia=" + calificacionMedia
				+ ", id=" + id + "]";
	}

}
