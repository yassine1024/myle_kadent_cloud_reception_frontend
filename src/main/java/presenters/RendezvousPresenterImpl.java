package presenters;

import cabinet.CabinetService;
import com.google.inject.Inject;

import employee.medecin.Medecin;
import patient.Patient;
import rendezvous.Rendezvous;
import rendezvous.RendezvousService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import views.RendezvousController;
import views.addRendezvousController;

import java.util.List;

public class RendezvousPresenterImpl implements RendezvousPresenter {

    private final RendezvousService rendezvousService;
    private final RendezvousController view;
    private final addRendezvousController rendezvousController;
    private final CabinetService cabinetService;

    @Inject
    public RendezvousPresenterImpl(RendezvousService rendezvousService, RendezvousController view
            , CabinetService cabinetService, addRendezvousController rendezvousController) {
        this.rendezvousService = rendezvousService;
        this.cabinetService = cabinetService;
        this.view = view;
        this.rendezvousController = rendezvousController;


    }


    @Override
    public void getAllAppointment() {

//        List<Rendezvous> rendezvous = this.rendezvousService.getAllRendezvous();

        this.rendezvousService.getAllRendezvous(
                new Callback<List<Rendezvous>>() {
                    @Override
                    public void onResponse(Call<List<Rendezvous>> call, Response<List<Rendezvous>> response) {
                        handleResponse(response);
                    }

                    @Override
                    public void onFailure(Call<List<Rendezvous>> call, Throwable t) {
                        handleError(t);
                    }
                }
        );

    }

    @Override
    public void addRendezvous(String date, String time, Patient patient) {

        this.rendezvousService.addRendezvous(
                new Callback<Void>() {

                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        handleAddRendezvousResponse(response);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        handleAddRendezvousError(t);
                    }
                }


                , date, time, patient);

    }

    @Override
    public void getPatientsByCabinet(String id) {
        this.cabinetService.getPatientsByCabinet(
                new Callback<List<Patient>>() {
                    @Override
                    public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                        if (response.isSuccessful()) {

                            List<Patient> patients = response.body();
                            rendezvousController.setPatientsList(patients);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Patient>> call, Throwable t) {
                        System.out.println("Error to get list of patients by cabinet");
                    }
                }, id
        );
    }

    @Override
    public void getMedecinsByCabinet(String id) {
        System.out.println("farouk");
        try {


        this.cabinetService.getMedecinsByCabinet(
                new Callback<List<Medecin>>() {

                    @Override
                    public void onResponse(Call<List<Medecin>> call, Response<List<Medecin>> response) {
                        System.out.println("slimane!!!");
                        if (response.isSuccessful()) {

                            List<Medecin> medecins = response.body();
                            System.out.println(medecins.size());
                            medecins.forEach(
                                    medecin ->{
                                        System.out.println(medecin.getRecette());
                                        System.out.println(medecin.getAddress());
                                    }
                            );
                            rendezvousController.setDoctorsList(medecins);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Medecin>> call, Throwable t) {
                        System.out.println("Error to get list of doctors by cabinet");
                        System.out.println(t.getMessage());
                        System.out.println(t.toString());

                    }
                }
       , id );
        }catch (Exception e){
            System.out.println("kkkk: "+e.getMessage());

        }
    }


    public void handleAddRendezvousError(Throwable t) {
        System.out.println(t.getMessage());
    }

    public void handleAddRendezvousResponse(Response<Void> response) {
        System.out.println("Success");
    }

    public void handleError(Throwable t) {
    }

    public void handleResponse(Response<List<Rendezvous>> response) {

        if (response.isSuccessful()) {

            List<Rendezvous> rendezvousList = response.body();

            this.view.addAppointmentsToAgenda(rendezvousList);

        }

    }
}
