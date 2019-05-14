package edu.asu.CSE360;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import edu.asu.CSE360.gui.IconCircle;
import edu.asu.CSE360.gui.IconConnection;
import edu.asu.CSE360.gui.IconSquare;
import edu.asu.CSE360.gui.ObservableIcon;

public class TestProblem {
	public static final int QUESTION_TYPE_DIAGRAM 	= 1;
	public static final int QUESTION_TYPE_CODE 		= 2;
	
	private int problemType;
	private boolean passed;
	private List<String> pDescriptions = new ArrayList<String>();
	private List codeList;
	private List iconObjList;
	private String answer;
	
	public TestProblem() {
	}
	
	public TestProblem( int problemType) {
		this();
		this.setProblemType(problemType);
	}
	public TestProblem( int problemType, List pDescriptions) throws Exception {
		this();
		this.setProblemType(problemType);
		this.pDescriptions = pDescriptions;
		List objList = getObservableIconObject();
		if ( problemType == TestProblem.QUESTION_TYPE_DIAGRAM) {
			iconObjList = objList;
		} else {
			codeList = new ArrayList();
			for ( int i = 0; i < objList.size(); i++) {
				codeList.add(((ObservableIcon)objList.get(i)).toString());
			}
		}
	}

	public void createAnswer() throws Exception {
		answer = getTextPresentation (iconObjList);
	}
	
	public String getTextPresentation ( ) throws Exception {
		String result = "";
		if ( this.getProblemType() == TestProblem.QUESTION_TYPE_CODE) {
			for ( int i = 0; i < codeList.size(); i++) {
				result += codeList.get(i) + "\r\n";
			}
		} else if ( this.getProblemType() == TestProblem.QUESTION_TYPE_DIAGRAM) {
			result = getTextPresentation ( iconObjList );
		} 
		
		return result;
	}
	public String getTextPresentation ( List objList) throws Exception {
		String result = "";
		List pList = getCodeList();
		switch ( getProblemType() ) {
		case TestProblem.QUESTION_TYPE_CODE:
			for ( int i = 0; i < pList.size(); i++) {
				result += pList.get(i) + "\r\n";
			}
			break;
		case TestProblem.QUESTION_TYPE_DIAGRAM:
			if ( iconObjList == null ) {
				iconObjList = getObservableIconObject ();
			}
			IconConnection ic = null;
			List cList = this.getIconObjList();
			
			for ( int i = 0; i < cList.size(); i++ ) {
				if ( cList.get(i) instanceof IconConnection ) {
					ic = (IconConnection) cList.get(i);
					result += ic.toString();
				} else if ( cList.get(i) instanceof IconCircle  ||
						cList.get(i) instanceof IconSquare ) {
					result += cList.get(i).toString();
				}
			}			
			break;
		}
		return result;
	}
	
	public int getProblemType() {
		return problemType;
	}

	public boolean isPassed() {
		return passed;
	}

	public List getPDescriptions() {
		return pDescriptions;
	}

	public String getAnswer() {
		return answer;
	}

	public List getIconObjList() {
		return iconObjList;
	}
	
	public List getCodeList() {
		return codeList;
	}
	
	public ObservableIcon getIconOb( int ioIndex) {
		return (ObservableIcon) iconObjList.get(ioIndex);
	}
	
	public ObservableIcon getIconOb( List ioList, String iName) {
		ObservableIcon result = null;
		
		for ( int i = 0; i < ioList.size(); i++ ) {
			if ( !((ObservableIcon)ioList.get(i) instanceof IconConnection ) &&
				 ((ObservableIcon)ioList.get(i)).getName().equals(iName)) {
				result = (ObservableIcon)ioList.get(i);
				break;
			}
		}
		return result;
	}

	public void setIconObjList(List iconObjList) {
		this.iconObjList = iconObjList;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setPDescriptions(List pDescriptions) throws Exception {
		this.pDescriptions = pDescriptions;
		List objList = getObservableIconObject();
		if ( problemType == TestProblem.QUESTION_TYPE_DIAGRAM) {
			setIconObjList(objList);
		} else {
			codeList = new ArrayList();
			for ( int i = 0; i < objList.size(); i++) {
				codeList.add(((ObservableIcon)objList.get(i)).toString());
			} 
		}
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public void setProblemType(int problemType) {
		this.problemType = problemType;
	}
	
	private List getObservableIconObject () throws Exception {
		List result = new ArrayList();
		String objList = (String) pDescriptions.get(0);
		String[] objToken = objList.split(" ");
		int icType;
		IconCircle icObj;
		IconSquare isObj;
		IconConnection iconObj;
		ObservableIcon fiObj, tiObj;
		
		for ( int i = 0; i < objToken.length; i += 2) {
			if ( objToken[i].equals("i" )) {
				ImageIcon ii1 = new ImageIcon(getClass().getResource("circle.png"));
				icObj = new IconCircle(ii1);
				icObj.setName(objToken[i+1]);
				icObj.setToolTipText(objToken[i+1]);
				result.add(icObj);
			} else if ( objToken[i].equals("c" )) {
				ImageIcon ii2 = new ImageIcon(getClass().getResource("square.png"));
				isObj = new IconSquare(ii2);
				isObj.setName(objToken[i+1]);
				isObj.setToolTipText(objToken[i+1]);
				result.add(isObj);
			} 
		}
		for ( int i = 1; i < pDescriptions.size(); i++) {
			objList = (String) pDescriptions.get(i);
			objToken = objList.split(" ");
			fiObj = this.getIconOb(result, objToken[0]);
			if ( fiObj == null ) {
				throw new Exception ("Object is not listed: " + objToken[0]);
			}
			tiObj = this.getIconOb(result, objToken[2]);
			if ( tiObj == null ) {
				throw new Exception ("Object is not listed: " + objToken[2]);
			}
			if (objToken[1].equals("i") ) {
				icType = IconConnection.CONNECTION_TYPE_INHERITANCE;
                                iconObj = new IconConnection (icType, tiObj, fiObj);
			} else if (objToken[1].equals("g") ) {
				icType = IconConnection.CONNECTION_TYPE_AGGREGATION;
                                iconObj = new IconConnection (icType, fiObj, tiObj);
			} else if (objToken[1].equals("s") ) {
				icType = IconConnection.CONNECTION_TYPE_ASSOCIATION;
                                iconObj = new IconConnection (icType, fiObj, tiObj);
			} else {
				throw new Exception ("Unnkown relation: " + objToken[1]);
			}
			
			result.add(iconObj);
		}
		return result;
	}
}
