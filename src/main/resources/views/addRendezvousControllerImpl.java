package views;

import com.google.inject.Inject;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import patient.Patient;
import presenters.RendezvousPresenter;

public class addRendezvousControllerImpl implements addRendezvousController{



    private RendezvousPresenter presenter;

    @FXML
    private MFXDatePicker dateRdv;

    @FXML
    private MFXTextField patientTextField;

    @FXML
    private TextField timeRdv;


    @Inject
    public addRendezvousControllerImpl(RendezvousPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    @FXML
    public void addRendezvous(ActionEvent event) {

        String date=dateRdv.getValue().toString();
        String patient = this.patientTextField.getText();
        String time= this.timeRdv.getText();
        Patient patient1= new Patient();
        patient1.setId(patient);
        this.presenter.addRendezvous(date, time, patient1);
        System.out.println("date: "+date+" time: "+time+" for patient: "+patient);

    }
}
