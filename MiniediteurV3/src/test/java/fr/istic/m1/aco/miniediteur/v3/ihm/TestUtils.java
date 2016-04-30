package fr.istic.m1.aco.miniediteur.v3.ihm;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JMenu;

import org.apache.log4j.Logger;

public class TestUtils {
	private static final Logger logger = Logger.getLogger(TestUtils.class);
	static int counter;

	public static Component getChildNamed(Component parent, String name) {
		
		if(logger.isDebugEnabled()){
			logger.debug("Class: " + parent.getClass() +  " Name: " + parent.getName());
		}

		if (name.equals(parent.getName())) {
			return parent;
		}

		if (parent instanceof Container) {
			Component[] children = (parent instanceof JMenu) ? ((JMenu) parent).getMenuComponents() : ((Container) parent).getComponents();

			for (int i = 0; i < children.length; ++i) {
				Component child = getChildNamed(children[i], name);
				if (child != null) {
					return child;
				}
			}
		}

		return null;
	}

}
