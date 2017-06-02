package model;

import java.util.Observable;

/**
 * Created by Florian on 02/06/2017.
 */
public class Bombe extends Observable{
    private int size;
    private int x;
    private int y;
    private int explosionSize;
    private int time;

    public Bombe(int size, int x, int y) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.time = 300;
        this.explode();
    }

    private void explode() {
        new Thread() {
            public void run() {
                while (getExplosionSize() < 60) {
                    setExplosionSize(getExplosionSize() + 15);
                    setChanged();
                    notifyObservers();
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                setExplosionSize(0);
                setChanged();
                notifyObservers();
            }
        }.start();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getExplosionSize() {
        return explosionSize;
    }

    public void setExplosionSize(int explosionSize) {
        this.explosionSize = explosionSize;
    }
}
