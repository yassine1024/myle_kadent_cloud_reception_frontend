package views;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;


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
    public void showDashboard(ActionEvent event)  {

        this.switchBetweenViews("/views/dashboard.fxml");

    }


    @Override
    @FXML
    public void showQueue(ActionEvent event) {
        this.switchBetweenViews("/views/salle_attente.fxml");
    }


    private void switchBetweenViews(String pathFile){

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathFile));
        AnchorPane centerView=null;
        try {
            centerView = loader.load();

        }catch (IOException exception){

            System.out.println(exception.getMessage());
        }
        this.updateView(centerView);
    }

    private void updateView(AnchorPane centerView) {

        if(!centerStackPane.getChildren().isEmpty()) {
            centerStackPane.getChildren().remove(0);
        }
        centerStackPane.getChildren().add(centerView);
    }


}
