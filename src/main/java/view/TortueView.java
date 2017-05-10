package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by calvi on 26/04/2017.
 */
public class TortueView implements Observer{

    private Tortue tortue;
    private int couleur = 0;
    Polygon dessinTortue = new Polygon();

    public TortueView(Tortue tortue){
        this.tortue = tortue;
        this.tortue.addObserver(this);
    }


    public void update(Observable o, Object arg) {
        this.setDessinTortue();
    }

    public Polygon getDessinTortue(){
        return dessinTortue;
    }
    public void setDessinTortue(){

    }
}
