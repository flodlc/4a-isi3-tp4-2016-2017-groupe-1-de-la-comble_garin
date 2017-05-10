package controller;

import model.Tortue;
import view.SimpleLogoView;

import javax.swing.*;
import java.awt.*;
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
    }

    public SimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    private void createTortue() {
        Tortue tortue = new Tortue(0,0, 1, new Rectangle(), 5);
        this.listTortues.add(tortue);
        this.simpleLogoView.addTortue(tortue);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object obj = actionEvent.getSource();
        String actionCommand = actionEvent.getActionCommand();
        if (obj instanceof JMenuItem) {
            // actions des boutons du haut
            if (actionCommand.equals("Avancer")) {
                System.out.println("commande avancer");
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue());
                    currentTortue.avancer(v);
                } catch (NumberFormatException ex) {
                    System.err.println("ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }

            } else if (actionCommand.equals("Droite")) {
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue.getText());
                    currentTortue.droite(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }
            } else if (actionCommand.equals("Gauche")) {
                try {
                    int v = Integer.parseInt(simpleLogoView.getInputValue.getText());
                    currentTortue.gauche(v);
                } catch (NumberFormatException ex) {
                    System.err.println("Ce n'est pas un nombre : " + simpleLogoView.getInputValue());
                }
            }
            // actions des boutons du bas
            else if (actionCommand.equals("Proc1"))
                currentTortue.carre();
            else if (actionCommand.equals("Proc2"))
                currentTortue.poly(60,8);
            else if (actionCommand.equals("Proc3"))
                currentTortue.spiral(50,40,6);
            else if (actionCommand.equals("Effacer"))
                currentTortue.effacer();
            else if (actionCommand.equals("Quitter"))
                currentTortue.quitter();
        }
    }
}
