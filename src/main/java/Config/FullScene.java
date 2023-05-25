package Config;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
}
