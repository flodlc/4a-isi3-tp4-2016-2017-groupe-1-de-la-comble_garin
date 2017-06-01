package controller;

import model.Tortue;

/**
 * Created by Lucas on 01/06/2017.
 */
public class ControllerGame extends ControllerMain {

    public ControllerGame() {
        super();
    }

    public void avancer() {
        for (Tortue tortue : super.listTortues) {
            tortue.flocking(super.listTortues);
        }
    }

//    public void tournerDroite() {
//        currentTortue.droite(vitesse);
//    }
//
//    public void tournerGauche() {
//        currentTortue.gauche(vitesse);
//    }
}
