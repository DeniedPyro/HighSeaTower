import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.File;

public class HighSeaTower extends Application {

    public static final int WIDTH = 350, HEIGHT = 480;
    String pathMusic = "src/OST/Floaties-K4Z-Remix.mp3";
    String pathJump = "src/OST/jump.mp3";
    Media mediaMusic = new Media(new File(pathMusic).toURI().toString());
    Media mediaJump = new Media(new File(pathJump).toURI().toString());
    private MediaPlayer mediaPlayMusic = new MediaPlayer(mediaMusic);
    private MediaPlayer mediaPlayJump = new MediaPlayer(mediaJump);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    /** 
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);

        Text position = new Text("Position =  ");
        position.setFill(Color.rgb(255,255,255));
        position.setFont(Font.font(10));
        Text v = new Text("v =  ");
        v.setFill(Color.rgb(255,255,255));
        v.setFont(Font.font(15));
        Text a = new Text("a =  ");
        a.setFont(Font.font(15));
        a.setFill(Color.rgb(255,255,255));
        position.setFont(Font.font(15));
        Text ground = new Text("Touche le sol : ");
        ground.setFont(Font.font(15));
        ground.setFill(Color.rgb(255,255,255));


        VBox vbox = new VBox(position,v,a,ground);
        v.setX(166);



        Text distance = new Text("0 m");
        distance.setFill(Color.rgb(255,255,255));
        distance.setX((WIDTH/2)-30);
        distance.setFont(Font.font(30));
        distance.setY(40);
        VBox vbox2 = new VBox(distance);
        vbox2.setAlignment(Pos.CENTER);

        root.getChildren().addAll(canvas,distance,vbox,vbox2);

        GraphicsContext context = canvas.getGraphicsContext2D();

        Controller controller = new Controller();
        scene.setOnKeyPressed((value) -> {
            if (value.getCode() == KeyCode.SPACE || value.getCode() == KeyCode.UP) {
                controller.jump();
                mediaPlayJump.play();

            }

            if (value.getCode() == KeyCode.LEFT) {
                controller.moveLeft();
            }

            if (value.getCode() == KeyCode.RIGHT) {
                controller.moveRight();
            }
            if (value.getCode() == KeyCode.T) {
                controller.toggleDebug();
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

                if (controller.getDebug()){
                    vbox.setVisible(true);
                    controller.updatePosition(position);
                    controller.updateSpeed(v);
                    controller.updateAcc(a);
                    controller.updateGround(ground);
                }
                else if (!controller.getDebug() && vbox.isVisible()){
                    vbox.setVisible(false);
                }

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

        mediaPlayMusic.setVolume(0.04);
        mediaPlayMusic.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayMusic.play();
    }
}