package fr.istic.m1.aco.miniediteur.v1.ihm;

import static org.junit.Assert.assertEquals;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Test;

import fr.istic.m1.aco.miniediteur.v1.factory.CommandFactory;
import fr.istic.m1.aco.miniediteur.v1.observer.Observer;
import fr.istic.m1.aco.miniediteur.v1.observer.Subject;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEditionImpl;

public class IHMGuiTest {
	private IHMGui ihmGui;
	private JTextField input;
	
	@Before
	public void setUp() throws Exception {
		MoteurEdition me = new MoteurEditionImpl();
		ihmGui = new IHMGui();
		((Subject) me).register((Observer)ihmGui);
		ihmGui.setCommandFactory(new CommandFactory(me, ihmGui));
		
		input = (JTextField)TestUtils.getChildNamed(ihmGui.getJframe(), "insertField");
	}
	
	private void insert(){
		input.setText("my first text");
		ihmGui.insert();
	}
	
	private void copy(){
		input.select(3, 8);
		ihmGui.copy();
	}
	
	private void paste(){
		input.select(0, 0);
		ihmGui.paste();
		assertEquals(ihmGui.getContent(), "firstmy first text");
	}
	
	private void modify(){
		input.setText("my second text");
		ihmGui.modify();
	}
	
	private void delete(){
		ihmGui.delete();
	}
	
	@Test
	public void insertTest() {
		insert();
		assertEquals(ihmGui.getContent(), "my first text");
	}
	
	@Test
	public void copyTest(){
		insert();
		assertEquals(ihmGui.getContent(), "my first text");
		
		copy();
		assertEquals(ihmGui.getClipboard(), "first");
	}
	
	@Test
	public void pasteTest(){
		// insert
		insert();
		assertEquals(ihmGui.getContent(), "my first text");
		// copy
		copy();
		assertEquals(ihmGui.getClipboard(), "first");
		// paste		
		paste();
		assertEquals(ihmGui.getContent(), "firstmy first text");
	}
	
	@Test
	public void modifyTest(){
		modify();
		assertEquals(ihmGui.getContent(), "my second text");
	}
	
	@Test
	public void deleteTest(){
		delete();
		assertEquals(ihmGui.getContent(), "");
	}

}
