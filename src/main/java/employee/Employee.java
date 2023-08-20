package employee;


import cabinet.Cabinet;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.sun.jdi.Type;
import employee.medecin.Medecin;
import lombok.Data;

import java.time.LocalDateTime;



@Data
public
class Employee {
    // Init it for serializing
    // You used values like 'bean1' & 'bean2' but using class name is more generic
    protected String type;

    @SerializedName("id")
    protected String id;
    @SerializedName("firstName")
    protected String firstName;
    @SerializedName("lastName")
    protected String lastName;
    @SerializedName("address")
    protected String address;
    @SerializedName("phoneNumber")
    protected String phoneNumber;

    //Path to the photo store in local storage
    @SerializedName("photo")
    protected String photo;
    //Path to the employee's document folder
    @SerializedName("documentFolder")
    protected String documentFolder;
    @SerializedName("cabinet")
    protected Cabinet cabinet;
    @SerializedName("createdAt")
    protected String createdAt;
    @SerializedName("updatedAt")
    protected String updatedAt;


    public Employee(){}


}
