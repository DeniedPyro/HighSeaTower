import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;

    private ArrayList<Platform> platforms = new ArrayList<Platform>();
    private Medusa medusa;
    private double screenAy = 2;
    private double screenVy = 50;
    private Random R = new Random();
    // Origine de la fenêtre☻
    public static double windowY = 0.0;

    public Jeu() {
        for (int i = 0; i < 4; i++) {
            platforms.add(generatePlatform(370 - i * 110));
        }

        medusa = new Medusa(WIDTH/2-25, HEIGHT-50);
    }

    public Medusa getMedusa() {
        return medusa;
    }

    private Platform generatePlatform(int y){
        int width = R.nextInt(96) + 80;
        int x = R.nextInt(WIDTH - width + 1);

        int choice = R.nextInt(100)+1;
        //to debug red platforms
        //if(true) return new PlateformeAccelere(width,x,y);

        if (choice <=65){
            return new PlateformeSimple(width,x,y);
        }

        else if (choice > 65 && choice <= 85){
            return new PlateformeRebond(width,x,y);
        }

        else if (choice > 85 && choice <= 95){
            return new PlateformeAccelere(width,x,y);

        }
        else{
            if(platforms.size() == 0) {
                return new PlateformeSolide(width, x, y);
            }
            else {
                Platform lastPlatform = platforms.get(platforms.size() - 1);

                Color color = Color.rgb(184, 15, 36);
                if (!lastPlatform.color.equals(color)) {
                    return new PlateformeSolide(width, x, y);
                }
            }
        }
        return this.generatePlatform(y);
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
        if (platforms.get(platforms.size()-1).y -windowY > 110 && windowY < 0){
            Platform p = this.generatePlatform((int) platforms.get(platforms.size() - 1).y-110);
            platforms.add(p);
        }

        Iterator<Platform> platformIterator = platforms.iterator();

        while(platformIterator.hasNext()){
            Platform p = platformIterator.next();
            if (medusa.collides(p)){
                p.giveEffect(this,medusa);
                }
            else {
                p.cancelEffect(this,medusa);
            }

        }
        medusa.update(dt);

        if (medusa.y + medusa.hauteur > HEIGHT + this.windowY+medusa.hauteur){
            medusa.isAlive= false;
        }
    }

    public void draw(GraphicsContext context) {
        context.setFill(Color.DARKBLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        medusa.draw(context,windowY);
        Iterator<Platform> p = platforms.iterator();

        while(p.hasNext()) {
            Platform obj = p.next();
            if (-obj.y > -Jeu.windowY-HEIGHT){
                obj.draw(context, Jeu.windowY);
            }
            else {
                context.clearRect(obj.x,obj.y,obj.largeur,obj.hauteur);
                p.remove();

            }
        }
    }

    public void resetJeu(){
        platforms.clear();
        for (int i = 0; i < 4; i++) {
            platforms.add(generatePlatform(370 - i * 110));
        }
        medusa = new Medusa(WIDTH/2-25, HEIGHT-50);
        screenAy = 2;
        screenVy = 50;
        windowY = 0.0;

    }
}

