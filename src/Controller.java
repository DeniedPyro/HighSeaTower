
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

    void updatePosition(Text position){
        position.setText( "Position = "+ "("+(int)jeu.getMedusa().x+", "+(int)jeu.getMedusa().y+")");
    }

    void updateSpeed(Text speed){
        speed.setText( "v = "+ "("+(int)jeu.getMedusa().vx+", "+(int)jeu.getMedusa().vy+")");
    }

    void updateAcc(Text acc){
        acc.setText( "v = "+ "("+(int)jeu.getMedusa().ax+", "+(int)jeu.getMedusa().ay+")");
    }
    void updateGround(Text ground){
        String etat = "";

        if (jeu.getMedusa().getParterre() == true){
            etat="oui";
        }
        else {
            etat = "non";
        }
        ground.setText( "Touche au sol : "+ etat);
    }

    void resetJeu(){
        jeu.resetJeu();
    }


}
