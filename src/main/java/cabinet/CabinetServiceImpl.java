package cabinet;

import Config.API;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import employee.Employee;
import employee.EmployeeDeserializer;
import employee.medecin.Medecin;
import patient.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;


public class
CabinetServiceImpl implements CabinetService {

    private final CabinetAPI cabinetAPI;


    public CabinetServiceImpl() {

        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Employee.class, new EmployeeDeserializer())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.ADDRESS.getAddress() + API.CABINET_PREFIX.getAddress())
                .addConverterFactory(GsonConverterFactory.create(gson))
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

    @Override
    public void getPatientsByCabinet(Callback<List<Patient>> callback, String id) {

        Call<List<Patient>> call = cabinetAPI.getPatientsByCabinet(id);
        call.enqueue(callback);

    }

    @Override
    public void getMedecinsByCabinet(Callback<List<Medecin>> callback, String id) {
        System.out.println("yassine");
        Call<List<Medecin>> call = cabinetAPI.getDoctorsByCabinet(id);
        call.enqueue(callback);
    }
}
