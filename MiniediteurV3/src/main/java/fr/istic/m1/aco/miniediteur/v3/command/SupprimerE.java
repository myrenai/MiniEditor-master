package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.memento.Memento;
/**
 * This class is the delete concrete registrable command. 
 * @author jiyoung
 *
 */
public class SupprimerE implements Command, Enregistable{
	private Supprimer supprimer;
	private Enregistrement enregistrateur;
	public SupprimerE(Supprimer supprimer, Enregistrement enregistrateur){
		this.supprimer = supprimer;
		this.enregistrateur = enregistrateur;
	}
	@Override
	public void execute() {
		supprimer.execute();
		enregistrateur.enregistrer(this);
		enregistrateur.addMemento();
	}
	
	@Override
	public Memento getMemento() {
		return null;
	}
	
	@Override
	public void setMemento(Memento m) {
	}


}
