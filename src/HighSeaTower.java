import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.application.Platform;

public class HighSeaTower extends Application {

    public static final int WIDTH = 350, HEIGHT = 480;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        Text distance = new Text("0 m");
        distance.setFill(Color.rgb(255,255,255));
        distance.setX((WIDTH/2)-40);
        distance.setFont(Font.font(40));
        distance.setY(40);

        root.getChildren().addAll(canvas,distance);
        distance.setTextAlignment(TextAlignment.CENTER);

        GraphicsContext context = canvas.getGraphicsContext2D();

        Controller controller = new Controller();

        scene.setOnKeyPressed((value) -> {
            if (value.getCode() == KeyCode.SPACE || value.getCode() == KeyCode.UP  ) {
                controller.jump();
            }

            if (value.getCode() == KeyCode.LEFT) {
                controller.moveLeft();
            }

            if (value.getCode() == KeyCode.RIGHT) {
                controller.moveRight();
            }
            if (value.getCode() == KeyCode.ESCAPE) {
                Platform.exit();
            }
        });
        scene.setOnKeyReleased((value) -> {
            if (value.getCode() == KeyCode.LEFT) {
                controller.stop();
            }

            if (value.getCode() == KeyCode.RIGHT) {
                controller.stop();
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            private long lastTime = 0;
            private final double maxDt = 0.01;

            @Override
            public void handle(long now) {
                if (lastTime == 0) {
                    lastTime = now;
                    return;
                }

                double deltaTime = (now - lastTime) * 1e-9;
                while (deltaTime > maxDt) {
                    controller.update(maxDt);
                    deltaTime -= maxDt;
                }
                controller.update(deltaTime);
                controller.updateDistance(distance);
                controller.draw(context);

                lastTime = now;
            }
        };
        timer.start();

        primaryStage.setScene(scene);
        primaryStage.setTitle("High Sea Tower");
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("/images/jellyfish1.png"));
    }
}