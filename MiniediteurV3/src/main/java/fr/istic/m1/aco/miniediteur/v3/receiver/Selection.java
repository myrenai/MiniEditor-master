package fr.istic.m1.aco.miniediteur.v3.receiver;

public class Selection {
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
	
	public Selection() {
	}

	private Selection(int debut, int fin) {
		this.debut = debut;
		this.fin = fin;
	}

	public Selection clone(){
		return new Selection(debut, fin);
	}
}
