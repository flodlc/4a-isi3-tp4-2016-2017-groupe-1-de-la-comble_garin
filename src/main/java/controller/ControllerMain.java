package controller;

import model.Tortue;
import view.FormeRectangle;
import view.SimpleLogoView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lucas on 01/06/2017.
 */
public abstract class ControllerMain {

    protected ArrayList<Tortue> listTortues;
    protected SimpleLogoView simpleLogoView;
    protected int vitesse;
    protected ArrayList<Color> colors;

    public ControllerMain() {
        this.colors = new ArrayList<Color>();
        this.colors.add(Color.BLUE);
        this.colors.add(Color.BLACK);
        this.colors.add(Color.GREEN);
        this.colors.add(Color.YELLOW);
        this.simpleLogoView = new SimpleLogoView(this);
        this.listTortues = new ArrayList<Tortue>();
        this.vitesse = 10;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 25; i++) {
                this.createTortue(j);
            }
        }
        for (Tortue tortue : listTortues) {
            tortue.aleatoireMove(listTortues);
        }
        startFlocking();


    }


    public void startFlocking() {
        new Thread() {
            public void run() {
                while (true) {
                    moveFlocking();
                    try {
                        sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    public void moveFlocking() {
        for (Tortue tortue : listTortues) {
            tortue.flocking(listTortues);
        }

    }

    public SimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    protected void createTortue(int coul) {
        Random rd = new Random();
        int x = 300 + rd.nextInt(100);
        int y = 300 + rd.nextInt(100);
        Tortue tortue = new Tortue(x, y, colors.get(coul), new FormeRectangle(), this.vitesse, 5, 1);
        this.listTortues.add(tortue);
        this.simpleLogoView.addTortue(tortue);
    }
}
