package Controller;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {

    public boolean mvLeft, mvRight, jump = false;

    public Controller(Stage stage) {

        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        jump = true;
                        break;
                    case LEFT:
                        mvLeft = true;
                        break;
                    case RIGHT:
                        mvRight = true;
                        break;
                    case SPACE:
                        jump = true;
                        break;
                }
            }
        });

        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        jump = false;
                        break;
                    case LEFT:
                        mvLeft = false;
                        break;
                    case RIGHT:
                        mvRight = false;
                        break;
                    case SPACE:
                        jump = false;
                        break;
                }
            }
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (jump) System.out.println("Jump");
                if (mvLeft) System.out.println("Moving Left");
                if (mvRight) System.out.println("Moving Right");

            }
        };
        timer.start();
    }



}






