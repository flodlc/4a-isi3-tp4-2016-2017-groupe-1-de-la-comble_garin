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
    private Forme forme;

    public TortueView(Tortue tortue, String forme){
        this.tortue = tortue;
        this.tortue.addObserver(this);
        setForme(forme);
        this.dessinTortue = this.forme.getPolygon(tortue);
        setDessinTortue();
    }

    public void update(Observable o, Object arg) {
        this.setDessinTortue();
    }
    public void setForme(String forme){
        if(forme == "Rectangle"){
            this.forme = new FormeRectangle();
        }
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
        return dessinTortue;

    }
    public int getCouleur(){
        return tortue.getColor();
    }

}
