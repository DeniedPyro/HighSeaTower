import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Jeu {

    public static final int WIDTH = 640, HEIGHT = 320;

    private Plateforme[] plateformes = new Plateforme[5];
    private Medusa medusa;

    public Jeu() {
        for (int i = 0; i < plateformes.length; i++) {
            plateformes[i] = new Plateforme((double) i / plateformes.length * WIDTH, Math.random() * HEIGHT);
        }

        medusa = new Medusa(10, 10);
    }

    public void jump() {
        medusa.jump();
    }

    public void moveLeft() {
        medusa.moveLeft();
    }

    public void moveRight() {
        medusa.moveRight();
    }

    public void update(double dt) {
        /**
         * À chaque tour, on recalcule si le personnage se trouve parterre ou
         * non
         */
        medusa.setParterre(false);

        for (Plateforme p : plateformes) {
            p.update(dt);
            // Si le personnage se trouve sur une plateforme, ça sera défini ici
            medusa.testCollision(p);
        }
        medusa.update(dt);
    }

    public void draw(GraphicsContext context) {
        context.setFill(Color.CORNFLOWERBLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        medusa.draw(context);
        for (Plateforme p : plateformes) {
            p.draw(context);
        }
    }
}

