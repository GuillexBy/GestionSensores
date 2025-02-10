package retrofit.restaurantes;

import java.util.List;

import restaurantes.modelo.Restaurante;
import restaurantes.modelo.SitioTuristico;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestaurantesRestCliente {

	@GET("restaurantes/{id}")
	Call<Restaurante> getRestaurante(@Path("id") String id);

	@POST("restaurantes")
	Call<Void> createRestaurante(@Body Restaurante restaurante);

	@PUT("restaurantes/{id}")
	Call<Void> updateRestaurante(@Path("id") String id, @Body Restaurante restaurante);

	@DELETE("restaurantes/{id}")
	Call<Void> deleteRestaurante(@Path("id") String id);

	@GET("restaurantes")
	Call<Listado> getListado();

	@GET("restaurantes/{id}/sitios_turisticos")
	Call<List<SitioTuristico>> getSitiosTuristicos(@Path("id")String id); 

	@PUT("restaurantes/{id}/sitios_turisticos")
	Call<Void> setSitiosTuristicos(@Path("id") String id, @Body List<SitioTuristico> sitios);

	@FormUrlEncoded
	@PUT("restaurantes/{id}/platos")
	Call<Void> addPlatoAlRestaurante(@Path("id") String id, @Field("nombre") String nombre,
			@Field("descripcion") String descripcion, @Field("precio") double precio);

	@DELETE("restaurantes/{id}/platos/{plato}")
	Call<Void> deletePlato(@Path("id") String id, @Path("plato") String plato);

	@FormUrlEncoded
	@PUT("restaurantes/{id}/platos/{plato}")
	Call<Void> updatePlato(@Path("id") String id, @Path("plato") String plato, @Field("descripcion") String descripcion, @Field("precio") double precio);
	
	//void activarValoraciones();

	//List<ResumenValoracion>recuperarValoraciones();
}
