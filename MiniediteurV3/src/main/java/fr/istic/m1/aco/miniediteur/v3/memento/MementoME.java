package fr.istic.m1.aco.miniediteur.v3.memento;

import fr.istic.m1.aco.miniediteur.v3.receiver.Buffer;
import fr.istic.m1.aco.miniediteur.v3.receiver.PressPapier;
import fr.istic.m1.aco.miniediteur.v3.receiver.Selection;
/**
 * Concrete memento of the MoteurEdition class
 * @author jiyoung
 *
 */
public class MementoME implements Memento{
	private Buffer buffer;
	private Selection selection;
	private PressPapier pp;
	public Buffer getBuffer() {
		return buffer;
	}
	public void setBuffer(Buffer buffer) {
		this.buffer = buffer;
	}
	public Selection getSelection() {
		return selection;
	}
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
	public PressPapier getPp() {
		return pp;
	}
	public void setPp(PressPapier pp) {
		this.pp = pp;
	}
	public MementoME clone(){
		MementoME me = new MementoME();
		me.setBuffer(buffer.clone());
		me.setPp(pp.clone());
		me.setSelection(selection.clone());
		return me;
	}
	
	

}
