/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.listener;

import javax.swing.event.*;

import edu.asu.CSE360.ObservableObj;
import edu.asu.CSE360.gui.DrawPanelTwo;
import edu.asu.CSE360.gui.DrawingTabbedPanel;
import edu.asu.CSE360.gui.JavaCodeArea;

import java.awt.Color;

/**
 *
 * @author tinap
 */
public class TabbedPaneListener implements ChangeListener {
    public DrawPanelTwo panelTwo;
    public void stateChanged(ChangeEvent e) {
        DrawingTabbedPanel tp = (DrawingTabbedPanel)e.getSource();
        int count = tp.getTabCount();
        int index = tp.getSelectedIndex();
        //System.out.println(tp.getSelectedIndex());
        //System.out.println(tp.getTitleAt(tp.getSelectedIndex()));
        if (tp.getTitleAt(index).equals("+")) {     
            //Add drawing panel to tab
            //Old code moved to here from DrawFrame
            panelTwo = new DrawPanelTwo();
            panelTwo.setOptionsPanel(tp.getOptionsPanel());
            panelTwo.setOpaque(true);
            panelTwo.setBackground(Color.white);
            new DropListener(panelTwo);
            //DrawOptionsPanel optionsPanel = new DrawOptionsPanel();
            panelTwo.addMouseListener(new DrawPanelTwoMouseListener(tp.getOptionsPanel()));
            
            tp.add("Page_" + (count),panelTwo);
            tp.setSelectedIndex(count);
            tp.getParent().invalidate();
        } else {
        	panelTwo = (DrawPanelTwo) tp.getComponent(index);
        }
        ObservableObj obsObj = panelTwo.getObsObj();
        if ( obsObj.getObserverCount() > 0 && index > 0) {
        	((JavaCodeArea)obsObj.getObserver()).setCurrentListIndex(index);
    	}
//        System.out.println("TabbedPaneListener.actionPerformed: " + ((JTabbedPane)e.getSource()).getSelectedComponent());
    }
}
