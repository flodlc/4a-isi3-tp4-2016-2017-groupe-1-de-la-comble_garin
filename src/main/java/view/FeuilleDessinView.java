package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;


/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 *
 * @author J. Ferber
 * @version 2.0
 */


public class FeuilleDessinView extends AbstractFeuilleDessin {

    public FeuilleDessinView(SimpleLogoView simpleLogoView) {
        super();
        this.simpleLogoView = simpleLogoView;
    }
}
