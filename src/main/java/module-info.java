module com.example.kadent {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kadent to javafx.fxml;
    exports com.example.kadent;
}