package model;

import view.Forme;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


/*************************************************************************

	Un petit Logo minimal qui devra etre ameliore par la suite

	Source originale : J. Ferber - 1999-2001

			   Cours de DESS TNI - Montpellier II

	@version 2.0
	@date 25/09/2001

**************************************************************************/


public class Tortue extends Observable
{

	protected static final int rp=10, rb=5; // Taille de la pointe et de la base de la fleche
	protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

	/*
	protected ArrayList<Segment> listSegments; // Trace de la tortue
	*/
	protected int x, y;	
	protected int dir;
	protected int coul;
	protected boolean estCourante;
	public Forme forme;
	public int taille;
	public int vitesse;
	public int separation;
	public void setColor(int n) {coul = n;}


	public Tortue(int x, int y, int coul, Forme forme, int taille, int vitesse, int separation) {
		this.x = x;
		this.coul = coul;
		this.y = y;
		this.forme = forme;
		this.taille = taille;
		this.vitesse = vitesse;
		this.separation = separation;
		System.out.println("Tortue crée");
	}

	public void reset() {
		x = 0;
		y = 0;
		dir = -90;
		coul = 0;
	//	listSegments.clear();
  	}

	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}


	protected Color decodeColor(int c) {
		switch(c) {
			case 0: return(Color.black);
			case 1: return(Color.blue);
			case 2: return(Color.cyan);
			case 3: return(Color.darkGray);
			case 4: return(Color.red);
			case 5: return(Color.green);
			case 6: return(Color.lightGray);
			case 7: return(Color.magenta);
			case 8: return(Color.orange);
			case 9: return(Color.gray);
			case 10: return(Color.pink);
			case 11: return(Color.yellow);
			default : return(Color.black);
		}
	}

	public void avancer(ArrayList<Tortue> listTortues) {
		int newX = (int) Math.round(x+vitesse*Math.cos(ratioDegRad*dir));
		int newY = (int) Math.round(y+vitesse*Math.sin(ratioDegRad*dir));
		boolean positionOk = false;
		while(!positionOk){
			positionOk = checkPosition(newX, newY, listTortues, separation);
			if(!positionOk){
				newX = (int) Math.round(x+(vitesse-1)*Math.cos(ratioDegRad*dir));
				newY = (int) Math.round(y+(vitesse-2)*Math.sin(ratioDegRad*dir));
			}
		}
		x = newX;
		y = newY;
		setChanged();
		notifyObservers();
	}

	public void droite(int ang) {
		dir = (dir + ang) % 360;
	}

	public void gauche(int ang) {
		dir = (dir - ang) % 360;
	}


	public void couleur(int n) {
		coul = n % 12;
	}

	public void couleurSuivante() {
	 	couleur(coul+1);
	}

	/** quelques classiques */


	public void carre() {
		for (int i=0;i<4;i++) {
			//avancer(100);
			droite(90);
			setChanged();
			notifyObservers();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
	}

	public void poly(int n, int a) {
		for (int j=0;j<a;j++) {
			//avancer(n);
			droite(360/a);
			setChanged();
			notifyObservers();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
	}

	public void spiral(int n, int k, int a) {
		for (int i = 0; i < k; i++) {
			couleur(coul+1);
			//avancer(n);
			droite(360/a);
			n = n+1;
			setChanged();
			notifyObservers();
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
	}

	/*
	FLOCKING
	 */

	public void flocking(ArrayList<Tortue> listTortues, int separation){

		listTortues = getTortuesInFront(listTortues);
		this.setVitesse(getVitesseMoyenne(listTortues));
		this.setOrientation(getOrientationMoyenne(listTortues));
		this.avancer(listTortues);

	}

	public ArrayList<Tortue> getTortuesInFront(ArrayList<Tortue> listTortues){
		//On elimine celles qui sont deriere

		return listTortues;
	}

	public int getVitesseMoyenne(ArrayList<Tortue> listTortues){

		int vitesseMoyenne = 0;
		for(Tortue tortue : listTortues){
			vitesseMoyenne += tortue.getVitesse();
		}

		return vitesseMoyenne/listTortues.size();
	}
	public int getOrientationMoyenne(ArrayList<Tortue> listTortues){
		int orientationMoyenne =0;

		for(Tortue tortue : listTortues){
			orientationMoyenne += tortue.getOrientation();
		}
		return orientationMoyenne/listTortues.size();
	}


	//On regarde si une des tortues devant nous va se retrouver proche de nous
	public boolean checkPosition(int x, int y, ArrayList<Tortue> listTortues, int separation){
		boolean ok =true;
		for(Tortue tortue : listTortues){
			if(Math.abs(x-tortue.getX()) < separation && Math.abs(y-tortue.getY()) < separation){
				ok =false;
			}
		}
		return ok;
	}

	/*
	SETTEURS
	 */

	public void setOrientation(int orientation){
		this.dir = orientation;
	}
	public void setVitesse(int vitesse){
		this.vitesse = vitesse;
	}
	public void setSeparation(int separation){
		this.separation = separation;
	}

	/*
	GETTERS
	 */

	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getColor() {return coul;}
	public int getTaille(){
		return taille;
	}
	public Forme getForme(){
		return this.forme;
	}
	public int getVitesse(){ return this.vitesse;}
	public int getOrientation(){return this.dir;}

}
