package fr.istic.m1.aco.miniediteur.v3.factory;


import fr.istic.m1.aco.miniediteur.v3.command.Coller;
import fr.istic.m1.aco.miniediteur.v3.command.CollerE;
import fr.istic.m1.aco.miniediteur.v3.command.Command;
import fr.istic.m1.aco.miniediteur.v3.command.Copier;
import fr.istic.m1.aco.miniediteur.v3.command.CopierE;
import fr.istic.m1.aco.miniediteur.v3.command.Couper;
import fr.istic.m1.aco.miniediteur.v3.command.CouperE;
import fr.istic.m1.aco.miniediteur.v3.command.Defaire;
import fr.istic.m1.aco.miniediteur.v3.command.DemarreE;
import fr.istic.m1.aco.miniediteur.v3.command.Modifier;
import fr.istic.m1.aco.miniediteur.v3.command.ModifierE;
import fr.istic.m1.aco.miniediteur.v3.command.Refaire;
import fr.istic.m1.aco.miniediteur.v3.command.Rejouer;
import fr.istic.m1.aco.miniediteur.v3.command.Reset;
import fr.istic.m1.aco.miniediteur.v3.command.SaisirTexte;
import fr.istic.m1.aco.miniediteur.v3.command.SaisirTexteE;
import fr.istic.m1.aco.miniediteur.v3.command.Selectionner;
import fr.istic.m1.aco.miniediteur.v3.command.SelectionnerE;
import fr.istic.m1.aco.miniediteur.v3.command.SetCursorPosition;
import fr.istic.m1.aco.miniediteur.v3.command.SetCursorPositionE;
import fr.istic.m1.aco.miniediteur.v3.command.StopperE;
import fr.istic.m1.aco.miniediteur.v3.command.Supprimer;
import fr.istic.m1.aco.miniediteur.v3.command.SupprimerE;
import fr.istic.m1.aco.miniediteur.v3.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
/**
 * This is the factory class which creates each commands. 
 * @author Jiyoung Park
 *
 */
public class CommandFactory {
	private MoteurEdition me;
	private Invoker ihm;
	private Enregistrement enregistrement;

	public CommandFactory(MoteurEdition me, Invoker ihm, Enregistrement enregistrement){
		this.me = me;
		this.ihm = ihm;
		this.enregistrement = enregistrement;
	}
	/**
	 * This method returns the command of each demands
	 * @param type CommandType
	 * @return
	 */
	public Command getCommand(CommandType type){
		switch (type) {
		
		case START_E:
			return new DemarreE(enregistrement);
		case STOP_E:
			return new StopperE(enregistrement);
		case REJOUER:
			return new Rejouer(enregistrement);
		case UNDO:
			return new Defaire(enregistrement);
		case REDO:
			return new Refaire(enregistrement);
		case RESET:
			return new Reset(enregistrement);
		default:
			return null;
		}
	}
	/**
	 * This method returns the command of each demands
	 * @param type CommandType
	 * @param enregistrementMode if enregistrementMode is true, this method return the commands enregistable
	 * @return
	 */
	public Command getCommand(CommandType type, boolean enregistrementMode){
		switch (type) {
		case INSERT:
			return new SaisirTexteE(new SaisirTexte(me, ihm), enregistrement);
		case COPY:
			return new CopierE(new Copier(me), enregistrement);
		case MODIFY:
			return new ModifierE(new Modifier(me, ihm), enregistrement);
		case DELETE:
			return new SupprimerE(new Supprimer(me), enregistrement);
		case PASTE:
			return new CollerE(new Coller(me), enregistrement);
		case SELECTION:
			return new SelectionnerE(new Selectionner(me, ihm), enregistrement);
		case CUT:
			return new CouperE(new Couper(me), enregistrement);
		case CURSOR:
			return new SetCursorPositionE(new SetCursorPosition(me, ihm), enregistrement);
		default:
			return null;
		}
		
	}

}
