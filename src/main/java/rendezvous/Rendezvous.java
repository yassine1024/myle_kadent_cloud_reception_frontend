package rendezvous;

import lombok.Data;

@Data
public class Rendezvous {

    private Long id;
    private String date;
    private String time;
    private String complaint;
    private String acteToPerform;
}
