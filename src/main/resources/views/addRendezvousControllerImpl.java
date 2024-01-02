package views;

import Config.Const;
import Config.FullScene;
import Config.Time;
import com.google.inject.Inject;
import employee.medecin.Medecin;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import patient.Patient;
import presenters.RendezvousPresenter;
import rendezvous.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class addRendezvousControllerImpl implements addRendezvousController, Initializable, RendezvousListener {


    private RendezvousPresenter presenter;

    @FXML
    private MFXDatePicker dateRdv;


    @FXML
    private TextField patientTextField;

    @FXML
    private MFXComboBox<String> timeRdv;
    @FXML
    private MFXComboBox<String> actGroup;
    @FXML
    private MFXComboBox<String> doctorList;

    private List<Patient> patients;
    private List<Medecin> medecins;


    @Inject
    public addRendezvousControllerImpl(RendezvousPresenter presenter) {
        this.presenter = presenter;
    }

    public addRendezvousControllerImpl(){
        initializeEventHandler();
    }

    private void initializeEventHandler() {
        // Register an event handler for the InitializeFormEvent
        EventBus eventBus = new EventBus();
        eventBus.addListener(this); // Add this controller as a listener to the event bus

    }

    private void initializeForm(RendezvousEvent event) {
        // Implement the code to initialize the form here
        System.out.println("Form initialized!");



        new FullScene().displayWindow("/views/addRendezvous.fxml", TemplateControllerImpl.rendezvousModule);
            //this.showScheduleByDoctor(null);
        System.out.println("Dr: "+event.getMedecinFullName());
        this.doctorList.setValue(event.getMedecinFullName());

    }
    @Override
    @FXML
    public void addRendezvous(ActionEvent event) {

        String date = dateRdv.getValue().toString();
        String patient = this.patientTextField.getText();
        String time = this.timeRdv.getValue().toString();
        String medecin = this.doctorList.getValue().toString();
        String acteGroup = this.actGroup.getValue().toString();

        Rendezvous rendezvous = new Rendezvous(getThePatient(patient),
                getTheMedecin(medecin), date, time, acteGroup);

        this.presenter.addRendezvous(rendezvous);


//        System.out.println("date: "+date+" time: "+time+" for patient: "+patient);

    }

    private Patient getThePatient(String fullname) {

        return this.patients.stream()
                .filter(patient -> (patient.getFirstName() + " " + patient.getLastName())
                        .equals(fullname))
                .findFirst()
                .orElse(null);
    }

    private Medecin getTheMedecin(String fullname) {
        return this.medecins.stream()
                .filter(medecin -> (medecin.getFirstName() + " " + medecin.getLastName())
                        .equals(fullname))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void setPatientsList(List<Patient> patients) {

        this.patients = patients;
        List<String> suggestionList = patients.stream()
                .map(patient -> patient.getFirstName() + " " + patient.getLastName())
                .collect(Collectors.toList());
        TextFields.bindAutoCompletion(this.patientTextField, FXCollections.observableList(suggestionList));

    }

    @Override
    public void setDoctorsList(List<Medecin> medecins) {

        this.medecins = medecins;
        List<String> suggestionList = medecins.stream()
                .map(medecin -> medecin.getFirstName() + " " + medecin.getLastName())
                .collect(Collectors.toList());

        this.doctorList.getItems().addAll(FXCollections.observableList(suggestionList));
    }

    @Override
    public void updateView(String value) {
        if (this.rendezvousAdded(value)) {
            this.cleanFields();
        }
        this.showAlert(value);

    }

    @Override
    public void fireEvent(Event event) {
        
    }

    private void showAlert(String value) {

        Alert notification = new Alert(Alert.AlertType.INFORMATION);
        notification.setTitle("Notification");
        notification.setHeaderText("Infos:");
        notification.setContentText(value);
        notification.showAndWait();
    }

    private void cleanFields() {
        patientTextField.clear();
        timeRdv.getSelectionModel().clearSelection();
        actGroup.getSelectionModel().clearSelection();
        doctorList.getSelectionModel().clearSelection();
    }

    private boolean rendezvousAdded(String value) {

        return MessagesRendezvous.ADD_RDV_SUCCESS.getMessage()
                .equals(value);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.intializePatientsList();
        this.intializeDoctorsList();
        this.intializeActGroup();
        this.initializeTimeRDV();
    }

    private void initializeTimeRDV() {
        this.timeRdv.getItems().addAll(FXCollections.observableList(Time.getTimePerHour()));
    }

    private void intializeDoctorsList() {
        this.presenter.getMedecinsByCabinet(Const.cabinetId, "addRendezvous");
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

    @Override
    public void handleRendezvousEvent(RendezvousEvent event) {
        // Handle the event and initialize the form
        initializeForm(event);
    }
}
