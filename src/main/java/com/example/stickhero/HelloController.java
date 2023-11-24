package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloController {
    @FXML
    private Line stick;

    @FXML
    private Button upButton;

    private double start_y;
    private double end_y;
    private Parent root;
    private Stage stage;
    private Scene scene;

    private boolean isButtonPressed = false;

    private AnimationTimer animationTimer;

    public HelloController() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                up(null);
            }
        };
    }
    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//    public void switchToScene2(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    @FXML
    public void handleUpButtonPressed() {
        // Start AnimationTimer when the button is pressed
        isButtonPressed = true;
        animationTimer.start();
    }

    @FXML
    public void handleUpButtonReleased() throws IOException {
        // Stop AnimationTimer when the button is released
        isButtonPressed = false;
        animationTimer.stop();

        // Switch to the new scene
        switchToScene2();
    }

    private void switchToScene2() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) upButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    private void up(ActionEvent e) {
        // Only adjust the stick position if the button is pressed
        if (isButtonPressed) {
            start_y = stick.getStartY();
            end_y = stick.getEndY();
            stick.setStartY(start_y - 1);
            stick.setEndY(end_y - 1);
        }
    }
}