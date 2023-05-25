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
}
