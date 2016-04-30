package fr.istic.m1.aco.miniediteur.v2.observer;


public interface Subject {
	/**
	 * Adds a new observer to the ArrayList
	 * @param o
	 */
	public void register(Observer o);
	/**
	 * Removes observer from the ArrayList
	 * @param o
	 */
	public void unregister(Observer o);
	/**
	 * 	Cycle through all observers and notifies them of
		shared attribut's changes
	 */
	public void notifyObserver();
	
}
