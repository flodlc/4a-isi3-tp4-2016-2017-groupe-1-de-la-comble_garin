package view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Florian on 02/06/2017.
 */
public class FeuilleDessinListener implements MouseListener{
    private FeuilleDessinViewGame feuilleDessinView;

    public FeuilleDessinListener(FeuilleDessinViewGame feuilleDessinView) {
        this.feuilleDessinView = feuilleDessinView;
    }

    public FeuilleDessinViewGame getFeuilleDessinView() {
        return feuilleDessinView;
    }

    public void setFeuilleDessinView(FeuilleDessinViewGame feuilleDessinView) {
        this.feuilleDessinView = feuilleDessinView;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        feuilleDessinView.layBombe(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}