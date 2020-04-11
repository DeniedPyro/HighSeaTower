
import javafx.scene.canvas.GraphicsContext;


public class Controller {

    Jeu jeu;

    public Controller() {
        jeu = new Jeu();
    }

    void draw(GraphicsContext context) {
        jeu.draw(context);
    }

    void update(double deltaTime) {
        jeu.update(deltaTime);
    }

    void jump() {
        jeu.jump();
    }
}
