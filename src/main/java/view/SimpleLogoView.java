package view;// package logo;


import controller.ControllerSimpleLogo;
import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*************************************************************************

 Un petit Logo minimal qui devra etre ameliore par la suite

 J. Ferber - 1999-2001

 Cours de DESS TNI - Montpellier II

 @version 2.0
 @date 25/09/


 **************************************************************************/


public class SimpleLogoView extends JFrame {
    public static final Dimension VGAP = new Dimension(1, 5);
    public static final Dimension HGAP = new Dimension(5, 1);

    private FeuilleDessinView feuilleDessinView;
    private ControllerSimpleLogo controller;


    public void quitter() {
        System.exit(0);
    }

	public SimpleLogoView(ControllerSimpleLogo controller) {
		super("TORTUE : mode aléatoire");
		this.feuilleDessinView = new FeuilleDessinView(this);
		this.controller = controller;
		logoInit();
		
		addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent arg0) {
		        super.windowClosing(arg0);
		        System.exit(0);
		    }
		});
        setFocusable(true);
        addKeyListener(new SimpleLogoViewListener(this));
	}

	public void avancer() {
        controller.avancer();
    }

    public void tournerDroite() {
        controller.tournerDroite();
    }

    public void tournerGauche() {
        controller.tournerGauche();
    }

	public void setCurrentTortue(Tortue tortue) {
		controller.setCurrentTortue(tortue);
	}

	public void addTortue(Tortue tortue) {
		feuilleDessinView.addTortue(tortue);
	}

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(feuilleDessinView, "Center");

        // Deplacement de la tortue au centre de la feuille;

        pack();
        ImageIcon icon = new ImageIcon("images/favicon.png");
        this.setIconImage(icon.getImage());
        setVisible(true);
    }


    // efface tout et reinitialise la feuille
    public void effacer() {
		/*
		feuille.reset();
		feuille.repaint();

		// Replace la tortue au centre
		Dimension size = feuille.getSize();
		courante.setPosition(size.width/2, size.height/2);*/
    }

    //utilitaires pour installer des boutons et des menus
    public void addButton(JComponent p, String name, String tooltiptext, String imageName) {
        JButton b;
        if ((imageName == null) || (imageName.equals(""))) {
            b = (JButton) p.add(new JButton(name));
        } else {
            java.net.URL u = this.getClass().getResource(imageName);
            if (u != null) {
                ImageIcon im = new ImageIcon(u);
                b = (JButton) p.add(new JButton(im));
            } else
                b = (JButton) p.add(new JButton(name));
            b.setActionCommand(name);
        }

        b.setToolTipText(tooltiptext);
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        b.setMargin(new Insets(0, 0, 0, 0));
        b.addActionListener(controller);
    }

    public void addMenuItem(JMenu m, String label, String command, int key) {
        JMenuItem menuItem;
        menuItem = new JMenuItem(label);
        m.add(menuItem);

        menuItem.setActionCommand(command);
        menuItem.addActionListener(controller);
        if (key > 0) {
            if (key != KeyEvent.VK_DELETE)
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, Event.CTRL_MASK, false));
            else
                menuItem.setAccelerator(KeyStroke.getKeyStroke(key, 0, false));
        }
    }
}