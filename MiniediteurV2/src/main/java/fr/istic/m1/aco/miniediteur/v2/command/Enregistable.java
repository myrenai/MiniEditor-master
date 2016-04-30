package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Memento;

/**
 * Originator class of the Memento DP
 * Every registrable commands will implement
 * @author Jiyoung Park
 *
 */
public interface Enregistable {
	
	public Memento getMemento();
	public void setMemento(Memento m);

}
