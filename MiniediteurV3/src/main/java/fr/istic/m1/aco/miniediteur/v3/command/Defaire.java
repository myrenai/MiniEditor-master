package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
/**
 * This class is the undo concrete command. 
 * @author Jiyoung Park
 *
 */
public class Defaire implements Command{
	private Enregistrement enregistrement;
	public Defaire(Enregistrement enregistrement){
		this.enregistrement = enregistrement;
	}
	
	@Override
	public void execute() {
		enregistrement.undo();
	}
}
