package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;


/*************************************************************************

 Un petit Logo minimal qui devra etre ameliore par la suite

 Source originale : J. Ferber - 1999-2001

 Cours de DESS TNI - Montpellier II

 @version 2.0
 @date 25/09/2001

 **************************************************************************/


public class Tortue extends Observable {

    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    /*
    protected ArrayList<Segment> listSegments; // Trace de la tortue
    */
    private double x, y;
    private int dir;
    private Color coul;
    private double taille;
    private int vitesse; // distance parcourue a chaque ité
    private int iterOutOfFlocking;
    public int champDeVision; // A changer eventuellement
    private double distance = 60;
    private static int SIZE_GAME = 700;


    public Tortue(int x, int y, Color coul, int taille, int vitesse, int champVision) {
        this.x = x;
        this.coul = coul;
        this.y = y;
        this.taille = taille;
        this.vitesse = vitesse;
        this.champDeVision = champVision;
        this.iterOutOfFlocking = 0;
    }

    public void avancer() {
        int newX = (int) Math.round(x + vitesse * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(y + vitesse * Math.sin(ratioDegRad * dir));

        //cas droite
        if (newX >= SIZE_GAME) {
            newX = 1;
        }
        //cas gauche
        if (newX <= 0) {
            newX = SIZE_GAME;
        }
        //cas bas
        if (newY >= SIZE_GAME) {
            newY = 1;
        }
        //cas haut
        if (newY <= 0) {
            newY = SIZE_GAME;
        }
        x = newX;
        y = newY;
        setChanged();
        notifyObservers();
    }


    public void flocking(ArrayList<Tortue> listTortues) {
        if (iterOutOfFlocking == 0) {
            listTortues = getTortuesInFront(listTortues);
            this.setOrientation(getOrientationMoyenne(listTortues));
        } else {
            iterOutOfFlocking--;
        }
        this.avancer();
    }

    //retourne la liste des tortues qui sont dans le champ de vision
    public ArrayList<Tortue> getTortuesInFront(ArrayList<Tortue> listTortues) {

        ArrayList<Tortue> list = new ArrayList<Tortue>();
        for (Tortue tortue : listTortues) {
            if (tortue.getColor() == coul && this.estADistance(tortue) && this.estDansChamp(tortue.getX(), tortue.getY())) {
                list.add(tortue);
            }
        }
        return list;
    }

    public boolean estADistance(Tortue tortue) {
        return getDistance(x, y, tortue.getX(), tortue.getY()) < distance;
    }

    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)));
    }

    public boolean estDansChamp(double x2, double y2) {
        return Math.toDegrees(getAngle(x2, y2)) < champDeVision / 2;
    }

    private double getAngle(double x2, double y2) {
        double tmpX = Math.round(x + 10 * Math.cos(ratioDegRad * dir));
        double tmpY = Math.round(y + 10 * Math.sin(ratioDegRad * dir));
        double a = getDistance(x, y, x2, y2);
        double b = getDistance(x, y, tmpX, tmpY);
        double c = getDistance(tmpX, tmpY, x2, y2);
        double division = (Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2))/(2*a*b);
        return Math.acos(division);
    }

    //retourne l'orientation moyenne de la liste de tortue
    public int getOrientationMoyenne(ArrayList<Tortue> listTortues) {
        int orientationMoyenne = 0;
        for (Tortue tortue : listTortues) {
            orientationMoyenne += tortue.getOrientation();
        }
        return (listTortues.size() > 0) ? orientationMoyenne / listTortues.size() : dir;
    }

    public void aleatoireMove() {
        int angle = (int) (Math.random() * 360);
        this.setOrientation(angle % 360);
        avancer();
    }

    public void checkBombe(Bombe bombe) {
        if (this.getDistance(this.x, this.y, bombe.getX(), bombe.getY()) <= bombe.getMaxSizeExplosion()) {
            this.iterOutOfFlocking = 50;
            this.dir = 180 - (int)Math.toDegrees(this.getAngle(bombe.getX(), bombe.getY()));

            setChanged();
            notifyObservers();
        }
    }

    /*
    SETTEURS
     */
    public void setOrientation(int orientation) {
        this.dir = orientation;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /*
    GETTERS
     */
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Color getColor() {
        return coul;
    }

    public double getTaille() {
        return taille;
    }

    public int getVitesse() {
        return this.vitesse;
    }

    public int getOrientation() {
        return this.dir;
    }
}
