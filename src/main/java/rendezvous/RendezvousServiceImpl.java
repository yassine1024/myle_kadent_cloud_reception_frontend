package rendezvous;

import Config.API;
import cabinet.CabinetAPI;
import patient.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class RendezvousServiceImpl implements RendezvousService{

    private final RendezvousAPI rendezvousAPI;

    public RendezvousServiceImpl() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.ADDRESS.getAddress()+API.RENDEZVOUS_PREFIX.getAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rendezvousAPI = retrofit.create(RendezvousAPI.class);
    }


    @Override
    public void getAllRendezvous(Callback<List<Rendezvous>> callback) {

        Call<List<Rendezvous>> call = rendezvousAPI.getAllRendezvous();
        call.enqueue(callback);

    }

    @Override
    public void addRendezvous(Callback<Void> callback, String date, String time, Patient patient) {

        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setDate(date);
        rendezvous.setTime(time);
        rendezvous.setPatient(patient);

        Call<Void> call= rendezvousAPI.addRendezvous(patient.getId(),rendezvous);
        call.enqueue(callback);

    }


}
