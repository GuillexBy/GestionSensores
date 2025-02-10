package restaurantes.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SitioTuristico {

	private String nombre;
	private String resumen;
	private String distancia;
	private String wikipediaUrl;
	private String imagenWikimedia;
	private List<String> categorias;
	private List<String> externalLinks;
	
	public SitioTuristico() {

	}
	public List<String> getCategorias() {
		if (categorias==null)
			return new ArrayList<String>();
		return categorias;
	}
	public void setExternalLinks(List<String> externalLinks) {
		this.externalLinks = externalLinks;
	}
	public List<String> getExternalLinks() {
		if (externalLinks==null)
			return new ArrayList<String>();
		return externalLinks;
	}
	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
	public void setImagenWikimedia(String imagenWikimedia) {
		this.imagenWikimedia = imagenWikimedia;
	}
	public String getImagenWikimedia() {
		if (imagenWikimedia == null)
			return "No se encontró imagen";
		return imagenWikimedia;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getResumen() {
		if (resumen == null)
			return "No se encontró resumen";
		return resumen;
	}

	public void setResumen(String descripcion) {
		this.resumen = descripcion;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}

	
	
	@Override
	public String toString() {
		 return "Nombre: " + getNombre() + "\n"
			        + "Descripcion: " + getResumen() + "\n"
			        + "Distancia: " + getDistancia() + "\n"
			        + "Articulo de Wikipedia: " + getWikipediaUrl() + "\n"
			        + "Imagen de Wikipedia: " + getImagenWikimedia() + "\n"
			        + "\nCategorias:\n" + getCategorias().stream().filter(Objects::nonNull).collect(Collectors.joining("\n")) + "\n"
			        + "\nEnlaces externos:\n" + getExternalLinks().stream().collect(Collectors.joining("\n"))
			        ;
	}
	
	@Override
	public int hashCode() {
	    int result = nombre != null ? nombre.hashCode() : 0;
	    result = 31 * result + (resumen != null ? resumen.hashCode() : 0);
	    result = 31 * result + (distancia != null ? distancia.hashCode() : 0);
	    result = 31 * result + (wikipediaUrl != null ? wikipediaUrl.hashCode() : 0);
	    result = 31 * result + (imagenWikimedia != null ? imagenWikimedia.hashCode() : 0);
	    result = 31 * result + (categorias != null ? categorias.hashCode() : 0);
	    result = 31 * result + (externalLinks != null ? externalLinks.hashCode() : 0);
	    return result;
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    SitioTuristico other = (SitioTuristico) obj;
	    return Objects.equals(nombre, other.nombre)
	           && Objects.equals(resumen, other.resumen)
	           && Objects.equals(distancia, other.distancia)
	           && Objects.equals(wikipediaUrl, other.wikipediaUrl)
	           && Objects.equals(imagenWikimedia, other.imagenWikimedia)
	           && Objects.equals(categorias, other.categorias)
	           && Objects.equals(externalLinks, other.externalLinks);
	}

	
	
}
