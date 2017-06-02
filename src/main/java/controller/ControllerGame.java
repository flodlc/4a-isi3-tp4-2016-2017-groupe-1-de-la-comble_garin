package controller;

import model.Bombe;
import model.Tortue;
import view.SimpleLogoViewGame;

/**
 * Created by Lucas on 01/06/2017.
 */
public class ControllerGame extends ControllerMain {
    private int porteeBombe;

    public ControllerGame(int nbGroupe, int nbTortues, int porteeTortue, int porteeBombe) {
        super();
        this.porteeBombe = porteeBombe;
        this.simpleLogoView = new SimpleLogoViewGame(this);
        this.startPopulation(nbGroupe, nbTortues, porteeTortue);
    }

    public void layBombe(int x, int y) {
        Bombe bombe = new Bombe(10, x, y, 100);
        ((SimpleLogoViewGame) simpleLogoView).addBombe(bombe);
        for (Tortue tortue : super.listTortues){
            tortue.checkBombe(bombe);
        }
    }
}
