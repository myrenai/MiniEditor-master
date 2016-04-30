package fr.istic.m1.aco.miniediteur.v3.invoker;

import fr.istic.m1.aco.miniediteur.v3.command.Command;



public interface Invoker{
	public int getDebut() ;
	public int getFin() ;
	public String getTxt();
	public void invoke(Command c);
}
