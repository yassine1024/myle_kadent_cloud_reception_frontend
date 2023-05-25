package views;


import Config.CreateAppointment;
import Config.FullScene;
import com.google.inject.Inject;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.scene.control.agenda.Agenda;
import presenters.RendezvousPresenter;
import rendezvous.Rendezvous;


import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class RendezvousControllerImpl implements RendezvousController, Initializable {

    private RendezvousPresenter presenter;

    private Agenda appointmentAgenda;

    @FXML
    private StackPane agendaStackPane;



    @Inject
    public RendezvousControllerImpl(RendezvousPresenter presenter) {
        this.presenter = presenter;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.intializeAgenda();
        this.presenter.getAllAppointment();

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
        System.out.println("ffff");
        LocalDate currentStartDate = this.appointmentAgenda.getDisplayedLocalDateTime().toLocalDate();
        LocalDate nextWeekStartDate = currentStartDate.plusWeeks(1);
        this.appointmentAgenda.displayedLocalDateTime().set(nextWeekStartDate.atStartOfDay());

    }

    @Override
    @FXML
    public void goToPreviousWeek(MouseEvent event) {
        LocalDate currentStartDate = this.appointmentAgenda.getDisplayedLocalDateTime().toLocalDate();
        LocalDate previousWeekStartDate = currentStartDate.minusWeeks(1);
        this.appointmentAgenda.displayedLocalDateTime().set(previousWeekStartDate.atStartOfDay());
    }


    @Override
    @FXML
    public void maximizeAgenda(MouseEvent event) {

        Stage stage =(Stage) this.agendaStackPane.getScene().getWindow();

       new  FullScene().fullScene(this.getCopyFromAgendaStackPane(), stage);
    }

    private StackPane getCopyFromAgendaStackPane(){

        // Create a new AnchorPane
        StackPane fullScreenStackPane = new StackPane();

        // Copy the contents of agendaAnchorPane to the newAnchorPane
        ObservableList<Node> nodes = this.agendaStackPane.getChildren();
        fullScreenStackPane.getChildren().addAll(nodes);

        return fullScreenStackPane;
    }

}
