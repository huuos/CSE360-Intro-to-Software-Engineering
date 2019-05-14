/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.gui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseListener;
import javax.swing.*;

import edu.asu.CSE360.ObservableObj;
import edu.asu.CSE360.listener.DrawPanelTwoLayout;
import edu.asu.CSE360.listener.DrawPanelTwoMouseListener;
import edu.asu.CSE360.listener.DropListener;

import java.awt.*;

/**
 *
 * @author Adam
 */
public class DrawPanelTwo extends JPanel  {
    private static final long serialVersionUID = 1L;
    private static final int PANEL_MODEL_PROBLEM 	= 1;
    private static final int PANEL_MODEL_ANSWER 	= 2;
    
    public static ObservableObj obsObj = new  ObservableObj ();
    private DrawOptionsPanel optionsPanel;
    private Color backgroundColor = Color.white;
    private boolean allowDrop = false;
    private int model;
    
    public int x1,y1,x2,y2, x, y, pic, lineCount, lineType;
    
    //Vectors will store points of all lines drawn
    public ObservableIcon to, from;
    //public List<IconConnection> connectionList = new ArrayList<IconConnection>();
    //public Color col;
    
    
        
    public DrawPanelTwo(){
    	super();
    	setLayout(new DrawPanelTwoLayout());
    	this.setMinimumSize(new Dimension(600, 100));
    	this.setPreferredSize(new Dimension(600, 400));
    	super.setOpaque(true);
    	super.setBackground(Color.white);
    	new DropListener(this);
    	TransferHandler dragAndDrop = new TransferHandler(){
            @Override
            public boolean canImport(TransferSupport support){
                if (!support.isDrop()){
                    return false;
                }
                if (!support.isDataFlavorSupported(DataFlavor.imageFlavor)){
                    return false;
                }
                return true;
            }
            
            @Override
            public boolean importData(TransferSupport support){
                if (!canImport(support)){
                    return false;
                }
                Transferable transfer = support.getTransferable();
                Icon pic;
                try{
                    pic = (Icon) transfer.getTransferData(DataFlavor.imageFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                add(new JLabel(pic));
                return true;
            }
        };
        setTransferHandler(dragAndDrop); 
    }//End of constructor
   
    public DrawOptionsPanel getOptionsPanel() {
        return optionsPanel;
    }

    public void setOptionsPanel(DrawOptionsPanel optionsPanel) {
        this.optionsPanel = optionsPanel;
    }
    
    public Component add (Component cObj) {
    	super.add(cObj);
    	obsObj.add(cObj);
    	return cObj;
    }
    
    public void addConnection(IconConnection ic){
        obsObj.addConnection(ic);
        System.out.println("ConnectionAddedtopanel"+ic.getName().substring(ic.getName().length()-1));
        repaint();
    }
    
    public void remove(int index) {
    	Component cObj = super.getComponent(index);
//    	obsObj.remove(index);
    	obsObj.remove(cObj.getName());
    	super.remove(index);
    }
    
    public void removeAll() {
    	obsObj.remove();
    	super.removeAll();
    }
    
    public void clean () {
    	this.removeAll();
        x1 = y1 = x2 = y2 = x = y = lineCount=0;
        MouseListener[] mLList = this.getMouseListeners();

        for ( int i = 0; i < mLList.length; i++) {
        	((DrawPanelTwoMouseListener)mLList[i]).setLineCount(0);
        }
        super.paint(getParent().getGraphics());
    }
    //Draw line between 2 user mouse clicks on panel 2
    //ArrayList<IconConnection> connectionList = new ArrayList<IconConnection>();
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        System.out.println("RepaintCalled");
//        MainPanel dtPanel = (DrawingTabbedPanel) getParent();
        if ( obsObj != null ) {
	        for(int i = 0;i<obsObj.getConnctionAmount();i++){
	            if(obsObj.getComponent(i).getClass().toString().contains("IconConnection")){
	                IconConnection ic = (IconConnection)obsObj.getComponent(i);
//	                if ( ic.getName().endsWith(""+dtPanel.getSelectedIndex())) {
	                	ic.draw(g);
//	                }
	            }
	        }
        }
    }        //End of paintComponent
    
    //
    //  getters and setters
    //

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
    }

    
    public Color getBackgroundColor() {
		return backgroundColor;
	}

	public ObservableObj getObsObj() {
		return obsObj;
	}

	public boolean isAllowDrop() {
		return allowDrop;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
		if ( model == this.PANEL_MODEL_ANSWER) {
			setAllowDrop ( true);
		} else if ( model == this.PANEL_MODEL_PROBLEM) {
			setAllowDrop ( false);
		} else {
			
		}
	}

	public void setAllowDrop(boolean allowDrop) {
		this.allowDrop = allowDrop;
	}

	public void setObsObj(ObservableObj obsObj) {
		this.obsObj = obsObj;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

    public void setTo(ObservableIcon t){
        this.to = t;
    }
    public void setFrom(ObservableIcon f){
        this.from = f;
    }
    public ObservableIcon getTo(){
        return to;
    }
    public ObservableIcon getFrom(){
        return from;
    }
     public int getLineRelationship(){
        return optionsPanel.getRelationshipOptions();
    }
     
     public int getTypeComponentCount (String cType) {
     	int result = 0;
     	Component[] cList = getComponents();
     	
     	for ( int i = 0; i < cList.length; i++) {
     		if ( cList[i].getClass().getSimpleName().equals(cType)) {
     			result ++;
     		}
     	}
     	return result;
     }

}
