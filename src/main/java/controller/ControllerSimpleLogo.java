package controller;

import model.Tortue;
import view.FormeRectangle;
import view.SimpleLogoView;

import javax.swing.*;
import javax.swing.Timer;
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

    public ControllerSimpleLogo() {
        this.simpleLogoView = new SimpleLogoView(this);
        this.listTortues = new ArrayList<Tortue>();
        this.vitesse = 10;
        for(int j= 0; j < 4; j++) {
            for (int i = 0; i < 25; i++) {
                this.createTortue(j);
            }
        }
        for(Tortue tortue : listTortues) {
            tortue.aleatoireMove(listTortues);
        }
        startFlocking();



    }


    public void startFlocking(){
        new Thread() {
            public void run() {
                while(true) {
                    moveFlocking();

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }
    public void moveFlocking(){

        for (Tortue tortue : listTortues) {
                tortue.flocking(listTortues, 1);
        }

    }

    public SimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    private void createTortue(int coul) {
        Random rd = new Random();
        int x = 400+rd.nextInt(200);
        int y = 400+rd.nextInt(200);
        Tortue tortue = new Tortue(x, y, coul, new FormeRectangle(), this.vitesse, 1, 1);
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

    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        String actionCommand = actionEvent.getActionCommand();
        if (obj instanceof javax.swing.JButton) {
            // actions des boutons du haut
            if (actionCommand.equals("Avancer")) {
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue());
                    for (Tortue tortue : listTortues) {
                        if (!tortue.getEstCourante())
                            tortue.setVitesse(0);
                    }
                    currentTortue.avancer();

                    for (Tortue tortue : listTortues) {
                        if (!tortue.getEstCourante())
                            tortue.flocking(listTortues, 1);
                    }

                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }

            } else if (actionCommand.equals("Add")) {
                System.out.println("Nouvelle tortue !");
                this.createTortue(0);
            } else if (actionCommand.equals("Droite")) {
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue());
                    currentTortue.droite(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }
            } else if (actionCommand.equals("Gauche")) {
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue());
                    currentTortue.gauche(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }
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
