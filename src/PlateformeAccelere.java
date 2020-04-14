import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlateformeAccelere extends Platform {

    double speedEffect;
    public PlateformeAccelere(int largeur, int x, double y) {

        this.x = x;
        this.y = y;
        this.largeur = largeur;
        this.hauteur = 10;
        this.color = Color.rgb(230, 221, 58) ;
        this.speedEffect = 0;
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
    public void giveEffect(Jeu jeu,Medusa m){
        if (m.getParterre()){
            m.vy = 0;
        }
        
        if ( (m.getParterre() && this.speedEffect == 0) ||  (m.getParterre() && this.speedEffect == jeu.getScreenVy())) {
            this.speedEffect = jeu.getScreenVy()*3;
            jeu.setScreenVy(this.speedEffect);
        }
    }

    @Override
    public void cancelEffect(Jeu jeu,Medusa m){
        if (this.speedEffect != 0 && !m.getParterre()){
        jeu.setScreenVy(this.speedEffect/3);
        this.speedEffect = 0 ;
        }
    }


}

