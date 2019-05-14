/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.listener;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.asu.CSE360.gui.DrawOptionsPanel;


/**
 *
 * @author tinap
 */
public class DrawPanelTwoMouseListener extends MouseAdapter {
    private DrawOptionsPanel optionsPanel;
    private int mouseClickXOne=0, mouseClickYOne=0, mouseClickXTwo=0, mouseClickYTwo=0;
    private int lineCount = 0;
                int clickNumber=2;
                int lineType;
                Color col;
    public DrawOptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(DrawOptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }
    public DrawPanelTwoMouseListener() {
        
    }
    public DrawPanelTwoMouseListener(DrawOptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }
    public void mouseClicked(MouseEvent e){              
    	if(clickNumber<2){
            mouseClickXOne = e.getX();
            mouseClickYOne = e.getY();
            clickNumber = 2;
        }
        else{
            mouseClickXTwo = e.getX();
            mouseClickYTwo = e.getY();
            clickNumber = 1;
            lineCount++;
            col = getOptionsPanel().GetLineColorOptions();
            lineType = getOptionsPanel().GetLineOptions();
            //((DrawPanelTwo)e.getSource()).makeLine(mouseClickXOne, mouseClickYOne, mouseClickXTwo, mouseClickYTwo, lineCount, col, lineType);
        }
    }
    
    public void setLineCount( int lineCount) {
    	this.lineCount = lineCount;
    }
}
