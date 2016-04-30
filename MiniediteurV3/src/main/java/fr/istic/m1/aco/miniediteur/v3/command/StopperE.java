package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
/**
 * This command class indicates the stop of the saving commands
 * @author Jiyoung Park
 *
 */
public class StopperE implements Command{
	
	private Enregistrement enregistrateur;
	public StopperE(Enregistrement enregistrateur){
		this.enregistrateur = enregistrateur;
	}
	
	@Override
	public void execute() {
		enregistrateur.stopperE();
	}

}
