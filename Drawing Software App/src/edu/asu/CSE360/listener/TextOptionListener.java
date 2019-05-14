package edu.asu.CSE360.listener;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTextField;

import edu.asu.CSE360.gui.DrawFrame;
import edu.asu.CSE360.gui.DrawPanelTwo;
import edu.asu.CSE360.gui.DrawingTabbedPanel;


public class TextOptionListener extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DrawingTabbedPanel dtPanel;
	
	public TextOptionListener () {}
	public TextOptionListener ( DrawingTabbedPanel dtPanel ) {
		this.dtPanel = dtPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton jb =  (JButton) e.getSource();
		DrawFrame mainFrame = (DrawFrame) jb.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();

		if ( mainFrame != null) {
			JTextField jt = new JTextField();
			int fSize = Integer.parseInt( (String)mainFrame.getTextSizeCB().getSelectedItem());
			String fName = (String) mainFrame.getTextFontCB().getSelectedItem();
			String fFace = (String) mainFrame.getTextFormatCB().getSelectedItem();
			String fColor = (String) mainFrame.getTextColorCB().getSelectedItem();
			String tWidth = (String) mainFrame.getTfWidthSize().getText();
			String tHeight = (String) mainFrame.getTfHeightSize().getText();
			DrawPanelTwo dp2 = (DrawPanelTwo) dtPanel.getComponentAt(dtPanel.getSelectedIndex());
			int jtfWidth = 100;
			int jtfHeight = 20;
			if ( tWidth != null && tWidth.trim().length() > 0) {
				jtfWidth = Integer.parseInt(tWidth.trim());
			} 
			if ( tHeight != null && tHeight.trim().length() > 0) {
				jtfHeight = Integer.parseInt(tHeight.trim());
			} 
			Font f = new Font (fName, getFontStyle(fFace), fSize);
			int nextY = 10;
			for ( int i = 0; i < dp2.getComponentCount(); i++) {
				Component c = dp2.getComponent(i);
				if ( c instanceof JTextField) {
					int y = c.getY();
					if ( y >= nextY) {
						nextY += y+jtfHeight+4;
					}
				}
			}
			jt.setBounds(10, nextY, jtfWidth , jtfHeight );
			jt.setFont(f);
			jt.setForeground(getFontColor(fColor));
			dp2.add(jt);
			dp2.repaint();
		}
	}

	private int getFontStyle (String fStyle) {
		int result = 0;
		
		if ( fStyle.equalsIgnoreCase("PLAIN")) {
			result = Font.PLAIN;
		} else if ( fStyle.equalsIgnoreCase("BOLD")) {
			result = Font.BOLD;
		} else if ( fStyle.equalsIgnoreCase("ITALIC")) {
			result = Font.ITALIC;
		} else {
			
		}
		return result;
	}
	private Color getFontColor (String fStyle) {
		Color result = Color.BLACK;
		
		if ( fStyle.equalsIgnoreCase("red")) {
			result = Color.RED;
		} else if ( fStyle.equalsIgnoreCase("black")) {
			result = Color.BLACK;
		} else if ( fStyle.equalsIgnoreCase("blue")) {
			result = Color.BLUE;
		} else if ( fStyle.equalsIgnoreCase("green")) {
			result = Color.GREEN;
		} else {
			
		}
		return result;
	}
}
