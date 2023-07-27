package views;

import com.google.inject.Inject;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import patient.Patient;
import presenters.RendezvousPresenter;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class addRendezvousControllerImpl implements addRendezvousController, Initializable {



    private RendezvousPresenter presenter;

    @FXML
    private MFXDatePicker dateRdv;


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
        Patient patient1= new Patient();
        patient1.setId(patient);
        this.presenter.addRendezvous(date, time, patient1);
        System.out.println("date: "+date+" time: "+time+" for patient: "+patient);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> suggestionList = Arrays.asList("Apple", "Banana", "Orange", "Pineapple", "Mango");
        TextFields.bindAutoCompletion(this.patientTextField, FXCollections.observableList(suggestionList));
    }


}
