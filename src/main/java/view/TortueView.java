package view;

import model.Tortue;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by calvi on 26/04/2017.
 */
public class TortueView implements Observer{

    private Tortue tortue;
    private int couleur = 0;


    public TortueView(Tortue tortue){
        this.tortue = tortue;
        this.tortue.addObserver(this);
    }

    public void setCouleur(int couleur){
        this.couleur = couleur;
    }

    public void update(Observable o, Object arg) {
        
    }
}
