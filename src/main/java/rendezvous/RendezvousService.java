package rendezvous;

import retrofit2.Call;
import retrofit2.Callback;

import java.util.List;

public interface RendezvousService {


    public void getAllRendezvous(Callback<List<Rendezvous>> callback);
}
