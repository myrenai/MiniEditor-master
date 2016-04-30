package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
/**
 * This class is the cut concrete command. 
 * @author Jiyoung Park
 *
 */
public class Couper implements Command{
	private MoteurEdition me;
	public Couper(MoteurEdition me){
		this.me = me;
	}
	@Override
	public void execute() {
		me.couper();
	}
}
