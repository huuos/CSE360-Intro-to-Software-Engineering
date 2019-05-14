/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

import edu.asu.CSE360.gui.DrawingTabbedPanel;

/**
 *
 * @author tinap
 */
public class TabbedPaneMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent me) {
        //System.out.println("TabbedPaneMouseListener.MouseCliked: button " + me.getButton());
        //Double click to rename current tab
        if (me.getClickCount()==2) {
            DrawingTabbedPanel dtPane = (DrawingTabbedPanel)me.getSource();
            String tName = dtPane.getTitleAt(dtPane.getSelectedIndex());
            if (!tName.equalsIgnoreCase("+")) { 
                tName = (String)JOptionPane.showInputDialog(dtPane, "Enter New Tab Name:", "Rename", JOptionPane.PLAIN_MESSAGE) ;
                if ( tName != null && tName.trim().length() > 0 ) {
                    dtPane.setTitleAt(dtPane.getSelectedIndex(), tName);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
