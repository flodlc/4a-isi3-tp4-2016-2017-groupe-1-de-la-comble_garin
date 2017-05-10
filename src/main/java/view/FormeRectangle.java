package view;

import java.awt.*;
import model.Tortue;

/**
 * Created by calvi on 10/05/2017.
 */
public class FormeRectangle implements Forme {

    public Polygon getPolygon(Tortue tortue) {

        Polygon polygon = new Polygon();

        int x = tortue.getX();
        int y = tortue.getY();
        int taille = tortue.getTaille();
        //point haut gauche
        Point p1 = new Point(x, y);
        polygon.addPoint(p1.x, p1.y);
        //point haut droite
        Point p2 = new Point(x + taille, y);
        polygon.addPoint(p2.x, p2.y);
        //point bas droite
        Point p3 = new Point(x + taille, y - taille);
        polygon.addPoint(p3.x, p3.y);

        //point bas gauche
        Point p4 = new Point(x, y - taille);
        polygon.addPoint(p4.x, p4.y);

        return polygon;
    }
}
