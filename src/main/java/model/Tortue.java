package model;

import controller.MovingObject;
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


public class Tortue extends MovingObject {


	public int champDeVision = 180; // A changer eventuellement
	public int distance;
	public int taille;
	private static int SIZE_GAME = 2000;

	public Tortue(int x, int y, Color coul,int distance, int taille) {
		this.x = x;
		this.coul = coul;
		this.y = y;
		this.distance = distance;
		this.taille = taille;
	}

	/*
    FLOCKING
	 */

	public void flocking(ArrayList<Tortue> listTortues, int separation) {

		listTortues = getTortuesInFront(listTortues);
		if(listTortues.isEmpty()){
			aleatoireMove(180);
		}
		else{
			this.setVitesse(getVitesseMoyenne(listTortues));
			this.setOrientation(getOrientationMoyenne(listTortues));
			this.avancer();
		}
	}

	//retourne la liste des tortues qui sont dans le champ de vision
	public ArrayList<Tortue> getTortuesInFront(ArrayList<Tortue> listTortues){
		//On elimine celles qui sont deriere
		ArrayList<Tortue> list = new ArrayList<Tortue>();
		for(Tortue tortue : listTortues){
			if(this.estADistance(tortue) && this.estDansChamp(tortue) && tortue.getColor() == coul){
				list.add(tortue);
			}
		}
		return list;
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

		return (listTortues.size() > 0)? vitesseMoyenne/listTortues.size() : 0;
	}

	//retourne l'orientation moyenne de la liste de tortue
	public int getOrientationMoyenne(ArrayList<Tortue> listTortues){
		int orientationMoyenne =0;

		for (Tortue tortue : listTortues) {
			orientationMoyenne += tortue.getOrientation();
		}
		return (listTortues.size() > 0)? orientationMoyenne / listTortues.size() : 0;
	}

	public void aleatoireMove(double angle){
		int alea = (int)(Math.random() * angle);
		this.setOrientation(alea % 360);
		avancer();

	}

	/*
    SETTEURS
	 */
	public void setColor(Color c) {coul = c;}
	public void setOrientation(int orientation){this.dir = orientation;}
	public void setVitesse(int vitesse){this.vitesse = vitesse;}
	/*
	GETTERS
	 */
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public Color getColor() {
		return coul;
	}
	public int getVitesse(){ return this.vitesse;}
	public int getOrientation(){return this.dir;}
	public int getTaille(){return this.taille;}

}
