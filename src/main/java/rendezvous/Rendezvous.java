package rendezvous;

import lombok.Data;
import patient.Patient;

@Data
public class Rendezvous {

    private Long id;
    private String date;
    private String time;
    private String complaint;
    private String acteToPerform;
    private Patient patient;
}
