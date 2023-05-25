package views;


import javafx.scene.input.MouseEvent;
import presenters.RendezvousPresenter;
import rendezvous.Rendezvous;

import java.util.List;

public interface RendezvousController {

    public void intializeAgenda();

    public void addAppointmentsToAgenda(List<Rendezvous> rendezvousList);

    void goToNextWeek(MouseEvent event);
    void goToPreviousWeek(MouseEvent event);

    void maximizeAgenda(MouseEvent event);

}
