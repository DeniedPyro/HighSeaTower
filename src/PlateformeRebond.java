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

    private void resetColor(){
        this.color = Color.LIGHTGREEN ;
    }

    
    /** permet de dessiner la plateforme
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        super.draw(context);
    }

    /** donner un effet à la meduse si elle est sur la plateforme:
     * donner une vitesse y à la meduse
     * @param j
     * @param m
     */
    @Override
    public void giveEffect( Jeu j ,Medusa m){

        if(m.getParterre() && j.getDebug()){
            this.color = Color.YELLOW;
        }

        if(!j.getDebug()){
            this.resetColor();
        }

        if(m.getParterre()){
            double v = m.vy *1.5;
                if (v < 750){
                m.vy = -750;
            }
            else {
                m.vy = -v;
            }
            m.setParterre(false);
        }
    }

    
    /** annuler les effets recus par la meduse au moment ou elle sort de la collision avec la plateforme
     * @param j
     * @param m
     */
    @Override
    public void cancelEffect( Jeu j, Medusa m){
        if(!this.color.equals(color.LIGHTGREEN)){
            this.resetColor();
        }
    }

}
