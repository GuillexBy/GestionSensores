package retrofit.opiniones;

import java.util.List;

import okhttp3.ResponseBody;
import restaurantes.modelo.Valoracion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import utils.OpinionRequest;

public interface OpinionesRestCliente {

	@POST("opiniones")
	Call<ResponseBody> altaOpiniones(@Body OpinionRequest id);
	
	@GET("opiniones/{id}")
	Call<List<Valoracion>> recuperarValoraciones(@Path("id") String id);
}
