package edu.asu.CSE360.listener;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.asu.CSE360.TestAndScoreObject;
import edu.asu.CSE360.TestProblem;
import edu.asu.CSE360.gui.DrawPanelTwo;
import edu.asu.CSE360.gui.IconConnection;
import edu.asu.CSE360.gui.JavaCodeArea;

public class TestButtonListener extends AbstractAction {
	private DrawPanelTwo dPanel;
	private JavaCodeArea jcArea;
	
	public TestButtonListener() {
		// TODO Auto-generated constructor stub
	}

	public TestButtonListener(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public TestButtonListener(String arg0, Icon arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public TestButtonListener(DrawPanelTwo dPanel, JavaCodeArea jcArea) {
		super();
		this.dPanel = dPanel;
		this.jcArea = jcArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String aComd = ae.getActionCommand();
		TestProblem tbObj = null;
		
		if ( aComd.equals("Eraser") ) {
			if ( dPanel.isAllowDrop() ) {
				Component[] cList = dPanel.getComponents();
	        	if ( cList == null || cList.length == 0) {
	        		JOptionPane.showMessageDialog(dPanel, "There is no component to be deleted", "Information", JOptionPane.INFORMATION_MESSAGE, null);
	        	} else {
	        		String[] column = {"Index", "Component Name"};
	        		JTable jt = new JTable( new DefaultTableModel(column, cList.length) );
	        		for ( int i = 0; i < cList.length; i++) {
	        			jt.setValueAt(""+(i+1), i, 0);
	        			jt.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
	        		}
	                                                
	        		int status = JOptionPane.showConfirmDialog(dPanel, jt, "Select to be deleted Component", JOptionPane.OK_CANCEL_OPTION);
	        		if ( status == JOptionPane.OK_OPTION) {
	        			int rowIndex[] = jt.getSelectedRows();
	        			for ( int i = rowIndex.length-1; i >=0; i-- ) {
	        				dPanel.remove(rowIndex[i]);
	        			}
	        			dPanel.repaint();
	        		}
	        	}
			}
		} else {
			int pIndex = Integer.parseInt(aComd.substring(1, aComd.length()))-1; 
			if ( TestAndScoreObject.getInstance().getProblemPassed(pIndex)) {
				JOptionPane.showMessageDialog(dPanel, "This problem is answered!");
				return;
			}
			TestAndScoreObject.getInstance().setCurrentProblemIndex(pIndex);
			jcArea.setText("");
			dPanel.removeAll();

			tbObj = TestAndScoreObject.getInstance().getProblems(pIndex);
			switch (tbObj.getProblemType()) {
			case TestProblem.QUESTION_TYPE_DIAGRAM:
				dPanel.setAllowDrop(false);
				jcArea.setEnabled(true);
				// add objects
				displayTestDiagram (tbObj);
				break;
			case TestProblem.QUESTION_TYPE_CODE:
				dPanel.setAllowDrop(true);
				try {
					displayTestText(tbObj);
					jcArea.setEnabled(false);
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				
				break;
			default:
				System.out.println("TestButtonListener.actionPerformed: Unknown problem type " + tbObj.getProblemType() );
			}
			dPanel.repaint();
		} 
	}

	private void displayTestText(TestProblem tbObj) throws Exception {
		jcArea.setText(tbObj.getTextPresentation());
	}
	
	private void displayTestDiagram(TestProblem tbObj) {
		List pList;
		Point startPoint = null, endPoint;
		
		if ( tbObj.getProblemType() == TestProblem.QUESTION_TYPE_DIAGRAM) {
			pList = tbObj.getIconObjList();
			for ( int i = 0; i < pList.size(); i++) {
				if ( pList.get(i) instanceof IconConnection ) {
					startPoint = ((IconConnection)pList.get(i)).getFromObj().getLocation();
					endPoint = ((IconConnection)pList.get(i)).getToObj().getLocation();
					((IconConnection)pList.get(i)).setFromPoint(startPoint);
					((IconConnection)pList.get(i)).setToPoint(endPoint);
					dPanel.add((Component)pList.get(i));
				} else {
					switch (i%2) {
					case 0:					
						startPoint = new Point (60+i*100, 100);						
						break;
					case 1:
						startPoint = new Point (60+i*100, 200);	
						break;
					}
					((JComponent) pList.get(i)).setLocation(startPoint);
					((JComponent) pList.get(i)).setSize(new Dimension(60,50));
					dPanel.add((Component)pList.get(i));
				}
			}
		}
	}
	
	public DrawPanelTwo getdPanel() {
		return dPanel;
	}

	public JavaCodeArea getJcArea() {
		return jcArea;
	}

	public void setJcArea(JavaCodeArea jcArea) {
		this.jcArea = jcArea;
	}

	public void setdPanel(DrawPanelTwo dPanel) {
		this.dPanel = dPanel;
	}

}
