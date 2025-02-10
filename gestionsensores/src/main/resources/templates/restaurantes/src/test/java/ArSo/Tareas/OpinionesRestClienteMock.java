package ArSo.Tareas;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import restaurantes.modelo.Valoracion;
import retrofit.opiniones.OpinionesRestCliente;
import retrofit2.Call;
import retrofit2.Response;
import utils.OpinionRequest;

public class OpinionesRestClienteMock implements OpinionesRestCliente{

	@SuppressWarnings("unchecked")
	@Override
	public Call<ResponseBody> altaOpiniones(OpinionRequest id) {
		
		Call<ResponseBody> call = mock(Call.class);
		
		try {
			when(call.execute()).thenReturn(Response.success(200, ResponseBody.create("1234", MediaType.get("text/plain"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return call;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Call<List<Valoracion>> recuperarValoraciones(String id) {
		Call<List<Valoracion>> call = mock(Call.class);
		Valoracion v = new Valoracion();
		v.setCorreo("prueba@gmail.com");
		v.setFecha("2023-05-18T18:07:00Z");
		v.setCalificacion(2);
		Valoracion v2 = new Valoracion();
		v2.setCorreo("prueba2@gmail.com");
		v2.setFecha("2023-05-18T02:07:00Z");
		v2.setCalificacion(0);
		List<Valoracion> valoraciones = Arrays.asList(
				v,
				v2
		);
		try {
			when(call.execute()).thenReturn(Response.success(valoraciones));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return call;
	}
	
}
