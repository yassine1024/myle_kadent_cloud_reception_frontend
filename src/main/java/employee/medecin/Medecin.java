package employee.medecin;



import com.google.gson.annotations.SerializedName;
import employee.Employee;
import lombok.Data;
import patient.Patient;

import java.util.Set;


@Data
public class Medecin extends Employee {



    public Medecin() {
        super();
    }

    public Medecin(String id) {
        this.id = id;
    }


    @SerializedName("salary")
    private float salary;
    @SerializedName("recette")
    private float recette;
    @SerializedName("pourcentage")
    private float pourcentage;

    private Set<Patient> treatedPatients;

    /*private Set<Patient> treatedPatients;*/


}
