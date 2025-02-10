package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javax.json.JsonObject;

import restaurantes.modelo.SitioTuristico;



public class JSONDownloader implements IJSONDownload{

	private String qualifiedName;
	private JsonObject root;
	private static JSONDownloader servicio;

	public static JSONDownloader getJSONDownloader() {
		if (servicio != null)
			return servicio;
		else
			return new JSONDownloader();
	}
	
	public void setQualifiedName(String name) {
		String decoded = URLDecoder.decode(name, StandardCharsets.UTF_8);
		qualifiedName=decoded;
	}
	public String getQualifiedName() {
		return qualifiedName;
	}
	
	public SitioTuristico completarSitio(SitioTuristico sitio) throws IOException  {
		setQualifiedName(sitio.getWikipediaUrl().substring(sitio.getWikipediaUrl().lastIndexOf('/') + 1));
		System.out.println(qualifiedName);
		root = getRoot(sitio.getWikipediaUrl());
		sitio.setCategorias(getCategorias(root, qualifiedName));
		try {
			sitio.setResumen(getAbstract(root,qualifiedName));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sitio.setExternalLinks(getWikiLinks(root, qualifiedName));
		sitio.setImagenWikimedia(getWikimediaImgUrl(root, qualifiedName));
		return sitio;
	}
	public List<SitioTuristico> completarSitio(List<SitioTuristico> lista)
	{
		List<SitioTuristico> resultados = lista.stream()
		.map(sitio -> {
			try {
				return completarSitio(sitio);
			} catch (Exception e) {
				System.out.println("Excepcion a la hora de completar");
				return sitio;
			}
		})
		.collect(Collectors.toList());
		
		return resultados.stream().filter(sitio -> sitio!=null).collect(Collectors.toList());
	}
	
}
