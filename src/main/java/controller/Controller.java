package controller;
import model.Tortue;
import view.SimpleLogoView;

/**
 * Created by calvi on 26/04/2017.
 */
public class Controller {

    private Tortue tortue;
    private SimpleLogoView simpleLogoView;

    public Controller(){
        this.tortue = new Tortue();
        this.simpleLogoView = new SimpleLogoView(tortue);
    }

    public SimpleLogoView getSimpleLogoView(){
        return simpleLogoView;
    }



}
