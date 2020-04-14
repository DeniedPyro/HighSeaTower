import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bubble extends Entity{

    private double x, y;
    private double r;

    private double vx = 0 ;
    private double vy;

    // Gravité
    private double ax = 0, ay = 0;

    public Bubble(double x, double y, double r , double vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vy = vy;
    }

    /**
     * Met à jour la position et la vitesse de la balle
     *
     * @param dt Temps écoulé depuis le dernier update() en secondes
     */

    @Override
    public void update(double dt) {
        vx += dt * ax;
        vy += dt * ay;

        x += dt * vx;
        y += dt * vy;

        if (x + r > HighSeaTower.WIDTH || x - r < 0) {
            vx *= -0.5;
        }
        if (y + r > HighSeaTower.HEIGHT || y - r < 0) {
            vy *= -0.5;
        }

        x = Math.min(x, HighSeaTower.WIDTH - r);
        x = Math.max(x, r);

        y = Math.min(y, HighSeaTower.HEIGHT - r);
        y = Math.max(y, r);
    }

    /**
     * Indique s'il y a intersection entre les deux balles
     *
     * @param other
     * @return true s'il y a intersection
     */
    public boolean intersects(Bubble other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double d2 = dx * dx + dy * dy;
        return d2 < (this.r + other.r) * (this.r + other.r);
    }

    /**
     * Interchange les vitesses s'il y a collision entre les deux balles
     *
     * @param other l'autre balle
     */
    public void testCollision(Bubble other) {
        if (this.intersects(other)) {
            double vx = this.vx;
            double vy = this.vy;

            this.vx = other.vx * 0.9;
            this.vy = other.vy * 0.9;

            other.vx = vx * 0.9;
            other.vy = vy * 0.9;

            // Calculer la quantité qui overlap en X, same en Y
            // Déplacer les deux de ces quantités/2
            double dx = other.x - this.x;
            double dy = other.y - this.y;
            double d2 = dx * dx + dy * dy;
            double d = Math.sqrt(d2);

            // Overlap en pixels
            double overlap = d - (this.r + other.r);

            // Direction dans laquelle se déplacer (normalisée)
            double directionX = dx / d;
            double directionY = dy / d;

            double deplacementX = directionX * overlap / 2;
            double deplacementY = directionY * overlap / 2;

            this.x += deplacementX;
            this.y += deplacementY;
            other.x -= deplacementX;
            other.y -= deplacementY;
        }
    }

    public void updateAcc(double x, double y) {
        ax = x - this.x;
        ay = y - this.y;
        double norm = Math.sqrt(ax * ax + ay * ay);
        ax *= 1000/norm;
        ay *= 1000/norm;
    }

    @Override
    public void draw(GraphicsContext context) {
        ;
    }

    public void draw(GraphicsContext context, double windowY) {

        double yAffiche = this.y - windowY;

        context.fillOval(this.getX(),this.getY(),
                this.getW(), this.getH());
        context.setFill(Color.rgb(0, 0, 255, 0.4));

}


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getW() {
        return 2 * r;
    }

    public double getH() {
        return 2 * r;
    }
}
