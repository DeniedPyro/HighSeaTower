import Controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    GameController gameController;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gameController = new GameController(primaryStage);
        gameController.buildView();
    }
}
