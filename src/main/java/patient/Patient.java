package patient;

import lombok.Data;

@Data
public class Patient {

    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String gender;
    private String job;
    private String age;

    public Patient(){}

    public Patient(String firstName, String lastName, String phoneNumber, String address,
                   String gender, String job, String age) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.job = job;
        this.age = age;
    }
}
