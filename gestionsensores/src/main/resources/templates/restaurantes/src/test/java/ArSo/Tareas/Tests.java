package ArSo.Tareas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import restaurantes.modelo.Plato;
import restaurantes.modelo.Restaurante;
import restaurantes.modelo.SitioTuristico;
import restaurantes.modelo.Valoracion;
import restaurantes.servicio.IServicioRestaurante;
import servicio.FactoriaServicios;
import servicio.FindNearbyService;

class Tests {

	private static IServicioRestaurante servicio;
	private static OpinionesRestClienteMock service;
	private static FindNearbyService servicioSitios;
	private Restaurante restaurante1;
//
	@BeforeAll
	public static void setup() {
		service = new OpinionesRestClienteMock();
		
	}



	// CREAR RESTAURANTES

	@Test
	void crearRestauranteConFalloNombre() { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		String nombre = "";
		String longitud = "20";
		String latitud = "20";
		String gestor = "Gestor";
		Throwable exception = null;
		try {
			servicio.crearRestaurante(nombre, longitud, latitud, gestor);
		} catch (Throwable e) {
			exception = e;
		}

		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

	}

	@Test
	void crearRestauranteConFalloLatitud() { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		String nombre = "hola";
		String longitud = "";
		String latitud = "20";
		String gestor = "Gestor";
		Throwable exception = null;
		try {
			servicio.crearRestaurante(nombre, longitud, latitud, gestor);
		} catch (Throwable e) {
			exception = e;
		}

		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

	}

	@Test
	void crearRestauranteConFalloGestor() { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		String nombre = "Pruyeba";
		String longitud = "20";
		String latitud = "20";
		String gestor = "";
		Throwable exception = null;
		try {
			servicio.crearRestaurante(nombre, longitud, latitud, gestor);
		} catch (Throwable e) {
			exception = e;
		}

		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

	}

	@Test
	void crearRestauranteConFalloLongitud() { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		String nombre = "Pruyeba";
		String longitud = "";
		String latitud = "20";
		String gestor = "gestor";
		Throwable exception = null;
		try {
			servicio.crearRestaurante(nombre, longitud, latitud, gestor);
		} catch (Throwable e) {
			exception = e;
		}

		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

	}

	@Test
	void crearRestauranteConExito() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		String nombre = "La casa Paco";
		String longitud = "20";
		String latitud = "20";
		String gestor = "Paco";

		String id = servicio.crearRestaurante(nombre, longitud, latitud, gestor);
		assertTrue(servicio.getRepositorio().getAll().size() == 1);

	}

	// ACTUALIZAR RESTAURANTE

	@Test
	void actualizarRestauranteConExito() throws Exception {
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);

		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(0);
		r.setLatitud(0);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		// Cambia de atributo
		r.setLatitud(20);
		servicio.actualizarRestaurante(r);
		Restaurante r2 = servicio.recuperarRestaurante(id);
		assertTrue(r2.getLatitud() == 20.0);
	}

	@Test
	void actualizarRestauranteConFallos() throws Exception {
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);

		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(0);
		r.setLatitud(0);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);

		// Cambia de atributo
		r.setNombre("");
		Throwable exception = null;
		try {
			servicio.actualizarRestaurante(id, "", "10", "10", "Paco");
		} catch (Throwable e) {
			exception = e;
		}

		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

		try {
			servicio.actualizarRestaurante(id, "Paco", "", "10", "Paco");
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

		try {
			servicio.actualizarRestaurante(id, "Paco", "10", "", "Paco");
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

		try {
			servicio.actualizarRestaurante(id, "Paco", "10", "10", "");
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

		try {
			servicio.actualizarRestaurante(null, "Paco", "10", "10", "Paco");
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(EntidadNoEncontrada.class, exception.getClass());
	}

	// Borrar restaurantes

	@Test
	void borrarRestauranteConExito() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);

		servicio.borrarRestaurante(id);
		assertTrue(servicio.getRepositorio().getAll().size() == 0);

	}

	@Test
	void borrarRestauranteSinExito() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		Throwable exception = null;
		try {
			servicio.borrarRestaurante("No existe");
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(EntidadNoEncontrada.class, exception.getClass());

	}

	// Añadir plato

	@Test
	void addPlatoAlRestauranteConExisto() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);

		servicio.addPlatoAlRestaurante(id, "macarrones", "macarrones", 10d);

		assertTrue(servicio.getRepositorio().getById(id).getPlatos().size() == 1);

	}

	@Test
	void addPlatoAlRestauranteConFallos() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);

		Throwable exception = null;
		try {
			servicio.addPlatoAlRestaurante(id, "", "descripcion", 10d);
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

		try {
			servicio.addPlatoAlRestaurante(id, "p", "", 10d);
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

		try {
			servicio.addPlatoAlRestaurante(id, "p", "descripcion", 0d);
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());

	}

// Borrar plato

	@Test
	void borrarPlatoConExito() throws Exception {
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		servicio.addPlatoAlRestaurante(id, "macarrones", "macarrones", 10d);

		servicio.borrarPlato(id, "macarrones");
		assertTrue(servicio.getRepositorio().getById(id).getPlatos().size() == 0);
	}

	@Test
	void borrarPlatoConFallo() throws Exception {
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		servicio.addPlatoAlRestaurante(id, "macarrones", "macarrones", 10d);

		Throwable exception = null;
		try {
			servicio.borrarPlato(id, "");
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());
	}

// Actualizar plato
	@Test
	void actualizarPlatoConExito() throws Exception {
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		servicio.addPlatoAlRestaurante(id, "macarrones", "macarrones", 10d);
		
		servicio.actualizarPlato(id, "macarrones", "Descripcion Nueva", 10d);
		Plato p = servicio.recuperarRestaurante(id).getPlatos().get("macarrones");
		assertEquals(p.getDescripcion(),"Descripcion Nueva");
	}

	@Test
	void actualizarPlatoConFallos() throws Exception {
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		servicio.addPlatoAlRestaurante(id, "macarrones", "macarrones", 10d);

		Throwable exception = null;
		try {
			servicio.actualizarPlato(id, "macarrones", "", 10d);
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());
		
		try {
			servicio.actualizarPlato(id, "", "des", 10d);
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());
		
		try {
			servicio.actualizarPlato(id, "macarrones", "de", 0d);
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());
	}

	
// Recuperar Lista Restaurantes
	@Test
	void recuperarListaRestaurantes() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		assertTrue(servicio.getListaRestaurantes().size()==1);
		
	}
	
	
//Sitios turisticos
	
	@Test
	void establecerSitiosTuristicosConExisto() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		servicioSitios = new FindNearbyService();
		List<SitioTuristico> lista = servicioSitios.findNearbyCoords(10d, 10d);
		servicio.establecerSitiosDestacados(id, lista);
		assertNotNull(servicio.obtenerSitiosTuristicos(id));
		
		
		
	}
	
	@Test
	void establecerSitiosTuristicosConError() throws Exception { // OK
		IServicioRestaurante servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		Restaurante r = new Restaurante();
		r.setNombre("Prueba");
		r.setLongitud(10);
		r.setLatitud(10);
		r.setGestor("Paco");
		String id = servicio.crearRestaurante(r);
		
		Throwable exception = null;
		try {
			servicio.establecerSitiosDestacados(id, null);
		} catch (Throwable e) {
			exception = e;
		}
		Assertions.assertNotNull(exception);
		Assertions.assertEquals(NullPointerException.class, exception.getClass());
		
		
		
		
	}

	@Test
	void activarValoraciones() throws Exception {
		servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		restaurante1 = new Restaurante();
		restaurante1.setGestor("guille@um.es");
		restaurante1.setLatitud(40);
		restaurante1.setLongitud(40);
		restaurante1.setNombre("Restaurante de prueba 1");
		// servicio.setRepositorioNull();
		try {
			String idRestaurante = servicio.crearRestaurante(restaurante1);
			String idOpinion = servicio.activarValoraciones(idRestaurante);
			assertEquals(idOpinion, "1234");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void recuperarValoraciones() throws Exception {
		servicio = FactoriaServicios.getServicio(IServicioRestaurante.class);
		servicio.setServiceTest(service);
		restaurante1 = new Restaurante();
		restaurante1.setGestor("guille@um.es");
		restaurante1.setLatitud(40);
		restaurante1.setLongitud(40);
		restaurante1.setNombre("Restaurante de prueba 1");
		List<Valoracion> valoraciones = new LinkedList<Valoracion>();
		String idRestaurante;
		try {
			idRestaurante = servicio.crearRestaurante(restaurante1);
			servicio.activarValoraciones(idRestaurante);
			valoraciones = servicio.recuperarValoraciones(idRestaurante);
			assertEquals(valoraciones.size(), 2);
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EntidadNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
