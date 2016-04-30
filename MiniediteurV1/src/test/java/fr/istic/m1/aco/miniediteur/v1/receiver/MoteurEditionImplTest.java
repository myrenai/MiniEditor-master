package fr.istic.m1.aco.miniediteur.v1.receiver;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.istic.m1.aco.miniediteur.v1.ihm.IHMText;
import fr.istic.m1.aco.miniediteur.v1.observer.Observer;
import fr.istic.m1.aco.miniediteur.v1.observer.Subject;

public class MoteurEditionImplTest {
	MoteurEdition me;
	IHMText ihm;
	
	@Before
	public void setUp() throws Exception {
		me = new MoteurEditionImpl();
		ihm = new IHMText();
		((Subject) me).register((Observer)ihm);
	}

	@Test
	public void insertTest() {
		me.saisirTexte("my first text");
		assertEquals(ihm.getContent(), "my first text");
	}
	
	@Test
	public void copyTest(){
		me.saisirTexte("my first text");
		me.selectionner(0, 3);
		me.copier();
		assertEquals(ihm.getClipboard(), "my ");
	}
	
	@Test
	public void pasteTest(){
		me.saisirTexte("my first text");
		me.selectionner(0, 3);
		me.copier();
		me.setCusorPosition(5);
		me.coller();
		assertEquals(ihm.getClipboard(), "my ");
		assertEquals(ihm.getContent(), "my fimy rst text");
	}
	
	@Test
	public void cutTest(){
		me.saisirTexte("my first text");
		me.selectionner(0, 3);
		me.copier();
		me.couper();
		
		assertEquals(ihm.getClipboard(), "my ");
		assertEquals(ihm.getContent(), "first text");
	}
	
	@Test
	public void modifyTest(){
		me.saisirTexte("my first text");
		assertEquals(ihm.getContent(), "my first text");
		
		me.modifier("fff");
		assertEquals(ihm.getContent(), "fff");
	}
}
