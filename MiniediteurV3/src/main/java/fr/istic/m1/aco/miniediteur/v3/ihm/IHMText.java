package fr.istic.m1.aco.miniediteur.v3.ihm;

import java.util.Scanner;

import org.apache.log4j.Logger;


public class IHMText extends BaseIHM{
	private static final Logger logger = Logger.getLogger(IHMText.class);
	private Scanner sc = new Scanner(System.in);
	
	
	/**
	 * Gets the user inputed number
	 * @return
	 */
	private int getNumberFromScanner(){
		try {
			int menu = sc.nextInt();
			return menu;
		} catch (Exception e) {
			logger.error("please enter a number.");
			sc.nextLine();
			return getNumberFromScanner();
		}
	}
	
	/**
	 * Prints the menu
	 */
	private void printMenu(){
		logger.info("");
		logger.info("== select menu ==");
		System.out.print("1) Saisir Texte, 2) Copier, 3) Coller, 4) Couper, 5) Modifier, 6) Supprimer");
		
		if(startEFlag)
			System.out.print(", 7) Démarrer Enregistrement");
		if(!startEFlag)
			System.out.print(", 8) Stopper Enregistrement");
		if(replayEFlag)
			System.out.print(", 9) Rejouer");
		
		if(undoFlag)
			System.out.print(", 10) Undo");
		if(redoFlag)
			System.out.print(", 11) Redo");
		
		logger.info(", -1) Exist");
		logger.info("");
	}
	
	/////////////// parent class's abstract methods implementations ///////////////
	protected void insert(){
		logger.info("---------------------");
		logger.info("Saisir Texte...");
		super.insert();
	}
	
	protected void copy(){
		logger.info("---------------------");
		logger.info("Copier...");
		super.copy();
	}
	
	protected void paste(){
		logger.info("---------------------");
		logger.info("Coller...");
		super.paste();
	}
	protected void cut(){
		logger.info("---------------------");
		logger.info("Couper...");
		super.cut();
	}
	
	protected void modify(){
		logger.info("---------------------");
		logger.info("Modifier...");
		super.modify();
	}
	
	protected void delete(){
		logger.info("---------------------");
		logger.info("Supprimer...");
		super.delete();
	}
	
	protected void exite(){
		logger.info("---------------------");
		logger.info("Au revoir...");
		sc.close();
		System.exit(0);
	}
	
	protected void deparrerE(){
		logger.info("---------------------");
		logger.info("Démarrer Enregistrement...");
		super.deparrerE();
	}
	
	protected void stopperE(){
		logger.info("---------------------");
		logger.info("Arrêter Enregistrement...");
		super.stopperE();
	}
	
	protected void rejouerE(){
		logger.info("---------------------");
		logger.info("Rejouer Enregistrement...");
		super.rejouerE();
	}
	
	protected void undo(){
		logger.info("---------------------");
		logger.info("Undo...");
		super.undo();
	}
	
	protected void redo(){
		logger.info("---------------------");
		logger.info("Redo...");
		super.redo();
	}
	
	/**
	 * IHM's start point
	 */
	@Override
	public void start(){
		while(true){
			printMenu();
			int menuNum = getNumberFromScanner();
			switch (menuNum) {
			case 1:
				insert();
				break;
			case 2:
				copy();
				break;
			case 3:
				paste();
				break;
			case 4:
				cut();
				break;
			case 5:
				modify();
				break;
			case 6:
				delete();
				break;
			case 7:
				deparrerE();
				break;
			case 8:
				stopperE();
				break;
			case 9:
				rejouerE();
				break;
			case 10:
				undo();
				break;
			case 11:
				redo();
				break;
			case -1:
				exite();
				break;
			default:
				break;
			}
		}
	}
	/**
	 * Prints the shared attributes - clip board, content - with moteurEdition
	 * Called by parent class's update method
	 */
	@Override
	protected void printContent() {
		logger.info("commandName : "+ getCommandName());
		logger.info("Press Papier : ["+ clipboard + "]");
		logger.info("content : "+ getContentWithCursor());
	}
	/**
	 * Returns the start point of the content.
	 */
	@Override
	public int getDebut() {
		int debut = 0;
		while(true){
			logger.info("Debut?");
			debut = getNumberFromScanner();
			if(debut < 0){
				logger.error("The 'debut' must be >= 0. You entered " + debut + ". Please try it again\n");
			}else if(debut > content.length()){
				logger.error("The 'debut' must be >= 0 and < " + content.length() + "(content's length). Please try it again\n");
			}else{
				break;
			}
		}
		this.debut = debut;
		return debut;
	}
	/**
	 * Returns the end point of the content
	 */
	@Override
	public int getFin() {
		int fin = 0;
		while(true){
			logger.info("Fin?");
			fin = getNumberFromScanner();
			if(fin < 0){
				logger.error("The 'fin' must be >= 0. You entered " + fin + ". Please try it again\n");
			}else if(fin < debut){
				logger.error("violated fin <= debut condition. current debut is "+ debut + ".\n");
			}else if(fin > content.length()){
				logger.error("The 'fin' must be >= 0 and < " + content.length() + "(content's length). Please try it again\n");
			}else{
				break;
			}
		}
		return fin;
	}
	/**
	 * Returns the user inputed text
	 */
	@Override
	public String getTxt() {
		String str = sc.nextLine();
		if (!str.equals(""))
			return str;
		else
			return sc.nextLine();
	}
}
