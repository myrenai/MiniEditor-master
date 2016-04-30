package fr.istic.m1.aco.miniediteur.v2.command;
import fr.istic.m1.aco.miniediteur.v2.receiver.MoteurEdition;
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
}
