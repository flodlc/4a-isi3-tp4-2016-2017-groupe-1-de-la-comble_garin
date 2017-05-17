package view;

import model.Tortue;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

/**
 * Created by Florian on 17/05/2017.
 */
public class TortueMouseListener extends MouseAdapter {

    private Map<Tortue, TortueView> map;
    private FeuilleDessinView feuilleDessinView;

    public TortueMouseListener(Map<Tortue, TortueView> map, FeuilleDessinView feuilleDessinView) {
        this.map = map;
    }

    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        for (Map.Entry<Tortue, TortueView> entry : map.entrySet()) {
            if (entry.getValue().getDessinTortue().contains(p)) {
                feuilleDessinView.setCurrentTortue(entry.getKey());
                break;
            }
        }
    }
}
