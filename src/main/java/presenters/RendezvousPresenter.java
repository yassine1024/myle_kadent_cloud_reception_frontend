package presenters;

import patient.Patient;
import rendezvous.Rendezvous;

public interface RendezvousPresenter {

    public void getAllAppointment();

    void addRendezvous(Rendezvous rendezvous);

    void getPatientsByCabinet(String id);

    void getMedecinsByCabinet(String id, String source);
}
