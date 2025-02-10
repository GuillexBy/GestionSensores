package restaurantes.servicio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import repositorio.EntidadNoEncontrada;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import restaurantes.modelo.Plato;
import restaurantes.modelo.Restaurante;
import restaurantes.modelo.ResumenOpinion;
import restaurantes.modelo.SitioTuristico;
import restaurantes.modelo.Valoracion;
import retrofit.opiniones.OpinionesRestCliente;

public interface IServicioRestaurante {

	/**
	 * Metodo de alta de un restaurante
	 * 
	 * @param Información básica de un restaurante
	 * @return El identificador del nuevo restaurante
	 * @throws Exception 
	*/
	  
	String crearRestaurante(String nombre, String longitud, String Latitud, String gestor) throws Exception;
	
	
	
	String crearRestaurante(Restaurante restaurante) throws Exception;
	
	/**
	 * Metodo para actualizar un restaurante
	 * 
	 * @param Identificador de un restaurante y la informacion basica actualizada
	 * @return ?
	 * @throws Exception 
	 * 
	 */
	
	void actualizarRestaurante(String id, String nombre, String longitud, String latitud, String gestor) throws Exception;
	
	
	
	void actualizarRestaurante(Restaurante restaurante) throws Exception ;
	
	/**
	 * 
	 * Obtener sitios turisiticos proximos
	 * 
	 * @param Identificador de un restaurante
	 * @return Lista de sitios turisticios (Informacion obtenido de GeoNames y DBPedia
	 * @throws Exception 
	 * 
	 * */
	
	List<SitioTuristico> obtenerSitiosTuristicos(String id) throws Exception;
	
	
	/**
	 * Estableces sitios turisticos destacados ( Esta operación establece como sitios turísticos del restaurante los que se establecen como parámetro. Nota: observa 
	 * que la aplicación ofrecerá al gestor del restaurante una lista de sitios turísticos próximos y 
	 * el gestor seleccionará algunos como destacados para su restaurante) 
	 * 
	 * @param indentificador del restaurante y lista de sitios turisticos
	 * @return  
	 * @throws Exception 
	 *
	 * 
	 * */
	
	void establecerSitiosDestacados(String id, List<SitioTuristico> sitios) throws Exception;
	
	
	
	/**
	 * 
	 * Añadir un plato al restaurante
	 * 
	 * @param identificador del restaurante y un plato.
	 * @return ?
	 * @throws Exception 
	 * 
	 * 
	 * */
	
	void addPlatoAlRestaurante(String id, String nombrePlato, String descripcion, double precio) throws Exception;
	
	/**
	 * 
	 * Borrar un plato del restaurante (id es el nombre)
	 * 
	 * @param identificador del restaurante y nombre del plato
	 * @throws Exception 
	 * 
	 * 
	 * */
	
	void borrarPlato(String id, String nombrePlato) throws Exception;
	
	/**
	 * 
	 * Actualizar un plato del restaurante (Es requisito que el nombre del plato forme parte del restaurante.)
	 * 
	 * @param identificador del restaurante y un plato
	 * @throws Exception 
	 * 
	 * 
	 * */
	
	void actualizarPlato(String id, String nombrePlato, String descripcion, double precio) throws Exception;
	
	
	/**
	 * 
	 * Recuperar un restaurante
	 * 
	 * @param identificador del restaurante.
	 * @return el restaurante dado un id
	 * @throws Exception 
	 * 
	 * */
	
	Restaurante recuperarRestaurante(String id) throws Exception;
	
	/**
	 * 
	 * Borrar un restaurante
	 * 
	 * @param identificador del restaurante.
	 * @throws Exception 
	 * 
	 * 
	 * */
	
	void borrarRestaurante(String id) throws Exception;
	
	/**
	 * 
	 * Listado de restaurantes
	 * 
	 * @return  Una lista con un resumen (información básica) de todos los restaurantes.
	 * @throws Exception 
	 * 
	 * */
	
	List<RestauranteResumen> getListaRestaurantes() throws Exception;
	
	/**
	 * 
	 * Activar valoraciones
	 * 
	 * @param 	Identificador del restaurante
	 * @throws Exception 
	 * 
	 */
	
	String activarValoraciones(String id) throws Exception;
	
	/**
	 * 
	 * Recuperar valoraciones de un restaurante
	 * 
	 * @param	Identificador de un restaurante
	 * @return	Lista de valoraciones
	 * @throws Exception 
	 * 
	 */
	
	List<Valoracion>recuperarValoraciones(String id) throws IOException, RepositorioException, EntidadNoEncontrada, Exception;
	
	/**
	 * 
	 * Subscribir el servicio a la cola de RabbitMQ 
	 * @throws Exception 
	 *
	 *
	 */
	
	void subscribirEvento() throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException, IOException, TimeoutException, Exception;
		
	
	/*
	 * Establecer el mock para simular la comunicación entre servicios
	 */
	void setServiceTest(OpinionesRestCliente service);
	
	
	
	// Para tests
	Repositorio<Restaurante, String> getRepositorio();
}
