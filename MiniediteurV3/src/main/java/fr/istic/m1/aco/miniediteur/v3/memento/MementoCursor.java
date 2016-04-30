package fr.istic.m1.aco.miniediteur.v3.memento;
/**
 * Concrete Memento class to save the cursor information
 * @author Jiyoung Park
 *
 */
public class MementoCursor implements Memento{
	
	private int cursor;

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

	

}
