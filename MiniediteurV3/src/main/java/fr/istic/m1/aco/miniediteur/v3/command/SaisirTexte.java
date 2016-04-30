package fr.istic.m1.aco.miniediteur.v3.command;

import fr.istic.m1.aco.miniediteur.v3.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
/**
 * This class is the insert concrete command. 
 * @author Jiyoung Park
 *
 */
public class SaisirTexte implements Command{
	private MoteurEdition me;
	private Invoker ihm;
	private String txt;
	
	public SaisirTexte(MoteurEdition me, Invoker ihm){
		this.me = me;
		this.ihm = ihm;
	}

	@Override
	public void execute() {
		if(txt == null){
			// if txt is empty, get txt from the ihm.
			txt = ihm.getTxt();
		}
		me.saisirTexte(txt);
	}
	
	/**
	 * call by SaisirTextE.execute()
	 * @return
	 */
	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

}
