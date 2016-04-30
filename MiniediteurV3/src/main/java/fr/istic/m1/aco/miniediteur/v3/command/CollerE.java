package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.memento.Memento;

/**
 * This class is the paste concrete registrable command. 
 * @author Jiyoung Park
 *
 */
public class CollerE implements Command, Enregistable{
	private Coller coller;
	private Enregistrement enregistrateur;
	public CollerE(Coller coller, Enregistrement enregistrateur) {
		this.coller = coller;
		this.enregistrateur = enregistrateur;
	}

	@Override
	public void execute() {
		coller.execute();
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
