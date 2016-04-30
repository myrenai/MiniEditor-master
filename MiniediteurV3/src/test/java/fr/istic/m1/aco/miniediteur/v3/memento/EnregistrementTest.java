package fr.istic.m1.aco.miniediteur.v3.memento;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.istic.m1.aco.miniediteur.v3.command.Coller;
import fr.istic.m1.aco.miniediteur.v3.command.CollerE;
import fr.istic.m1.aco.miniediteur.v3.command.Copier;
import fr.istic.m1.aco.miniediteur.v3.command.CopierE;
import fr.istic.m1.aco.miniediteur.v3.command.Couper;
import fr.istic.m1.aco.miniediteur.v3.command.CouperE;
import fr.istic.m1.aco.miniediteur.v3.command.Defaire;
import fr.istic.m1.aco.miniediteur.v3.command.DemarreE;
import fr.istic.m1.aco.miniediteur.v3.command.Modifier;
import fr.istic.m1.aco.miniediteur.v3.command.ModifierE;
import fr.istic.m1.aco.miniediteur.v3.command.Refaire;
import fr.istic.m1.aco.miniediteur.v3.command.SaisirTexte;
import fr.istic.m1.aco.miniediteur.v3.command.SaisirTexteE;
import fr.istic.m1.aco.miniediteur.v3.command.Selectionner;
import fr.istic.m1.aco.miniediteur.v3.command.SelectionnerE;
import fr.istic.m1.aco.miniediteur.v3.command.SetCursorPosition;
import fr.istic.m1.aco.miniediteur.v3.command.SetCursorPositionE;
import fr.istic.m1.aco.miniediteur.v3.command.StopperE;
import fr.istic.m1.aco.miniediteur.v3.command.Supprimer;
import fr.istic.m1.aco.miniediteur.v3.command.SupprimerE;
import fr.istic.m1.aco.miniediteur.v3.ihm.IHMText;
import fr.istic.m1.aco.miniediteur.v3.observer.Observer;
import fr.istic.m1.aco.miniediteur.v3.observer.Subject;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEditionImpl;
public class EnregistrementTest {
	MoteurEdition me;
	IHMText ihm;
	Enregistrement eng;
	String str = "aaaaa";
	String strModify = "ooo";
	String strAfterCut = "o";
	String copyString = str.substring(0, 3);
	
	@Before
	public void setUp() throws Exception {
		me = new MoteurEditionImpl();
		eng = new Enregistrement(me);
		ihm = new IHMText();
		((Subject) me).register((Observer)ihm);
		eng.register(ihm);
	}
	
	private void executeStartE(){
		System.out.println("start register the commands >>>>>>>>>>>>>>>>>>>>");
		DemarreE e = new DemarreE(eng);
		ihm.setCommandName(e.getClass().getSimpleName());
		e.execute();
	}
	
	private void executeStopE(){
		StopperE e = new StopperE(eng);
		ihm.setCommandName(e.getClass().getSimpleName());
		e.execute();
		System.out.println("<<<<<<<<<<<<<<<< stop register the commands");
	}
	
	private void executeSaisirTextE(String txt){
		SaisirTexte c = new SaisirTexte(me, ihm);
		c.setTxt(txt);
		SaisirTexteE e = new SaisirTexteE(c, eng);
		ihm.setCommandName(e.getClass().getSimpleName());
		e.execute();
	}
	
	private void executeCopierE(int debut, int fin){
		Selectionner s = new Selectionner(me, ihm);
		s.setDebut(debut);
		s.setFin(fin);
		SelectionnerE ss = new SelectionnerE(s, eng);
		ss.execute();
		
		CopierE e = new CopierE(new Copier(me),eng);
		ihm.setCommandName(e.getClass().getSimpleName());
		e.execute();
	}
	
	private void executeCollerE(int cursor){
		SetCursorPosition s = new SetCursorPosition(me, ihm);
		s.setCursor(cursor);
		SetCursorPositionE spe = new SetCursorPositionE(s, eng);
		spe.execute();
		
		CollerE e = new CollerE(new Coller(me),eng);
		ihm.setCommandName(e.getClass().getSimpleName());
		e.execute();
	}
	
	private void executeModifierE(String txt){
		Modifier m = new Modifier(me, ihm);
		m.setTxt(txt);
		ModifierE me = new ModifierE(m, eng);
		ihm.setCommandName(me.getClass().getSimpleName());
		me.execute();
	}
	
	private void executeSupprimerE(){
		Supprimer s = new Supprimer(me);
		SupprimerE se = new SupprimerE(s, eng);
		ihm.setCommandName(se.getClass().getSimpleName());
		se.execute();
	}
	
	private void executeCouperE(int debut, int fin){
		Selectionner s = new Selectionner(me, ihm);
		s.setDebut(debut);
		s.setFin(fin);
		SelectionnerE ss = new SelectionnerE(s, eng);
		ss.execute();
		Couper c = new Couper(me);
		CouperE e = new CouperE(c,eng);
		ihm.setCommandName(e.getClass().getSimpleName());
		e.execute();
	}
	
	private void undo(){
		Defaire undo = new Defaire(eng);
		undo.execute();
	}
	
	private void redo(){
		Refaire undo = new Refaire(eng);
		undo.execute();
	}
	
	private void executeCommands(){
		// 1.insert
		executeSaisirTextE(str);
		assertEquals(str, ihm.getContent());
		// 2.copy
		String copyString = str.substring(0, 3);
		executeCopierE(0,3);
		assertEquals(copyString, ihm.getClipboard());
		// 3.coller
		executeCollerE(5);
		assertEquals(str + copyString, ihm.getContent());
		// 4.modifier
		executeModifierE(strModify);
		assertEquals(strModify, ihm.getContent());
		// 5.cut
		executeCouperE(0,2);
		assertEquals(strAfterCut, ihm.getContent());
		// 6.delete
		executeSupprimerE();
		assertEquals("", ihm.getContent());
	}
	
	@Test
	public void macroTest(){
		
		executeStartE();
		executeCommands();
		executeStopE();
		
		eng.rejouerE();
		
		assertEquals(9, eng.nbCommandsEng());
		assertEquals(eng.nbCommandsEng(), eng.nbMementoEng());
		assertEquals("", ihm.getContent());
	}

	@Test
	public void undoTest() {
		
		executeCommands();

		undo();// 5. after cut
		assertEquals(strAfterCut, ihm.getContent());
		
		undo();// 4.after modifier
		assertEquals(strModify, ihm.getContent());
		
		undo();// 3.after paste
		assertEquals(str + copyString, ihm.getContent());
		
		// copy isn't registered because there is no change of the buffer.
		
		undo();// 1.after insert
		assertEquals(str, ihm.getContent());
		
		undo();// 0. init status
		assertEquals("", ihm.getContent());
		
	}
	
	@Test
	public void redoTest(){
		
		undoTest();
		
		redo();// 1.after insert
		assertEquals(str, ihm.getContent());
		
		redo();// 3.after paste
		assertEquals(str + copyString, ihm.getContent());
		
		redo();// 4.after modifier
		assertEquals(strModify, ihm.getContent());
		
		redo();// 5. after cut
		assertEquals(strAfterCut, ihm.getContent());
		
		redo();// 6.after delete
		assertEquals("", ihm.getContent());
	}

}
