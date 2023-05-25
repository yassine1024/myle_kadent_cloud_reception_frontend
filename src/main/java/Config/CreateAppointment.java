package Config;

import jfxtras.scene.control.agenda.Agenda;
import rendezvous.Rendezvous;

import java.time.LocalDateTime;

public class CreateAppointment implements Agenda.Appointment {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String summary;
    private String description;
    private boolean wholeDay;
    private Agenda.AppointmentGroup appointmentGroup;

    private Rendezvous rendezvousDetail;


    public CreateAppointment(String dateString, String timeString, Rendezvous rendezvousDetail
            , Agenda.AppointmentGroup appointmentGroup) {

        this.rendezvousDetail = rendezvousDetail;

        String[] date = dateString.split("-");
        String[] time = timeString.split(":");

        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);

        this.startDateTime = LocalDateTime.of(year, month, day, hour, minute);
        this.endDateTime = LocalDateTime.of(year, month, day, hour + 1, minute);
        this.summary = "Rendezvous";
        this.description = "description...";
        this.wholeDay = true;
        this.appointmentGroup = appointmentGroup;

    }


    public void showAppointmentDetails() {
       /* Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rendezvous Details");
        alert.setHeaderText(appointment.getSummary());
        alert.setContentText("Monsieur/Madamme: " + rendezvousDetail.getPatient().getFirstName()+
                rendezvousDetail.getPatient().getLastName() +
                "\nRendezvous de : " + rendezvousDetail.getDate()+" à "+rendezvousDetail.getTime() );
        alert.showAndWait();*/
    }

    @Override
    public Boolean isWholeDay() {
        return true;
    }

    @Override
    public void setWholeDay(Boolean aBoolean) {
        this.wholeDay = aBoolean;
    }

    @Override
    public String getSummary() {
        return this.summary;
    }

    @Override
    public void setSummary(String s) {

    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setDescription(String s) {

    }

    @Override
    public String getLocation() {
        return null;
    }

    @Override
    public void setLocation(String s) {

    }

    @Override
    public Agenda.AppointmentGroup getAppointmentGroup() {
        return this.appointmentGroup;
    }

    @Override
    public void setAppointmentGroup(Agenda.AppointmentGroup appointmentGroup) {
        this.appointmentGroup = appointmentGroup;
    }

    @Override
    public LocalDateTime getStartLocalDateTime() {
        return startDateTime;
    }

    @Override
    public LocalDateTime getEndLocalDateTime() {
        return endDateTime;
    }

    @Override
    public void setStartLocalDateTime(LocalDateTime localDateTime) {
        this.startDateTime = localDateTime;
    }

    @Override
    public void setEndLocalDateTime(LocalDateTime localDateTime) {
        this.endDateTime = localDateTime;
    }
}
