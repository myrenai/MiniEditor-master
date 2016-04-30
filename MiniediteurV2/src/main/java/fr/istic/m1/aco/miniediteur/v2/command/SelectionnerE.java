package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.memento.MementoSelectionner;

/**
 * This class is the select concrete registrable command. 
 * @author Jiyoung Park
 *
 */
public class SelectionnerE implements Command, Enregistable{
	private Selectionner selectionner;
	private Enregistrement enregistrateur;
	private int debut = -1;
	private int fin = -1;
	
	public SelectionnerE(Selectionner selectionner, Enregistrement enregistrateur){
		this.selectionner = selectionner;
		this.enregistrateur = enregistrateur;
	}
	@Override
	public void execute() {
		if(debut == -1 && fin == -1){
			selectionner.execute();
			debut = selectionner.getDebut();
			fin = selectionner.getFin();
			enregistrateur.enregistrer(this);
		}else{
			selectionner.setDebut(debut);
			selectionner.setFin(fin);
			selectionner.execute();
		}
	}
	
	@Override
	public Memento getMemento() {
		MementoSelectionner m = new MementoSelectionner();
		m.setDebut(debut);
		m.setFin(fin);
		return m;
	}
	
	@Override
	public void setMemento(Memento m) {
		MementoSelectionner sm = (MementoSelectionner)m;
		debut = sm.getDebut();
		fin = sm.getFin();
	}

}
