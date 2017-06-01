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
            for (int i = 0; i < 10; i++) {
                this.createTortue(j);
            }
        }
        for (Tortue tortue : listTortues) {
            tortue.aleatoireMove(360);
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
            tortue.flocking(listTortues, 1);
        }

    }

    public SimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    private void createTortue(int coul) {
        Random rd = new Random();
        int x = rd.nextInt(1000);
        int y = rd.nextInt(1000);
        Tortue tortue = new Tortue(x, y, colors.get(coul), 30,10);
        this.listTortues.add(tortue);
        this.simpleLogoView.addTortue(tortue);
    }


    public void avancer() {

        for (Tortue tortue : listTortues) {
            tortue.flocking(listTortues, 1);
        }
    }

    public void tournerDroite() {
        //currentTortue.droite(vitesse);
    }

    public void tournerGauche() {
        //currentTortue.gauche(vitesse);
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
        }
    }

}
