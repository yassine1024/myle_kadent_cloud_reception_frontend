package rendezvous;

import Config.API;
import cabinet.CabinetAPI;
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
}
