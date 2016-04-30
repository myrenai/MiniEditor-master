package fr.istic.m1.aco.miniediteur.v1.observer;

public interface Observer {
	/**
	 * The Observers update method is called when the Subject changes
	 * @param zoneT
	 * @param debut
	 * @param fin
	 * @param pressPapier
	 */
	public void update(String zoneT, int debut, int fin, String pressPapier);

}
