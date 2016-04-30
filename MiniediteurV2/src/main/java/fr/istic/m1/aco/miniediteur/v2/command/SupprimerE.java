package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.memento.Memento;
/**
 * This class is the delete concrete registrable command. 
 * @author Jiyoung Park
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
	}
	
	@Override
	public Memento getMemento() {
		return null;
	}
	
	@Override
	public void setMemento(Memento m) {
	}


}
