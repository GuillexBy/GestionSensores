package opiniones.graphql;

import java.time.LocalDateTime;

import com.coxautodev.graphql.tools.GraphQLRootResolver;

import opiniones.servicio.IServicioOpinion;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;



public class Mutation implements GraphQLRootResolver {
    
	private IServicioOpinion servicio = FactoriaServicios.getServicio(IServicioOpinion.class);
	
    public String crearOpinion(String recurso) throws RepositorioException {
        
    	return servicio.crearOpinion(recurso);
    }
    
    public boolean addValoracion(String id, String email, String fecha, int calificacion, String comentario) throws RepositorioException, EntidadNoEncontrada {
    	servicio.addValoracion(id, email, fecha, calificacion, comentario);
    	return true;
    }
    
    public boolean deleteOpinion(String id) throws RepositorioException, EntidadNoEncontrada {
    	servicio.deleteOpinion(id);
    	return true;
    }
}
