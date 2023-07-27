package patient;

import Config.API;
import rendezvous.RendezvousAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientServiceImpl implements PatientService{


    private PatientAPI patientAPI;

    public PatientServiceImpl(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.ADDRESS.getAddress()+API.PATIENT_PREFIX.getAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        patientAPI = retrofit.create(PatientAPI.class);
    }

    @Override
    public void addPatient(Callback<Void> callback, Patient patient) {


        Call<Void> call = patientAPI.addPatient(patient);
        call.enqueue(callback);
    }
}
