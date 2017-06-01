package controller;

import java.awt.*;
import java.util.Observable;

/**
 * Created by calvi on 01/06/2017.
 */
public abstract class MovingObject extends Observable{

    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

    protected double x, y;
    protected int dir;
    protected Color coul;

    public int vitesse; // distance parcourue a chaque ité
    private static int SIZE_GAME = 2000;

    public void setPosition(double newX, double newY) {
        x = newX;
        y = newY;
    }

    public void avancer() {
        System.out.println("ouiiiiiiiiiiiiii");
        int newX = (int) Math.round(x + vitesse * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(y + vitesse * Math.sin(ratioDegRad * dir));
        //cas droite
        if(newX >= SIZE_GAME){
            newX = 1;
        }
        //cas gauche
        if(newX < 0){
            newX = SIZE_GAME-1;
        }
        //cas bas
        if(newY >= SIZE_GAME){
            newY = 1;
        }
        //cas haut
        if(newY < 0){
            newY = SIZE_GAME-1;
        }
        x = newX;
        y = newY;
        setChanged();
        notifyObservers();
    }

    public void droite(int ang) {
        dir = (dir + ang) % 360;
    }

    public void gauche(int ang) {
        dir = (dir - ang) % 360;
    }

}
