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


public class Tortue extends Observable {

    protected static final int rp = 10, rb = 5; // Taille de la pointe et de la base de la fleche
    protected static final double ratioDegRad = 0.0174533; // Rapport radians/degres (pour la conversion)

	/*
	protected ArrayList<Segment> listSegments; // Trace de la tortue
	*/
	protected double x, y;
	protected int dir;
	protected int coul;
	protected boolean estCourante;
	public Forme forme;
	public double taille;
	public int vitesse; // distance parcourue a chaque ité
	public int separation; // distance minimale a laisser entre chaque tortue
	public int champDeVision = 180; // A changer eventuellement
	public double distance =50;


    public Tortue(int x, int y, int coul, Forme forme, int taille, int vitesse, int separation) {
        this.x = x;
        this.coul = coul;
        this.y = y;
        this.forme = forme;
        this.taille = taille;
        this.vitesse = vitesse;
        this.separation = separation;
    }

	public void reset() {
		setPosition(0,0);
		dir = -90;
		coul = 0;
	//	listSegments.clear();
  	}

	public void setPosition(double newX, double newY) {
		x = newX;
		y = newY;
	}

    public void avancer(ArrayList<Tortue> listTortues) {
        int newX = (int) Math.round(x + vitesse * Math.cos(ratioDegRad * dir));
        int newY = (int) Math.round(y + vitesse * Math.sin(ratioDegRad * dir));
        boolean positionOk = false;
        while (!positionOk) {
            positionOk = checkPosition(newX, newY, listTortues, separation);
            if (!positionOk) {
                newX = (int) Math.round(x + (vitesse - 1) * Math.cos(ratioDegRad * dir));
                newY = (int) Math.round(y + (vitesse - 2) * Math.sin(ratioDegRad * dir));
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
        couleur(coul + 1);
    }

    /**
     * quelques classiques
     */


	public void carre(ArrayList<Tortue> listTortues) {
		listTortues = getTortuesInFront(listTortues);
		for (int i=0;i<4;i++) {
			avancer(listTortues);
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

	public void poly(int n, int a, ArrayList<Tortue> listTortues) {
		listTortues = getTortuesInFront(listTortues);
		for (int j=0;j<a;j++) {
			avancer(listTortues);
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

	public void spiral(int n, int k, int a, ArrayList<Tortue> listTortues) {
		listTortues = getTortuesInFront(listTortues);
		for (int i = 0; i < k; i++) {
			couleur(coul+1);
			avancer(listTortues);
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

    public void flocking(ArrayList<Tortue> listTortues, int separation) {

        listTortues = getTortuesInFront(listTortues);
        this.setVitesse(getVitesseMoyenne(listTortues));
        this.setOrientation(getOrientationMoyenne(listTortues));
        this.avancer(listTortues);
        System.out.println("flocking");

    }

	//retourne la liste des tortues qui sont dans le champ de vision
	public ArrayList<Tortue> getTortuesInFront(ArrayList<Tortue> listTortues){
		//On elimine celles qui sont deriere
		for(Tortue tortue : listTortues){
			if(!this.estADistance(tortue)){
				listTortues.remove(tortue);
			}
			else{
				if(!this.estDansChamp(tortue)){
					listTortues.remove(tortue);
				}
			}
		}

        return listTortues;
    }

	public boolean estADistance(Tortue tortue){
		return getDistance(x, y, tortue.getX(), tortue.getY()) < distance;
	}
	public double getDistance(double x1, double y1, double x2, double y2){
		return Math.sqrt((double)(Math.pow((x2-x1),2) + Math.pow((y2-y1),2)));
	}

	public boolean estDansChamp(Tortue tortue){
		return Math.abs(getAngle(tortue)) < champDeVision/2;
	}

	private double getAngle(Tortue tortue){

		double tmpX = Math.round(x+Math.cos(ratioDegRad*dir));
		double tmpY = (int) Math.round(y+Math.sin(ratioDegRad*dir));
		double a = getDistance(x, y, tortue.getX(), tortue.getY());
		double b = getDistance(x, y, tmpX, tmpY);
		double c = getDistance(tmpX, tmpY, tortue.getX(), tortue.getY());
		double division = (Math.pow(c, 2) - Math.pow(a, 2) - Math.pow(b, 2))/ (2*a*c);

		return Math.acos(division);
	}

	//retourne la vitesse moyenne de la liste de tortue
	public int getVitesseMoyenne(ArrayList<Tortue> listTortues){

        int vitesseMoyenne = 0;
        for (Tortue tortue : listTortues) {
            vitesseMoyenne += tortue.getVitesse();
        }

		return vitesseMoyenne/listTortues.size();
	}

	//retourne l'orientation moyenne de la liste de tortue
	public int getOrientationMoyenne(ArrayList<Tortue> listTortues){
		int orientationMoyenne =0;

        for (Tortue tortue : listTortues) {
            orientationMoyenne += tortue.getOrientation();
        }
        return orientationMoyenne / listTortues.size();
    }


	//On regarde si une des tortues devant nous va se retrouver dans la zone critique de promiscuitée
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
	public void setColor(int n) {coul = n;}
	public void setOrientation(int orientation){this.dir = orientation;}
	public void setVitesse(int vitesse){this.vitesse = vitesse;}
	public void setSeparation(int separation){this.separation = separation;}
	public void setCourante(boolean estCourante){
		this.estCourante = estCourante;
	}
	/*
	GETTERS
	 */
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public int getColor() {
		if (estCourante) return 4;
		return coul;
	}
	public double getTaille(){
		return taille;
	}
	public Forme getForme(){
		return this.forme;
	}
	public int getVitesse(){ return this.vitesse;}
	public int getOrientation(){return this.dir;}
    public void setCurrent(boolean set) {
        this.estCourante = set;
        setChanged();
        notifyObservers();
    }

}
