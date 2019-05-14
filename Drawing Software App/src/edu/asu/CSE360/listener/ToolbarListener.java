/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.listener;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.asu.CSE360.gui.DrawPanelTwo;
import edu.asu.CSE360.gui.DrawingTabbedPanel;
import edu.asu.CSE360.gui.IconCircle;
import edu.asu.CSE360.gui.IconConnection;
import edu.asu.CSE360.gui.IconSquare;
import edu.asu.CSE360.gui.JavaCodeArea;
import edu.asu.CSE360.gui.ObservableIcon;

/**
 *
 * @author tinap
 */
public class ToolbarListener extends AbstractAction {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DrawingTabbedPanel dtPanel;
        
    boolean iconExtend = false;
    boolean extendFromSquareFlag = false;
    boolean extendFromCircleFlag = false;
    boolean aggrFromSquareFlag = false;
    boolean aggrFromCircleFlag = false;
    boolean assocFromSquareFlag = false;
    boolean assocFromCircleFlag = false;
    boolean extendToSquareFlag = false;
    boolean extendToCircleFlag = false;
    String extendFromTFString;
    String extendToTFString;
    String aggrFromTFString;
    String aggrToTFString;
    String assocFromTFString;
    String assocToTFString;
    
    int extendFromTFInt;
    int extendToTFInt;
    int implementNum = 0;
    int iconImplementNum;
    
    int aggrFromTFInt;
    int aggrToTFInt;
    int aggrNum = 0;
    int iconAggrNum;
    
    int assocFromTFInt;
    int assocToTFInt;
    int assocNum = 0;
    int iconAssocNum;
    
    private ArrayList<String> implementNameRow;
    private ArrayList<String> aggrNameRow;
    private ArrayList<String> assocNameRow;
    
    IconSquare iconSquare = new IconSquare();
    IconCircle iconCircle = new IconCircle();
        
    
    public ToolbarListener() {
    }
    public ToolbarListener(DrawingTabbedPanel x) {
        dtPanel = x;
    }
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        int tIndex = dtPanel.getSelectedIndex();
        if(cmd.equals("Delete Tab")) {
            int tCount = dtPanel.getTabCount();
            if ( tCount <= 2) {
                //Remove content
                DrawPanelTwo dPanel = (DrawPanelTwo) dtPanel.getComponentAt(tIndex);
//                dPanel.clean();
                Component[] cList = dPanel.getComponents();
                for ( int i = cList.length-1; i>=0; i--) {
                	dPanel.remove(i);
                }
                dPanel.repaint();
                return;
            } else {
                
                dtPanel.remove(tIndex);
            }
        }
        else if (cmd.equals("Duplicate Tab")) {
            DrawPanelTwo dp2 = (DrawPanelTwo)dtPanel.getComponentAt(tIndex);
            ((JavaCodeArea) dp2.getObsObj().getObserver()).setCurrentListIndex(dtPanel.getComponentCount());
            DrawPanelTwo ndp2 = duplicatesDrawPanelTwo(dp2);
            dtPanel.add("Copy of "+dtPanel.getTitleAt(tIndex), ndp2);
            dtPanel.setSelectedIndex(dtPanel.getComponentCount()-1);
            
            ndp2.repaint();
        }
        else if (cmd.equals("Delete Components")) {
        	DrawPanelTwo dPanel = (DrawPanelTwo) dtPanel.getComponentAt(tIndex);
        	Component[] cList = dPanel.getComponents();
        	if ( cList == null || cList.length == 0) {
        		JOptionPane.showMessageDialog(dtPanel, "There is no component to be deleted", "Information", JOptionPane.INFORMATION_MESSAGE, null);
        	} else {
        		String[] column = {"Index", "Component Name"};
        		JTable jt = new JTable( new DefaultTableModel(column, cList.length) );
        		for ( int i = 0; i < cList.length; i++) {
        			jt.setValueAt(""+(i+1), i, 0);
        			jt.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
        		}
                        
                        
        		int status = JOptionPane.showConfirmDialog(dtPanel, jt, "Select to be deleted Component", JOptionPane.OK_CANCEL_OPTION);
        		if ( status == JOptionPane.OK_OPTION) {
        			int rowIndex[] = jt.getSelectedRows();
        			for ( int i = rowIndex.length-1; i >=0; i-- ) {
        				dPanel.remove(rowIndex[i]);
        			}
        			dPanel.repaint();
        		}
        	}
        }
        
        //Start of Inheritance Button
        else if (cmd.equals("Inheritance")) {
            DrawPanelTwo dPanel = (DrawPanelTwo) dtPanel.getComponentAt(tIndex);
            Component[] cList = dPanel.getComponents();
            if ( cList.length < 2) {
        	JOptionPane.showMessageDialog(dtPanel, "You need atleast 2 components to do this operation", "Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
            else { 
                String[] column = {"Index", "Component Name"};
        	JTable jt = new JTable( new DefaultTableModel(column, cList.length) );
        	for ( int i = 0; i < cList.length; i++) {
                    jt.setValueAt(""+(i+1), i, 0);
                    jt.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
        	}
                
                JTable jt2 = new JTable( new DefaultTableModel(column, cList.length) );
        	for ( int i = 0; i < cList.length; i++) {
                    jt2.setValueAt(""+(i+1), i, 0);
                    jt2.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
        	}
                
                int status = JOptionPane.showConfirmDialog(dtPanel, jt, "Select starting component", JOptionPane.OK_CANCEL_OPTION);   
                int status2 = JOptionPane.showConfirmDialog(dtPanel, jt2, "Select ending component", JOptionPane.OK_CANCEL_OPTION);
                
        	if ( status == JOptionPane.OK_OPTION && status2 == JOptionPane.OK_OPTION) {
                    int fromObj = jt.getSelectedRow();
                    int toObj = jt2.getSelectedRow();
                         
                    Point fromObjLocation = cList[fromObj].getLocation();
                    //System.out.println(fromObjLocation);
                    Point toObjLocation = cList[toObj].getLocation();
                    //System.out.println(toObjLocation);

                    
                    
                    //////////////////////
                    extendFromTFString = Integer.toString(fromObj+1);
                    iconExtend = true;
                    System.out.println(extendFromTFString);
                    extendFromSquareFlag = iconSquare.iconNameListContains(extendFromTFString);
                    extendFromCircleFlag = iconCircle.iconNameListContains(extendFromTFString);
                    if(extendFromSquareFlag == true) {
                        iconSquare.setIconExtend(iconExtend);
                    }
                    else if(extendFromCircleFlag == true) {
                       iconCircle.setIconExtend(iconExtend);
                    }
                    //////////////////////
                    extendToTFString = Integer.toString(toObj+1);
                extendToTFInt = Integer.parseInt(extendToTFString);
                extendFromTFInt = Integer.parseInt(extendFromTFString);
                extendFromTFInt = extendFromTFInt - 1;
                extendToSquareFlag = iconSquare.iconNameListContains(extendToTFString);
                extendToCircleFlag = iconCircle.iconNameListContains(extendToTFString);
                if(extendToSquareFlag == true) {
                    if(extendFromSquareFlag == true) {
                        iconSquare.removeIndexExtendName(extendFromTFInt);
                        iconSquare.insertExtendName(extendFromTFInt, extendToTFInt);
                        extendFromSquareFlag = false;
                    }
                    else if(extendFromCircleFlag == true) {
                        iconCircle.removeIndexExtendName(extendFromTFInt);
                        iconCircle.insertExtendName(extendFromTFInt, extendToTFInt);
                        extendFromCircleFlag = false;
                    }
                }
                else if(extendToCircleFlag == true){
                    if(extendFromSquareFlag == true) {
                        implementNameRow = new ArrayList<String>();
                        implementNameRow = iconSquare.getImplementNameRow(extendFromTFInt);
                        iconSquare.removeImplementName(extendFromTFInt);
                        extendToTFString = Integer.toString(extendToTFInt);
                        
                        System.out.println("implementNameRow: " + implementNameRow);
                        System.out.println("extendToTFString: "+ extendToTFString + "!");
                        implementNameRow.add(extendToTFString);
                        //implementNameRow.add(0, extendToTFString);
                        iconSquare.addImplementNameRow(extendFromTFInt, implementNameRow);
                        iconSquare.displayImplementNameArray();
                        iconCircle.displayImplementNameArray();
                        
                        //iconSquare.removeIndexImplementName(extendToTFInt-1);
                        //iconSquare.insertImplementName(extendFromTFInt, extendToTFInt);
                        extendFromSquareFlag = false;
                        implementNum++;
                        iconImplementNum = Integer.parseInt(iconSquare.getIconImplementNum(extendFromTFInt));
                        iconImplementNum++;
                        iconSquare.removeIndexIconImplementNum(extendFromTFInt);                        
                        iconSquare.insertIconImplementNum(extendFromTFInt, iconImplementNum);
                        
                        iconSquare.setImplementNum(implementNum);
                    }
                    else if(extendFromCircleFlag == true) {
                        implementNameRow = iconSquare.getImplementNameRow(extendFromTFInt);
                        iconCircle.removeImplementName(extendFromTFInt);
                        implementNameRow.add(Integer.toString(extendToTFInt));
                        iconCircle.addImplementNameRow(extendFromTFInt, implementNameRow);
                        iconSquare.displayImplementNameArray();
                        iconCircle.displayImplementNameArray();
                        
                        //iconCircle.removeIndexImplementName(extendToTFInt-1);
                        //iconCircle.insertImplementName(extendFromTFInt, extendToTFInt);
                        extendFromCircleFlag = false;
                        implementNum++;
                        iconImplementNum = Integer.parseInt(iconCircle.getIconImplementNum(extendFromTFInt));
                        iconImplementNum++;
                        iconCircle.removeIndexIconImplementNum(extendFromTFInt);
                        iconCircle.insertIconImplementNum(extendFromTFInt, iconImplementNum);
                        iconCircle.setImplementNum(implementNum);
                    }
                }
                
                
                iconExtend = true;
                iconSquare.setIconExtend(iconExtend);    

                IconConnection ic = new IconConnection("Inheritance", fromObjLocation, toObjLocation);
                ic.setConnectionType(IconConnection.CONNECTION_TYPE_INHERITANCE);
                dPanel.add(ic);
                dPanel.addConnection(ic);                
                }
            }
        }
        
        //Start of Aggregation Button
        else if (cmd.equals("Aggregation")) {
            DrawPanelTwo dPanel = (DrawPanelTwo) dtPanel.getComponentAt(tIndex);
            Component[] cList = dPanel.getComponents();
            if ( cList.length < 2) {
        	JOptionPane.showMessageDialog(dtPanel, "You need atleast 2 components to do this operation", "Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
            else { 
                String[] column = {"Index", "Component Name"};
        	JTable jt = new JTable( new DefaultTableModel(column, cList.length) );
        	for ( int i = 0; i < cList.length; i++) {
                    jt.setValueAt(""+(i+1), i, 0);
                    jt.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
        	}
                
                JTable jt2 = new JTable( new DefaultTableModel(column, cList.length) );
        	for ( int i = 0; i < cList.length; i++) {
                    jt2.setValueAt(""+(i+1), i, 0);
                    jt2.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
        	}
                
                int status = JOptionPane.showConfirmDialog(dtPanel, jt, "Select starting component", JOptionPane.OK_CANCEL_OPTION);   
                int status2 = JOptionPane.showConfirmDialog(dtPanel, jt2, "Select ending component", JOptionPane.OK_CANCEL_OPTION);
                
        	if ( status == JOptionPane.OK_OPTION && status2 == JOptionPane.OK_OPTION) {
                    int fromObj = jt.getSelectedRow();
                    int toObj = jt2.getSelectedRow();
                         
                    Point fromObjLocation = cList[fromObj].getLocation();
                    Point toObjLocation = cList[toObj].getLocation();
                     
                    aggrFromTFString = Integer.toString(fromObj+1);
                    aggrFromSquareFlag = iconSquare.iconNameListContains(aggrFromTFString);
                    aggrFromCircleFlag = iconCircle.iconNameListContains(aggrFromTFString); 
                    
                    aggrToTFString = Integer.toString(toObj+1);
                    aggrToTFInt = Integer.parseInt(aggrToTFString);
                    aggrFromTFInt = Integer.parseInt(aggrFromTFString);
                    aggrFromTFInt = aggrFromTFInt - 1;
                    if(aggrFromSquareFlag == true) {
                        aggrNameRow = new ArrayList<String>();
                        aggrNameRow = iconSquare.getAggrNameRow(aggrFromTFInt);
                        iconSquare.removeAggrName(aggrFromTFInt);
                        aggrToTFString = Integer.toString(aggrToTFInt);
                        
                        System.out.println("aggrNameRow: " + aggrNameRow);
                        System.out.println("aggrToTFString: "+ aggrToTFString + "!");
                        aggrNameRow.add(aggrToTFString);
                        iconSquare.addAggrNameRow(aggrFromTFInt, aggrNameRow);
                        iconSquare.displayAggrNameArray();
                        iconCircle.displayAggrNameArray();
                        aggrFromSquareFlag = false;
                        aggrNum++;
                        iconAggrNum = Integer.parseInt(iconSquare.getIconAggrNum(aggrFromTFInt));
                        iconAggrNum++;
                        iconSquare.removeIndexIconAggrNum(aggrFromTFInt);                        
                        iconSquare.insertIconAggrNum(aggrFromTFInt, iconAggrNum);
                        
                        iconSquare.setAggrNum(aggrNum);
                    }
                    else if(aggrFromCircleFlag == true) {
                        aggrNameRow = iconSquare.getAggrNameRow(aggrFromTFInt);
                        iconCircle.removeAggrName(aggrFromTFInt);
                        aggrNameRow.add(Integer.toString(aggrToTFInt));
                        iconCircle.addAggrNameRow(aggrFromTFInt, aggrNameRow);
                        iconSquare.displayAggrNameArray();
                        iconCircle.displayAggrNameArray();
                        aggrFromCircleFlag = false;
                        aggrNum++;
                        iconAggrNum = Integer.parseInt(iconCircle.getIconAggrNum(aggrFromTFInt));
                        iconAggrNum++;
                        iconCircle.removeIndexIconAggrNum(aggrFromTFInt);
                        iconCircle.insertIconAggrNum(aggrFromTFInt, iconAggrNum);
                        iconCircle.setAggrNum(aggrNum);
                    }
                    
                    IconConnection ic = new IconConnection("Aggregation", fromObjLocation, toObjLocation);
                    ic.setConnectionType(IconConnection.CONNECTION_TYPE_AGGREGATION);
                    dPanel.add(ic);
                    dPanel.addConnection(ic);
                }
            }
        }

        //Start of Association Button
        else if (cmd.equals("Association")) {
            DrawPanelTwo dPanel = (DrawPanelTwo) dtPanel.getComponentAt(tIndex);
            Component[] cList = dPanel.getComponents();
            if ( cList.length < 2) {
        	JOptionPane.showMessageDialog(dtPanel, "You need atleast 2 components to do this operation", "Error", JOptionPane.INFORMATION_MESSAGE, null);
            }
            else { 
                String[] column = {"Index", "Component Name"};
        	JTable jt = new JTable( new DefaultTableModel(column, cList.length) );
        	for ( int i = 0; i < cList.length; i++) {
                    jt.setValueAt(""+(i+1), i, 0);
                    jt.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
        	}
                
                JTable jt2 = new JTable( new DefaultTableModel(column, cList.length) );
        	for ( int i = 0; i < cList.length; i++) {
                    jt2.setValueAt(""+(i+1), i, 0);
                    jt2.setValueAt(cList[i].getClass().getSimpleName(), i, 1);
        	}
                
                int status = JOptionPane.showConfirmDialog(dtPanel, jt, "Select starting component", JOptionPane.OK_CANCEL_OPTION);   
                int status2 = JOptionPane.showConfirmDialog(dtPanel, jt2, "Select ending component", JOptionPane.OK_CANCEL_OPTION);
                
        	if ( status == JOptionPane.OK_OPTION && status2 == JOptionPane.OK_OPTION) {
                    int fromObj = jt.getSelectedRow();
                    int toObj = jt2.getSelectedRow();
                         
                    Point fromObjLocation = cList[fromObj].getLocation();                    
                    Point toObjLocation = cList[toObj].getLocation();
                    
                    assocFromTFString = Integer.toString(fromObj+1);
                    assocFromSquareFlag = iconSquare.iconNameListContains(assocFromTFString);
                    assocFromCircleFlag = iconCircle.iconNameListContains(assocFromTFString);
                    
                    assocToTFString = Integer.toString(toObj+1);
                    assocToTFInt = Integer.parseInt(assocToTFString);
                    assocFromTFInt = Integer.parseInt(assocFromTFString);
                    assocFromTFInt = assocFromTFInt - 1;
                    if(assocFromSquareFlag == true) {
                        assocNameRow = new ArrayList<String>();
                        assocNameRow = iconSquare.getAssocNameRow(assocFromTFInt);
                        iconSquare.removeAssocName(assocFromTFInt);
                        assocToTFString = Integer.toString(assocToTFInt);
                        
                        System.out.println("assocNameRow: " + assocNameRow);
                        System.out.println("assocToTFString: "+ assocToTFString + "!");
                        assocNameRow.add(assocToTFString);
                        iconSquare.addAssocNameRow(assocFromTFInt, assocNameRow);
                        iconSquare.displayAssocNameArray();
                        iconCircle.displayAssocNameArray();
                        assocFromSquareFlag = false;
                        assocNum++;
                        iconAssocNum = Integer.parseInt(iconSquare.getIconAssocNum(assocFromTFInt));
                        iconAssocNum++;
                        iconSquare.removeIndexIconAssocNum(assocFromTFInt);                        
                        iconSquare.insertIconAssocNum(assocFromTFInt, iconAssocNum);
                        
                        iconSquare.setAssocNum(assocNum);
                    }
                    else if(assocFromCircleFlag == true) {
                        assocNameRow = iconSquare.getAssocNameRow(assocFromTFInt);
                        iconCircle.removeAssocName(assocFromTFInt);
                        assocNameRow.add(Integer.toString(assocToTFInt));
                        iconCircle.addAssocNameRow(assocFromTFInt, assocNameRow);
                        iconSquare.displayAssocNameArray();
                        iconCircle.displayAssocNameArray();
                        assocFromCircleFlag = false;
                        assocNum++;
                        iconAssocNum = Integer.parseInt(iconCircle.getIconAssocNum(assocFromTFInt));
                        iconAssocNum++;
                        iconCircle.removeIndexIconAssocNum(assocFromTFInt);
                        iconCircle.insertIconAssocNum(assocFromTFInt, iconAssocNum);
                        iconCircle.setAssocNum(assocNum);
                    }
                                        
                    IconConnection ic = new IconConnection("Association", fromObjLocation, toObjLocation);
                    ic.setConnectionType(IconConnection.CONNECTION_TYPE_ASSOCIATION);
                    dPanel.add(ic);
                    dPanel.addConnection(ic);
                }
            }
        }

        
        
        System.out.println("ToolBarListener.actionPerformed: " + e.getActionCommand());
    }
    
    private DrawPanelTwo duplicatesDrawPanelTwo(DrawPanelTwo dp2) {
        DrawPanelTwo result = null;
        
        if ( dp2 != null) {
            result = new DrawPanelTwo ();
            result.setOptionsPanel(dp2.getOptionsPanel());
            // copy background color
            result.setBackgroundColor(dp2.getBackground());
            result.setBackground(dp2.getBackground());
            // copy data
            
            result.setX1(dp2.getX1());
            result.setX2(dp2.getX2());
            result.setY1(dp2.getY1());
            result.setY2(dp2.getY2());
            result.setLineCount(dp2.getLineCount());
            result.setOpaque(true);
            new DropListener(result);
            result.addMouseListener(new DrawPanelTwoMouseListener(dp2.getOptionsPanel()));
            
            Component[] oList = dp2.getComponents();
            if ( oList != null ) {
            	String iName;
            	for ( int i = 0; i < oList.length; i++) {
            		try {
            			ObservableIcon oi = (ObservableIcon) ((ObservableIcon)oList[i]).clone();
						result.add( (oi));
						if ( oi instanceof IconConnection ) {
							iName = oi.getName()+(((JTabbedPane) dp2.getParent()).getComponentCount());
							oi.setName(iName);
							result.addConnection((IconConnection)oi);
						}
					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	}
            }
            
            result.setObsObj(dp2.getObsObj());
        }
    return result;
    }
    
}
