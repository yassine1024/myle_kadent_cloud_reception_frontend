package Config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class FullScene {



    public  void fullScene(Parent root, Stage stage){
// Hide the original stage
        stage.hide();

        // Create a new stage
        Stage newStage = new Stage();
        Scene scene = new Scene(root);

        // Set the scene to the new stage
        newStage.setScene(scene);

        // Maximize the window
        newStage.setMaximized(true);

        newStage.initStyle(StageStyle.DECORATED);
        // Show the new stage
        newStage.show();

    }

    public    void displayWindow(String pathFXML, AbstractModule module){///function dayerha les choses li yat3wdou

        Injector injector = Guice.createInjector(module);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(pathFXML));
        loader.setControllerFactory(injector::getInstance);

        AnchorPane centerView=null;
        try {
            centerView = loader.load();

        }catch (IOException exception){

            System.out.println(exception.getMessage());
        }

        // Create a new stage
        Stage newStage = new Stage();
       Scene scene = new Scene(centerView);

        // Set the scene to the new stage
        newStage.setScene(scene);


        newStage.initStyle(StageStyle.DECORATED);
        // Show the new stage
        newStage.showAndWait();

    }
}
