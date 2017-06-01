package view;// package logo;


import controller.ControllerMain;
import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
    private ControllerMain controller;


	public SimpleLogoView(ControllerMain controller) {
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

	public void addTortue(Tortue tortue) {
		feuilleDessinView.addTortue(tortue);
	}

    public void logoInit() {
        getContentPane().setLayout(new BorderLayout(10, 10));

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(feuilleDessinView, "Center");

        // Deplacement de la tortue au centre de la feuille;

        setResizable(false);
        pack();
        ImageIcon icon = new ImageIcon("images/favicon.png");
        this.setIconImage(icon.getImage());
        setVisible(true);
    }
}