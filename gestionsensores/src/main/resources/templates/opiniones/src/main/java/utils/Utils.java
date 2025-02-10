package utils;

import java.io.File;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class Utils {

	public static String createId() {

		return UUID.randomUUID().toString();
	}

	// T requiere tener la notacion de @XmlRootElement
	public static <T> void marshalXML(Class<?> clase, T act, String fich) throws JAXBException {

		JAXBContext contexto = JAXBContext.newInstance(clase);
		// Empaquetado en un documento XML (marshalling)
		Marshaller marshaller = contexto.createMarshaller();
		marshaller.setProperty("jaxb.formatted.output", true);
		marshaller.marshal(act, new File(fich));

	}

	public static <T> T unmarshalXML(Class<?> clase, String fichero) throws JAXBException {
		JAXBContext contexto = JAXBContext.newInstance(clase);
		// Empaquetado en un documento XML (marshalling)

		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		T obj = (T) unmarshaller.unmarshal(new File(fichero));
		
		 return obj;
	}
	
	
}
