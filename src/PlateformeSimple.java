import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class PlateformeSimple extends Platform {

    public PlateformeSimple(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;

        this.color = Color.rgb(230, 134, 58);
    }

    private void resetColor(){
        Color orange = Color.rgb(230, 134, 58);
        this.color = orange ;
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
            this.resetColor();
        }

        if (m.getParterre()) {
            m.vy = 0;
        }
    }
    
    /** 
     * @param j
     * @param m
     */
    @Override
    public  void  cancelEffect(Jeu j, Medusa m){
        Color orange = Color.rgb(230, 134, 58);
        if (!this.color.equals(orange)){
            this.resetColor();
        }
    }

}