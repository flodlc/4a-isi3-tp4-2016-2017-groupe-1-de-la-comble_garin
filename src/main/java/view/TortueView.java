package view;

import model.Tortue;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by calvi on 26/04/2017.
 */
public class TortueView implements Observer{

    private Tortue tortue;
    private int couleur = 0;
    private Polygon dessinTortue;

    public TortueView(Tortue tortue){
        this.tortue = tortue;
        this.tortue.addObserver(this);
        this.dessinTortue = tortue.getForme().getPolygon(tortue);
        setDessinTortue();
    }

    public void update(Observable o, Object arg) {
        this.setDessinTortue();
    }

    /*
    SETTEURS
     */

    public void setDessinTortue(){
        dessinTortue = tortue.getForme().getPolygon(tortue);

    }
    /*
    GETTEURS
     */

    public Polygon getDessinTortue(){
        System.out.println("Dessin tortue envoyé");
        return dessinTortue;

    }
    public int getCouleur(){
        return tortue.getColor();
    }

}
