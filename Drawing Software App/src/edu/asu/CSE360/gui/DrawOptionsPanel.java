/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Adam
 */
public class DrawOptionsPanel extends JPanel {
     public static Color lineColor;                                             //Byte to store color data
     public static byte lineDrawOptions;                                        //Byte to store line type
     public byte lineRelationship;
     private String[] colorOptions = {"Black","Red","Blue","Yellow"};                   //For color dropdown menu
     private String[] lineOptions = {"Solid","Dashed"};                                 //For line options menu
     private String[] relationshipOptions = {"General", "Inheritance", "Aggregation", "Association"};
     private JComboBox relationshipOptionsMenu;
     
     public DrawOptionsPanel(){
        LineOptionDropDown();
        TitledBorder border = new TitledBorder("Line Options:");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);
        setBorder(border);
     }//End of constructor
     
     public void LineOptionDropDown(){
         //This will be used to draw dropdown menu text and options
         JComboBox lineOptionsMenu = new JComboBox(lineOptions);                //Drop down menu for line options
         JComboBox colorOptionsMenu = new JComboBox(colorOptions);              //Drop down menu for color options
         relationshipOptionsMenu = new JComboBox(relationshipOptions);//Drop down menu for relationship options

         lineOptionsMenu.setSelectedIndex(0);
         colorOptionsMenu.setSelectedIndex(0);
         relationshipOptionsMenu.setSelectedIndex(0);         

         
         lineOptionsMenu.addActionListener(new ActionListener(){
             public void actionPerformed(ActionEvent e){
                 //Action logic
                 JComboBox cb = (JComboBox)e.getSource();
                 byte sel = (byte)cb.getSelectedIndex();
                 switch(sel){
                     case 0: lineDrawOptions = 0;
                        break;
                     case 1: lineDrawOptions = 1;
                        System.out.println("set to dashed");
                        break;
                     default: lineDrawOptions = 0;
                 }
             }
         });
         
         colorOptionsMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent d){
                //Action logic
                JComboBox cb = (JComboBox)d.getSource();
                byte colorSel = (byte)cb.getSelectedIndex();
                switch(colorSel){
                    case 0: lineColor = Color.BLACK;
                        break;
                    case 1: lineColor = Color.RED;
                        break;
                    case 2: lineColor = Color.BLUE;
                        break;
                    case 3: lineColor = Color.YELLOW;
                        break;
                    default: lineColor = Color.BLACK;
                }
            } 
         });
         
         relationshipOptionsMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent r) {
                JComboBox cb = (JComboBox)r.getSource();
                byte relationSelection = (byte)cb.getSelectedIndex();
                switch(relationSelection) {
                    case 0:
                        //General
                        lineRelationship = 0;
                        break;
                    case 1: 
                        //Inheritance
                        lineRelationship = 1;
                        break;
                    case 2:
                        //Aggregation
                        lineRelationship = 2;
                        break;
                    case 3:
                        //Association
                        lineRelationship = 3;
                        break;
                }
            }                               
         });
         add ( new JLabel("Line Style: "));
         add(lineOptionsMenu);
         add ( new JLabel("Line Color: "));
         add(colorOptionsMenu);
         add(new JLabel("Line Relationship: "));
         add(relationshipOptionsMenu);
     }
     
     public int GetLineOptions(){
         
         return lineDrawOptions;
     }
     
     public Color GetLineColorOptions(){
         
         return lineColor;
     } 
     
     public int getRelationshipOptions(){
         return lineRelationship;
     }

	public JComboBox getRelationshipOptionsMenu() {
		return relationshipOptionsMenu;
	}

	public void setRelationshipOptionsMenu(JComboBox relationshipOptionsMenu) {
		this.relationshipOptionsMenu = relationshipOptionsMenu;
	}
}//End of class
