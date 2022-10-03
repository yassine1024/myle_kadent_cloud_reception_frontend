package src.controllers;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageReadyEvent extends ApplicationEvent {

	 /**
	 * 
	 */

	public  StageReadyEvent(Stage stage) {
		super(stage);
		System.out.println("Hi from StageReadyEvent:construct()");
		// TODO Auto-generated constructor stub
	}

	public Stage getStage() {
		// TODO Auto-generated method stub
		return ((Stage) getSource());
	}

}
