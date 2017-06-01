package view;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
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

    public void paintComponent(Graphics g) {
        this.setSize(30, 30);
        super.paintComponent(g);
        this.setBackground(Color.white);
        setOpaque(false);
        this.setOpaque(false);
        g.setColor(getCouleur());
        AffineTransform at = new AffineTransform();
        at.rotate(Math.toRadians(tortue.getOrientation()), tortue.getTaille()/2, tortue.getTaille()/2);
        ((Graphics2D)g).rotate(Math.toRadians(tortue.getOrientation()), tortue.getTaille()/2, tortue.getTaille()/2);
        g.fillPolygon(dessinTortue);
        this.setLocation((int) this.tortue.getX(), (int) this.tortue.getY());
    }

    public void update(Observable o, Object arg) {
        this.repaint();
    }

    public void setForme(String forme) {
        if (forme.equals("Rectangle")) this.forme = new FormeTriangle();
    }

    /*
    GETTEURS
     */

    public Color getCouleur() {
        return this.tortue.getColor();
    }

    public Tortue getTortue() {
        return tortue;
    }
}
