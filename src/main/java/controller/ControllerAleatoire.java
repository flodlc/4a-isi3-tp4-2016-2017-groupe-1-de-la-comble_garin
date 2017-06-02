package controller;

import view.SimpleLogoView;

/**
 * Created by calvi on 26/04/2017.
 */
public class ControllerAleatoire extends ControllerMain {

    public ControllerAleatoire() {
        super();
        simpleLogoView = new SimpleLogoView(this);
        this.startPopulation(3, 100, 150);
    }
}
