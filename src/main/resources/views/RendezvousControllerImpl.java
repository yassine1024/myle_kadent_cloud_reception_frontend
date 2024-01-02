package views;


import Config.Const;
import Config.CreateAppointment;
import Config.FullScene;
import agenda.RendezvousSchedule;
import com.google.inject.Inject;

import com.google.inject.Singleton;
import employee.medecin.Medecin;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.scene.control.agenda.Agenda;
import presenters.RendezvousModule;
import presenters.RendezvousPresenter;
import rendezvous.Rendezvous;


import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Singleton
public class RendezvousControllerImpl implements RendezvousController, Initializable {

    private RendezvousPresenter presenter;

    private Agenda appointmentAgenda;

    @FXML
    private StackPane agendaStackPane;
    @FXML
    private MFXComboBox<String> doctorsList;
    @FXML
    private Label dateLabel;
    private List<Medecin> medecins;


    @Inject
    public RendezvousControllerImpl(RendezvousPresenter presenter) {
        this.presenter = presenter;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

        //this.intializeAgenda();
        this.initializeDoctorsList();

        //this.presenter.getAllAppointment();

    }

    private void defaultDoctor() {
        if (this.medecins != null) {

            this.doctorsList.setValue(medecins.get(0).getFirstName() + " " + medecins.get(0).getLastName());

        }
    }

    private void initializeDoctorsList() {
        this.presenter.getMedecinsByCabinet(Const.cabinetId, "rendezvous");
    }

    @Override
    public void intializeAgenda() {

        this.intializeAppointementAgenda();
        this.affectAppointementAgendaToStackPane();

    }

    private void intializeAppointementAgenda() {
        this.appointmentAgenda = new Agenda();
        this.appointmentAgenda.setPrefSize(658, 327);

//        appointmentAgendaClicked();

    }

    private void appointmentAgendaClicked() {
        // Register event handler for appointment click
       /* for (Agenda.Appointment appointment : agenda.appointments()) {
            appointment.getNode().setOnMouseClicked(event -> {
                System.out.println("Clicked appointment: " + appointment.getSummary());
            });
        }*/
    }

    private void affectAppointementAgendaToStackPane() {

        this.agendaStackPane.getChildren().add(appointmentAgenda);
    }

    @Override
    public void addAppointmentsToAgenda(List<Rendezvous> rendezvousList) {
// Create an appointment group
        Agenda.AppointmentGroup appointmentGroup = new Agenda.AppointmentGroupImpl();

        /* The exception you're encountering indicates that you are performing
         UI-related operations on a thread other than the JavaFX Application Thread.
          In JavaFX, all UI updates and interactions should be performed on
           the JavaFX Application Thread to ensure thread safety. To resolve this issue,
            you can wrap the UI-related code that triggers the exception inside a Platform.runLater()
         */
        Platform.runLater(() -> {

            rendezvousList.forEach(
                    rendezvous -> {

                        CreateAppointment appointment = new CreateAppointment(rendezvous.getDate(),
                                rendezvous.getTime(), rendezvous, appointmentGroup);


                        this.appointmentAgenda.appointments().add(appointment);


                    }
            );

        });


    }

    @Override
    @FXML
    public void goToNextWeek(MouseEvent event) {

       /* LocalDate currentStartDate = this.appointmentAgenda.getDisplayedLocalDateTime().toLocalDate();
        LocalDate nextWeekStartDate = currentStartDate.plusWeeks(1);
        this.appointmentAgenda.displayedLocalDateTime().set(nextWeekStartDate.atStartOfDay());*/
        this.rendezvousSchedule.weekAfter();
        this.agendaStackPane
                .getChildren()
                .clear();
        this.agendaStackPane
                .getChildren()
                .add(rendezvousSchedule.drawSchedule(
                this.rendezvous, this
        ));

    }

    @Override
    @FXML
    public void goToPreviousWeek(MouseEvent event) {
        /*LocalDate currentStartDate = this.appointmentAgenda.getDisplayedLocalDateTime().toLocalDate();
        LocalDate previousWeekStartDate = currentStartDate.minusWeeks(1);
        this.appointmentAgenda.displayedLocalDateTime().set(previousWeekStartDate.atStartOfDay());*/
        this.rendezvousSchedule.weekBefore();
        this.agendaStackPane
                .getChildren()
                .add(rendezvousSchedule.drawSchedule(
                        this.rendezvous, this
                ));

    }


    @Override
    @FXML
    public void maximizeAgenda(MouseEvent event) {

        Stage stage = (Stage) this.agendaStackPane.getScene().getWindow();

        new FullScene().fullScene(this.getCopyFromAgendaStackPane(), stage);
    }

    @Override
    @FXML
    public void addRendezvous(MouseEvent event) {

        new FullScene().displayWindow("/views/addRendezvous.fxml", TemplateControllerImpl.rendezvousModule);
        this.showScheduleByDoctor(null);
    }


    private RendezvousSchedule rendezvousSchedule;
    public static String medecinFullName;
    @Override
    @FXML
    public void showScheduleByDoctor(ActionEvent event) {

        medecinFullName = this.doctorsList.getValue().toString();

        this.presenter.getAllRendezvousByDoctor(this.getTheMedecin(medecinFullName).getId());
        System.out.println("Med: "+medecinFullName);


    }

    @Override
    public void setDoctorsList(List<Medecin> medecins) {

        this.medecins = medecins;
        List<String> suggestionList = medecins.stream()
                .map(medecin -> medecin.getFirstName() + " " + medecin.getLastName())
                .collect(Collectors.toList());

        this.doctorsList.getItems().addAll(FXCollections.observableList(suggestionList));
        this.defaultDoctor();
    }

    private List<Rendezvous> rendezvous;

    @Override
    public List<Rendezvous> getRendezvous() {
        return rendezvous;
    }

    @Override
    public void setRendezvousList(List<Rendezvous> rendezvous) {


        this.rendezvous = rendezvous;

        this.rendezvousSchedule = new RendezvousSchedule();
        this.agendaStackPane
                .getChildren()
                .add(rendezvousSchedule.drawSchedule(
                        this.rendezvous, this
                ));
    }

    @Override
    public void updateDateLabel(String startDate, String endDate) {
        this.dateLabel.setText(startDate + " -----------> " + endDate);
    }

    private Medecin getTheMedecin(String fullname) {
        return this.medecins.stream()
                .filter(medecin -> (medecin.getFirstName() + " " + medecin.getLastName())
                        .equals(fullname))
                .findFirst()
                .orElse(null);
    }

    private StackPane getCopyFromAgendaStackPane() {

        // Create a new AnchorPane
        StackPane fullScreenStackPane = new StackPane();

        // Copy the contents of agendaAnchorPane to the newAnchorPane
        ObservableList<Node> nodes = this.agendaStackPane.getChildren();
        fullScreenStackPane.getChildren().addAll(nodes);

        return fullScreenStackPane;
    }

}
