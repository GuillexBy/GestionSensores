package opiniones.repositorio;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import opiniones.modelo.Opinion;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import repositorio.RepositorioString;
import utils.Utils;

public class RepositorioOpiniones<T extends Identificable> implements RepositorioString<T> {

	private HashMap<String, T> mapa = new HashMap<>();
	private int id = 1;
	
	
	// MONGO
	// Conexiones a mongo
	
	String connectionString = "mongodb://Aure:arso@ac-gbkha7k-shard-00-00.gbzbs08.mongodb.net:27017,ac-gbkha7k-shard-00-01.gbzbs08.mongodb.net:27017,ac-gbkha7k-shard-00-02.gbzbs08.mongodb.net:27017/?ssl=true&replicaSet=atlas-ekxpjk-shard-0&authSource=admin&retryWrites=true&w=majority";	
	CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
    CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
    		pojoCodecRegistry);
	
    MongoClientSettings settings = MongoClientSettings.builder()
	        .applyConnectionString(new ConnectionString(connectionString))
	        .build();
	MongoClient mongoClient = MongoClients.create(settings);
	MongoDatabase database = mongoClient.getDatabase("Proyecto-ARSO");
	

	
	MongoCollection<Opinion> opiniones = database.getCollection("opiniones", Opinion.class).withCodecRegistry(codecRegistry);
	

	public String add(T entity) throws RepositorioException { // OK
		String id_string = Integer.toString(id);
		entity.setId(Utils.createId());
		id+=1;
		this.mapa.put(id_string, entity); // EN memoria
		
		opiniones.insertOne((Opinion)entity); // EN Mongo
		return id_string;
	}

	public void update(T entity) throws RepositorioException, EntidadNoEncontrada { // OK
		
		Opinion r = (Opinion) entity;
		Document filter = new Document("_id", r.getId());
		if(opiniones.find(filter).first() == null) {
			throw new EntidadNoEncontrada(entity.getId() + "no está");
		}
		opiniones.findOneAndReplace(filter, r);
		this.mapa.put((entity.getId()), entity);
	}

	public void delete(T entity) throws RepositorioException, EntidadNoEncontrada {
		Opinion r = (Opinion) entity;
		this.mapa.remove((entity.getId()));
		opiniones.deleteOne(new Document("_id", r.getId()));
	}

	@SuppressWarnings("unchecked")
	public T getById(String id) throws RepositorioException, EntidadNoEncontrada {
		Document filter = new Document("_id", id);
		Opinion r = opiniones.find(filter).first();
		return (T) r;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() throws RepositorioException {
		//
		ArrayList<Opinion> lista = new ArrayList<>();
		FindIterable<Opinion> iterable = opiniones.find();
		Iterator<Opinion> iterator = iterable.iterator();
		
		while(iterator.hasNext()) {
			Opinion r = iterator.next();
			lista.add(r);
		}
		return (List<T>) lista;
	}

	public List<String> getIds() throws RepositorioException {
		return new ArrayList<String>(this.mapa.keySet());
	}

}
