package views;


import employee.medecin.Medecin;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import rendezvous.Rendezvous;

import java.util.List;

public interface RendezvousController {

    public void intializeAgenda();

    public void addAppointmentsToAgenda(List<Rendezvous> rendezvousList);

    void goToNextWeek(MouseEvent event);
    void goToPreviousWeek(MouseEvent event);

    void maximizeAgenda(MouseEvent event);
    public void addRendezvous(MouseEvent event);

    void showScheduleByDoctor(ActionEvent event);

    void setDoctorsList(List<Medecin> medecins);
}
