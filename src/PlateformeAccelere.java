import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeAccelere extends Platform {

    double speedEffect;
    double accelerateEffect;
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

    
    /** 
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

    
    /** 
     * @param context
     * @param windowY
     */
    public void draw(GraphicsContext context,double windowY) {

        double yAffiche = this.y - Jeu.windowY;

        context.setFill(color);
        context.fillRect(x, yAffiche, largeur, hauteur);
    }

    
    /** 
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

    
    /** 
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

