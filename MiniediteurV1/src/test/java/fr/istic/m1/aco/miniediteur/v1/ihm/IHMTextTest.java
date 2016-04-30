package fr.istic.m1.aco.miniediteur.v1.ihm;

import static org.junit.Assert.assertEquals;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.istic.m1.aco.miniediteur.v1.factory.CommandFactory;
import fr.istic.m1.aco.miniediteur.v1.observer.Subject;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEditionImpl;

public class IHMTextTest {

private IHMText ihmText;
	
	@Rule  public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Before
	public void setUp() throws Exception {
		MoteurEdition me = new MoteurEditionImpl();
		ihmText = new IHMText();
		((Subject) me).register(ihmText);
		ihmText.setCommandFactory(new CommandFactory(me, ihmText));
	}
	
	@Test
	public void insert() {
		systemInMock.provideText("my first text\n");
		ihmText.insert();
		assertEquals(ihmText.getContent(), "my first text");
	}
	
	@Test
	public void copy(){
		systemInMock.provideText("my first text\n");
		ihmText.insert();
		assertEquals(ihmText.getContent(), "my first text");
		
		systemInMock.provideText("3\n8\n");
		ihmText.copy();
		assertEquals(ihmText.getClipboard(), "first");
	}
	
	@Test
	public void paste(){
		// insert
		systemInMock.provideText("my first text\n");
		ihmText.insert();
		assertEquals(ihmText.getContent(), "my first text");
		// copy
		systemInMock.provideText("3\n8\n");
		ihmText.copy();
		assertEquals(ihmText.getClipboard(), "first");
		// paste		
		systemInMock.provideText("0\n");
		ihmText.paste();
		assertEquals(ihmText.getContent(), "firstmy first text");
	}
	
	@Test
	public void modify(){
		systemInMock.provideText("my second text\n");
		ihmText.modify();
		assertEquals(ihmText.getContent(), "my second text");
	}
	
	@Test
	public void delete(){
		ihmText.delete();
		assertEquals(ihmText.getContent(), "");
	}

}
