package fr.istic.m1.aco.miniediteur.v3.observer;

public interface Observer {
	/**
	 * update the data of MVC pattern
	 * @param zoneT
	 * @param debut
	 * @param fin
	 * @param pressPapier
	 */
	public void update(String zoneT, int debut, int fin, String pressPapier);
	/**
	 * update boolean values of startEFlag and replayEFlag
	 * @param startEFlag true -> DemarrerE button or menu active on the IHM
	 * @param replayEFlag true -> RejouserE button or menu active on the IHM
	 */
	public void update(boolean startEFlag, boolean replayEFlag);
	/**
	 * update the commandName 
	 * called by rejouerE method of Enregistrement class to update the real command name when rejouer method is executed
	 * @param commmandName
	 */
	public void update(String commmandName);
	
	public void updateUndoRedo(boolean undoFlag, boolean redoFlag);

}
