package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.memento.Memento;
import fr.istic.m1.aco.miniediteur.v3.memento.MementoSaisirTexte;

/**
 * This class is the insert concrete registrable command. 
 * @author jiyoung
 *
 */
public class SaisirTexteE implements Command, Enregistable{
	private SaisirTexte saisirTexte;
	private Enregistrement enregistrateur;
	private String txt = null;
	
	public SaisirTexteE(SaisirTexte saisirTexte, Enregistrement enregistrateur){
		this.saisirTexte = saisirTexte;
		this.enregistrateur = enregistrateur;
	}

	@Override
	public void execute() {
		if(txt == null){
			saisirTexte.execute();
			txt = saisirTexte.getTxt();
			enregistrateur.enregistrer(this);
		}else{
			saisirTexte.setTxt(txt);
			saisirTexte.execute();
			txt = null;
		}
		enregistrateur.addMemento();
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
