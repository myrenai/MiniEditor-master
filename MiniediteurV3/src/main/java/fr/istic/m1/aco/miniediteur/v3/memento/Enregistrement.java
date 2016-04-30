package fr.istic.m1.aco.miniediteur.v3.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import fr.istic.m1.aco.miniediteur.v3.command.Command;
import fr.istic.m1.aco.miniediteur.v3.command.Enregistable;
import fr.istic.m1.aco.miniediteur.v3.observer.Observer;
import fr.istic.m1.aco.miniediteur.v3.observer.Subject;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;


/**
 * Caretaker class of the Memento DP
 * @author jiyoung
 *
 */
public class Enregistrement implements Subject{
	private Observer observer;
	private MoteurEdition me;
	private boolean isDemarreERunning = false;
	// Where all commands are saved after the enregistrement is true
	private List<Enregistable> cList = new ArrayList<Enregistable>();
	// Where all mementos are saved after the enregistrement is true
	private List<Memento> mList = new ArrayList<Memento>();
	// Where all mementos are saved with the commands which call the addMemento method
	private Stack<MementoME> m1 = new Stack<MementoME>();
	// When 'undo' method called, the last memento of the m1 will be push into this Stack
	private Stack<MementoME> m2 = new Stack<MementoME>();
	
	public Enregistrement(MoteurEdition me){
		this.me = me;
		addMemento();
	}
	
	
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
	/**
	 * This method returns whether the demarrerE command is executed or not
	 * @return true when the DemarreE command is running
	 */
	public boolean isDemarreERunning(){
		return !isDemarreERunning;
	}
	/**
	 * Return true when the resisted command's size is positive and the DemarreE command is running
	 * If this method returns true that indicates on IHM, the RejouerE menu can be active
	 * @return
	 */
	public boolean canRejouerEActive(){
		return cList.size() > 0 && isDemarreERunning == false;
	}
	
	public boolean hasUndoMemento(){
		return m1.size()>1;
	}
	
	public boolean hasRedoMemento(){
		return !m2.isEmpty();
	}
	
	// Adds memento to the Stack
	public void addMemento(){
		m1.push(me.getMemento());
		if(!m2.isEmpty()){
			m2.clear();
		}
		if(m1.size()>1){
			notifyObserver();
		}
	}
	
	public void undo(){
		if(m1.size()>1){
			m2.push(m1.pop());
			me.setMemento(m1.peek().clone());
		}
		notifyObserver();
	}
	
	public void redo(){
		if(m2.size()>0){
			MementoME m = m2.pop();
			me.setMemento(m);
			m1.push(m);
		}
		notifyObserver();
	}
	
	public void reset(){
		MementoME m = m1.get(0);
		m1.clear();
		m1.push(m);
		m2.clear();
		notifyObserver();
	}
	
	/**
	 * Send to the subject whether the DemarreE command is running and the size of the stored commands
	 */
	@Override
	public void notifyObserver() {
		observer.update(!isDemarreERunning, cList.size() > 0 && isDemarreERunning == false);
		boolean undoFlag = false;
		boolean redoFlag = false;
		if(m1.size()>1){
			undoFlag = true;
		}
		if(m2.size()>0){
			redoFlag = true;
		}
		observer.updateUndoRedo(undoFlag, redoFlag);
	}
	
	@Override
	public void register(Observer o) {
		this.observer = o;
	}
	
	@Override
	public void unregister(Observer o) {
		this.observer = null;
	}
	
	public int nbCommandsEng(){
		return cList.size();
	}
	
	public int nbMementoEng(){
		return mList.size();
	}
	
}
