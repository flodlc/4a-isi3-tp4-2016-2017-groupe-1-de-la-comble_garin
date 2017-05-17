package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
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


public class FeuilleDessinView extends JPanel implements Observer {

    private List<Tortue> tortues; // la liste des tortues enregistrees
    private List<TortueView> tortueViews;

    public FeuilleDessinView() {
        tortues = new ArrayList<Tortue>();
        tortueViews = new ArrayList<TortueView>();
        feuilleDessinInit();
    }

    public void feuilleDessinInit() {
        setBackground(Color.white);
        setSize(new Dimension(600, 400));
        setPreferredSize(new Dimension(600, 400));
    }

    public void addTortue(Tortue tortue) {
        tortue.addObserver(this);
        TortueView tortueView = new TortueView(tortue);
        tortueViews.add(tortueView);
    }

    public void reset() {
        for (Iterator it = tortues.iterator(); it.hasNext(); ) {
            Tortue t = (Tortue) it.next();
            t.reset();
        }
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
        for (Iterator it = tortueViews.iterator(); it.hasNext();) {
            TortueView tortueView = (TortueView) it.next();
            g.setColor(getTortueColor(tortueView));
            Polygon polygon = tortueView.getDessinTortue();
            g.fillPolygon(polygon);
        }
    }

    private Color getTortueColor(TortueView tortueView) {
        int couleurNb = tortueView.getCouleur();
        switch (couleurNb) {
            case 0:
                return (Color.black);
            case 1:
                return (Color.blue);
            case 2:
                return (Color.cyan);
            case 3:
                return (Color.darkGray);
            case 4:
                return (Color.red);
            case 5:
                return (Color.green);
            case 6:
                return (Color.lightGray);
            case 7:
                return (Color.magenta);
            case 8:
                return (Color.orange);
            case 9:
                return (Color.gray);
            case 10:
                return (Color.pink);
            case 11:
                return (Color.yellow);
            default:
                return (Color.black);
        }
    }


    public void update(Observable o, Object arg) {
        Graphics graphics = getGraphics();
        Dimension dim = getSize();
        graphics.clearRect(0, 0, dim.width, dim.height);
        paintComponent(getGraphics());
    }
}
