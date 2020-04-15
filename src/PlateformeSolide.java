import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeSolide extends Platform{

    public PlateformeSolide(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;
        this.color = Color.rgb(184, 15, 36);
    }

    private void resetColor(){
        this.color = Color.rgb(184, 15, 36); ;
    }

    
    /** permet de dessiner la plateforme
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }

    
    /**
     * @param j
     * @param m
     */
    @Override
    public void giveEffect(Jeu j , Medusa m){
        if(m.getParterre() && j.getDebug()){
            this.color = Color.YELLOW;
        }
        if(!j.getDebug()){
            resetColor();
        }
        m.vy = 0 ;

    }

    
    /** reset la couleur de la plateforme en mode debug
     * @param j
     * @param m
     */
    @Override
    public  void  cancelEffect(Jeu j, Medusa m){
        Color red = Color.rgb(184, 15, 36);
        if (!this.color.equals(red)){
            this.resetColor();
        }
    }

}
