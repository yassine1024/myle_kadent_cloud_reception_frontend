package views;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import presenters.RendezvousModule;
import presenters.TemplateModule;

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

        this.switchBetweenViews("/views/dashboard.fxml", new TemplateModule());

    }


    @Override
    @FXML
    public void showQueue(ActionEvent event) {
        this.switchBetweenViews("/views/salle_attente.fxml", new TemplateModule());
    }


    @Override
    @FXML
    public void showRendezvous(ActionEvent event) {

        this.switchBetweenViews("/views/rendezvous.fxml", new RendezvousModule());
    }


    private void switchBetweenViews(String pathFile, AbstractModule module){

        Injector injector = Guice.createInjector(module);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathFile));
        loader.setControllerFactory(injector::getInstance);
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
