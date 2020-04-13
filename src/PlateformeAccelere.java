import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeAccelere extends Platform {

    public PlateformeAccelere(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;
        this.color = Color.rgb(230, 221, 58) ;
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

    public void giveEffect(Jeu jeu){
        jeu.setScreenVy(jeu.getScreenVy()*3);
    }




}

