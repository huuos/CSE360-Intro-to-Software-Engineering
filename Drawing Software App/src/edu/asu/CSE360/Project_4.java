/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360;

import javax.swing.*;

import edu.asu.CSE360.gui.DrawFrame;


/**
 *
 * @author Adam
 */
public class Project_4 {
    private static final String INSTRUCTION="When typing the answer, follow conventions exemplified in Q6-10.\r\n To find out the name of the icon, hover over it with the mouse.\r\n";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DrawFrame frame = new DrawFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds( 40, 30, 960,700);
        
        try {
			TestAndScoreObject.getInstance().loadProblem("project4TestFile");
	 JOptionPane.showMessageDialog(frame, INSTRUCTION);

	
        } catch (Exception e) {
			JOptionPane.showMessageDialog(frame, e.getMessage());
		}
    }
    
}
