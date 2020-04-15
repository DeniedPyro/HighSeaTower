import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Jeu {

    public static final int WIDTH = 350, HEIGHT = 480;

    private ArrayList<Platform> platforms = new ArrayList<Platform>();
    private ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
    private Medusa medusa;
    private double screenAy = 2;
    private double screenVy = 50;
    private Random R = new Random();
    private double speedEffect = 0;
    private double bubbleTimeIntervalTrack = 0.0;
    private boolean lock = false;
    private boolean debug = false;
    // Origine de la fenêtre☻
    public static double windowY = 0.0;

    public Jeu() {
        for (int i = 0; i < 4; i++) {
            platforms.add(generatePlatform(370 - i * 110));
        }

        medusa = new Medusa(WIDTH/2-25, HEIGHT-50);
    }

    public boolean getDebug(){
        return this.debug;
    }

    public void setDebug(boolean debug){
        this.debug = debug;
    }

    public Medusa getMedusa() {
        return medusa;
    }
    private int generateNumBetween(int min, int max) {
        return R.nextInt((max - min) + 1) + min;
    }

    private void addBubbleGroup(ArrayList<Bubble> b) {
        Bubble[] balles = new Bubble[5];
        int basex = generateNumBetween(0, WIDTH);
        for (int i = 0; i < 5; i++) {
            int v = generateNumBetween(350, 450);
            int r = generateNumBetween(5, 20);
            int x = generateNumBetween(0, 40)-20;
            int y = generateNumBetween(0, 20);
            b.add(new Bubble(basex - x, HEIGHT - y + windowY, r, -v));
        }
    }

        private boolean isMedusaUp75(){
        return (medusa.y < HEIGHT * 0.25 + this.windowY);
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

    public double getScreenAy(){
        return this.screenAy;
    }

    public void setScreenAy(double ay){
        this.screenAy =ay;
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

        this.bubbleTimeIntervalTrack +=dt;

        if(this.bubbleTimeIntervalTrack > 3.0 &&  this.bubbles.isEmpty() ){
            for (int i =0; i < 3; i++){
                addBubbleGroup(bubbles);
            }
            this.bubbleTimeIntervalTrack -=3 ;
        }

        if (!this.bubbles.isEmpty()){
            for (int i = 0; i < this.bubbles.size(); i++) {
                Bubble b = this.bubbles.get(i);
                //b.updateAcc(b.getX(), b.getY()-HEIGHT-b.getY());
                b.update(dt);

                // Collision de la balle avec toutes les balles suivantes
                //for (int j = i + 1; j < bubbles.size(); j++) {
                //    b.testCollision(this.bubbles.get(j));
                //}
        }
        }

        if (medusa.hasMoved()) {
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

        if(isMedusaUp75() && speedEffect == 0){
            System.out.println();
            this.speedEffect = this.screenVy;
            this.screenVy = Math.abs(medusa.vy);
            lock = true;
        }

        if(!isMedusaUp75() && lock){
            this.screenVy = this.speedEffect;
            this.speedEffect = 0;
            lock = false;
        }

        if (medusa.y > HEIGHT + this.windowY){
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

        for(int i=0; i<this.bubbles.size(); i++) {
            Bubble bubble = this.bubbles.get(i);
            if (bubble.getY() > Jeu.windowY - HEIGHT) {
                bubble.draw(context, Jeu.windowY);
            }
            else {
                context.clearRect(bubble.getX(),bubble.getY(),bubble.getW(),bubble.getH());
                this.bubbles.remove(i); // Retire l'élément zéro
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

