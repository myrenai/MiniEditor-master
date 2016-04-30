package fr.istic.m1.aco.miniediteur.v3.client;

import java.util.Scanner;

import org.apache.log4j.Logger;

import fr.istic.m1.aco.miniediteur.v3.factory.CommandFactory;
import fr.istic.m1.aco.miniediteur.v3.ihm.BaseIHM;
import fr.istic.m1.aco.miniediteur.v3.ihm.IHMGui;
import fr.istic.m1.aco.miniediteur.v3.ihm.IHMText;
import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.observer.Subject;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEditionImpl;

/**
 * This class demonstrates the Mini-Editor of GUI version and  Text version. 
 * @author Jiyoung Park
 *
 */
public class Editor {
	private static final Logger logger = Logger.getLogger(Editor.class);
	private static Scanner sc = new Scanner(System.in);
	private final int TEXT_TYPE = 1;
	private final int GUI_TYPE = 2;

	public static void main(String[] args){
		logger.info("Mini Editor Version3");
		MoteurEdition me = new MoteurEditionImpl();
		Enregistrement eng = new Enregistrement(me);
		
		// Create a editor instance;
		Editor editor = new Editor();
		int editorType = editor.getEditorType();
		BaseIHM ihm = null;
		
		if(editorType == editor.TEXT_TYPE){
			ihm = new IHMText();
		}else if(editorType == editor.GUI_TYPE){
			ihm = new IHMGui();
		}
		
		eng.register(ihm);
		// set command factory
		ihm.setCommandFactory(new CommandFactory(me, ihm, eng));
		// BaseIHM implemented also the Observer interface of the Observer pattern.
		((Subject) me).register(ihm);
		ihm.start();
	}


	/**
	 * This method returns the editor's type number
	 * 1 => text type editor
	 * 2 => gui type editor
	 * @return number
	 */
	private int getEditorType(){
		
		logger.info("");
		logger.info("== Please select the type of the editor ==");
		logger.info("1) Text type, 2) GUI type");
		logger.info("");
		try {
			int menu = sc.nextInt();
			return menu;
		} catch (Exception e) {
			logger.error("please enter the editor type as number.");
			sc.nextLine();
			return getEditorType();
		}
	}
}
