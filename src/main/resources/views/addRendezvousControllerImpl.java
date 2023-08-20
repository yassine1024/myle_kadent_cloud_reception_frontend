package views;

import Config.Const;
import com.google.inject.Inject;
import employee.medecin.Medecin;
import io.github.palexdev.materialfx.controls.MFXComboBox;
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
import java.util.stream.Collectors;

public class addRendezvousControllerImpl implements addRendezvousController, Initializable {



    private RendezvousPresenter presenter;

    @FXML
    private MFXDatePicker dateRdv;


    @FXML
    private TextField patientTextField;

    @FXML
    private TextField timeRdv;
    @FXML
    private MFXComboBox<String> actGroup;
    @FXML
    private MFXComboBox<String> doctorList;



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
    public void setPatientsList(List<Patient> patients) {

        List<String> suggestionList = patients.stream()
                .map(patient -> patient.getFirstName() + " " + patient.getLastName())
                .collect(Collectors.toList());
        TextFields.bindAutoCompletion(this.patientTextField, FXCollections.observableList(suggestionList));

    }

    @Override
    public void setDoctorsList(List<Medecin> medecins) {

        List<String> suggestionList = medecins.stream()
                .map(medecin -> medecin.getFirstName()+" "+ medecin.getLastName())
                .collect(Collectors.toList());

        this.doctorList.getItems().addAll(FXCollections.observableList(suggestionList));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.intializePatientsList();
        this.intializeDoctorsList();
        this.intializeActGroup();
    }

    private void intializeDoctorsList() {
        this.presenter.getMedecinsByCabinet(Const.cabinetId);
    }

    private void intializePatientsList() {
        this.presenter.getPatientsByCabinet(Const.cabinetId);
    }

    private void intializeActGroup() {
        /*ODF ------> red
        ENDO ------> green
        Restoration ---------> yellow
        Fix Prostetics ----------> blue
        Movible Prostetics ---------> Orange
        Implant -----------------> purple
        Tooth Extraction ------------> brown
        Surgery --------------> grey
        Perio -----------------> white
        Consultation -----------> black*/

        ObservableList<String> acts = FXCollections.observableArrayList(
                "ODF",
                "ENDO",
                "Restoration",
                "Fix Prostetics",
                "Movible Prostetics",
                "Implant",
                "Tooth Extraction",
                "Surgery",
                "Perio",
                "Consultation"
        );

        // Set the items to the MFXComboBox
        this.actGroup.getItems().addAll(acts);
    }

}
