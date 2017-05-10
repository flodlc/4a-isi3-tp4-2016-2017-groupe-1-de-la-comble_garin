package view;

import model.Tortue;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by calvi on 10/05/2017.
 */
public interface Forme {

    Polygon getPolygon(Tortue tortue);
}
