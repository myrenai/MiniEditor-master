package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.memento.Memento;
import fr.istic.m1.aco.miniediteur.v3.memento.MementoSaisirTexte;
/**
 * This class is the modify concrete registrable command. 
 * @author jiyoung
 *
 */
public class ModifierE implements Command, Enregistable{
	private Modifier modifier;
	private String txt;
	private Enregistrement enregistrateur;
	
	public ModifierE(Modifier modifier, Enregistrement enregistrateur){
		this.modifier = modifier;
		this.enregistrateur = enregistrateur;
	}
	
	@Override
	public void execute() {
		if(txt == null){
			modifier.execute();
			txt = modifier.getTxt();
			enregistrateur.enregistrer(this);
		}else{
			modifier.setTxt(txt);
			modifier.execute();
			txt = null;
		}
		enregistrateur.addMemento();
	}
	
	@Override
	public Memento getMemento() {
		MementoSaisirTexte m = new MementoSaisirTexte();
		m.setTxt(txt);
		txt = null;
		return m;
	}
	
	@Override
	public void setMemento(Memento m) {
		this.txt = ((MementoSaisirTexte)m).getTxt();
	}
	

}
