package views;

import Config.FullScene;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import presenters.PatientModule;


public class DashboardControllerImpl implements DashboardController {

    @FXML
    @Override
    public void newPatient(MouseEvent event) {

        new FullScene().displayWindow("/views/newPatient.fxml",
                new PatientModule());

    }
}
