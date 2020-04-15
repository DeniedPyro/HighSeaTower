import javafx.scene.canvas.GraphicsContext;

class Platform extends Entity {

    
    /** 
     * @param context
     */
    @Override
    public void draw(GraphicsContext context) {
        context.setFill(color);
        context.fillRect(x, y, largeur, hauteur);
    }

    
    /** 
     * @param context
     * @param windowY
     */
    public void draw(GraphicsContext context, double windowY) {

        double yAffiche = this.y - Jeu.windowY;

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
