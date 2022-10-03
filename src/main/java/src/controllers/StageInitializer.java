package src.controllers;

import java.io.IOException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

	/*private final String applicationTitle;
	private final Resource fxml;
	private final ApplicationContext ac;
	private final String link = "/src/main/resources/com/example/kadent";

*/
	public StageInitializer() {
		// TODO Auto-generated constructor stub
		System.out.println("Hi from StageInitializer:construct");
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		
		/*Stage stage = event.getStage();
		URL url = this.fxml.getClass().getResource(this.link+"/login.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(url);
		//this is lambda
		//fxmlLoader.setControllerFactory(param -> ac.getBean(param));
		//replace lambda with methode reference
		fxmlLoader.setControllerFactory(ac::getBean);
		try {
			Parent root = fxmlLoader.load();
			Scene scene =  new Scene(root);
			stage.setScene(scene);
			stage.setTitle(this.applicationTitle);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

}
