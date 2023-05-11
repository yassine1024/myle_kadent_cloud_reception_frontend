package src.cabinet;


import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface CabinetAPI {

    @GET("")
    Call<List<Cabinet>> getAllCabinet();

    @GET("{id}")
    Call<Cabinet> getCabinet(@Path("id") String id);

    @POST("")
    Call<Cabinet> addCabinet(@Body Cabinet cabinet);

    @PUT("{id}")
    Call<Cabinet> updateCabinet(@Body Cabinet cabinet);

    @DELETE("{id}")
    Call<Cabinet> deleteCabinet(@Path("id") String id);
}
