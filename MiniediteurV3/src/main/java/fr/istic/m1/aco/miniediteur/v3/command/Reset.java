package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;

public class Reset implements Command{
	private Enregistrement enregistrement;
	public Reset(Enregistrement enregistrement){
		this.enregistrement = enregistrement;
	}
	
	@Override
	public void execute() {
		enregistrement.reset();
	}
}
