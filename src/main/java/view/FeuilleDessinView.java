package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;


/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 *
 * @author J. Ferber
 * @version 2.0
 */


public class FeuilleDessinView extends JPanel {

    private Map<Tortue, TortueView> map;
    private SimpleLogoView simpleLogoView;

    public FeuilleDessinView(SimpleLogoView simpleLogoView) {
        feuilleDessinInit();
        this.simpleLogoView = simpleLogoView;
        map = new HashMap<Tortue, TortueView>();
    }

    public void feuilleDessinInit() {
        setBackground(Color.white);
        setSize(new Dimension(1000, 1000));
        setPreferredSize(new Dimension(1000, 1000));
    }

    public void setCurrentTortue(Tortue tortue) {
        simpleLogoView.setCurrentTortue(tortue);
    }

    public void addTortue(Tortue tortue) {
        TortueView tortueView = new TortueView(tortue,"Rectangle", this);
        map.put(tortue, tortueView);
        Graphics graphics = getGraphics();
        Dimension dim = getSize();
        graphics.clearRect(0, 0, dim.width, dim.height);
        paintComponent(getGraphics());
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();
        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);
        
        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for (Map.Entry<Tortue, TortueView> entry : map.entrySet()) {
            TortueView tortueView = entry.getValue();
            this.add(tortueView);
        }
    }
}
