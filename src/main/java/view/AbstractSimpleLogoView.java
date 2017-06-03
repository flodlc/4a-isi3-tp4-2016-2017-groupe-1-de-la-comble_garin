package view;

import controller.ControllerMain;
import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Florian on 02/06/2017.
 */
public class AbstractSimpleLogoView extends JFrame {

    protected AbstractFeuilleDessin feuilleDessinView;
    protected ControllerMain controller;

    public AbstractSimpleLogoView() {
        super("TORTUE : mode aléatoire");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
    }

    public void addTortue(Tortue tortue) {
        feuilleDessinView.addTortue(tortue);
    }

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(feuilleDessinView, "Center");
        setResizable(false);
        pack();
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/favicon.png"));
        this.setIconImage(icon.getImage());
        setVisible(true);
    }
}
