


import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import presenters.RendezvousModule;
import presenters.RendezvousPresenter;
import views.RendezvousController;


public class Main extends Application {


    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create an undecorated stage

        primaryStage.initStyle(StageStyle.UNDECORATED);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/template.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
