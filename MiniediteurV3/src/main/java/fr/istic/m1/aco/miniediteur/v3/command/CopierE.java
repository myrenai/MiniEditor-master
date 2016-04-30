package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.memento.Memento;

/**
 * This class is the copy concrete registrable command. 
 * @author jiyoung
 *
 */
public class CopierE implements Command, Enregistable{
	private Enregistrement enregistrateur;
	private Copier copier;
	public CopierE(Copier copier, Enregistrement enregistrateur) {
		this.copier = copier;
		this.enregistrateur = enregistrateur;
	}
	@Override
	public void execute() {
		copier.execute();
		//enregistrateur.addMemento();
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
