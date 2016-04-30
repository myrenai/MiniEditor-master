package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.memento.Memento;

/**
 * This class is the paste concrete registrable command. 
 * @author jiyoung
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
