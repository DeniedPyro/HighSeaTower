import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;

    private ArrayList<Plateforme> plateformes = new ArrayList<Plateforme>();
    private Medusa medusa;
    private double screenAy = 2;
    private double screenVy = 50;

    // Origine de la fenêtre
    public static double windowY = 0;

    public Jeu() {
        for (int i = 0; i < 4; i++) {
            plateformes.add(new Plateforme(0, 380 - i * 100));
        }

        medusa = new Medusa(WIDTH/2-25, HEIGHT-50);
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

    public void stop(){
        medusa.stop();
    }

    public void update(double dt) {
        double vy,ay,y;
        boolean createPlatform = false;
        if (medusa.hasMoved()){

            screenVy += dt * screenAy;
            windowY -= dt * screenVy;

        }
        /**
         * À chaque tour, on recalcule si le personnage se trouve parterre ou
         * non
         */
        //if ((int)(-windowY) % 100 == 0 && windowY < 0){
        //    createPlatform = true;
        //}
        //if (createPlatform) {
        //    plateformes.add(new Plateforme(0,plateformes.get(plateformes.size() - 1).y));
        //    createPlatform = !createPlatform;
        //}
        //if (plateformes.get(0).y < HEIGHT + windowY) {
        //    plateformes.remove(0);
        //}
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

        medusa.draw(context,windowY);
        for (Plateforme p : plateformes) {
            p.draw(context, windowY);
        }

    }
}

