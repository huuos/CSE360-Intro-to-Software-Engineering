/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.gui;
import javax.swing.JTabbedPane;
/**
 *
 * @author tinap
 */
public class DrawingTabbedPanel extends JTabbedPane {
    private DrawOptionsPanel optionsPanel;
    public DrawingTabbedPanel() {
        super();
    }
    
    public DrawingTabbedPanel(int tabPlacement) {
        super(tabPlacement);
    }

    public DrawOptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(DrawOptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }
    
}
