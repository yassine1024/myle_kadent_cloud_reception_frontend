package presenters;

import com.google.inject.Inject;

import patient.Patient;
import rendezvous.Rendezvous;
import rendezvous.RendezvousService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import views.RendezvousController;

import java.util.List;

public class RendezvousPresenterImpl implements RendezvousPresenter {

    private final RendezvousService rendezvousService;
    private final RendezvousController view;

    @Inject
    public RendezvousPresenterImpl(RendezvousService rendezvousService, RendezvousController view) {
        this.rendezvousService = rendezvousService;
        this.view = view;

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
