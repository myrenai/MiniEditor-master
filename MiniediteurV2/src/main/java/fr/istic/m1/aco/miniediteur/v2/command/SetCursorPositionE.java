package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.memento.MementoCursor;
/**
 * This class is the cursor concrete registrable command. 
 * @author Jiyoung Park
 *
 */
public class SetCursorPositionE implements Command, Enregistable{
	private SetCursorPosition selectionner;
	private Enregistrement enregistrateur;
	private int cursor = -1;
	
	public SetCursorPositionE(SetCursorPosition selectionner, Enregistrement enregistrateur){
		this.selectionner = selectionner;
		this.enregistrateur = enregistrateur;
	}
	@Override
	public void execute() {
		if(cursor == -1){
			selectionner.execute();
			cursor = selectionner.getCursor();
			enregistrateur.enregistrer(this);
		}else{
			selectionner.setCursor(cursor);
			selectionner.execute();
		}
	}
	
	@Override
	public Memento getMemento() {
		MementoCursor m = new MementoCursor();
		m.setCursor(cursor);
		return m;
	}
	
	@Override
	public void setMemento(Memento m) {
		MementoCursor cm = (MementoCursor)m;
		cursor = cm.getCursor();
	}
}
