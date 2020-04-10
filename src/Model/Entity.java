package Model;

public abstract class Entity {
    protected double x, y;
    protected int hauteur,largeur;
    protected double vx, vy;
    protected double ax = 0, ay = 200;

    public double getX() { return x; }
    public double getY() { return y; }
    public abstract double getW();
    public abstract double getH();

    public boolean intersects(Entity other) {
        return !(
        // Un des carrés est à gauche de l’autre
        x + largeur / 2 < other.x - other.largeur / 2
        || other.x + other.largeur / 2 < this.x - this.largeur / 2
        // Un des carrés est en haut de l’autre
        || y + largeur / 2 < other.y - other.largeur / 2
        || other.y + other.largeur / 2 < this.y - largeur / 2);
    }

}
