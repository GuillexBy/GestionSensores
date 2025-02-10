package utils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public interface IJSONDownload {

	/*
	 * Resumen: propiedad http://dbpedia.org/ontology/abstract
	 * Categorías: http://www.w3.org/1999/02/22-rdf-syntax-ns#type. 
	 * Enlaces externos: propiedad http://dbpedia.org/ontology/wikiPageExternalLink
	 * Imagen en Wikimedia: http://es.dbpedia.org/property/imagen.
	 */
	
	public default JsonObject getRoot(String url) throws IOException {
//		https://es.dbpedia.org/data/San_Pedro_del_Pinatar.json
//		https://es.wikipedia.org/wiki/Catedral_de_Murcia
		
		URL dbpediaUrl=null;
		try {
			dbpediaUrl = new URL("https://es.dbpedia.org/data/"+url.substring(url.lastIndexOf('/') + 1)+".json");
		} catch (MalformedURLException e2) {
			System.out.println("JSON: URL mal formada");
			e2.printStackTrace();
		}
		JsonReader jsonReader = null;
		try {
			jsonReader = Json.createReader(dbpediaUrl.openStream());
		} catch (IOException e) {
			System.out.println("JSON: Error al leer la URL "+dbpediaUrl);
			throw new IOException();
		}
		JsonObject root = jsonReader.readObject();
		
		return root;
	}
	
	public default List<String> getCategorias(JsonObject root, String name) {
		JsonArray array = root.getJsonObject("http://es.dbpedia.org/resource/"+name)
				.getJsonArray("http://www.w3.org/1999/02/22-rdf-syntax-ns#type");
		
		List<String> lista = array.stream()
				.map(string -> ((JsonObject) string.asJsonObject()).getString("value").toString())
				.collect(Collectors.toList());
		
		return lista;
	}
	
	public default String getAbstract(JsonObject root, String name) throws UnsupportedEncodingException {
		System.out.println(URLDecoder.decode(name, "UTF-8"));
		System.out.println("Dentro de getAbstract con "+name);
		JsonArray array = root.getJsonObject("http://es.dbpedia.org/resource/"+name)
				.getJsonArray("http://dbpedia.org/ontology/abstract");
		
		return array.stream()
				.findFirst()
				.map(string -> ((JsonObject) string.asJsonObject()).getString("value").toString())
				.orElse(null);
		
		
	}
	public default String getWikimediaImgUrl(JsonObject root, String name) {
		//http://es.dbpedia.org/property/imagen
		JsonArray array = root.getJsonObject("http://es.dbpedia.org/resource/"+name)
				.getJsonArray("http://es.dbpedia.org/property/imagen");
		if (array ==null)
		{
			array = root.getJsonObject("http://es.dbpedia.org/resource/"+name)
					.getJsonArray("http://es.dbpedia.org/property/foto");
			
		}
		if(array == null)
			return "No se encontró imagen";
		
			return array.stream()
					.findFirst()
					.map(string -> ((JsonObject) string.asJsonObject()).getString("value").toString())
					.orElse("No se encontró imagen");
		
	}
	
	public default List<String> getWikiLinks(JsonObject root, String name) {
		JsonArray array = root.getJsonObject("http://es.dbpedia.org/resource/"+name)
				.getJsonArray("http://dbpedia.org/ontology/wikiPageExternalLink");
		if(array !=null)
		return array.stream()
				.map(string -> ((JsonObject) string.asJsonObject()).getString("value").toString())
				.collect(Collectors.toList());
		else
			return new ArrayList<String>();
	}
	
 
}
