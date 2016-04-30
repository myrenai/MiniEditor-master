package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.memento.Memento;

/**
 * This class is the cut concrete registrable command. 
 * @author jiyoung
 *
 */
public class CouperE implements Command, Enregistable{
	private Couper couper;
	private Enregistrement enregistrateur;
	public CouperE(Couper couper, Enregistrement enregistrateur){
		this.couper = couper;
		this.enregistrateur = enregistrateur;
	}
	@Override
	public void execute() {
		couper.execute();
		enregistrateur.addMemento();
		enregistrateur.enregistrer(this);
	}
	
	@Override
	public Memento getMemento() {
		return null;
	}
	
	@Override
	public void setMemento(Memento m) {
	}

}
