package fr.istic.m1.aco.miniediteur.v1.command;

import fr.istic.m1.aco.miniediteur.v1.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition;
/**
 * This class is the cursor concrete command. 
 * @author Jiyoung Park
 *
 */
public class SetCursorPosition implements Command{
	private MoteurEdition me;
	private Invoker ihm;
	
	public SetCursorPosition(MoteurEdition me, Invoker ihm){
		this.me = me;
		this.ihm = ihm;
	}
	@Override
	public void execute() {
		me.setCusorPosition(ihm.getDebut());
	}
}
