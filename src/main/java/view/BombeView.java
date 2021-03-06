package view;

import model.Bombe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Florian on 02/06/2017.
 */
public class BombeView extends JPanel implements Observer {

    private Bombe bombe;

    public BombeView(Bombe bombe) {
        this.bombe = bombe;
        bombe.addObserver(this);
        this.setSize(this.bombe.getMaxSizeExplosion()*2, this.bombe.getMaxSizeExplosion()*2);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);
        this.setSize(this.bombe.getMaxSizeExplosion()*2, this.bombe.getMaxSizeExplosion()*2);
        this.setLocation(bombe.getX() - 50, bombe.getY() - 50);
        this.setOpaque(false);
        g.setColor(Color.red);
        g.fillOval(50 - bombe.getExplosionSize() / 2, 50 - bombe.getExplosionSize() / 2, bombe.getExplosionSize(), bombe.getExplosionSize());
    }

    public void update(Observable o, Object arg) {
        super.repaint();
        this.repaint();
    }
}
