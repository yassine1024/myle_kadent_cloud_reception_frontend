package views;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import presenters.RendezvousPresenter;

public class addRendezvousControllerImpl implements addRendezvousController{



    private RendezvousPresenter presenter;

    @FXML
    private DatePicker dateRdv;

    @FXML
    private TextField patientTextField;

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

        this.presenter.addRendezvous(date, time, patient);
        System.out.println("date: "+date+" time: "+time+" for patient: "+patient);

    }
}
