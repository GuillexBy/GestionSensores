package utils;

import org.w3c.dom.Document;

public interface IXMLDownload {
    
    /**
     * 
     * Descarga un documento XML desde una URL específica.
     * 
     * @param urlString la URL del documento XML a descargar.
     * 
     * @return el documento XML descargado o null en caso de error.
     */
    public Document descargarXMLbyURL(String urlString);
    
    
}