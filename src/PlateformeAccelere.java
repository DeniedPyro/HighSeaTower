import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeAccelere extends Platform {

    double speedEffect;
    public PlateformeAccelere(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;
        this.color = Color.rgb(230, 221, 58) ;
        this.speedEffect = 0;
    }

    private void resetColor(){
        this.color = Color.rgb(230, 221, 58);
    }

    
    /** permet de dessiner la plateforme avec une position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }

    /** donner un effet à la meduse si elle est sur la plateforme:
     * multiplier la vitesse de l'ecran par 3
     * @param j
     * @param m
     */
    @Override
    public void giveEffect(Jeu j,Medusa m){

        if(m.getParterre() && j.getDebug()){
            this.color = Color.YELLOW;
        }

        if(!j.getDebug()){
            this.resetColor();
        }


        if (m.getParterre()){
            m.vy = 0;
        }
        
        if ( (m.getParterre() && this.speedEffect == 0) ||  (m.getParterre() && this.speedEffect == j.getScreenVy())) {
            this.speedEffect = j.getScreenVy()*3;
            j.setScreenVy(this.speedEffect);
        }
    }

    
    /** annuler les effets recus par la meduse au moment ou elle sort de la collision avec la plateforme
     * @param jeu
     * @param m
     */
    @Override
    public void cancelEffect(Jeu jeu,Medusa m){
        Color yellow = Color.rgb(230, 221, 58) ;
        if(!this.color.equals(yellow)){
            resetColor();
        }

        if (this.speedEffect != 0 && !m.getParterre()){
            jeu.setScreenVy(this.speedEffect/3);
            this.speedEffect = 0 ;
        }
    }


}

