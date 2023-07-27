package Config;

public enum API {

    ADDRESS("http://127.0.0.1:8093/"),
    CABINET_PREFIX("api/cabinet/"),
    RENDEZVOUS_PREFIX("api/"),

    PATIENT_PREFIX("api/patients/");

    private final String address;

    private API(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
