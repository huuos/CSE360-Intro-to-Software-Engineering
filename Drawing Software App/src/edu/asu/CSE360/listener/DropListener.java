/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.listener;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.Component;
import java.awt.Point;
import javax.swing.*;

import edu.asu.CSE360.gui.DrawPanelTwo;
import edu.asu.CSE360.gui.DrawingTabbedPanel;
import edu.asu.CSE360.gui.IconCircle;
import edu.asu.CSE360.gui.IconSquare;
import edu.asu.CSE360.gui.ObservableIcon;

/**
 *
 * @author Adam Hanrahan
 */
public class DropListener extends DropTargetAdapter {
    public DropTarget target;
    public JPanel panel;
    DrawingTabbedPanel dtPanel;
    
    public DropListener(JPanel panel) {
        this.panel = panel;
        target = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
    }
    
    @Override
    public void drop(DropTargetDropEvent e){
        try{
            DropTarget targ = (DropTarget) e.getSource();
            Component ca = (Component) targ.getComponent();
            Point dropPoint = ca.getMousePosition();
            Transferable transfer = e.getTransferable();
            Object dObj = null;            
            DataFlavor[] ddFlavor = transfer.getTransferDataFlavors();
            for ( int i = 0; i < ddFlavor.length; i++ ) {
            	dObj = transfer.getTransferData(ddFlavor[i]);
            	if ( dObj != null) {
            		break;
            	}
            }
            if ( dObj != null && ((DrawPanelTwo) panel).isAllowDrop() ) {     
            	ObservableIcon c = (ObservableIcon) ((ObservableIcon)dObj).clone();
            	String cName = null;
            	if ( c instanceof IconSquare ) {
            		cName = "class"+(((DrawPanelTwo) panel).getTypeComponentCount(c.getClass().getSimpleName())+1);
            	} else if ( c instanceof IconCircle ) {
            		cName = "interface"+(((DrawPanelTwo) panel).getTypeComponentCount(c.getClass().getSimpleName())+1);
            	} 
            	c.setName(cName);
            	c.setToolTipText(cName);
            	c.setLocation(dropPoint);
                c.addMouseListener(new IconMouseListener(c,(DrawPanelTwo)panel));   //attach a mouse click listener to the new icon
            	panel.add(c );
            }
        } catch (Exception ex){
            e.rejectDrop();
        }
    }
}
