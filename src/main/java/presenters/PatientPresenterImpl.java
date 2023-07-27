package presenters;

import com.google.inject.Inject;
import patient.Patient;
import patient.PatientService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import views.NewPatientController;

public class PatientPresenterImpl implements PatientPresenter {

    private PatientService service;
    private NewPatientController view;

    @Inject
    public PatientPresenterImpl(PatientService service, NewPatientController view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void addPatient(Patient patient) {

        this.service.addPatient(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                handleAddPatientResponse(response);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                handlePatientError(t);
            }
        }, patient);


    }

    private void handlePatientError(Throwable t) {
        System.out.println(t.getMessage());
    }

    private void handleAddPatientResponse(Response<Void> response) {
        this.view.cleanForm();
    }
}
