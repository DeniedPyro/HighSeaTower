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

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

    private void resetColor(){
        Color orange = Color.rgb(230, 134, 58);
        this.color = orange ;
    }

    public void draw(GraphicsContext context,double windowY) {

        double yAffiche = this.y - Jeu.windowY;

        context.setFill(color);
        context.fillRect(x, yAffiche, largeur, hauteur);
    }

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
    @Override
    public  void  cancelEffect(Jeu j, Medusa m){
        Color orange = Color.rgb(230, 134, 58);
        if (!this.color.equals(orange)){
            this.resetColor();
        }
    }

}