package views;

import employee.medecin.Medecin;
import javafx.event.ActionEvent;
import patient.Patient;

import java.util.List;

public interface addRendezvousController {


    public void addRendezvous(ActionEvent event);

    public void setPatientsList(List<Patient> patients);


    void setDoctorsList(List<Medecin> medecins);
}
