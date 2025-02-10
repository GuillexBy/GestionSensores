package ArSo.Tareas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;

import okhttp3.ResponseBody;
import restaurantes.modelo.Valoracion;
import retrofit.opiniones.OpinionesRestCliente;
import retrofit2.Call;
import retrofit2.Response;
import utils.OpinionRequest;

public class OpinionRestClienteMockPrueba implements OpinionesRestCliente{
	
	private OpinionesRestCliente clienteMock;
	
	
	public OpinionRestClienteMockPrueba() {
		this.clienteMock = Mockito.mock(OpinionesRestCliente.class);
	}

	@Override
	public Call<ResponseBody> altaOpiniones(OpinionRequest id) {
		// TODO Auto-generated method stub
		
		
		
		Response<ResponseBody> response = Response.success(null);
		@SuppressWarnings("unchecked")
		Call<ResponseBody> call = Mockito.mock(Call.class);
		try {
			Mockito.when(call.execute()).thenReturn(response);
			return call;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Call<List<Valoracion>> recuperarValoraciones(String id) {
		List<Valoracion> valoraciones = new ArrayList<>();
		
		Response<List<Valoracion>> response = Response.success(valoraciones);
		
		@SuppressWarnings("unchecked")
		Call<List<Valoracion>> call = Mockito.mock(Call.class);
		try {
			Mockito.when(call.execute()).thenReturn(response);
			return call;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
