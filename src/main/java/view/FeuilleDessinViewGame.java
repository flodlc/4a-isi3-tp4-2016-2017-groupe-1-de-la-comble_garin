package view;

import model.Bombe;

/**
 * Created by Florian on 02/06/2017.
 */
public class FeuilleDessinViewGame extends AbstractFeuilleDessin {

    public FeuilleDessinViewGame(SimpleLogoViewGame simpleLogoView) {
        super();
        this.addMouseListener(new FeuilleDessinListener(this));
        this.simpleLogoView = simpleLogoView;
    }

    public void layBombe(int x, int y) {
        ((SimpleLogoViewGame) simpleLogoView).layBombe(x, y);
    }

    public void addBombe(Bombe bombe) {
        this.add(new BombeView(bombe));
        this.repaint();
    }
}
