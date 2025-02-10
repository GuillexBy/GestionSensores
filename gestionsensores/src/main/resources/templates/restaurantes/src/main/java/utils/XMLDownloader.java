package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import utils.IXMLDownload;

public class XMLDownloader implements IXMLDownload{

	// Esta clase es un singleton.

	private static XMLDownloader servicio;

	public static XMLDownloader getXMLDownloader() {
		if (servicio != null)
			return servicio;
		else
			return new XMLDownloader();
	}

	/**
	 * 
	 * Descarga un documento XML desde una URL específica.
	 * 
	 * @param urlString la URL del documento XML a descargar.
	 * 
	 * @return el documento XML descargado o null en caso de error.
	 */
	public Document descargarXMLbyURL(String urlString) {
		DocumentBuilder docBuilder = null;
		URL url = null;
		InputStream stream = null;
		try {
			docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}

		try {
			url = new URL(urlString);
			System.out.println(urlString);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try {
			stream = url.openStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			Document doc = docBuilder.parse(stream);
			return doc;
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

}

