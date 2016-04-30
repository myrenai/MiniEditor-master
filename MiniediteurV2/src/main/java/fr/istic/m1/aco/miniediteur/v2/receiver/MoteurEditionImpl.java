package fr.istic.m1.aco.miniediteur.v2.receiver;

import java.util.ArrayList;

import fr.istic.m1.aco.miniediteur.v2.observer.Observer;
import fr.istic.m1.aco.miniediteur.v2.observer.Subject;

/**
 * Concrete receiver class
 * @author jiyoung
 *
 */
public class MoteurEditionImpl implements MoteurEdition ,Subject{
	/**
	 * clip board 
	 */
	private PressPapier pressPapier;
	/**
	 * main buffer 
	 */
	private Buffer buffer;
	/**
	 * selection boundary
	 */
	private Selection selection;
	/**
	 * Creates an ArrayList to hold all observers
	 */
	private ArrayList<Observer> observers;
	
	public MoteurEditionImpl(){
		buffer = new Buffer();
		selection = new Selection();
		pressPapier = new PressPapier();
		this.observers = new ArrayList<Observer>();
	}
	
	@Override
	public void register(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void unregister(Observer o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyObserver() {
		for(Observer observer : observers){
			observer.update(buffer.getContent(), selection.getDebut(), selection.getFin(), pressPapier.getCpp());
		}
	}
	
	@Override
	public void saisirTexte(String str) {
		buffer.insert(selection.getDebut(), str);
		int index = selection.getDebut() + str.length();
		selectionner(index, index);
		
		notifyObserver();
	}
	
	@Override
	public void copier() {
		pressPapier.setCpp(buffer.getContent(selection.getDebut(), selection.getFin()));
		
		notifyObserver();
	}
	
	@Override
	public void coller() {
		buffer.insert(selection.getDebut(), pressPapier.getCpp());
		int index = selection.getDebut() + pressPapier.getCpp().length();
		selectionner(index, index);
		
		notifyObserver();
	}
	
	@Override
	public void supprimer() {
		buffer.deleteAll();
		selection.setDebut(0);
		selection.setFin(0);
		
		notifyObserver();
	}
	
	@Override
	public void couper() {
		pressPapier.setCpp(buffer.getContent(selection.getDebut(), selection.getFin()));
		buffer.delete(selection.getDebut(), selection.getFin());
		selection.setFin(selection.getDebut());
		
		notifyObserver();
	}
	
	@Override
	public void modifier(String str) {
		buffer.deleteAll();
		buffer.insert(0, str);
		selection.setDebut(buffer.getContentLength());
		selection.setFin(buffer.getContentLength());
		
		notifyObserver();
	}

	@Override
	public void selectionner(int debut, int fin) {
		if(debut > buffer.getContentLength()){
			debut = buffer.getContentLength();
			fin = buffer.getContentLength();
		}
		if(fin > buffer.getContentLength()){
			fin = buffer.getContentLength();
		}
		selection.setDebut(debut);
		selection.setFin(fin);
	}
	
	@Override
	public void setCusorPosition(int position) {
		selectionner(position, position);
	}

}
