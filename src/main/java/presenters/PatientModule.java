package presenters;

import com.google.inject.AbstractModule;
import patient.PatientService;
import patient.PatientServiceImpl;
import views.DashboardController;
import views.DashboardControllerImpl;
import views.NewPatientController;
import views.NewPatientControllerImpl;

public class PatientModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PatientService.class).to(PatientServiceImpl.class);
        bind(PatientPresenter.class).to(PatientPresenterImpl.class);
        bind(NewPatientController.class).to(NewPatientControllerImpl.class);

    }
}
