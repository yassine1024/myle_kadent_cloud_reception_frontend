package cabinet;



import retrofit2.Callback;


import java.util.List;


public interface CabinetService {

    public void getAllCabinet(Callback<List<Cabinet>> callback);

    public void getCabinet(Callback<Cabinet> callback, String id);
}
