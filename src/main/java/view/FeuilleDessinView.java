package view;

import model.Tortue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;


/**
 * Titre :        Logo
 * Description :  Un exemple de programme graphique utilisant la celebre Tortue Logo
 * Copyright :    Copyright (c) 2000
 * Societe :      LIRMM
 * @author J. Ferber
 * @version 2.0
 */



public class FeuilleDessinView extends JPanel implements Observer {
	private ArrayList<Tortue> tortues; // la liste des tortues enregistrees

	private TortueView tortueView;
	private Tortue tortue;
	private TortueView[] tortueViews;

	public FeuilleDessinView(Tortue tortue) {

		this.tortue = tortue;
		this.tortueView = new TortueView(tortue);

	//	tortues = new ArrayList<Tortue>();
	}

	public void feuilleDessinInit() {
		setBackground(Color.white);
		setSize(new Dimension(600,400));
		setPreferredSize(new Dimension(600,400));
		addTortue(tortue);
	}

	public void addTortue(Tortue o) {
		tortues.add(o);
	}

	public void reset() {
		for (Iterator it = tortues.iterator();it.hasNext();) {
			Tortue t = (Tortue) it.next();
			t.reset();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Color c = g.getColor();
		
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0,0,dim.width, dim.height);
		g.setColor(c);

		showTurtles(g);
	}
	
	public void showTurtles(Graphics g) {
		for (Iterator it = tortues.iterator();it.hasNext();) {
			Tortue tortue = (Tortue) it.next();
			Polygon polygon = tortueView.getDessinTortue();
			g.fillPolygon(polygon);
		}
	}


	public void update(Observable o, Object arg) {
		tortue.addObserver(this);
	}

	/*
	GETTERS
	 */

	public TortueView getTortueView() {
		return tortueView;
	}


}
