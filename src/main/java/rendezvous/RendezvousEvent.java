package rendezvous;

import java.util.EventObject;

public class RendezvousEvent extends EventObject {

    private final String medecinFullName;
    private final String date;
    private final String time;


    public RendezvousEvent(Object source, String medecinFullName, String date, String time) {
        super(source);
        this.medecinFullName = medecinFullName;
        this.date = date;
        this.time = time;
    }

    public String getMedecinFullName() {
        return medecinFullName;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
