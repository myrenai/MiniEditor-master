package fr.istic.m1.aco.miniediteur.v3.memento;
/**
 * Concrete Memento class to save the selection position information
 * @author Jiyoung Park
 *
 */
public class MementoSelectionner implements Memento{
	
	private int debut;
	private int fin;
	public int getDebut() {
		return debut;
	}
	public void setDebut(int debut) {
		this.debut = debut;
	}
	public int getFin() {
		return fin;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
	

}
