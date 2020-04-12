import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Plateforme extends Entity {

    public Plateforme(double x, double y) {
        Random widthR = new Random();
        this.x = x;
        this.y = y;
        this.largeur = widthR.nextInt(96) + 80;
        this.hauteur = 15;

        this.color = Color.DARKORCHID;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

    public void draw(GraphicsContext context,double windowY) {

        double yAffiche = this.y - windowY;

        context.setFill(color);
        context.fillRect(x, yAffiche, largeur, hauteur);

    }
}