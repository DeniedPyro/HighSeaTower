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
        System.out.println(m.vy);
        if (m.vy < 0){
            m.vy = 0;
            m.pushDown(this);
        }
        else {
            m.pushOut(this);
        }
    }

}
