package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
/**
 * This class is the paste concrete command. 
 * @author Jiyoung Park
 *
 */
public class Coller implements Command{
	private MoteurEdition me;
	public Coller(MoteurEdition me) {
		this.me = me;
	}

	@Override
	public void execute() {
		me.coller();
	}

	@Override
	public String toString() {
		return "Coller []";
	}
	
	
}
