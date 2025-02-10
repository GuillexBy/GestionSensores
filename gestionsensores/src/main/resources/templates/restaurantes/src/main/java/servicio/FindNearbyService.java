package servicio;


import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import restaurantes.modelo.SitioTuristico;
import utils.JSONDownloader;
import utils.XMLDownloader;

public class FindNearbyService {

	private XMLDownloader servicioDescargaXML;
	private JSONDownloader servicioDescargaJSON;
	private static double MAX_RADIUS = 20.0;

	// Se recupera la clase para descargar documentos XML.
	public FindNearbyService() {
		servicioDescargaXML = XMLDownloader.getXMLDownloader();
		servicioDescargaJSON = JSONDownloader.getJSONDownloader();
	}

	private Document descargarXML(String url) {

		return servicioDescargaXML.descargarXMLbyURL(url);
	}

	public List<SitioTuristico> findNearbyCoords(double latitud, double longitud, double distanciaLimite) {
		return findNearby(latitud, longitud, distanciaLimite);
	}
	public List<SitioTuristico> findNearbyCoords(double latitud, double longitud) {
		return findNearby(latitud, longitud, MAX_RADIUS);
	}

	/**
	 * Encuentra lugares turísticos cercanos utilizando la API de Geonames.
	 * 
	 * @param lat    Latitud del lugar.
	 * @param lng    Longitud del lugar.
	 * @param radius Radio de búsqueda en Km. El valor máximo es de 20Km.
	 * @return Una lista de lugares turísticos. La lista contiene objetos de tipo
	 *         LugarTuristico INCOMPLETOS.
	 * 
	 */
	private List<SitioTuristico> findNearby(double lat, double lng, double radius) {
		// Creamos la URL para llamada a la API
		String url = "http://api.geonames.org/findNearbyWikipedia?";
		String user = "arsohernandezvalero";

		// Si el radio es mayor a 20, se fija a esta cantidad.
		if (radius > MAX_RADIUS)
			radius = 20.0;
		url = url.concat("lat=" + lat + "&lng=" + lng + "&radius=" + radius + "&username=" + user + "&format=xml&lang=es");

		// Se crea la lista de resultados.
		ArrayList<SitioTuristico> listaResultados = new ArrayList<SitioTuristico>();

		// Se obtiene el documento XML llamando a la clase XMLDownloader.
		Document documento = descargarXML(url);

		// Se obtienen todas las entradas del documento
		NodeList entradas = documento.getElementsByTagName("entry");

		// Se obtienen los campos necesarios mediante un bucle.
		for (int i = 0; i < entradas.getLength(); i++) {
			Element entry = (Element) entradas.item(i);
			Element distancia = (Element) entry.getElementsByTagName("distance").item(0);
			Element titulo = (Element) entry.getElementsByTagName("title").item(0);
//			Element resumen = (Element) entry.getElementsByTagName("summary").item(0);
			Element enlaceWiki = (Element) entry.getElementsByTagName("wikipediaUrl").item(0);

			// Se crea un objeto POJO LugarTuristico
			SitioTuristico lugar = new SitioTuristico();

			// Se asignan los valores necesarios.
//			lugar.setResumen(resumen.getTextContent());
			lugar.setDistancia(distancia.getTextContent());
			lugar.setWikipediaUrl(enlaceWiki.getTextContent());
			lugar.setNombre(titulo.getTextContent());

			// Se añade a la lista.
			listaResultados.add(lugar);

		}
		// Se muestran los resultados.
//		System.out.println("Resultados");
//		listaResultados.stream()
//				.forEach(r -> System.out.println(
//						"Nombre: " + r.getNombre() + "\n" + "Descripcion: " + r.getResumen() + "\n" + "Distancia: "
//								+ r.getDistancia() + "\n" + "Artículo de Wikipedia: " + r.getWikipediaUrl() + "\n"));

		// Se devuelve la lista de resultados.
		
		
		return servicioDescargaJSON.completarSitio(listaResultados);
	}

	
	
}