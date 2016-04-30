package fr.istic.m1.aco.miniediteur.v3.receiver;

import fr.istic.m1.aco.miniediteur.v3.memento.MementoME;

/**
 * This is the main interface of the Mini Editor's engine.
 * This interface takes the roles of
 *     1. the receiver which has some placeholder for some concrete command interaction with receiver
 *     2. the Subject to update all Observers
 * @author jiyoung
 *
 */
public interface MoteurEdition {
	/**
	 * Cut buffer's selected text into the clip board
	 */
	public void couper();
	
	/**
	 * Paste the clip board into the buffer with cursor position
	 */
	public void coller();
	
	/**
	 * Copy buffer's selected text into the clip board
	 */
	public void copier();
	
	/**
	 * Delete buffer's content
	 */
	public void supprimer();
	
	/**
	 * Insert the text of the buffer
	 * @param str - user inputed text by user
	 */
	public void saisirTexte(String str);
	
	/**
	 * Modifier the text of the buffer
	 * @param str - user inputed text by user
	 */
	public void modifier(String str);
	
	/**
	 * Select the boundary of the buffer
	 * @param debut - start point inputed by user
	 * @param fin - end point inputed by user
	 */
	public void selectionner(int debut, int fin);
	
	/**
	 * Select the cursor point of the buffer
	 * @param position - cursor position inputed by user
	 */
	public void setCusorPosition(int position);
	
	public MementoME getMemento();
	public void setMemento(MementoME m);
}
