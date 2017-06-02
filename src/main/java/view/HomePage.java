package view;

import controller.ControllerHomePage;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Lucas on 01/06/2017.
 */
public class HomePage extends JFrame {
    private ControllerHomePage controllerHomePage;
    private HomePagePanel homePagePanel;

    public HomePage(ControllerHomePage controllerHomePage) {
        this.controllerHomePage = controllerHomePage;
        this.setTitle("TORTUE : Home Page");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.homePagePanel = new HomePagePanel(controllerHomePage);
        this.setContentPane(this.homePagePanel);
        setResizable(false);
        this.setBackground(new Color(0, 61, 79));

        //favicon
        ImageIcon icon = new ImageIcon("images/favicon.png");
        this.setIconImage(icon.getImage());

        this.setVisible(true);
    }

    public void close() {
        this.setVisible(false);
        dispose();
    }
}
