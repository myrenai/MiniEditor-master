package fr.istic.m1.aco.miniediteur.v1.ihm;

import fr.istic.m1.aco.miniediteur.v1.command.Command;
import fr.istic.m1.aco.miniediteur.v1.factory.CommandFactory;
import fr.istic.m1.aco.miniediteur.v1.factory.CommandType;
import fr.istic.m1.aco.miniediteur.v1.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v1.observer.Observer;

/** 
 * This is the parent class of the IHMText class and the IHMGui class.
 * This class takes a role of  
 *     1. the Invoker which has a method invoke() that when executed 
 *        causes the execute method to be called
 *     2. the Observer that is monitoring changes in the subject
 *     3. the View of the MVC Pattern
 * @author Jiyoung Park
 */
public abstract class BaseIHM  implements Invoker, Observer{
	protected CommandFactory commandFactory;
	protected String content = "";
	protected String clipboard = "";
	protected int debut = 0;
	protected int fin = 0;
	
	protected String commandName;
	
	public BaseIHM() {
	}
	
	/**
	 * Gets the character offset that indicates the start of the content.
	 */
	public abstract int getDebut();
	
	/**
	 * Gets the character offset that indicates the end of the content.
	 */
	public abstract int getFin();
	
	/**
	 * Gets the text inputed by the user in the child classes
	 */
	public abstract String getTxt();
	
	/**
	 * Prints all observed attributes in the child classes
	 */
	protected abstract void printContent();
	
	/**
	 * Start point
	 */
	public abstract void start();
	
	
	/**
	 * Called by the subject object to update and print all observed attributes
	 */
	@Override
	public void update(String zoneT, int debut, int fin, String pressPapier) {
		this.content = zoneT;
		this.debut = debut;
		this.fin = fin;
		this.clipboard = pressPapier;
		printContent();
	}
	
	/**
	 * Command pattern's invoker method for which execute the registered command
	 */
	@Override
	public void invoke(Command c) {
		setCommandName(c.getClass().getSimpleName());
		c.execute();
	}
	
	public void setCommandFactory(CommandFactory commandFactory) {
		this.commandFactory = commandFactory;
	}

	//////////////////////////////////////////////////////////////////////////
	
	protected void insert(){
		invoke(commandFactory.getCommand(CommandType.INSERT));
	}
	protected void copy(){
		invoke(commandFactory.getCommand(CommandType.SELECTION));
		invoke(commandFactory.getCommand(CommandType.COPY));
	}
	protected void paste(){
		invoke(commandFactory.getCommand(CommandType.CURSOR));
		invoke(commandFactory.getCommand(CommandType.PASTE));
	}
	protected void cut(){
		invoke(commandFactory.getCommand(CommandType.SELECTION));
		invoke(commandFactory.getCommand(CommandType.CUT));
	}
	protected void modify(){
		invoke(commandFactory.getCommand(CommandType.MODIFY));
	}
	protected void delete(){
		invoke(commandFactory.getCommand(CommandType.DELETE));
	}
	
	/**
	 * Simple common method for the concrete ihm classes.
	 * This method returns a user inputed text decorated with '[' and ']'
	 * This decoration shows user's selection boundary.
	 * @return
	 */
	protected String getContentWithCursor() {
		StringBuffer copy = new StringBuffer(content);
		copy.insert(debut, "[");
		copy.insert(fin + 1, "]");
		return copy.toString();
	}
	
	/**
	 * This method returns the current command's name
	 * @return
	 */
	protected String getCommandName(){
		return commandName;
	}
	public void setCommandName(String commandName){
		this.commandName = commandName;
	}
	
	////////////////// JUnit test support methods //////////////////////////
	
	public String getContent(){
		return content;
	}
	
	public String getClipboard(){
		return clipboard;
	}
}
