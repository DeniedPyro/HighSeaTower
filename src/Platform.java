import javafx.scene.canvas.GraphicsContext;

class Platform extends Entity {

    
    /** permet de dessiner la plateforme avec une position initiale
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        double yAffiche = y - Jeu.windowY;

        context.setFill(color);
        context.fillRect(x, yAffiche, largeur, hauteur);
    }

    /** 
     * @param j
     * @param m
     */
    public void giveEffect(Jeu j , Medusa m){
        ;
    }

    
    /** 
     * @param j
     * @param m
     */
    public void cancelEffect(Jeu j , Medusa m){
        ;
    }


}
