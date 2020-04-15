
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;


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
        if(!jeu.getMedusa().isAlive){
            resetJeu();
        }
    }

    void jump() {
        jeu.jump();
    }

    void moveLeft() {
        jeu.moveLeft();
    }

    void moveRight() {
        jeu.moveRight();
    }
    
    void stop() {
        jeu.stop();
    }

    void toggleDebug() {
        jeu.setDebug(!jeu.getDebug());
    }

    void updateDistance(Text distance){
        distance.setText((int)Math.abs(Jeu.windowY )+ "m");
    }

    void resetJeu(){
        jeu.resetJeu();
    }


}
