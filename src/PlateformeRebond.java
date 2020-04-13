import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeRebond extends Platform {

        public PlateformeRebond(int largeur, int x, double y) {

            this.x = x;
            this.y = y;
            this.largeur = largeur;
            this.hauteur = 15;

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

    public void giveFffect(Medusa m){
        double v = m.vy *1.5;

        if (v < 100){
            m.y = -100;
        }
        else {
            m.y = -v;
        }
    }
}

