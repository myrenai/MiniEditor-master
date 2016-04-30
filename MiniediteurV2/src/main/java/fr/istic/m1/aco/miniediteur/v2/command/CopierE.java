package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.memento.Memento;

/**
 * This class is the copy concrete registrable command. 
 * @author Jiyoung Park
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
