package fr.istic.m1.aco.miniediteur.v2.ihm;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.istic.m1.aco.miniediteur.v2.factory.CommandFactory;
import fr.istic.m1.aco.miniediteur.v2.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v2.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v2.observer.Observer;
import fr.istic.m1.aco.miniediteur.v2.observer.Subject;
import fr.istic.m1.aco.miniediteur.v2.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v2.receiver.MoteurEditionImpl;
public class IHMTest {
	
	private IHMText ihmText;
	private IHMGui ihmGui;
	private JTextField input;
	
	@Rule  public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Before
	public void setUp() throws Exception {
		MoteurEdition me1 = new MoteurEditionImpl();
		Enregistrement eng1 = new Enregistrement();
		
		
		ihmText = new IHMText();
		setIHMConfig(me1, ihmText);
		ihmText.setCommandFactory(new CommandFactory(me1, ihmText, eng1));
		eng1.register(ihmText);
		
		MoteurEdition me2 = new MoteurEditionImpl();
		Enregistrement eng2 = new Enregistrement();
		ihmGui = new IHMGui();
		setIHMConfig(me2, ihmGui);
		ihmGui.setCommandFactory(new CommandFactory(me2, ihmGui, eng2));
		eng2.register(ihmGui);
		
		input = (JTextField)TestUtils.getChildNamed(ihmGui.getJframe(), "insertField");
		
		insertGui();
		insertText();
	}
	
	private void setIHMConfig(MoteurEdition me, Invoker ihm){
		((Subject) me).register((Observer)ihm);
	}
	
	private void insertText() {
		systemInMock.provideText("my first text\n");
		ihmText.insert();
		assertEquals(ihmText.getContent(), "my first text");
	}
	
	
	private void insertGui(){
		input.setText("my first text");
		ihmGui.insert();
		assertEquals(ihmGui.getContent(), "my first text");
	}
	
	///////////////////////////////////////////////////////////////////////////::
	
	@Test
	public void copyText(){
		systemInMock.provideText("3\n8\n");
		ihmText.copy();
		assertEquals(ihmText.getClipboard(), "first");
	}
	
	@Test
	public void copyGui(){
		input.select(3, 8);
		ihmGui.copy();
		assertEquals(ihmGui.getClipboard(), "first");
	}
	
	@Test
	public void pasteText(){
		copyText();
		systemInMock.provideText("0\n");
		ihmText.paste();
		assertEquals(ihmText.getContent(), "firstmy first text");
	}
	
	@Test
	public void pasteGui(){
		copyGui();
		input.select(0, 0);
		ihmGui.paste();
		assertEquals(ihmGui.getContent(), "firstmy first text");
	}
	
}
