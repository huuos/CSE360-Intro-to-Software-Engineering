package edu.asu.CSE360;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import edu.asu.CSE360.gui.DrawPanelTwo;

public class TestAndScoreObject {
	private static TestAndScoreObject taObj;

	private TestProblem[] problems = new TestProblem[10];
	private int currentProblemIndex;
	
	private TestAndScoreObject(){}

	public static synchronized  TestAndScoreObject getInstance() {
		if ( taObj == null ) {
			taObj = new TestAndScoreObject ();
		}
		return taObj;
	}

	public void loadProblem ( String filePath) throws Exception {
		List<String> pDesc = null; 
		BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(filePath)) );
		TestProblem tpObj = null; 
		String line = null;
		int pIndex = 0;
		
		while ((line = br.readLine()) != null) {
			if ( line.trim().equalsIgnoreCase("qBegin")) {
				line = br.readLine();
				if ( line.trim().equalsIgnoreCase("Diagram") ) {
					tpObj = new TestProblem(TestProblem.QUESTION_TYPE_DIAGRAM);
				} else if ( line.trim().equalsIgnoreCase("Code") ) {
					tpObj = new TestProblem(TestProblem.QUESTION_TYPE_CODE);
				} else {
					throw new IOException ("Unnkown Problem Type: " + line);
				}
				pDesc = new ArrayList<String>();
				while ((line = br.readLine()) != null) {
					if ( line.trim().equalsIgnoreCase("qEnd")) {
						try {
							tpObj.setPDescriptions(pDesc);
						} catch (Exception e) {
							e.printStackTrace();
						}
						problems[pIndex++] = tpObj;
						break;
					} 
					pDesc.add(line);
				}
			} 
		}

		//add answer for each problem
		for ( int i = 0; i < problems.length && problems[i] != null; i++ ) {
			problems[i].createAnswer();
		}
	}
	
	public int getTotalScroe() {
		int result = 0;
		
		for ( int i = 0; i < problems.length; i++ ) {
			if ( problems[i].isPassed()) {
				result ++;
			}
		}
		return result;
	}
	
	public int getNUmberOfProblem () {
		return problems.length;
	}
	
	public boolean getProblemPassed( int pIndex ) throws IndexOutOfBoundsException {
		return problems[pIndex].isPassed();
	}

	public TestProblem[] getProblems() {
		return problems;
	}
	
	public TestProblem getCurrentProblem( ) throws IndexOutOfBoundsException{
		return problems[currentProblemIndex];
	}
	public TestProblem getProblems( int pIndex) throws IndexOutOfBoundsException{
		return problems[pIndex];
	}
	public int getCurrentProblemIndex() {
		return currentProblemIndex;
	}

	public boolean isCorrectAnswerForCurrentProblem ( String answer) {
		return getCurrentProblem().getAnswer().equals(answer);
	}
	
	public boolean isCurrentProblemPassed ( ) {
		return getCurrentProblem().isPassed();
	}
	
	public boolean isCorrectAnswerForProblem ( String answer, int pIndex) {
		return getProblems(pIndex).getAnswer().equals(answer);
	}
	
	public void setCurrentProblemIndex(int currentProblemIndex) {
		this.currentProblemIndex = currentProblemIndex;
	}

	public void setProblems(TestProblem[] problems) {
		this.problems = problems;
	}
	public void setProblems(TestProblem problem, int pIndex) throws IndexOutOfBoundsException {
		this.problems[pIndex] = problem;
	}
	
	public void setCurrentProblemPassed(boolean pPassed) {
		getCurrentProblem().setPassed(pPassed);
	}
	
	public void setProblemPassed(boolean problemPassed, int pIndex) throws IndexOutOfBoundsException {
		this.problems[pIndex].setPassed(problemPassed);
	}
		
	public String getAnswerOfProblem (DrawPanelTwo dPanel) {
		String result = "";
		Component[] cList = dPanel.getObsObj().getComponent();
		
		for ( int i = 0; i < cList.length; i++ ) {
			result += cList[i].toString();
		}
		
		return result; 
	}
}
