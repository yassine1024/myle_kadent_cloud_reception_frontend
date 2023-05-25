package presenters;

import com.google.inject.Inject;

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

    public void handleError(Throwable t) {
    }

    public void handleResponse(Response<List<Rendezvous>> response) {

        if (response.isSuccessful()) {

            List<Rendezvous> rendezvousList = response.body();

            this.view.addAppointmentsToAgenda(rendezvousList);

        }

    }
}
