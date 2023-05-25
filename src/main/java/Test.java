
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import jfxtras.internal.scene.control.skin.agenda.AgendaDaySkin;
import jfxtras.scene.control.agenda.Agenda;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class Test extends Application {

    Agenda agenda;
    Map<Agenda.Appointment, String> appointmentIdentifiers;

    Agenda.AppointmentGroup appointmentGroup;


    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
         agenda = new Agenda();
        agenda.setPrefSize(800, 600);

       // Customize the weekend days to Friday and Saturday
        EnumSet<DayOfWeek> weekendDays = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);

        // Create a custom skin for Agenda to render the weekend days differently
        /*AgendaDaySkin customSkin = new AgendaDaySkin(agenda) {
//            @Override
            protected boolean isWeekendDay(LocalDate date) {
                return weekendDays.contains(date.getDayOfWeek());
            }
        };*/
        //agenda.setSkin(customSkin);

        // Create a map to store appointment identifiers
        appointmentIdentifiers = new HashMap<>();

        // Create an appointment group
        appointmentGroup = new Agenda.AppointmentGroupImpl();

        agenda.appointments().add(this.initailizeAppointment(8, 0));
        agenda.appointments().add(this.initailizeAppointment(8, 0));
        agenda.appointments().add(this.initailizeAppointment(8, 0));

        // Register the appointment changed callback
        agenda.setAppointmentChangedCallback(changedAppointment -> {
            // Get the identifier of the changed appointment
            String appointmentIdentifier = appointmentIdentifiers.get(changedAppointment);


            // Check if the appointment is clicked
            if (appointmentIdentifier != null ) {
                // Perform the desired action when the appointment is clicked
                showAppointmentDetails(changedAppointment);
            }
            return null;
        });

        Button prevButton = new Button("Previous Week");
        prevButton.setOnAction(event -> goToPreviousWeek());

        Button nextButton = new Button("Next Week");
        nextButton.setOnAction(event -> goToNextWeek());

        BorderPane root = new BorderPane();
        root.setTop(prevButton);
        root.setCenter(agenda);
        root.setBottom(nextButton);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Agenda Navigation App");
        primaryStage.show();
    }

    public Agenda.Appointment initailizeAppointment(int hour, int minute) {

        // Create an appointment
        Agenda.Appointment appointment = new Agenda.AppointmentImplLocal()
                .withStartLocalDateTime(LocalDateTime.of(2023, 5, 15, hour, minute))
                .withEndLocalDateTime(LocalDateTime.of(2023, 5, 15, hour+1, minute))
                .withSummary("Meeting " + minute)
                .withAppointmentGroup(appointmentGroup);

        // Assign a unique identifier to the appointment
        String appointment1Identifier = "appointment" + minute;
        appointmentIdentifiers.put(appointment, appointment1Identifier);

        // Add the appointment to the Agenda
        return appointment;
    }

    // Method to show appointment details
    private void showAppointmentDetails(Agenda.Appointment appointment) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Appointment Details");
        alert.setHeaderText(appointment.getSummary());
        alert.setContentText("Start Time: " + appointment.getStartLocalDateTime() +
                "\nEnd Time: " + appointment.getEndLocalDateTime());
        alert.showAndWait();
    }

    private void goToPreviousWeek() {
        LocalDate currentStartDate = agenda.getDisplayedLocalDateTime().toLocalDate();
        LocalDate previousWeekStartDate = currentStartDate.minusWeeks(1);
        agenda.displayedLocalDateTime().set(previousWeekStartDate.atStartOfDay());

    }

    private void goToNextWeek() {
        LocalDate currentStartDate = agenda.getDisplayedLocalDateTime().toLocalDate();
        LocalDate nextWeekStartDate = currentStartDate.plusWeeks(1);
        agenda.displayedLocalDateTime().set(nextWeekStartDate.atStartOfDay());



    }
}
