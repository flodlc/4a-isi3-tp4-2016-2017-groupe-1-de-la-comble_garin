package controller;

import model.Tortue;
import view.AbstractSimpleLogoView;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lucas on 01/06/2017.
 */
public abstract class ControllerMain {

    protected ArrayList<Tortue> listTortues;
    protected AbstractSimpleLogoView simpleLogoView;
    protected ArrayList<Color> colors;

    public ControllerMain() {
        this.colors = new ArrayList<Color>();
        this.colors.add(Color.BLUE);
        this.colors.add(Color.BLACK);
        this.colors.add(Color.GREEN);
        this.colors.add(Color.YELLOW);
        this.listTortues = new ArrayList<Tortue>();
    }


    protected void startPopulation(int nbGroup, int nbByGroup, int porteeTortue) {
        for (int j = 0; j < nbGroup; j++) {
            for (int i = 0; i < nbByGroup; i++) {
                this.createTortue(j, porteeTortue);
            }
        }
        for (Tortue tortue : listTortues) {
            tortue.aleatoireMove();
        }
        startFlocking();
    }

    public void startFlocking() {
        new Thread() {
            public void run() {
                while (true) {
                    moveFlocking();
                    try {
                        sleep(15);
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

    public AbstractSimpleLogoView getSimpleLogoView() {
        return simpleLogoView;
    }

    protected void createTortue(int coul, int porteeTortue) {
        Random rd = new Random();
        int x = 200 + rd.nextInt(300);
        int y = 200 + rd.nextInt(300);
        Tortue tortue = new Tortue(x, y, colors.get(coul), 15, 10, 1, porteeTortue);
        this.listTortues.add(tortue);
        this.simpleLogoView.addTortue(tortue);
    }
}
