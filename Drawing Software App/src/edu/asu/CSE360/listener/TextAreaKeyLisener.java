package edu.asu.CSE360.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import edu.asu.CSE360.gui.JavaCodeArea;

public class TextAreaKeyLisener implements KeyListener {

	public TextAreaKeyLisener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		JavaCodeArea jcA = (JavaCodeArea) e.getSource();
		char c = e.getKeyChar();
//		if ( c == '\n') {
			jcA.getObsObj().setChanged();
			jcA.getObsObj().notifyObservers(jcA);
//		}
	}

}
