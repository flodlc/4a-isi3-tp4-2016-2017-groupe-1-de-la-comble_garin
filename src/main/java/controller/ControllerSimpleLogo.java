package controller;

import model.Tortue;
import view.FormeRectangle;
import view.SimpleLogoView;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by calvi on 26/04/2017.
 */
public class ControllerSimpleLogo implements ActionListener {

    private ArrayList<Tortue> listTortues;
    private Tortue currentTortue;
    private SimpleLogoView simpleLogoView;
    private int vitesse;
    private ArrayList<Color> colors;

    public ControllerSimpleLogo() {
        this.colors = new ArrayList<Color>();
        this.colors.add(Color.BLUE);
        this.colors.add(Color.BLACK);
        this.colors.add(Color.GREEN);
        this.colors.add(Color.YELLOW);
        this.simpleLogoView = new SimpleLogoView(this);
        this.listTortues = new ArrayList<Tortue>();
        this.vitesse = 10;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 25; i++) {
                this.createTortue(j);
            }
        }
        for (Tortue tortue : listTortues) {
            if (!tortue.getEstCourante()) {
                tortue.aleatoireMove(listTortues);
            }
        }
        startFlocking();



    }


    public void startFlocking() {
        new Thread() {
            public void run() {
                while (true) {
                    moveFlocking();

                    try {
                        sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    public void moveFlocking() {
        for (Tortue tortue : listTortues) {
            if (!tortue.getEstCourante()) {
                tortue.flocking(listTortues, 1);
            }
        }

    }

    public SimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    private void createTortue(int coul) {
        Random rd = new Random();
        int x = 400+rd.nextInt(200);
        int y = 400+rd.nextInt(200);
        Tortue tortue = new Tortue(x, y, this.colors.get(coul), new FormeRectangle(), this.vitesse, 1, 1);
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
        currentTortue.avancer();

        for (Tortue tortue : listTortues) {
            if (!tortue.getEstCourante())
                tortue.flocking(listTortues, 1);
        }
    }

    public void tournerDroite() {
        currentTortue.droite(vitesse);
    }

    public void tournerGauche() {
        currentTortue.gauche(vitesse);
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
                this.createTortue(4);
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
