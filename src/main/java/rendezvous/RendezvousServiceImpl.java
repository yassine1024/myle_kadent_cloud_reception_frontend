package rendezvous;

import Config.API;
import cabinet.CabinetAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import patient.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;
import java.util.Map;

public class RendezvousServiceImpl implements RendezvousService{

    private final RendezvousAPI rendezvousAPI;

    public RendezvousServiceImpl() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.ADDRESS.getAddress()+API.RENDEZVOUS_PREFIX.getAddress())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        rendezvousAPI = retrofit.create(RendezvousAPI.class);
    }


    @Override
    public void getAllRendezvous(Callback<List<Rendezvous>> callback) {

        Call<List<Rendezvous>> call = rendezvousAPI.getAllRendezvous();
        call.enqueue(callback);

    }

    @Override
    public void addRendezvous(Callback<Map<String, String>> callback, Rendezvous rendezvous) {

        String patientId= rendezvous.getPatient().getId();
        Call<Map<String, String>> call= rendezvousAPI.addRendezvous(patientId,rendezvous);
        call.enqueue(callback);
    }

    @Override
    public void getAllRendezvousByDoctor(Callback<List<Rendezvous>> callback, String medecinId) {

        Call<List<Rendezvous>> call = rendezvousAPI.getAllRendezvousByDoctor(medecinId);
        call.enqueue(callback);
    }


}
