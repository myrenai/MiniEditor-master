package fr.istic.m1.aco.miniediteur.v1.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/** 
 * This class implements an Graphic UserInterfaced application that
 * simply offers editor functions.
 * @author Jiyoung Park
 */
public class IHMGui extends BaseIHM {
	
	private JFrame jframe;
	/**
	 * leftContentArea has the text inputed from the user
	 */
	private JTextArea leftContentArea;
	
	/**
	 * After the user selects some text and pushes the Copy button or the Cut button,
	 * the selected text will be appeared here.
	 */
	private JTextArea rightClipBoardArea;
	
	/**
	 * User's working field.
	 */
	private JTextField insertField;
	// button's action listener
	private ButtonListener buttonListener;
	// buttons version1
	private JButton insertB;
	private JButton modifyB;
	private JButton copyB;
	private JButton pasteB;
	private JButton cutB;
	private JButton clearB;
	
	public IHMGui(){
		this.jframe = new JFrame("Mini Editor");
		buttonListener = new ButtonListener();
		setFrameOutline();
		composeWorkingArea();
		setButtons();
	}
	
	/**
	 * set the look and feel, the close event and the size of this window
	 */
	private void setFrameOutline(){
		// Look and Feel
		jframe.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = new Dimension((int) (screenSize.width / 2), (int) (screenSize.height / 2));
		int x = (int) (frameSize.width / 2);
		int y = (int) (frameSize.height / 2);
		jframe.setBounds(x, y, frameSize.width, frameSize.height);
	}
	
	/**
	 * This method composes the main area
	 */
	private void composeWorkingArea(){
		// ----------------------------------------
		// |                 |                    |
		// | leftContentArea | rightClipBoardArea |
		// |                 |                    |
		// ----------------------------------------
		// | InsertField                          |
		// ----------------------------------------
        
        leftContentArea = new JTextArea();
        leftContentArea.setBorder(new TitledBorder("Zone Travail"));
        leftContentArea.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(leftContentArea);
        
        rightClipBoardArea = new JTextArea();
        rightClipBoardArea.setBorder(new TitledBorder("Clip Board"));
        rightClipBoardArea.setEnabled(false);
        JScrollPane scrollPane2 = new JScrollPane(rightClipBoardArea);
        
        insertField = new JTextField(60);
        insertField.setName("insertField");
        insertField.addKeyListener(new KeyAdapter(){
        	/**
        	 * This event method allows avoid the StringIndexOutOfBoundsException
        	 */
        	public void keyReleased(KeyEvent e) {
        		if(!insertField.getText().equals(content)){
					copyB.setEnabled(false);
					cutB.setEnabled(false);
				}else{
					copyB.setEnabled(true);
					cutB.setEnabled(true);
				}
              }
        });
        
        JPanel textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new GridLayout(1,2));
        textAreaPanel.add(scrollPane1);
        textAreaPanel.add(scrollPane2);
        
        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(textAreaPanel, BorderLayout.CENTER);
        mainPanel.add(insertField, BorderLayout.SOUTH);
        
        jframe.getContentPane().add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * This method composes all buttons
	 */
	private void setButtons(){
		JPanel buttonBasePanel = new JPanel();
		buttonBasePanel.setLayout(new BoxLayout(buttonBasePanel, BoxLayout.Y_AXIS));
		
		// version 1 buttons
		ButtonGroup buttonGroup1 = new ButtonGroup();
		JPanel bPanel1 = new JPanel();
		bPanel1.setBorder(new TitledBorder("Mini Editor V1 Buttons"));
		insertB = addButton(buttonGroup1, bPanel1, "Insert");
		modifyB = addButton(buttonGroup1, bPanel1, "Modify");
		copyB = addButton(buttonGroup1, bPanel1, "Copy");
		pasteB = addButton(buttonGroup1, bPanel1, "Paste");
		cutB = addButton(buttonGroup1, bPanel1, "Cut");
		clearB = addButton(buttonGroup1, bPanel1, "Clear");
		refreshButtons();
		
		buttonBasePanel.add(bPanel1);
		jframe.getContentPane().add(buttonBasePanel, BorderLayout.SOUTH);
	}
	
	/**
	 * This method 
	 *     1. creates a JButton
	 *     2. sets a Action Command and the buttonListener at the button created
	 *     3. adds this button at the referenced JPanel and ButtonGroup
	 * @param buttonGroup 
	 * @param panel
	 * @param buttonTitle
	 * @return
	 */
	private JButton addButton(ButtonGroup buttonGroup, JPanel panel, String buttonTitle){
		JButton bt = new JButton(buttonTitle);
		bt.setActionCommand(buttonTitle);
		bt.addActionListener(buttonListener);
		panel.add(bt);
		buttonGroup.add(bt);
		return bt;
	}
	
	/**
	 * This method will be called after all the button pressed event.
	 * When the content is empty, the insert button will be enabled.
	 * When the content is not empty, the insert button will be disabled. 
	 */
	private void refreshButtons(){
		if(this.content.length()>0){
			insertB.setEnabled(false);
			modifyB.setEnabled(true);
			copyB.setEnabled(true);
			pasteB.setEnabled(true);
			cutB.setEnabled(true);
			clearB.setEnabled(true);
		}else{
			insertB.setEnabled(true);
			modifyB.setEnabled(false);
			copyB.setEnabled(false);
			pasteB.setEnabled(false);
			cutB.setEnabled(false);
			clearB.setEnabled(false);
		}
	}
	
	/////////////////////////////////////////////////////////
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String commandName = e.getActionCommand();
			if (commandName.equals("Insert")){
				insert();
			}else if (commandName.equals("Modify")){
				modify();
			}else if (commandName.equals("Copy")){
				copy();
			}else if (commandName.equals("Paste")){
				paste();
			}else if (commandName.equals("Cut")){
				cut();
			}else if (commandName.equals("Clear")){
				delete();
				leftContentArea.setText("");
				rightClipBoardArea.setText("");
			}
			refreshButtons();
		}
	}
	
	/////////////// parent class's abstract methods implementations ///////////////
	
	@Override
	public void start() {
		jframe.setVisible(true);
	}
	
	/**
	 * This method shows
	 *     1. the simulated content on the leftContentArea.
	 *     2. the clip board content on the rightClipBoardArea.
	 *     3. the plain content on the insertField.
	 * @see getContentWithCursor();
	 */
	@Override
	public void printContent(){
		leftContentArea.append("[" + getCommandName() + "] >" + getContentWithCursor() + "\n");
		rightClipBoardArea.append("[" + getCommandName() + "] >" + this.clipboard + "\n");
		insertField.setText(this.content);			
	}
	
	@Override
	public int getDebut() {
		return insertField.getSelectionStart();
	}

	@Override
	public int getFin() {
		return insertField.getSelectionEnd();
	}

	@Override
	public String getTxt() {
		return insertField.getText();
	}

	//////////////////JUnit test support methods //////////////////////////
	
	JFrame getJframe() {
		return jframe;
	}
	
}
