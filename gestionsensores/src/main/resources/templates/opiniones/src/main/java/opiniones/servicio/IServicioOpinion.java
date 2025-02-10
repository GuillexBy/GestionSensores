package opiniones.servicio;

import java.time.LocalDateTime;
import opiniones.modelo.Opinion;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;

public interface IServicioOpinion {

	/**
	 * Metodo de alta de una Opinion
	 * 
	 * @param Información básica de una Opinion
	 * @return El identificador de la nueva Opinion
	*/
	  
	String crearOpinion(String nombre) throws RepositorioException;
	
	
	
	/**
	 * Metodo para añadir una valoración a una opinión
	 * 
	 * @param Identificador de una opinión y los atributos de una valoracion
	 * @return ?
	 * @throws EntidadNoEncontrada 
	 * @throws RepositorioException 
	 * 
	 */
	
	//No sé cuál de los dos usar
	void addValoracion(String id, String email, String fecha, int calificacion, String comentario) throws RepositorioException, EntidadNoEncontrada; // Búsqueda por id o por nombre de recurso???
		
	/**
	 * 
	 * Método para recuperar una opinión
	 * 
	 * @param identificador de la opinion
	 * @return la opinion dado un id
	 * @throws EntidadNoEncontrada 
	 * @throws RepositorioException 
	 * 
	 */
	
	Opinion getOpinion(String id) throws RepositorioException, EntidadNoEncontrada;
	
	
	/**
	 * 
	 * Método para borrar una opinión
	 * 
	 * @param identificador de la opinión
	 * @throws EntidadNoEncontrada 
	 * @throws RepositorioException 
	 * 
	 */
	
	void deleteOpinion(String id) throws RepositorioException, EntidadNoEncontrada;
	
	
}
