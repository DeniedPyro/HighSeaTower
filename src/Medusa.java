import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.awt.*;

public class Medusa extends Entity {
    private Image[][] frames;
    private Image image;
    private double frameRate = 8; // 8 frames par sec
    private double tempsTotal = 0;
    public boolean isAlive = true;
    private boolean parterre;
    private boolean moved;

    public Medusa(double x, double y) {
        this.x = x;
        this.y = y;
        this.largeur = 50;
        this.hauteur = 50;
        this.ay = 1200;
        this.ax = 0;
        this.vx = 0;
        this.vy = 0 ;
        this.parterre = true;

        // Chargement des images
        frames = new Image[][]{{
                new Image("images/jellyfish1.png"),
                new Image("images/jellyfish2.png"),
                new Image("images/jellyfish3.png"),
                new Image("images/jellyfish4.png"),
                new Image("images/jellyfish5.png"),
                new Image("images/jellyfish6.png")
        },{
                new Image("images/jellyfish1g.png"),
                new Image("images/jellyfish2g.png"),
                new Image("images/jellyfish3g.png"),
                new Image("images/jellyfish4g.png"),
                new Image("images/jellyfish5g.png"),
                new Image("images/jellyfish6g.png")
        }};
        image = frames[0][0];
    }




    /** Permet de mettre à jour la position,vitesse et acceleration de la meduse
     * Permet aussi de choisir l'image de la meduse par rapport
     * à un framerate et au temps
     * @param dt
     */
    @Override
    public void update(double dt) {
        // Physique du personnage
        super.update(dt);

        // Mise à jour de l'image affichée
        tempsTotal += dt;
        int frame = (int) (tempsTotal * frameRate);
        if(this.direction){
            image = frames[0][frame % frames[0].length];
        }
        else{
            image = frames[1][frame % frames[1].length];
        }
    }


    /**
     * La collision avec une plateforme a lieu seulement si :
     *
     * - Il y a une intersection entre la plateforme et le personnage
     *
     * - La collision a lieu entre la plateforme et le *bas du personnage*
     * seulement
     *
     * - La vitesse va vers le bas (le personnage est en train de tomber,
     * pas en train de sauter)
     * @param other
     * @return boolean
     */
    public boolean collides(Platform other) {

        if (intersects(other) && Math.abs(this.y + hauteur - other.y) > 10 && this.vy < 0){
            return true;
        }
        if (intersects(other) && Math.abs(this.y + hauteur - other.y) < 10
                && vy > 0 ) {
            pushOut(other);
            //this.vy = 0;
            this.parterre = true;
            return true;
        }
        else {
            return false;
        }

    }


    /** Regarde si le carré représentant meduse intersecte
     * une plateforme
     * @param other
     * @return boolean
     */
    public boolean intersects(Platform other) {
        return !( // Un des carrés est à gauche de l’autre
                x + largeur < other.x
                        || other.x + other.largeur < this.x
                        // Un des carrés est en haut de l’autre
                        || y + hauteur < other.y
                        || other.y + other.hauteur < this.y
        );
    }

    /** Repousse le personnage vers le haut (sans déplacer la
     * plateforme)
     */
    public void pushOut(Platform other) {
        double deltaY = this.y + this.hauteur - other.y;
        this.y -= deltaY;
    }

    /** Permet de dessiner la meduse à la position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        context.drawImage(image, x, y, largeur, hauteur);
    }


    /** Permet de dessiner la meduse à une position variable Y
     * @param context
     * @param windowY
     */
    public void draw(GraphicsContext context,double windowY) {

        double yAffiche = this.y - windowY;

        context.drawImage(image, x, yAffiche, largeur, hauteur);

    }
    /** Setter de moved
     * @param moved
     */
    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    /** Getter de moved
     * @return boolean
     */
    public boolean hasMoved(){
        return this.moved;
    }

    /** Setter de parterre
     * @param parterre
     */
    public void setParterre(boolean parterre) {
        this.parterre = parterre;
    }

    /** Getter de parterre
     * @return boolean
     */
    public boolean getParterre() {
        return this.parterre;
    }


    /** Permet de setter la direction de la meduse à gauche
     *
     */
    public void lookingLeft(){
        this.direction = false;
    }
    /** Permet de setter la direction de la meduse à droite
     *
     */
    public void lookingRight(){
        this.direction = true;
    }
    /** La meduse peut seulement sauter si elle se trouve sur une
     * plateforme
     */
    public void jump() {
        if (!this.moved){
            setMoved(true);
        }
        if (this.parterre) {
            vy = -600;
            setParterre(false);
        }
    }
    /** Permet a la meduse de bouger à gauche
     *
     */
    public void moveLeft(){
        if (!this.moved){
            setMoved(true);
        }
        this.ax = -1200;
        lookingLeft();
    }
    /** Permet a la meduse de bouger à droite
     *
     */
    public void moveRight(){
        if (!moved){
            setMoved(true);
        }
        this.ax = 1200;
        lookingRight();
    }
    /** Arrete le mouvement de la meduse
     *
     */
    public void stop(){
        this.vx = 0;
        this.ax = 0;
    }
} 