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

    // Origine de la fenêtre☻
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
        if (medusa.hasMoved()){

            screenVy += dt * screenAy;
            windowY -= dt * screenVy;

        }
        /**
         * À chaque tour, on recalcule si le personnage se trouve parterre ou
         * non
         */
        if (plateformes.get(plateformes.size()-1).y -windowY > 100 && windowY < 0){
            Random R = new Random();
            Random posX = new Random();
            int widthX = R.nextInt(81) + 95;
            int X = posX.nextInt(WIDTH - widthX + 1);
            plateformes.add(new Plateforme(widthX,X,plateformes.get(plateformes.size() - 1).y-115));
        }


        for (Plateforme p : plateformes) {
            // Si le personnage se trouve sur une plateforme, ça sera défini ici
            //p.update(dt);
            medusa.testCollision(p);
        }
        medusa.update(dt);
    }

    public void draw(GraphicsContext context) {
        context.setFill(Color.DARKBLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        medusa.draw(context,windowY);
        Iterator<Plateforme> p = plateformes.iterator();

        while(p.hasNext()) {
            Plateforme obj = p.next();
            if (-obj.y > -Jeu.windowY-HEIGHT){
                obj.draw(context, Jeu.windowY);
            }
            else {
                context.clearRect(obj.x,obj.y,obj.largeur,obj.hauteur);
                //System.out.println("ool");
                p.remove();

            }
            //System.out.println(plateformes.toString());
        }

//        for (Plateforme p : plateformes) {
//            if (p.y > windowY){
//                p.draw(context, windowY);
//            }
//            else {
//                context.clearRect(p.x,p.y,p.largeur,p.hauteur);
//                plateformes.remove(p);
//                //System.out.println(plateformes.toString());
//
//            }
//        }

    }
}

