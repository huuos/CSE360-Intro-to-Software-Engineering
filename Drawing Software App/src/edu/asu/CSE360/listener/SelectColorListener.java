package edu.asu.CSE360.listener;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import edu.asu.CSE360.gui.DrawingTabbedPanel;


public class SelectColorListener implements ItemListener {
	private DrawingTabbedPanel dtPanel;
	
	public SelectColorListener () {}
	public SelectColorListener (DrawingTabbedPanel dtPanel) {
		this.dtPanel = dtPanel;
	}
	
	@Override
	public void itemStateChanged(ItemEvent event) {
		JPanel dp = (JPanel) dtPanel.getSelectedComponent();
		JComboBox colorBox = (JComboBox) event.getSource();
            if (colorBox.getSelectedItem().equals("White")){
                dp.setBackground(Color.white);
            }
            else if(colorBox.getSelectedItem().equals("Black")){
                dp.setBackground(Color.black);
            }
            else if(colorBox.getSelectedItem().equals("Red")){
                dp.setBackground(Color.red);
            }
            else if(colorBox.getSelectedItem().equals("Blue")){
                dp.setBackground(Color.blue);
            }
            else if(colorBox.getSelectedItem().equals("Light Gray")){
                dp.setBackground(Color.lightGray);
            }
	}

}
