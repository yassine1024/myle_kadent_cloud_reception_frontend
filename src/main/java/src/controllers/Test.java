package src.controllers;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Test extends Application {

	
	private ConfigurableApplicationContext applicationContext;
	
	
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("Hi from Test:init()");
		applicationContext = new SpringApplicationBuilder(Main.class).run();
	}

	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		applicationContext.close();
		Platform.exit();
	}

	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Hi from Test:start()");
		//applicationContext.publishEvent(new StageReadyEvent(primaryStage));
		applicationContext.publishEvent(new StageReadyEvent(primaryStage));
		
		/*StackPane root = new StackPane();
        root.getChildren();
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
		*/
		String ifLoged="/com/example/kadent/nouvel article.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(ifLoged));
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		Scene scene = new Scene(root);
		 primaryStage.setScene(scene);
	        primaryStage.show();
	        
	}

}
