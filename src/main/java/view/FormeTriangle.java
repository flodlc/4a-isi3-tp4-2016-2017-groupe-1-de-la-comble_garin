package view;

import model.Tortue;

import java.awt.*;

/**
 * Created by Florian on 01/06/2017.
 */
public class FormeTriangle implements Forme {

    public Polygon getPolygon(Tortue tortue) {

        Polygon polygon = new Polygon();

        double x = 0;
        double y = 0;
        double taille = tortue.getTaille();

        //point haut gauche
        Point p1 = new Point((int) (x), (int) (y));
        polygon.addPoint(p1.x, p1.y);

        //point haut droite
        Point p2 = new Point((int) (x + taille), (int) (y + taille/4));
        polygon.addPoint(p2.x, p2.y);

        //point bas droite
        Point p3 = new Point((int) (x), (int) (y + taille/2));
        polygon.addPoint(p3.x, p3.y);

        return polygon;
    }
}
