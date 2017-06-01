package view;

import java.awt.*;
import java.awt.geom.AffineTransform;

import model.Tortue;

/**
 * Created by calvi on 10/05/2017.
 */
public class FormeRectangle implements Forme {

    public Polygon getPolygon(Tortue tortue) {

        Polygon polygon = new Polygon();

        double x = 0;
        double y = 0;
        double taille = tortue.getTaille();
        //point haut gauche
        Point p1 = new Point((int) (x), (int) (y));
        polygon.addPoint(p1.x, p1.y);
        //point haut droite
        Point p2 = new Point((int) (x + taille), (int) (y));
        polygon.addPoint(p2.x, p2.y);
        //point bas droite
        Point p3 = new Point((int) (x + taille), (int) (y - taille));
        polygon.addPoint(p3.x, p3.y);

        //point bas gauche
        Point p4 = new Point((int) (x), (int) (y - taille));
        polygon.addPoint(p4.x, p4.y);

        return polygon;
    }
}
