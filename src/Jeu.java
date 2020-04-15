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
    public static boolean debug = false;
    // Origine de la fenêtre☻
    public static double windowY = 0.0;

    public Jeu() {
        for (int i = 0; i < 4; i++) {
            platforms.add(generatePlatform(370 - i * 110));
        }

        medusa = new Medusa(WIDTH / 2 - 25, HEIGHT - 50);
    }



    /**Genere un nombre entre min et max
     * @param min
     * @param max
     * @return int
     */
    private int generateNumBetween(int min, int max) {
        return R.nextInt((max - min) + 1) + min;
    }


    /**Ajoute une groupe de bulle a la liste
     * @param b
     */
    private void addBubbleGroup(ArrayList<Bubble> b) {
        Bubble[] balles = new Bubble[5];
        int basex = generateNumBetween(0, WIDTH);
        for (int i = 0; i < 5; i++) {
            int v = generateNumBetween(350, 450);
            int r = generateNumBetween(5, 20);
            int x = generateNumBetween(0, 40) - 20;
            int y = generateNumBetween(0, 20);
            b.add(new Bubble(basex - x, HEIGHT - y + windowY, r, -v));
        }
    }


    /**Retourne is la meduse est positioner a 75% de lecran
     * @return boolean
     */
    private boolean isMedusaUp75() {
        return (medusa.y < HEIGHT * 0.25 + this.windowY);
    }


    /**retourne un type de platforme
     * @param y
     * @return Platform
     */
    private Platform generatePlatform(int y) {
        int width = R.nextInt(96) + 80;
        int x = R.nextInt(WIDTH - width + 1);

        int choice = R.nextInt(100) + 1;

        if (choice <= 65) {
            return new PlateformeSimple(width, x, y);
        } else if (choice > 65 && choice <= 85) {
            return new PlateformeRebond(width, x, y);
        } else if (choice > 85 && choice <= 95) {
            return new PlateformeAccelere(width, x, y);

        } else {
            if (platforms.size() == 0) {
                return new PlateformeSolide(width, x, y);
            } else {
                Platform lastPlatform = platforms.get(platforms.size() - 1);

                Color color = Color.rgb(184, 15, 36);
                if (!lastPlatform.color.equals(color)) {
                    return new PlateformeSolide(width, x, y);
                }
            }
        }
        return this.generatePlatform(y);
    }

    /**retourne la meduse
     * @return Medusa
     */
    public Medusa getMedusa() {
        return medusa;
    }

    /**retourne la valeur du debug
     * @return boolean
     */
    public boolean getDebug() {
        return this.debug;
    }


    /**modifie la valeur du debug
     * @param debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }


    /**retourne la valeur de la vitesse en y du defilement de lecran
     * @return double
     */
    public double getScreenVy() {
        return this.screenVy;
    }


    /**modifie la valeur de la vitesse en y du defilement de lecran
     * @param vy
     */
    public void setScreenVy(double vy) {
        this.screenVy = vy;
    }


    /** retourne la valeur de lacceleration en y du defilement de lecran
     * @return double
     */
    public double getScreenAy() {
        return this.screenAy;
    }

    /**Permet a la meduse de bouger a gauche
     */
    public void moveLeft() {
        medusa.moveLeft();
    }
    /**Permet a la meduse de bouger a droite
     */
    public void moveRight() {
        medusa.moveRight();
    }

    /**Permet a la meduse de sauter
     */
    public void jump() {
        medusa.jump();
    }

    /**Arrete le mouvement de la meduse
     */
    public void stop() {
        medusa.stop();
    }


    /** fait la mise a jour du jeu
     * @param dt
     */
    public void update(double dt) {

        /**
         *Cree les bulles a chaque 3 secondes
         */
        this.bubbleTimeIntervalTrack += dt;
        if (this.bubbleTimeIntervalTrack > 3.0 && this.bubbles.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                addBubbleGroup(bubbles);
            }
            this.bubbleTimeIntervalTrack -= 0;
        }

        if (!this.bubbles.isEmpty()) {
            for (int i = 0; i < this.bubbles.size(); i++) {
                Bubble b = this.bubbles.get(i);
                b.update(dt);
            }
        }

        if (medusa.hasMoved() && ! debug) {
            screenVy += dt * screenAy;
            windowY -= dt * screenVy;
        }

        if (isMedusaUp75() && debug){
            screenVy += dt * screenAy;
            windowY -= dt * screenVy;
        }

        /**
         *Ajouter les platformes s'il y a de l'espace pour l'ajouter
         */
        if (platforms.get(platforms.size() - 1).y - windowY > 110 && windowY < 0) {
            Platform p = this.generatePlatform((int) platforms.get(platforms.size() - 1).y - 110);
            platforms.add(p);
        }

        Iterator<Platform> platformIterator = platforms.iterator();
        while (platformIterator.hasNext()) {
            Platform p = platformIterator.next();
            if (medusa.collides(p)) {
                p.giveEffect(this, medusa);
            } else {
                p.cancelEffect(this, medusa);
            }
        }


        medusa.update(dt);
        if (isMedusaUp75() && speedEffect == 0) {
            System.out.println();
            this.speedEffect = this.screenVy;
            this.screenVy = Math.abs(medusa.vy);
            lock = true;
        }

        if (!isMedusaUp75() && lock) {
            this.screenVy = this.speedEffect;
            this.speedEffect = 0;
            lock = false;
        }
        if (medusa.y > HEIGHT + this.windowY) {
            medusa.isAlive = false;
        }
    }


    /**Permet de dessiner la le contenue du jeu
     * @param context
     */
    public void draw(GraphicsContext context) {
        context.setFill(Color.DARKBLUE);
        context.fillRect(0, 0, WIDTH, HEIGHT);

        medusa.draw(context, windowY);
        Iterator<Platform> p = platforms.iterator();

        while (p.hasNext()) {
            Platform obj = p.next();
            if (-obj.y > -Jeu.windowY - HEIGHT) {
                obj.draw(context);
            } else {
                context.clearRect(obj.x, obj.y, obj.largeur, obj.hauteur);
                p.remove();
            }
        }

        for (int i = 0; i < this.bubbles.size(); i++) {
            Bubble bubble = this.bubbles.get(i);
            if (bubble.getY() > Jeu.windowY - HEIGHT) {
                bubble.draw(context);
            } else {
                context.clearRect(bubble.getX(), bubble.getY(), bubble.getW(), bubble.getH());
                this.bubbles.remove(i); // Retire l'élément zéro
            }
        }
    }

    /**Permet relancer le jeu
     */
    public void resetJeu() {
        platforms.clear();
        for (int i = 0; i < 4; i++) {
            platforms.add(generatePlatform(370 - i * 110));
        }
        medusa = new Medusa(WIDTH / 2 - 25, HEIGHT - 50);
        screenAy = 2;
        screenVy = 50;
        windowY = 0.0;
    }
}
