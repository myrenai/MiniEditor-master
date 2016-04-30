package fr.istic.m1.aco.miniediteur.v2.memento;
/**
 * Concrete Memento class to save the inserted text information
 * @author Jiyoung Park
 *
 */
public class MementoSaisirTexte implements Memento{
	
	public MementoSaisirTexte(){
		
	}
	
	private String txt;

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}
	
}
