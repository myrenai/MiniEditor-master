package fr.istic.m1.aco.miniediteur.v1.factory;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import fr.istic.m1.aco.miniediteur.v1.command.Command;
import fr.istic.m1.aco.miniediteur.v1.ihm.IHMText;
import fr.istic.m1.aco.miniediteur.v1.observer.Subject;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEdition;
import fr.istic.m1.aco.miniediteur.v1.receiver.MoteurEditionImpl;



public class CommandFactoryTest {
	CommandFactory commandFactory;
	MoteurEdition me;
	IHMText ihm;
	
	@Before
	public void setUp() throws Exception {
		me = new MoteurEditionImpl();
		ihm = new IHMText();
		commandFactory = new CommandFactory(me, ihm);
		((Subject) me).register(ihm);
	}

	@Test
	public void testGetCommand() {
		Command c = commandFactory.getCommand(CommandType.INSERT);
		assertEquals("SaisirTexte", c.getClass().getSimpleName());
		
		c = commandFactory.getCommand(CommandType.COPY);
		assertEquals("Copier", c.getClass().getSimpleName());
		
		c = commandFactory.getCommand(CommandType.CUT);
		assertEquals("Couper", c.getClass().getSimpleName());
		
		c = commandFactory.getCommand(CommandType.DELETE);
		assertEquals("Supprimer", c.getClass().getSimpleName());
		
		c = commandFactory.getCommand(CommandType.PASTE);
		assertEquals("Coller", c.getClass().getSimpleName());
		
		c = commandFactory.getCommand(CommandType.SELECTION);
		assertEquals("Selectionner", c.getClass().getSimpleName());
		
		c = commandFactory.getCommand(CommandType.MODIFY);
		assertEquals("Modifier", c.getClass().getSimpleName());
		
		
	}

}
