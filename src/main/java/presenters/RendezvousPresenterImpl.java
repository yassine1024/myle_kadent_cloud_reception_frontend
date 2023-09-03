package presenters;

import cabinet.CabinetService;
import com.google.inject.Inject;

import com.google.inject.name.Named;
import employee.medecin.Medecin;
import javafx.application.Platform;
import patient.Patient;
import rendezvous.Rendezvous;
import rendezvous.RendezvousService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import views.RendezvousController;
import views.addRendezvousController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        System.out.println("Controller instance: " + this);


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
    public void addRendezvous(Rendezvous rendezvous) {
        this.rendezvousService.addRendezvous(
                new Callback<Map<String, String>>() {

                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        handleAddRendezvousResponse(response, rendezvous);
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {
                        handleAddRendezvousError(t);
                    }
                }


                , rendezvous);

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
    public void getMedecinsByCabinet(String id, String source) {
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
                                        medecin -> {
                                            System.out.println(medecin.getRecette());
                                            System.out.println(medecin.getAddress());
                                        }
                                );
                                if (source.equals("rendezvous")) {
                                    view.setDoctorsList(medecins);
                                } else if (source.equals("addRendezvous")) {
                                    rendezvousController.setDoctorsList(medecins);
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<List<Medecin>> call, Throwable t) {
                            System.out.println("Error to get list of doctors by cabinet");
                            System.out.println(t.getMessage());
                            System.out.println(t.toString());

                        }
                    }
                    , id);
        } catch (Exception e) {
            System.out.println("kkkk: " + e.getMessage());

        }
    }

    @Override
    public void getAllRendezvousByDoctor(String id) {
        this.rendezvousService.getAllRendezvousByDoctor(
                new Callback<List<Rendezvous>>() {
                    @Override
                    public void onResponse(Call<List<Rendezvous>> call, Response<List<Rendezvous>> response) {
                        Platform.runLater(
                                () -> {
                                    view.setRendezvousList(response.body());
                                }
                        );

                    }

                    @Override
                    public void onFailure(Call<List<Rendezvous>> call, Throwable t) {

                    }
                }
                , id);
    }


    public void handleAddRendezvousError(Throwable t) {
        System.out.println(t.getMessage());
    }


    public void handleAddRendezvousResponse(Response<Map<String, String>> response, Rendezvous rendezvous) {


        Platform.runLater(() -> {
            // Update UI components here
            this.rendezvousController.updateView(response.body().get("value"));
        });
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
