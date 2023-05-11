package src.cabinet;

import src.Config.API;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;



public class
CabinetServiceImpl implements CabinetService {

    private final CabinetAPI cabinetAPI;




    public CabinetServiceImpl() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.ADDRESS.getAddress()+API.CABINET_PREFIX.getAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cabinetAPI = retrofit.create(CabinetAPI.class);
    }

    @Override
    public void getAllCabinet(Callback<List<Cabinet>> callback) {

        Call<List<Cabinet>> call = cabinetAPI.getAllCabinet();
        call.enqueue(callback);

    }

    @Override
    public void getCabinet(Callback<Cabinet> callback, String id) {
        Call<Cabinet> call = cabinetAPI.getCabinet(id);
        call.enqueue(callback);

    }
}
