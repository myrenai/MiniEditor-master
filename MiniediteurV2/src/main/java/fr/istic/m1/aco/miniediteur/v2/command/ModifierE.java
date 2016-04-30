package fr.istic.m1.aco.miniediteur.v2.command;

import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.memento.Memento;
import fr.istic.m1.aco.miniediteur.v2.memento.MementoSaisirTexte;
/**
 * This class is the modify concrete registrable command. 
 * @author Jiyoung Park
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
