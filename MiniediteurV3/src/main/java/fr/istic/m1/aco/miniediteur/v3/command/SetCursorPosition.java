package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
/**
 * This class is the cursor concrete command. 
 * @author Jiyoung Park
 *
 */
public class SetCursorPosition implements Command{
	private MoteurEdition me;
	private Invoker ihm;
	private int cursor = -1;
	
	public int getCursor() {
		return cursor;
	}
	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
	public SetCursorPosition(MoteurEdition me, Invoker ihm){
		this.me = me;
		this.ihm = ihm;
	}
	@Override
	public void execute() {
		if(cursor == -1){
			cursor = ihm.getDebut();
		}
		me.setCusorPosition(cursor);
	}
}
