package rendezvous;

import patient.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Path;

import java.util.List;

public interface RendezvousService {


    public void getAllRendezvous(Callback<List<Rendezvous>> callback);

    public void addRendezvous(Callback<Void> callback, String date, String time, Patient patient) ;

}
