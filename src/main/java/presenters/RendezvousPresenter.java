package presenters;

import patient.Patient;

public interface RendezvousPresenter {

    public void getAllAppointment();

    void addRendezvous(String date, String time, Patient patient);

    void getPatientsByCabinet(String id);

    void getMedecinsByCabinet(String id);
}
