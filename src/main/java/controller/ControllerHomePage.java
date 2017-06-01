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
        if (actionCommand.equals("MODE MANUEL")) {
            System.out.println("in");
            this.homePage.close();
            System.out.println("close");
            SwingUtilities.invokeLater(new Runnable(){
                public void run(){
                    ControllerSimpleLogo controller = new ControllerSimpleLogo();
                    controller.getSimpleLogoView().setVisible(true);
                }
            });
        }
    }

    public HomePage getHomePage() {
        return this.homePage;
    }
}
