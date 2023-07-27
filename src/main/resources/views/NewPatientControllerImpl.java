package views;

import com.google.inject.Inject;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import patient.Patient;
import presenters.PatientPresenter;

import java.net.URL;
import java.util.ResourceBundle;


public class NewPatientControllerImpl implements NewPatientController, Initializable {


    private PatientPresenter presenter;
    @FXML
    private TextField address;

    @FXML
    private MFXDatePicker birthdate;

    @FXML
    private TextField firstName;

    @FXML
    private MFXComboBox<String> gender;

    @FXML
    private TextField job;

    @FXML
    private TextField lastName;

    @FXML
    private TextField phoneNumber;


    @Inject
    public NewPatientControllerImpl(PatientPresenter presenter) {
        this.presenter = presenter;
    }


    @FXML
    @Override
    public void addPatient(ActionEvent event) {
        Patient patient = makePatient();
        this.presenter.addPatient(patient);

    }

    @Override
    public void cleanForm() {

        this.firstName.setText(null);
        this.lastName.setText(null);
        this.job.setText(null);
        this.address.setText(null);
        this.phoneNumber.setText(null);

    }

    private Patient makePatient() {

        return new Patient(this.firstName.getText(), this.lastName.getText(), this.phoneNumber.getText(),
                this.address.getText(), this.gender.getSelectedItem().toString(), this.job.getText(),
                this.birthdate.getText());
    }

    private void intializeGenders() {
        // Create a list of items
        ObservableList<String> items = FXCollections.observableArrayList(
                "FEMININ",
                "MASCULIN"
        );

        // Set the items to the MFXComboBox
        this.gender.getItems().addAll(items);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.intializeGenders();
    }
}
