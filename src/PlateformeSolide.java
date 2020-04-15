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

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

    @Override
    public void giveEffect(Jeu j , Medusa m){
        if(m.getParterre() && j.getDebug()){
            this.color = Color.YELLOW;
        }
        m.vy = 0 ;
    }

    @Override
    public  void  cancelEffect(Jeu j, Medusa m){
        Color red = Color.rgb(184, 15, 36);
        if (!this.color.equals(red)){
            this.color = red ;
        }
    }

}
