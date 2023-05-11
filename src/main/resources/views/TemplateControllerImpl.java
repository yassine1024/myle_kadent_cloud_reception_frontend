package views;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;




public class TemplateControllerImpl implements TemplateController{

    @FXML
    private JFXButton appointementMenu;

    @FXML
    private StackPane centerStackPane;

    @FXML
    private JFXButton configMenu;

    @FXML
    private JFXButton dashboardMenu;

    @FXML
    private Label label;

    @FXML
    private JFXButton paymentMenu;

    @FXML
    private JFXButton queueMenu;

    @FXML
    private AnchorPane slider;

    @FXML
    private AnchorPane slider1;

    @FXML
    private Label tasksLabel;


    @Override
    @FXML
    public void showDashboard(ActionEvent event) {
        
    }
}
