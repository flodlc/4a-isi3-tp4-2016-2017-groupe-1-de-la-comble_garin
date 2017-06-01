package controller;

import model.Tortue;
import view.FormeRectangle;
import view.SimpleLogoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by calvi on 26/04/2017.
 */
public class ControllerSimpleLogo implements ActionListener {

    private ArrayList<Tortue> listTortues;
    private Tortue currentTortue;
    private SimpleLogoView simpleLogoView;
    private int vitesse;

    public ControllerSimpleLogo() {
        this.simpleLogoView = new SimpleLogoView(this);
        this.listTortues = new ArrayList<Tortue>();
        this.vitesse = 10;
        this.createTortue();
        this.createTortue();
        this.createTortue();
        this.createTortue();
    }

    public SimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    private void createTortue() {
        Random rd = new Random();
        int x = rd.nextInt(400);
        int y = rd.nextInt(400);
        Tortue tortue = new Tortue(x, y, 0, new FormeRectangle(), this.vitesse, 20, 1);
        this.listTortues.add(tortue);
        this.simpleLogoView.addTortue(tortue);
        setCurrentTortue(tortue);
    }

    public void setCurrentTortue(Tortue tortue) {
        if (this.currentTortue != null)
            this.currentTortue.setCurrent(false);
        this.currentTortue = tortue;
        this.currentTortue.setCurrent(true);
        this.currentTortue.setVitesse(this.vitesse);
    }

    public void avancer() {
        try {
            int v = Integer.parseInt(simpleLogoView.getInputValue());
            for (Tortue tortue : listTortues) {
                if (!tortue.getEstCourante())
                    tortue.setVitesse(0);
            }
            currentTortue.avancer(this.listTortues);

            for (Tortue tortue : listTortues) {
                if (!tortue.getEstCourante())
                    tortue.flocking(listTortues, 1);
            }

        } catch (NumberFormatException ex) {
            System.err.println("ce n'est pas un nombre : " + simpleLogoView.getInputValue());
        }
    }

    public void tournerDroite() {
        try {
            int v = Integer.parseInt(simpleLogoView.getInputValue());
            currentTortue.droite(v);
        } catch (NumberFormatException ex) {
            System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
        }
    }

    public void tournerGauche() {
        try {
            int v = Integer.parseInt(simpleLogoView.getInputValue());
            currentTortue.gauche(v);
        } catch (NumberFormatException ex) {
            System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
        }
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        String actionCommand = actionEvent.getActionCommand();
        if (obj instanceof javax.swing.JButton) {
            // actions des boutons du haut
            if (actionCommand.equals("Avancer")) {
                avancer();
            } else if (actionCommand.equals("Add")) {
                System.out.println("Nouvelle tortue !");
                this.createTortue();
            } else if (actionCommand.equals("Droite")) {
                tournerDroite();
            } else if (actionCommand.equals("Gauche")) {
                tournerGauche();
            }
            // actions des boutons du bas
            else if (actionCommand.equals("Proc1"))
                currentTortue.carre(listTortues);
            else if (actionCommand.equals("Proc2"))
                currentTortue.poly(60, 8, listTortues);
            else if (actionCommand.equals("Proc3"))
                currentTortue.spiral(50, 40, 6, listTortues);
            else if (actionCommand.equals("Effacer"))
                simpleLogoView.effacer();
            else if (actionCommand.equals("Quitter"))
                simpleLogoView.quitter();
        }
    }

}
