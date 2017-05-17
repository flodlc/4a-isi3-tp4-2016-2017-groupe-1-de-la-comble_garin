package controller;

import model.Tortue;
import view.FormeRectangle;
import view.SimpleLogoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by calvi on 26/04/2017.
 */
public class ControllerSimpleLogo implements ActionListener {

    private List<Tortue> listTortues;
    private Tortue currentTortue;
    private SimpleLogoView simpleLogoView;

    public ControllerSimpleLogo() {
        this.simpleLogoView = new SimpleLogoView(this);
        this.listTortues = new ArrayList<Tortue>();
        this.createTortue();
        this.createTortue();
        this.createTortue();
        this.createTortue();
    }

    public SimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    private void createTortue() {
        Tortue tortue = new Tortue(200, 200, 1, new FormeRectangle(), 20);
        this.listTortues.add(tortue);
        this.simpleLogoView.addTortue(tortue);
        this.currentTortue = tortue;
    }

    private void setCurrentTortue(Tortue tortue) {
        this.currentTortue = tortue;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        String actionCommand = actionEvent.getActionCommand();
        if (obj instanceof javax.swing.JButton) {
            // actions des boutons du haut
            if (actionCommand.equals("Avancer")) {
                System.out.println("commande avancer");
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue());
                    currentTortue.avancer(v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }

            } else if (actionCommand.equals("Add")) {
                System.out.println("Nouvelle tortue !");
                this.createTortue();
            } else if (actionCommand.equals("Droite")) {
                System.out.println("commande Droite");
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue());
                    currentTortue.droite(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }
            } else if (actionCommand.equals("Gauche")) {
                System.out.println("commande Gauche");
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue());
                    currentTortue.gauche(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }
            }
            // actions des boutons du bas
            else if (actionCommand.equals("Proc1"))
                currentTortue.carre();
            else if (actionCommand.equals("Proc2"))
                currentTortue.poly(60, 8);
            else if (actionCommand.equals("Proc3"))
                currentTortue.spiral(50, 40, 6);
            else if (actionCommand.equals("Effacer"))
                simpleLogoView.effacer();
            else if (actionCommand.equals("Quitter"))
                simpleLogoView.quitter();
        }
    }
}
