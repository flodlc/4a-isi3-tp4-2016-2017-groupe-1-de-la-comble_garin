package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Florian on 02/06/2017.
 */
public class AbstractFeuilleDessin extends JPanel {

    protected Map<Tortue, TortueView> map;
    protected AbstractSimpleLogoView simpleLogoView;

    public AbstractFeuilleDessin() {
        feuilleDessinInit();
        map = new HashMap<Tortue, TortueView>();
    }

    public void feuilleDessinInit() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(700, 700));
    }

    public void addTortue(Tortue tortue) {
        TortueView tortueView = new TortueView(tortue,"Rectangle", this);
        map.put(tortue, tortueView);
        Graphics graphics = getGraphics();
        Dimension dim = getSize();
        graphics.clearRect(0, 0, dim.width, dim.height);
        paintComponent(getGraphics());
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Color c = g.getColor();
        Dimension dim = getSize();
        g.setColor(Color.white);
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(c);
        showTurtles(g);
    }

    public void showTurtles(Graphics g) {
        for (Map.Entry<Tortue, TortueView> entry : map.entrySet()) {
            TortueView tortueView = entry.getValue();
            this.add(tortueView);
        }
    }
}
