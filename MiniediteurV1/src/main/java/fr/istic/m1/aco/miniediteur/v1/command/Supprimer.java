package fr.istic.m1.aco.miniediteur.v1.command;

import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition;
/**
 * This class is the delete concrete command. 
 * @author Jiyoung Park
 *
 */
public class Supprimer implements Command{
	private MoteurEdition me;
	public Supprimer(MoteurEdition me){
		this.me = me;
	}
	@Override
	public void execute() {
		me.supprimer();
	}

}
