package opiniones.servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;

import opiniones.modelo.Opinion;
import opiniones.modelo.Valoracion;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;

public class ServicioOpinion implements IServicioOpinion {
	
	private Repositorio<Opinion, String> repositorio = FactoriaRepositorios.getRepositorio(Opinion.class);
	
	// Conexiones a mongo
	@Override
	public String crearOpinion(String nombre) throws RepositorioException {
		
		if (nombre.isBlank() || nombre.equals(null)){
			throw new IllegalArgumentException("El nombre no debe ser nulo o vacío");
		}
		
		
		Opinion o = new Opinion();
		o.setRecurso(nombre);
		
		return repositorio.add(o);
	}
	

	@Override
	public void addValoracion(String id, String email, String fecha, int calificacion, String comentario) throws RepositorioException, EntidadNoEncontrada {
		
		if(email.isBlank() || email.equals(null))
			throw new IllegalArgumentException("El correo de la valoración no puede ser nulo ni estar vacío");
		
		if(fecha.equals(null))
			throw new IllegalArgumentException("La fecha de registro de la valoración no puede ser nula");
		
		LocalDateTime fechaValoracion = LocalDateTime.parse(fecha);
		
		Valoracion v = new Valoracion();
		v.setEmail(email);
		v.setFechaRegistro(fechaValoracion);
		v.setCalificacion(calificacion);
		v.setComentario(comentario);
		
		Opinion o = repositorio.getById(id);
		ArrayList<Valoracion> valoraciones = o.getValoraciones();
		int i = 0;
		while(i < valoraciones.size()) {
			if(valoraciones.get(i).getEmail().equals(email))
				valoraciones.remove(i);
			i++;
		}
		valoraciones.add(v);
		o.setValoraciones(valoraciones);
		repositorio.update(o);
		
	}

	@Override
	public Opinion getOpinion(String id) throws RepositorioException, EntidadNoEncontrada {
	
		return repositorio.getById(id);
	}

	@Override
	public void deleteOpinion(String id) throws RepositorioException, EntidadNoEncontrada {
		Opinion o = repositorio.getById(id);
		repositorio.delete(o);
		
	}

	
	
}
