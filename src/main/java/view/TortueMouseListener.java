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

    private TortueView tortueView;

    public TortueMouseListener(TortueView tortueView) {
        this.tortueView = tortueView;
    }

    public void mousePressed(MouseEvent e) {
        tortueView.setCurrentTortue(tortueView.getTortue());
    }
}
