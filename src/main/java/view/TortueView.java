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

    public TortueView(Tortue tortue, String forme) {
        this.tortue = tortue;
        this.tortue.addObserver(this);
        setForme(forme);
        this.dessinTortue = this.forme.getPolygon(tortue);
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(getCouleur());
        g.fillPolygon(this.dessinTortue);
        this.setBounds((int)this.tortue.getX(), (int)this.tortue.getY(), (int)this.tortue.getTaille(),
                (int)this.tortue.getTaille());
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

}
