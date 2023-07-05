package rendezvous;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RendezvousAPI {

    @GET("patients/{patientId}/rendezvous")
    public Call<List<Rendezvous>> getAllRendezvousByPatient(@Path("patientId") String patientId) ;
    @GET("rendezvous")
    public Call<List<Rendezvous>> getAllRendezvous() ;

    @GET("rendezvous/{id}")
    public Call<Rendezvous> getRendezvous(@Path("id") Long id);

    @POST("patients/{patientId}/rendezvous")
    public Call<Void> addRendezvous(@Path("patientId") String patientId, @Body Rendezvous rendezvous) ;

    @PUT("patients/{patientId}/rendezvous/{id}")
    public Call<Rendezvous> updateRendezvous(@Path("id") String id, @Path("patientId") String patientId,
                                 @Body Rendezvous rendezvous);

    @DELETE("rendezvous/{id}")
    public Call<Rendezvous> deleteRendezvous(@Path("id") Long id);

}
