package view;

import controller.ControllerGame;
import model.Bombe;

/**
 * Created by Florian on 02/06/2017.
 */
public class SimpleLogoViewGame extends AbstractSimpleLogoView {

    public SimpleLogoViewGame(ControllerGame controller) {
        super();
        this.feuilleDessinView = new FeuilleDessinViewGame(this);
        this.controller = controller;
        logoInit();
    }

    public void layBombe(int x, int y) {
        ((ControllerGame) controller).layBombe(x, y);
    }

    public void addBombe(Bombe bombe) {
        ((FeuilleDessinViewGame) feuilleDessinView).addBombe(bombe);
    }
}
