package controller;

import model.Bombe;
import model.Tortue;
import view.SimpleLogoViewGame;

/**
 * Created by Lucas on 01/06/2017.
 */
public class ControllerGame extends ControllerMain {

    public ControllerGame() {
        super();
        this.simpleLogoView = new SimpleLogoViewGame(this);
        this.startPopulation(4, 100);
    }

    public void layBombe(int x, int y) {
        Bombe bombe = new Bombe(10, x, y, 100);
        ((SimpleLogoViewGame) simpleLogoView).addBombe(bombe);
        for (Tortue tortue : super.listTortues){
            tortue.checkBombe(bombe);
        }
    }
}
