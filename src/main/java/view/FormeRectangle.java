package view;

import javafx.scene.shape.Polygon;
import model.Tortue;

import java.util.ArrayList;

/**
 * Created by calvi on 10/05/2017.
 */
public class FormeRectangle implements Forme {

    public Polygon getPolygon(Tortue tortue) {

        int x = tortue.getX();
        int y = tortue.getY();
        int taille = tortue.getTaille();
        int[] listX = new int[4];
        int[] listY = new int[4];
        //point haut gauche
        listX[0] = x;
        listY[0] = y;
        //point haut droite
        listX[1] = x + taille;
        listY[1] = y;
        //point bas droite
        listX[2] = x + taille;
        listX[2] = x - taille;
        //point bas gauche
        listX[3] = x;
        listY[3] = y - taille;

        return new Polygon(listX[0], listY[0], 4);
    }
}
