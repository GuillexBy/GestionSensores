package restaurantes.servicio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import repositorio.EntidadNoEncontrada;
import repositorio.FactoriaRepositorios;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import restaurantes.eventos.EventoValoracionCreada;
import restaurantes.modelo.Restaurante;
import restaurantes.modelo.SitioTuristico;
import restaurantes.modelo.Valoracion;
import restaurantes.repositorio.RepositorioMemoria;
import retrofit.opiniones.OpinionesRestCliente;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import servicio.FindNearbyService;
import utils.ObjectMapperProvider;
import utils.OpinionRequest;


public class ServicioRestaurante implements IServicioRestaurante {
	private Repositorio<Restaurante, String> repositorio = FactoriaRepositorios.getRepositorio(Restaurante.class);
	private FindNearbyService servicioSitios = new FindNearbyService();
	private ObjectMapperProvider objectMapper = new ObjectMapperProvider();
	
	//HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
	
	private Retrofit retrofit = new Retrofit.Builder().baseUrl("http://opiniones:5000/api/")
			.addConverterFactory(JacksonConverterFactory.create())
			//.client(new OkHttpClient.Builder().addInterceptor(loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build())
			.build();
	
	private OpinionesRestCliente service = this.retrofit.create(OpinionesRestCliente.class);
	
	public void setServiceTest(OpinionesRestCliente service) {
		this.service = service;
		this.repositorio = new RepositorioMemoria<Restaurante>();
	}
	
	
	@Override
	public String crearRestaurante(String nombre, String longitud, String latitud, String gestor) throws Exception {
		
		if (nombre.isBlank() || nombre.equals(null)){
			throw new IllegalArgumentException("El nombre no debe ser nulo o vacío");
		}
		
		if(longitud.isBlank() || longitud == null) {
			throw new IllegalArgumentException("La longitud no debe ser nulo o vacío");
		}
		
		if(latitud.isBlank() || latitud == null) {
			throw new IllegalArgumentException("La latitud no debe ser nulo o vacío"); 
		}
		 		
		if(gestor.isBlank() || gestor == null)
			throw new IllegalArgumentException("El gestor no debe ser nulo o vacío");
		Restaurante r = new Restaurante();
		r.setId(new ObjectId().toString());
		r.setNombre(nombre);
		r.setLongitud(Double.parseDouble(longitud));
		r.setLatitud(Double.parseDouble(latitud));
		r.setGestor(gestor);
		return repositorio.add(r);
	}
	
	@Override
	public String crearRestaurante(Restaurante restaurante) throws Exception {
		
		return repositorio.add(restaurante);
	}

	@Override

	public void actualizarRestaurante(String id, String nombre, String longitud, String latitud, String gestor) throws Exception {
		
		if (nombre.isBlank() || nombre.equals(null)){
			throw new IllegalArgumentException("El nombre no debe ser nulo o vacío");
		}
		
		if(longitud.isBlank() || longitud == null) {
			throw new IllegalArgumentException("La longitu no debe ser nulo o vacío");
		}
		
		if(latitud.isBlank() || latitud == null) {
			throw new IllegalArgumentException("La latitud no debe ser nulo o vacío");
		}
		 		
		if(gestor.isBlank() || gestor == null)
			throw new IllegalArgumentException("El gestor no debe ser nulo o vacío");
		
		
		Restaurante r = repositorio.getById(id);
		r.setNombre(nombre);
		r.setLongitud(Double.parseDouble(longitud));
		r.setLatitud(Double.parseDouble(latitud));
		r.setGestor(gestor);
		repositorio.update(r);
	}

	@Override
	public void actualizarRestaurante(Restaurante restaurante) throws  Exception {
		
		repositorio.update(restaurante);
		
	}
	
	@Override
	public List<SitioTuristico> obtenerSitiosTuristicos(String id) throws Exception {
		
		Restaurante r = repositorio.getById(id);
		return r.getSitios();
	}

	@Override
	public void establecerSitiosDestacados(String id, List<SitioTuristico> sitios) throws Exception {
		
		
		if (sitios.equals(null)) {
			throw new IllegalArgumentException("sitios no puedeser null");
		}
		Restaurante r = repositorio.getById(id);
		r.setSitios(sitios);
		repositorio.update(r);
		
	}

	@Override
	public void addPlatoAlRestaurante(String id, String nombrePlato, String descripcion, double precio) throws Exception {
		if(nombrePlato.isBlank() || nombrePlato.equals(null) )
			throw new IllegalArgumentException("El argumento nombrePlato no puede ser null o vacio");
		
		if(descripcion.isBlank() || descripcion.equals(null))
			throw new IllegalArgumentException("El argumento descripvion no puede ser null o vacio");
		
		if(precio < 0.0 || precio==0d)
			throw new IllegalArgumentException("El argumento precio no puede ser valor negativo o vacio");
		
		
			Restaurante r = repositorio.getById(id);
			r.addPlato(nombrePlato,descripcion,precio);
			repositorio.update(r);
		
	}

	@Override
	public void borrarPlato(String id, String nombrePlato) throws Exception {
		if(nombrePlato.equals(null) || nombrePlato.isBlank())
			throw new IllegalArgumentException("El argumento plato no puede ser null");
		System.out.println("borrando plato...");
			Restaurante r = repositorio.getById(id);
			r.removePlato(nombrePlato);
			repositorio.update(r);
		
		
	}

	@Override
	public void actualizarPlato(String id, String nombrePlato, String descripcion, double precio) throws Exception {
		if(nombrePlato.isBlank() || nombrePlato.equals(null) )
			throw new IllegalArgumentException("El argumento nombrePlato no puede ser null o vacio");
		
		if(descripcion.isBlank() || descripcion.equals(null))
			throw new IllegalArgumentException("El argumento descripvion no puede ser null o vacio");
		
		if(precio < 0.0 || precio==0d)
			throw new IllegalArgumentException("El argumento precio no puede ser valor negativo o vacio");
		
			Restaurante r = repositorio.getById(id);
			r.updatePlato(nombrePlato,descripcion,precio);
			repositorio.update(r);		
	}

	@Override
	public Restaurante recuperarRestaurante(String id) throws Exception {
		return repositorio.getById(id);		
	}

	@Override
	public void borrarRestaurante(String id) throws Exception { // ok
		Restaurante r;
		
			r = repositorio.getById(id);
			repositorio.delete(r);
		
	}

	@Override
	public List<RestauranteResumen> getListaRestaurantes() throws Exception {
			LinkedList<RestauranteResumen> lista = new LinkedList<>();
			for(Restaurante r : repositorio.getAll()) {
				RestauranteResumen res = new RestauranteResumen();
				res.setId(r.getId().toString());
				res.setLongitud(r.getLongitud());
				res.setLatitud(r.getLatitud());
				res.setNombre(r.getNombre());
				res.setGestor(r.getGestor());
				lista.add(res);
			}
			return lista;
	}
	
	
	// TODO
	@Override
	public String activarValoraciones(String id) throws Exception {
//		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build();
        
//		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:5000/api/")
//				.addConverterFactory(JacksonConverterFactory.create())
//				.build();
		//.client(client)
		//OpinionesRestCliente service = this.retrofit.create(OpinionesRestCliente.class);
		
		OpinionRequest opinionRequest = new OpinionRequest();
		opinionRequest.setRecurso(id);
		
		//try {
		ResponseBody responseBody = service.altaOpiniones(opinionRequest).execute().body();
		String resultado = responseBody.string();
					
		System.out.println(resultado);
		return resultado;
	}

	@Override
	public List<Valoracion> recuperarValoraciones(String id) throws Exception {
//		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(loggingInterceptor)
//                .build();
        
//		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:5000/api/")
//				.addConverterFactory(JacksonConverterFactory.create())
//				.build();
		
		//.client(client)
		//OpinionesRestCliente service = retrofit.create(OpinionesRestCliente.class);
		
		String idOpinion = this.recuperarRestaurante(id).getOpinionId();
		List<Valoracion>valoraciones = new LinkedList<Valoracion>();
		
		
		valoraciones = service.recuperarValoraciones(idOpinion).execute().body();
		return valoraciones;
	}

	@Override
	public void subscribirEvento() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setUri("amqps://egedtdxz:9F9-pdh4iD3HLyexhCaxZBDwyHH_Lq0d@stingray.rmq.cloudamqp.com/egedtdxz");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		final String exchangeName = "evento.valoracion.creada";
		final String queueName = "arso-queue";
		final String bindingKey = "arsoRK";

		boolean durable = true;
		boolean exclusive = false;
		boolean autodelete = false;
		Map<String, Object> properties = null; // sin propiedades
		channel.queueDeclare(queueName, durable, exclusive, autodelete, properties);

		channel.queueBind(queueName, exchangeName, bindingKey);
		
		boolean autoAck = false;
		String cola = "arso-queue";
		String etiquetaConsumidor = "arso-consumidor";
		
		while(true) {
		channel.basicConsume(cola, autoAck, etiquetaConsumidor, 
				  
				  new DefaultConsumer(channel) {
				    @Override
				    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
				            byte[] body) throws IOException {
				        
//				        String routingKey = envelope.getRoutingKey();
//				        String contentType = properties.getContentType();
				        long deliveryTag = envelope.getDeliveryTag();

				        String contenido = new String(body);
				        
				        EventoValoracionCreada evento = objectMapper.getContext(null).readValue(contenido, EventoValoracionCreada.class);
				        System.out.println(evento.getResumenOpinion().toString());
				        List<Restaurante> restaurantes;
						try {
							restaurantes = repositorio.getAll();
							for (Restaurante restaurante : restaurantes) {
									if(restaurante.getOpinionId().equals(evento.getIdOpinion())) {
										restaurante.setCalificacionMedia(evento.getResumenOpinion().getCalificacionMedia());
										restaurante.setNumValoraciones(evento.getResumenOpinion().getNumValoraciones());
										repositorio.update(restaurante);
									}
										
								}
						} catch (RepositorioException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (EntidadNoEncontrada e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
				       

				        //System.out.println(contenido);
				        
				        // Confirma el procesamiento
				        channel.basicAck(deliveryTag, false);
				    }
				});
		}
	}


	public Repositorio<Restaurante, String> getRepositorio() {
		return repositorio;
	}

	// Test
	
	
	
}
