package opiniones.modelo;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonRepresentation;

import opiniones.modelo.Valoracion;

import opiniones.repositorio.Identificable;

@XmlRootElement
public class Opinion implements Identificable{
	
	@BsonId
	@BsonRepresentation(BsonType.OBJECT_ID)
	private String _id;
	private String recurso;
	private ArrayList<Valoracion> valoraciones = new ArrayList<Valoracion>();
	
	public int NumValoraciones() {
		return valoraciones.size();
	}
	
	public double CalificacionMedia() {
		
		return valoraciones.stream().mapToDouble(Valoracion::getCalificacion).sum() / valoraciones.size();
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public ArrayList<Valoracion> getValoraciones() {
		if(this.valoraciones.isEmpty())
			return new ArrayList<Valoracion>();
		return valoraciones;
	}

	public void setValoraciones(ArrayList<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

}
