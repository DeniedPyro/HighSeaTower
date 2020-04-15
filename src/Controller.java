
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Text;


public class Controller {

    Jeu jeu;

    public Controller() {
        jeu = new Jeu();
    }

    
    /** Call la methode draw de jeu
     * @param context
     */
    void draw(GraphicsContext context) {
        jeu.draw(context);
    }

    
    /** call la methode update de jeu puis reset le jeu si la meduse est morte
     * @param deltaTime
     */
    void update(double deltaTime) {
        jeu.update(deltaTime);
        if(!jeu.getMedusa().isAlive){
            resetJeu();
        }
    }

    /** call la methode jump de jeu
     *
     */
    void jump() {
        jeu.jump();
    }

    /** call la methode moveLeft du jeu
     *
     */
    void moveLeft() {
        jeu.moveLeft();
    }

    /** call la methode moveRight du jeu
     *
     */
    void moveRight() {
        jeu.moveRight();
    }

    /** call la methode stop du jeu
     *
     */
    void stop() {
        jeu.stop();
    }

    /** permet de toggle le mode Debug
     *
     */
    void toggleDebug() {
        jeu.setDebug(!jeu.getDebug());
    }

    
    /** ajouter le texte de distance parcourue
     * @param distance
     */
    void updateDistance(Text distance){
        distance.setText((int)Math.abs(Jeu.windowY )+ "m");
    }

    
    /** ajouter le texte de position de la meduse
     * @param position
     */
    void updatePosition(Text position){
        position.setText( "Position = "+ "("+(int)jeu.getMedusa().x+", "+((int)(-jeu.getMedusa().y+Jeu.windowY)+Jeu.HEIGHT)+")");
    }

    
    /** ajouter le texte de vitesse de la meduse
     * @param speed
     */
    void updateSpeed(Text speed){
        speed.setText( "v = "+ "("+(int)jeu.getMedusa().vx+", "+(-(int)jeu.getMedusa().vy+")"));
    }

    
    /** ajouter le texte d'acceleration de la meduse
     * @param acc
     */
    void updateAcc(Text acc){
        acc.setText( "v = "+ "("+(int)jeu.getMedusa().ax+", "+(-(int)jeu.getMedusa().ay)+")");
    }
    
    /** ajouter le texte d'etat de la meduse
     * @param ground
     */
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

    /** permet de reset le jeu
     *
     */
    void resetJeu(){
        jeu.resetJeu();
    }

}
