package fr.istic.m1.aco.miniediteur.v1.factory;

import fr.istic.m1.aco.miniediteur.v1.command.Coller;
import fr.istic.m1.aco.miniediteur.v1.command.Command;
import fr.istic.m1.aco.miniediteur.v1.command.Copier;
import fr.istic.m1.aco.miniediteur.v1.command.Couper;
import fr.istic.m1.aco.miniediteur.v1.command.Modifier;
import fr.istic.m1.aco.miniediteur.v1.command.SaisirTexte;
import fr.istic.m1.aco.miniediteur.v1.command.Selectionner;
import fr.istic.m1.aco.miniediteur.v1.command.SetCursorPosition;
import fr.istic.m1.aco.miniediteur.v1.command.Supprimer;
import fr.istic.m1.aco.miniediteur.v1.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition;
/**
 * This is the factory class which creates each commands. 
 * @author Jiyoung Park
 *
 */
public class CommandFactory {
	private MoteurEdition me;
	private Invoker ihm;
	
	public CommandFactory(MoteurEdition me, Invoker ihm){
		this.me = me;
		this.ihm = ihm;
	}
	
	/**
	 * This method returns the command of each demands
	 * @param type CommandType
	 * @return
	 */
	public Command getCommand(CommandType type){
		switch (type) {
		case INSERT:
			return new SaisirTexte(me, ihm);
		case COPY:
			return new Copier(me);
		case MODIFY:
			return new Modifier(me, ihm);
		case DELETE:
			return new Supprimer(me);
		case PASTE:
			return new Coller(me);
		case SELECTION:
			return new Selectionner(me, ihm);
		case CUT:
			return new Couper(me);
		case CURSOR:
			return new SetCursorPosition(me, ihm);
		default:
			return null;
		}
	}
}
