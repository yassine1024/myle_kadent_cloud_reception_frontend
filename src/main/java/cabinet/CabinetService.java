package cabinet;



import employee.medecin.Medecin;
import patient.Patient;
import retrofit2.Callback;
import java.util.List;


public interface CabinetService {

    public void getAllCabinet(Callback<List<Cabinet>> callback);

    public void getCabinet(Callback<Cabinet> callback, String id);

    public void getPatientsByCabinet(Callback<List<Patient>> callback, String id);

    public void getMedecinsByCabinet(Callback<List<Medecin>> callback, String id);
}
