package rendezvous;

import patient.Patient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Path;

import java.util.List;
import java.util.Map;

public interface RendezvousService {


    public void getAllRendezvous(Callback<List<Rendezvous>> callback);

    public void addRendezvous(Callback<Map<String, String>> callback, Rendezvous rendezvous) ;

}
