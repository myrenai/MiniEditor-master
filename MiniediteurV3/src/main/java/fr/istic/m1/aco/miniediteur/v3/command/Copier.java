package fr.istic.m1.aco.miniediteur.v3.command;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
/**
 * This class is the copy concrete command. 
 * @author Jiyoung Park
 *
 */
public class Copier implements Command{
	private MoteurEdition me;
	public Copier(MoteurEdition me) {
		this.me = me;
	}
	@Override
	public void execute() {
		me.copier();
	}
	
	@Override
	public String toString() {
		return "Copier []";
	}
}
