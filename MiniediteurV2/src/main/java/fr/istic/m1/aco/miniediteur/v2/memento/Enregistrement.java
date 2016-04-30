package fr.istic.m1.aco.miniediteur.v2.memento;

import java.util.ArrayList;
import java.util.List;

import fr.istic.m1.aco.miniediteur.v2.command.Command;
import fr.istic.m1.aco.miniediteur.v2.command.Enregistable;
import fr.istic.m1.aco.miniediteur.v2.observer.Observer;
import fr.istic.m1.aco.miniediteur.v2.observer.Subject;


/**
 * Caretaker class of the Memento DP
 * @author jiyoung
 *
 */
public class Enregistrement implements Subject{
	private Observer observer;
	private boolean isDemarreERunning = false;
	// Where all commands are saved
	private List<Enregistable> cList = new ArrayList<Enregistable>();
	// Where all mementos are saved
	private List<Memento> mList = new ArrayList<Memento>();
	/**
	 * Save the registrable commands and mementos
	 * @param c Enregistable
	 */
	public void enregistrer(Enregistable c){
		if(isDemarreERunning){
			cList.add(c);
			mList.add(c.getMemento());
		}
		notifyObserver();
	}
	/**
	 * start registration of the commands
	 */
	public void demarrerE(){
		isDemarreERunning = true;
		cList.clear();
		mList.clear();
		notifyObserver();
	}
	/**
	 * stop registration of the commands
	 */
	public void stopperE(){
		isDemarreERunning = false;
		notifyObserver();
	}
	/**
	 * replay all the commands saved
	 */
	public void rejouerE(){
		for(int i = 0 ; i < cList.size() ; i ++){
			Enregistable c = cList.get(i);
			c.setMemento(mList.get(i));
			// update the real command name
			observer.update(c.getClass().getSimpleName());
			((Command)c).execute();
		}
	}
	
	public int nbCommandsEng(){
		return cList.size();
	}
	
	public int nbMementoEng(){
		return mList.size();
	}
	
	/**
	 * Send to the subject whether the DemarreE command is running and the size of the stored commands
	 */
	@Override
	public void notifyObserver() {
		observer.update(!isDemarreERunning, cList.size() > 0 && isDemarreERunning == false);
	}
	
	@Override
	public void register(Observer o) {
		this.observer = o;
	}
	
	@Override
	public void unregister(Observer o) {
		this.observer = null;
	}

}
