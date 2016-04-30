package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;

/**
 * This command class indicates replaying of the saved commands
 * @author Jiyoung Park
 *
 */
public class Rejouer implements Command{
	
	private Enregistrement enregistrateur;
	public Rejouer(Enregistrement enregistrateur){
		this.enregistrateur = enregistrateur;
	}
	
	@Override
	public void execute() {
		enregistrateur.rejouerE();
	}

}
