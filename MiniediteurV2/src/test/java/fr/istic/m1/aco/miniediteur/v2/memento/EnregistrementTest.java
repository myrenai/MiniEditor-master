package fr.istic.m1.aco.miniediteur.v2.memento;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.istic.m1.aco.miniediteur.v2.command.Coller;
import fr.istic.m1.aco.miniediteur.v2.command.CollerE;
import fr.istic.m1.aco.miniediteur.v2.command.Copier;
import fr.istic.m1.aco.miniediteur.v2.command.CopierE;
import fr.istic.m1.aco.miniediteur.v2.command.Couper;
import fr.istic.m1.aco.miniediteur.v2.command.CouperE;
import fr.istic.m1.aco.miniediteur.v2.command.DemarreE;
import fr.istic.m1.aco.miniediteur.v2.command.Modifier;
import fr.istic.m1.aco.miniediteur.v2.command.ModifierE;
import fr.istic.m1.aco.miniediteur.v2.command.SaisirTexte;
import fr.istic.m1.aco.miniediteur.v2.command.SaisirTexteE;
import fr.istic.m1.aco.miniediteur.v2.command.Selectionner;
import fr.istic.m1.aco.miniediteur.v2.command.SelectionnerE;
import fr.istic.m1.aco.miniediteur.v2.command.SetCursorPosition;
import fr.istic.m1.aco.miniediteur.v2.command.SetCursorPositionE;
import fr.istic.m1.aco.miniediteur.v2.command.StopperE;
import fr.istic.m1.aco.miniediteur.v2.command.Supprimer;
import fr.istic.m1.aco.miniediteur.v2.command.SupprimerE;
import fr.istic.m1.aco.miniediteur.v2.ihm.IHMText;
import fr.istic.m1.aco.miniediteur.v2.observer.Subject;
import fr.istic.m1.aco.miniediteur.v2.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v2.receiver.MoteurEditionImpl;


public class EnregistrementTest {
	MoteurEdition me;
	IHMText ihm;
	Enregistrement eng;

	
	@Before
	public void setUp() throws Exception {
		me = new MoteurEditionImpl();
		eng = new Enregistrement();
		ihm = new IHMText();
		eng.register(ihm);
		((Subject) me).register(ihm);
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
	
	@Test
	public void observerTest(){
		executeStartE();
	}
	
	@Test
	public void macroTest(){
		String str = "aaaaa";
		String strModify = "ooo";
		String strAfterCut = "o";
		// 1 command and memento
		executeStartE();
		executeSaisirTextE(str);
		assertEquals(str, ihm.getContent());
		// 2 commands and mementos
		String copyString = str.substring(0, 3);
		executeCopierE(0,3);
		assertEquals(copyString, ihm.getClipboard());
		// 2 command and memento
		executeCollerE(5);
		assertEquals(str + copyString, ihm.getContent());
		// 1 command and memento
		executeModifierE(strModify);
		assertEquals(strModify, ihm.getContent());
		// 1 command and memento
		executeCouperE(0,2);
		assertEquals(strAfterCut, ihm.getContent());
		// 1 command and memento
		executeSupprimerE();
		assertEquals("", ihm.getContent());
		executeStopE();
		
		eng.rejouerE();
		
		assertEquals(9, eng.nbCommandsEng());
		assertEquals(eng.nbCommandsEng(), eng.nbMementoEng());
		assertEquals("", ihm.getContent());
	}

}
