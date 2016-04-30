package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v2.receiver.MoteurEdition;
/**
 * This class is the select concrete command. 
 * @author Jiyoung Park
 *
 */
public class Selectionner implements Command{
	private MoteurEdition me;
	private Invoker ihm;
	
	private int debut = -1;
	private int fin = -1;

	public Selectionner(MoteurEdition me, Invoker ihm){
		this.me = me;
		this.ihm = ihm;
	}
	
	@Override
	public void execute() {
		if(debut == -1 && fin == -1){
			debut = ihm.getDebut();
			fin = ihm.getFin();
		}
		me.selectionner(debut, fin);
	}
	
	
	public int getDebut() {
		return debut;
	}
	public int getFin() {
		return fin;
	}
	public void setDebut(int debut) {
		this.debut = debut;
	}
	public void setFin(int fin) {
		this.fin = fin;
	}
}
