package com.example.ecommerce;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecommerce extends Application {

    UserInterface ui = new UserInterface();
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(ui.createContent());
        stage.setTitle("E-commerce");
        stage.setMinWidth(1000);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}