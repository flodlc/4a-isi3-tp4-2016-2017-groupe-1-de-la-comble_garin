package controller;

import view.HomePage;
import view.SettingPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lucas on 01/06/2017.
 */
public class ControllerHomePage implements ActionListener {
    private HomePage homePage;
    private SettingPage settingPage;

    public ControllerHomePage() {
        this.homePage = new HomePage(this);
    }


    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        this.homePage.close();
        System.out.println(actionCommand);
        if (actionCommand.equals("MODE ALEATOIRE")) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ControllerMain controller = new ControllerAleatoire();
                    controller.getSimpleLogoView().setVisible(true);
                }
            });
        } else if (actionCommand.equals("MODE BOMBE")) {
            this.settingPage = new SettingPage(this);
        } else if (actionCommand.equals("VALIDER")) {
            final int nbGroupe = this.settingPage.getNbGroupe();
            final int nbTortues = this.settingPage.getNbTortues();
            final int porteeTortue = this.settingPage.getPorteeTortue();
            final int porteeBombe = this.settingPage.getPorteeBombe();
            this.settingPage.close();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    ControllerMain controller = new ControllerGame(nbGroupe, nbTortues, porteeTortue, porteeBombe);
                    controller.getSimpleLogoView().setVisible(true);
                }
            });
        }
    }

    public HomePage getHomePage() {
        return this.homePage;
    }
}
