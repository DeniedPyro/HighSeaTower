import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;

    private ArrayList<Platform> plateformeSimples = new ArrayList<Platform>();
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
            plateformeSimples.add(new PlateformeSimple(widthX,X,365 - i * 115));
        }

        medusa = new Medusa(WIDTH/2-25, HEIGHT-50);
    }

    public void jump() {
        medusa.jump();
    }

    public double getScreenVy(){
        return this.screenVy;
    }

    public void setScreenVy(double vy){
        this.screenVy =vy;
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
        if (plateformeSimples.get(plateformeSimples.size()-1).y -windowY > 100 && windowY < 0){
            Random R = new Random();
            Random posX = new Random();
            int widthX = R.nextInt(81) + 95;
            int X = posX.nextInt(WIDTH - widthX + 1);
            plateformeSimples.add(new PlateformeSimple(widthX,X, plateformeSimples.get(plateformeSimples.size() - 1).y-115));
        }


        for (Platform p : plateformeSimples) {
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
        Iterator<Platform> p = plateformeSimples.iterator();

        while(p.hasNext()) {
            Platform obj = p.next();
            if (-obj.y > -Jeu.windowY-HEIGHT){
                obj.draw(context, Jeu.windowY);
            }
            else {
                context.clearRect(obj.x,obj.y,obj.largeur,obj.hauteur);
                //System.out.println("ool");
                p.remove();

            }
        }
    }
}

