//This class handles creating connections between two icons when an icon is
//clicked. The user is not allowed to click the same icon twice to make a 
//connection.
package edu.asu.CSE360.listener;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import edu.asu.CSE360.gui.DrawFrame;
import edu.asu.CSE360.gui.DrawOptionsPanel;
import edu.asu.CSE360.gui.DrawPanelTwo;
import edu.asu.CSE360.gui.DrawingTabbedPanel;
import edu.asu.CSE360.gui.IconConnection;
import edu.asu.CSE360.gui.ObservableIcon;


/**
 *
 * @author Adam Hanrahan
 */
public class IconMouseListener extends MouseAdapter{
    private ObservableIcon thisIcon;
    private DrawPanelTwo dPanel;
    private int lineRelationshipSelection;
    private static ObservableIcon from, to;
    
  
    
    public IconMouseListener(ObservableIcon i, DrawPanelTwo x){
        this.thisIcon = i;
        this.dPanel = x;
    }
   

    public void mouseClicked(MouseEvent e){       
    	Component c = ((Component) e.getSource()).getParent();
//    	DrawingTabbedPanel dtPanel=null;
        if(DrawFrame.mouseClick < 2){
             dPanel.setTo(thisIcon);
             from = thisIcon;
             DrawFrame.mouseClick = 2;
//             System.out.println("First Icon Selected");
        }
        else{
            to = thisIcon;
            if(from.getX() == to.getX() && from.getY() == to.getY()){           //For some reason, comparin from.getLoc to to.getLoc doesnt work correctly for this
                DrawFrame.mouseClick = 2;
            }
            else{
            to = thisIcon;
            DrawFrame.mouseClick = 1;
            lineRelationshipSelection = dPanel.getLineRelationship();     
//            System.out.println("Second Icon Selected");
            String cName;
//            dtPanel = (DrawingTabbedPanel) dPanel.getParent();
            switch(lineRelationshipSelection) {
                    case 0:
                        //General
                        IconConnection ic = new IconConnection(DrawOptionsPanel.lineColor, DrawOptionsPanel.lineDrawOptions, to.getLocation(), from.getLocation());
//                        cName = ic.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic.getClass().getSimpleName())+1)+dtPanel.getSelectedIndex();
                        cName = ic.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic.getClass().getSimpleName())+1);
                        ic.setName(cName);
                        ic.setConnectionType(0);
                        ic.setFromObj(from);
                        ic.setToObj(to);
                        dPanel.add(ic);                                       
                        dPanel.addConnection(ic);
//                        System.out.println("General");  
                        break;
                    case 1: 
                        //Inheritance
                        IconConnection ic1 = new IconConnection("Inheritance", to.getLocation(), from.getLocation());
                        ic1.setConnectionType(IconConnection.CONNECTION_TYPE_INHERITANCE);
//                        cName = ic1.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic1.getClass().getSimpleName())+1)+dtPanel.getSelectedIndex();
                        cName = ic1.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic1.getClass().getSimpleName())+1);
                        ic1.setName(cName);
                        ic1.setFromObj(from);
                        ic1.setToObj(to);
                        dPanel.add(ic1);
                        dPanel.addConnection(ic1);
//                        System.out.println("Inheritance");  
                        break;
                    case 2:
                        //Aggregation
                        IconConnection ic2 = new IconConnection("Aggregation", to.getLocation(), from.getLocation());
                        ic2.setConnectionType(IconConnection.CONNECTION_TYPE_AGGREGATION);
//                        cName = ic2.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic2.getClass().getSimpleName())+1)+dtPanel.getSelectedIndex();
                        cName = ic2.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic2.getClass().getSimpleName())+1);
                        ic2.setName(cName);
                        ic2.setFromObj(from);
                        ic2.setToObj(to);
                        dPanel.add(ic2);
                        dPanel.addConnection(ic2);
//                        System.out.println("Aggregation");  
                        break;
                    case 3:
                        //Association
                        IconConnection ic3 = new IconConnection("Association", to.getLocation(), from.getLocation());
                        ic3.setConnectionType(IconConnection.CONNECTION_TYPE_ASSOCIATION);
//                        cName = ic3.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic3.getClass().getSimpleName())+1)+dtPanel.getSelectedIndex();
                        cName = ic3.getClass().getSimpleName()+(dPanel.getTypeComponentCount(ic3.getClass().getSimpleName())+1);
                        ic3.setName(cName);
                        ic3.setFromObj(from);
                        ic3.setToObj(to);
                        dPanel.add(ic3);
                        dPanel.addConnection(ic3);
//                        System.out.println("Association");
                        break;
                }
            }
        }      
        dPanel.repaint();
    }    
}
