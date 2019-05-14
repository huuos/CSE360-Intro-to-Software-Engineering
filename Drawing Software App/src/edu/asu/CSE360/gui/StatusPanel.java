package edu.asu.CSE360.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import edu.asu.CSE360.TestAndScoreObject;
import edu.asu.CSE360.TestProblem;

public class StatusPanel extends JPanel implements Observer {
	private JLabel iconItem;
	private Icon hapyFace;
	private Icon sadFace;
	private Icon OKFace;
	private JTextField scoreItem;
	private JPanel testButtonPanel;
	
	public StatusPanel() { 
		super();
		initGui();
	}
	public StatusPanel( LayoutManager lm) { 
		super(lm);
		initGui();
	}

	private void initGui () {
		setAlignmentX(Component.LEFT_ALIGNMENT);
		iconItem = new JLabel();
		scoreItem = new JTextField("   ");
		scoreItem.setPreferredSize(new Dimension(600,30));
		add ( iconItem);
		add ( scoreItem);
	}
	
	@Override
	public void update(Observable obsObj, Object source) {
		String text = null;
		String answer, uAnswer;
		
		if ( source instanceof ObservableIcon ) {
			if ( TestAndScoreObject.getInstance().getCurrentProblem().getProblemType() == TestProblem.QUESTION_TYPE_CODE ) {
				text = TestAndScoreObject.getInstance().getAnswerOfProblem((DrawPanelTwo)((ObservableIcon) source).getParent());	
			} else {
				return;
			}
		} else if (source instanceof JavaCodeArea ) {
			if ( TestAndScoreObject.getInstance().getCurrentProblem().getProblemType() == TestProblem.QUESTION_TYPE_DIAGRAM ) {
				text = ((JTextArea) source).getText();
			} else {
				return;
			}
		} 
		answer = TestAndScoreObject.getInstance().getCurrentProblem().getAnswer();
		answer = answer.replaceAll(" ", "").replaceAll("\r\n", "");
		uAnswer = text.replaceAll(" ", "").replaceAll("\r\n", "").replaceAll("\n", "");
		if ( answer.equals(uAnswer)) {
			TestAndScoreObject.getInstance().setCurrentProblemPassed(true);
			scoreItem.setText("Total Score: " + TestAndScoreObject.getInstance().getTotalScroe());
		} else {
			if ( text == null || text.trim().length() == 0 ) {
				scoreItem.setText(" ");
			} else {
				scoreItem.setText("Total Score: " + TestAndScoreObject.getInstance().getTotalScroe() +
						"; Problem "+(TestAndScoreObject.getInstance().getCurrentProblemIndex()+1)+" Incorrect answer:  " + text);
			}
		}
		//Disable the test button
		if ( TestAndScoreObject.getInstance().isCurrentProblemPassed()) {
			JButton jb = getTestButton ( TestAndScoreObject.getInstance().getCurrentProblemIndex());
			jb.setBackground(Color.GREEN);
		}
		if ( TestAndScoreObject.getInstance().getTotalScroe() >= 5) {
			iconItem.setIcon(hapyFace);
		} else {
			iconItem.setIcon(sadFace);
		}
	}

	private JButton getTestButton ( int bIndex) {
		return (JButton) testButtonPanel.getComponent(bIndex);		
	}
	
	public JLabel getIconItem() {
		return iconItem;
	}

	public JTextField getScoreItem() {
		return scoreItem;
	}

	public JPanel getTestButtonPanel() {
		return testButtonPanel;
	}
	public Icon getHapyFace() {
		return hapyFace;
	}
	public Icon getSadFace() {
		return sadFace;
	}
	public Icon getOKFace() {
		return OKFace;
	}
	public void setOKFace(Icon oKFace) {
		OKFace = oKFace;
	}
	public void setSadFace(Icon sadFace) {
		this.sadFace = sadFace;
		iconItem.setIcon(sadFace);
	}
	public void setHapyFace(Icon hapyFace) {
		this.hapyFace = hapyFace;
	}
	public void setTestButtonPanel(JPanel testButtonPanel) {
		this.testButtonPanel = testButtonPanel;
	}
	public void setScoreItem(JTextField scoreItem) {
		this.scoreItem = scoreItem;
	}

	public void setIconItem(JLabel iconItem) {
		this.iconItem = iconItem;
	}

}
