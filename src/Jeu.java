import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;

    private ArrayList<Plateforme> plateformes = new ArrayList<Plateforme>();
    private Medusa medusa;
    private double screenAy = 2;
    private double screenVy = 50;

    // Origine de la fenêtre
    public static double windowY = 0.0;

    public Jeu() {
        for (int i = 0; i < 4; i++) {
            Random R = new Random();
            Random posX = new Random();
            int widthX = R.nextInt(81) + 95;
            int X = posX.nextInt(WIDTH - widthX + 1);
            plateformes.add(new Plateforme(widthX,X,365 - i * 115));
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
        if (medusa.hasMoved()){

            screenVy += dt * screenAy;
            windowY -= dt * screenVy;

        }
        /**
         * À chaque tour, on recalcule si le personnage se trouve parterre ou
         * non
         */
        if ((int)(-windowY) % 100 == 0 && windowY < 0){
            Random R = new Random();
            Random posX = new Random();
            int widthX = R.nextInt(81) + 95;
            int X = posX.nextInt(WIDTH - widthX + 1);
            plateformes.add(new Plateforme(widthX,X,plateformes.get(plateformes.size() - 1).y-100));
        }


        for (Plateforme p : plateformes) {
            // Si le personnage se trouve sur une plateforme, ça sera défini ici
//          if (in){
//              p.update(dt);
                medusa.testCollision(p);
//          }

//          else{
//          //on enleve la plateforme...
//          }
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

