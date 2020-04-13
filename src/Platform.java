import javafx.scene.canvas.GraphicsContext;

class Platform extends Entity {

    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

    public void draw(GraphicsContext context, double windowY) {

        double yAffiche = this.y - Jeu.windowY;

        context.setFill(color);
        context.fillRect(x, yAffiche, largeur, hauteur);

    }


    public void giveEffect(Jeu j , Medusa m){
        ;
    }

    public void cancelEffect(Jeu j , Medusa m){
        ;
    }


}
