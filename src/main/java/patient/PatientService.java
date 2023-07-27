package patient;

import retrofit2.Callback;

public interface PatientService {


    public void addPatient(Callback<Void> callback, Patient patient);
}
