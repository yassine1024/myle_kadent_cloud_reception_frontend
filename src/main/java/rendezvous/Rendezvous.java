package rendezvous;

import employee.medecin.Medecin;
import lombok.Data;
import patient.Patient;

@Data
public class Rendezvous {

    private Long id;
    private Patient patient;
    private Medecin medecin;
    private String date;
    private String time;
    private String complaint;
    private String acteToPerform;

    public Rendezvous(Patient patient, Medecin medecin, String date, String time, String acteToPerform) {
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        this.time = time;
        this.acteToPerform = acteToPerform;
    }
}
