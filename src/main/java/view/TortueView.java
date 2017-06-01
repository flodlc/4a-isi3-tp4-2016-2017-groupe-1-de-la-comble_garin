package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by calvi on 26/04/2017.
 */
public class TortueView extends JPanel implements Observer {

    private Tortue tortue;
    private Color couleur;
    private Polygon dessinTortue;
    private Forme forme;
    private FeuilleDessinView feuilleDessinView;

    public TortueView(Tortue tortue, String forme, FeuilleDessinView feuilleDessinView) {
        this.tortue = tortue;
        this.tortue.addObserver(this);
        setForme(forme);
        this.dessinTortue = this.forme.getPolygon(tortue);
        this.addMouseListener(new TortueMouseListener(this));
        this.feuilleDessinView = feuilleDessinView;
    }

    public void setCurrentTortue(Tortue tortue) {
        feuilleDessinView.setCurrentTortue(tortue);
    }


    public void paintComponent(Graphics g){
        try {
            super.paintComponent(g);
            g.fillPolygon(this.dessinTortue);
            this.setBackground(getCouleur());
            this.setBounds((int) this.tortue.getX(), (int) this.tortue.getY(), (int) this.tortue.getTaille(),
                    (int) this.tortue.getTaille());
        } catch (Exception e) {

        }
    }

    public void update(Observable o, Object arg) {
        this.paintComponent(this.getGraphics());
    }

    public void setForme(String forme) {
        if (forme.equals("Rectangle")) this.forme = new FormeRectangle();
    }

    /*
    GETTEURS
     */
    public Polygon getDessinTortue() {
        return dessinTortue;
    }

    public Color getCouleur() {
        return Color.RED;
    }

    public Tortue getTortue() {
        return tortue;
    }
}
