package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
/**
 * This class is the redo concrete command. 
 * @author Jiyoung Park
 *
 */
public class Refaire implements Command{
	private Enregistrement enregistrement;
	public Refaire(Enregistrement enregistrement){
		this.enregistrement = enregistrement;
	}
	
	@Override
	public void execute() {
		enregistrement.redo();
	}
}
