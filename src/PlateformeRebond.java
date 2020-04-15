import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeRebond extends Platform {

    public PlateformeRebond(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;

        this.color = Color.LIGHTGREEN;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

    public void draw(GraphicsContext context,double windowY) {

        double yAffiche = this.y - Jeu.windowY;

        context.setFill(color);
        context.fillRect(x, yAffiche, largeur, hauteur);

    }
    @Override
    public void giveEffect( Jeu j ,Medusa m){

        if(m.getParterre() && j.getDebug()){
            this.color = Color.YELLOW;
        }

        if(m.getParterre()){
            double v = m.vy *1.5;
                if (v < 750){
                m.vy = -750;
            }
            else {
                m.vy = -v;
            }
        }
    }

    @Override
    public void cancelEffect( Jeu j, Medusa m){
        if(!this.color.equals(color.LIGHTGREEN)){
            this.color = Color.LIGHTGREEN;
        }

    }

}

