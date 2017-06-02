package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by calvi on 26/04/2017.
 */
public class TortueView extends JPanel implements Observer {

    private Tortue tortue;
    private Color couleur;
    private Polygon dessinTortue;
    private AbstractFeuilleDessin feuilleDessinView;

    public TortueView(Tortue tortue, String forme, AbstractFeuilleDessin feuilleDessinView) {
        this.tortue = tortue;
        this.tortue.addObserver(this);
        this.dessinTortue = buildPolygon();
        this.feuilleDessinView = feuilleDessinView;
        this.setLocation((int) this.tortue.getX(), (int) this.tortue.getY());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setSize(30, 30);
        setOpaque(false);
        g.setColor(getCouleur());
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(tortue.getOrientation()), tortue.getTaille() / 2, tortue.getTaille() / 2);
        ((Graphics2D) g).rotate(Math.toRadians(tortue.getOrientation()), tortue.getTaille() / 2, tortue.getTaille() / 2);
        g.fillPolygon(dessinTortue);
    }

    public void update(Observable o, Object arg) {
        super.repaint();
        this.setLocation((int) this.tortue.getX(), (int) this.tortue.getY());
        this.repaint();
    }

    public Polygon buildPolygon() {

        Polygon polygon = new Polygon();
        double taille = tortue.getTaille();

        //point haut gauche
        polygon.addPoint(0, 0);

        //point haut droite
        polygon.addPoint((int) (0 + taille), (int) (0 + taille / 4));

        //point bas droite
        polygon.addPoint(0, (int) (0 + taille / 2));

        return polygon;
    }

    public Color getCouleur() {
        return this.tortue.getColor();
    }

    public Tortue getTortue() {
        return tortue;
    }
}
