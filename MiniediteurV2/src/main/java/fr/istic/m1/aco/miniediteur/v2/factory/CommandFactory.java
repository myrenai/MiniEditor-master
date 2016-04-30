package fr.istic.m1.aco.miniediteur.v2.factory;


import fr.istic.m1.aco.miniediteur.v2.command.Coller;
import fr.istic.m1.aco.miniediteur.v2.command.CollerE;
import fr.istic.m1.aco.miniediteur.v2.command.Command;
import fr.istic.m1.aco.miniediteur.v2.command.Copier;
import fr.istic.m1.aco.miniediteur.v2.command.CopierE;
import fr.istic.m1.aco.miniediteur.v2.command.Couper;
import fr.istic.m1.aco.miniediteur.v2.command.CouperE;
import fr.istic.m1.aco.miniediteur.v2.command.DemarreE;
import fr.istic.m1.aco.miniediteur.v2.command.Modifier;
import fr.istic.m1.aco.miniediteur.v2.command.ModifierE;
import fr.istic.m1.aco.miniediteur.v2.command.Rejouer;
import fr.istic.m1.aco.miniediteur.v2.command.SaisirTexte;
import fr.istic.m1.aco.miniediteur.v2.command.SaisirTexteE;
import fr.istic.m1.aco.miniediteur.v2.command.Selectionner;
import fr.istic.m1.aco.miniediteur.v2.command.SelectionnerE;
import fr.istic.m1.aco.miniediteur.v2.command.SetCursorPosition;
import fr.istic.m1.aco.miniediteur.v2.command.SetCursorPositionE;
import fr.istic.m1.aco.miniediteur.v2.command.StopperE;
import fr.istic.m1.aco.miniediteur.v2.command.Supprimer;
import fr.istic.m1.aco.miniediteur.v2.command.SupprimerE;
import fr.istic.m1.aco.miniediteur.v2.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.receiver.MoteurEdition;
/**
 * This is the factory class which creates each commands. 
 * @author Jiyoung Park
 *
 */
public class CommandFactory {
	private MoteurEdition me;
	private Invoker ihm;
	private Enregistrement eng;
	

	public CommandFactory(MoteurEdition me, Invoker ihm, Enregistrement eng){
		this.me = me;
		this.ihm = ihm;
		this.eng = eng;
	}
	/**
	 * This method returns the command of each demands
	 * @param type CommandType
	 * @return
	 */
	public Command getCommand(CommandType type){
		switch (type) {
		
		case START_E:
			return new DemarreE(eng);
		case STOP_E:
			return new StopperE(eng);
		case REJOUER:
			return new Rejouer(eng);
		default:
			return null;
		}
	}

	/**
	 * This method returns the command of each demands
	 * @param type
	 * @param enregistrementMode
	 * @return
	 */
	public Command getCommand(CommandType type, boolean enregistrementMode){
		switch (type) {
		case INSERT:
			if(enregistrementMode)
				return new SaisirTexteE(new SaisirTexte(me, ihm), eng);
			else
				return new SaisirTexte(me, ihm);
		case COPY:
			if(enregistrementMode)
				return new CopierE(new Copier(me), eng);
			else
				return new Copier(me);
		case MODIFY:
			if(enregistrementMode)
				return new ModifierE(new Modifier(me, ihm), eng);
			else
				return new Modifier(me, ihm);
		case DELETE:
			if(enregistrementMode)
				return new SupprimerE(new Supprimer(me), eng);
			else
				return new Supprimer(me);
		case PASTE:
			if(enregistrementMode)
				return new CollerE(new Coller(me), eng);
			else
				return new Coller(me);
		case SELECTION:
			if(enregistrementMode)
				return new SelectionnerE(new Selectionner(me, ihm), eng);
			else
				return new Selectionner(me, ihm);
		case CUT:
			if(enregistrementMode)
				return new CouperE(new Couper(me), eng);
			else
				return new Couper(me);
		case CURSOR:
			if(enregistrementMode)
				return new SetCursorPositionE(new SetCursorPosition(me, ihm), eng);
			else
				return new SetCursorPosition(me, ihm);
		default:
			return null;
		}
		
	}

}
