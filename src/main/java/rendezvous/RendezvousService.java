package rendezvous;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Path;

import java.util.List;

public interface RendezvousService {


    public void getAllRendezvous(Callback<List<Rendezvous>> callback);

    public void addRendezvous(Call<Rendezvous> callback, String date, String time, String patient) ;

}
