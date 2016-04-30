package fr.istic.m1.aco.miniediteur.v1.command;

import fr.istic.m1.aco.miniediteur.v1.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition;
/**
 * This class is the modify concrete command. 
 * @author Jiyoung Park
 *
 */
public class Modifier implements Command{
	private MoteurEdition me;
	private Invoker ihm;
	public Modifier(MoteurEdition me, Invoker ihm){
		this.me = me;
		this.ihm = ihm;
	}
	@Override
	public void execute() {
		me.modifier(ihm.getTxt());
	}
}
