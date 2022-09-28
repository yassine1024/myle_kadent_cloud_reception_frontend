package com.example.kadent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private BorderPane parent;
    @FXML
    private Button btnMode;
    @FXML
    private ImageView imgMode;

    private boolean isLightMode = true;

    public void changeMode(ActionEvent event){
        isLightMode = !isLightMode;
        if(isLightMode){
            setLightMode();
        }else {
            setDarkMode();
        }
    }

    private void setLightMode(){
        parent.getStylesheets().remove("com.example.kadent/style.css");
        parent.getStylesheets().add("com.example.kadent/style.css");
        Image image = new Image("lightmode.png");
        imgMode.setImage(image);
    }

    private void setDarkMode(){
        parent.getStylesheets().remove("com.example.kadent/styleDark.css");
        parent.getStylesheets().add("com.example.kadent/styleDark.css");
        Image image = new Image("img/darktmode.png");
        imgMode.setImage(image);
    }
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}