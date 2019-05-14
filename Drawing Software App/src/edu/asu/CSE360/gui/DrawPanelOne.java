/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.gui;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import edu.asu.CSE360.listener.DragListener;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;


/**
 *
 * @author Adam
 */
public class DrawPanelOne extends JPanel{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel shapeSelectionPanel;
	private DragListener dragListen;
	
	public DrawPanelOne() throws Exception{
        setLayout(new BorderLayout());
//        JLabel pic1 = new JLabel (new ImageIcon(new URL("http://i.stack.imgur.com/gJmeJ.png")));
        
        dragListen = new DragListener();   //Drag listener for whole panel?? Should I add it in frame class??        
    }

	public void addSelectionItem (ObservableIcon jb ) {
		DragSource source1 = new DragSource();
        source1.createDefaultDragGestureRecognizer(jb, DnDConstants.ACTION_COPY, dragListen);
        shapeSelectionPanel.add(jb);
	}
	
	//
	//	getters and setters
	//
	public JPanel getShapeSelectionPanel() {
		return shapeSelectionPanel;
	}

	public void setShapeSelectionPanel(JPanel shapeSelectionPanel) {
		this.shapeSelectionPanel = shapeSelectionPanel;
	}
}
