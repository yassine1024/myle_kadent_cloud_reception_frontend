package patient;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface PatientAPI {

    @GET("")
    public Call<List<Patient>> getAllPatients();

    @GET("/{id}")
    public Call<Patient> getPatient(@Path("id") String id);

    @POST(".")
    public Call<Void> addPatient(@Body Patient patient);

    @PUT("/{id}")
    public Call<Void> updatePatient(@Body Patient patient);

    @DELETE("/{id}")
    public Call<Void> deletePatient(@Path("id") String id);

}
