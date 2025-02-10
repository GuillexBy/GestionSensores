package restaurantes.modelo;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import restaurantes.repositorio.Identificable;


public class Restaurante implements Identificable {
	
	@BsonId
	private String id;
	private String nombre;
	private double longitud;
	private double latitud;
	private String gestor;
	private List<SitioTuristico> sitios = new LinkedList<>();
	private HashMap<String, Plato> platos = new HashMap<>();
	private String opinionId;
	private int numValoraciones;
	private double calificacionMedia;
	
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

	public List<SitioTuristico> getSitios() {
		return new LinkedList<SitioTuristico>(sitios);
	}

	public void setSitios(List<SitioTuristico> sitios) {
		this.sitios = sitios;
	}
	
	public HashMap<String, Plato> getPlatos() {
		return new HashMap<String, Plato>(platos);
	}

	public void setPlatos(HashMap<String, Plato> platos) {
		this.platos = platos;
	}
	
	public String getOpinionId() {
		return opinionId;
	}

	public void setOpinionId(String opinionId) {
		this.opinionId = opinionId;
	}

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

	public void addPlato(String nombrePlato, String descripcion, double precio) {
		Plato p = new Plato();
		p.setNombre(nombrePlato);
		p.setDescripcion(descripcion);
		p.setPrecio(precio);
		this.platos.put(nombrePlato, p);
	}
	
	public void removePlato(String nombrePlato) {
		this.platos.remove(nombrePlato);
	}
	
	public boolean updatePlato(String nombrePlato, String descripcion, double precio) {
		if(!platos.containsKey(nombrePlato))
			return false;
		addPlato(nombrePlato,descripcion,precio);
		return true;
	}

	@Override
	public String toString() {
		return "Restaurante [_id=" + id + ", nombre=" + nombre + ", longitud=" + longitud + ", latitud=" + latitud
				+ ", gestor=" + gestor + ", sitios=" + sitios + ", platos=" + platos + "]";
	}

	
	

	
	
	
	
	
	
}
