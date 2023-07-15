package com.example.lab08;

// Main Class Starts
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CarMainApp extends Application {
    @Override
    // Start Method to run the javaFX Application
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CarView.fxml"));
        AnchorPane root = loader.load();

        CarViewController controller = loader.getController();
        controller.initialize();

        // Setting the scene to the dimensions
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Car Lot");
        primaryStage.getIcons().add(new Image("car.png"));
        primaryStage.show();
    }

    // launching the application
    public static void main(String[] args) {
        launch(args);
    }
}
// main Class Ends