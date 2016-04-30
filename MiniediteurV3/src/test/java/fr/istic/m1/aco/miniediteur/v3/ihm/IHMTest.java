package fr.istic.m1.aco.miniediteur.v3.ihm;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import javax.swing.JTextField;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.istic.m1.aco.miniediteur.v3.factory.CommandFactory;
import fr.istic.m1.aco.miniediteur.v3.invoker.Invoker;
import fr.istic.m1.aco.miniediteur.v3.memento.Enregistrement;
import fr.istic.m1.aco.miniediteur.v3.observer.Observer;
import fr.istic.m1.aco.miniediteur.v3.observer.Subject;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v3.receiver.MoteurEditionImpl;

public class IHMTest {
	
	private IHMText ihmText;
	private IHMGui ihmGui;
	private JTextField input;
	
	@Rule  public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Before
	public void setUp() throws Exception {
		initTextIHM();
		initGuiIHM();
		
		insertGui();
		insertText();
	}
	
	private void initTextIHM(){
		MoteurEdition me = new MoteurEditionImpl();
		Enregistrement eng = new Enregistrement(me);
		ihmText = new IHMText();
		setIHMConfig(me, ihmText);
		ihmText.setCommandFactory(new CommandFactory(me, ihmText, eng));
		eng.register(ihmText);
	}
	
	private void initGuiIHM(){
		MoteurEdition me = new MoteurEditionImpl();
		ihmGui = new IHMGui();
		Enregistrement eng = new Enregistrement(me);
		setIHMConfig(me, ihmGui);
		ihmGui.setCommandFactory(new CommandFactory(me, ihmGui, eng));
		input = (JTextField)TestUtils.getChildNamed(ihmGui.getJframe(), "insertField");
		eng.register(ihmGui);
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
