package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Florian on 01/06/2017.
 */
public class SimpleLogoViewListener implements KeyListener {

    private SimpleLogoView simpleLogoView;
    private List<Integer> pressedKeys;

    public SimpleLogoViewListener(SimpleLogoView simpleLogoView) {
        this.simpleLogoView = simpleLogoView;
        pressedKeys = new ArrayList<Integer>();
    }

    public void keyTyped(KeyEvent e) {

    }

    private void findAction(int keyCode) {
        System.out.println(keyCode + "      " + pressedKeys.size());
        switch (keyCode) {
            case 38 :
                simpleLogoView.avancer();
                break;
            case 39 :
                simpleLogoView.tournerDroite();
                break;
            case 37 :
                simpleLogoView.tournerGauche();
                break;
        }
    }


    public void keyPressed(KeyEvent e) {
        for (Integer keyCode : pressedKeys) {
            if (keyCode != e.getKeyCode())
                findAction(keyCode);
        }
        if (pressedKeys.indexOf(e.getKeyCode()) == -1)
            pressedKeys.add(e.getKeyCode());
        findAction(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pressedKeys.remove((Object) e.getKeyCode());
    }
}
