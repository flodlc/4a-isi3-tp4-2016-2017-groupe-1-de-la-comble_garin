package controller;

import model.Bombe;
import view.SimpleLogoViewGame;

/**
 * Created by Lucas on 01/06/2017.
 */
public class ControllerGame extends ControllerMain {

    public ControllerGame() {
        super();
        this.simpleLogoView = new SimpleLogoViewGame(this);
        this.startPopulation(1, 300);

    }

    public void layBombe(int x, int y) {
        Bombe bombe = new Bombe(10, x, y);
        ((SimpleLogoViewGame) simpleLogoView).addBombe(bombe);
    }
}
