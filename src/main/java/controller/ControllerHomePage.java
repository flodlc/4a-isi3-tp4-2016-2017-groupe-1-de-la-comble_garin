package controller;

import view.HomePage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Lucas on 01/06/2017.
 */
public class ControllerHomePage implements ActionListener {
    private HomePage homePage;

    public ControllerHomePage() {
        this.homePage = new HomePage(this);
    }


    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        this.homePage.close();
        if (actionCommand.equals("MODE ALEATOIRE")) {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    ControllerMain controller = new ControllerAleatoire();
                    controller.getSimpleLogoView().setVisible(true);
                }
            });
        }else if (actionCommand.equals("MODE SURVIVOR")) {
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    ControllerMain controller = new ControllerGame();
                    controller.getSimpleLogoView().setVisible(true);
                }
            });
        }
    }

    public HomePage getHomePage() {
        return this.homePage;
    }
}
