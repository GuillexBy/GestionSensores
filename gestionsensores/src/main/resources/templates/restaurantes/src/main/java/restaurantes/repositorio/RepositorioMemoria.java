package restaurantes.repositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioString;

public class RepositorioMemoria<T extends Identificable> implements RepositorioString<T> {
	
	private HashMap<String, T> mapa = new HashMap<>();
	
	private Integer id = 1;
	public String add(T entity) throws RepositorioException {
		System.out.println("add");
		String id = this.id.toString();
		entity.setId(id);
		this.id++;
		this.mapa.put(id, entity);	
		return id;
	}

	public void update(T entity) throws RepositorioException, EntidadNoEncontrada { // OK
		System.out.println("Update...");
		if (! this.mapa.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		this.mapa.put(entity.getId(), entity);
	}

	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		
		if (! this.mapa.containsKey(entity.getId()))
			throw new EntidadNoEncontrada(entity.getId() + " no existe en el repositorio");
		this.mapa.remove(entity.getId());
	}

	
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		if (! this.mapa.containsKey(id))
			throw new EntidadNoEncontrada(id + " no existe en el repositorio");
		
		return this.mapa.get(id);
	}

	
	public List<T> getAll() throws RepositorioException {
		return new ArrayList<>(this.mapa.values());
	}

	public List<String> getIds() throws RepositorioException {
		return new ArrayList<>(this.mapa.keySet());
	}
}
