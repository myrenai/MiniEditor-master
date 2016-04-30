package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;

/**
 * This command class indicates the start of the saving commands
 * @author Jiyoung Park
 *
 */
public class DemarreE implements Command{
	
	private Enregistrement enregistrateur;
	public DemarreE(Enregistrement enregistrateur){
		this.enregistrateur = enregistrateur;
	}
	
	@Override
	public void execute() {
		enregistrateur.demarrerE();
	}

}
