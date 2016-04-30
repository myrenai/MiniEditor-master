package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.memento.MementoSaisirTexte;

/**
 * This class is the insert concrete registrable command. 
 * @author Jiyoung Park
 *
 */
public class SaisirTexteE implements Command, Enregistable{
	private SaisirTexte saisirTexte;
	private Enregistrement eng;
	private String txt = null;
	
	public SaisirTexteE(SaisirTexte saisirTexte, Enregistrement enregistrateur){
		this.saisirTexte = saisirTexte;
		this.eng = enregistrateur;
	}

	@Override
	public void execute() {
		if(txt == null){
			saisirTexte.execute();
			txt = saisirTexte.getTxt();
			eng.enregistrer(this);
		}else{
			saisirTexte.setTxt(txt);
			saisirTexte.execute();
			txt = null;
		}
	}
	
	@Override
	public Memento getMemento() {
		MementoSaisirTexte m = new MementoSaisirTexte();
		m.setTxt(txt);
		return m;
	}
	
	@Override
	public void setMemento(Memento m) {
		this.txt = ((MementoSaisirTexte)m).getTxt();
	}


}
