/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.asu.CSE360.listener;

import java.awt.dnd.*;
import javax.swing.*;

import edu.asu.CSE360.gui.DrawFrame;

import java.awt.datatransfer.*;
import java.io.IOException;


/**
 *
 * @author Adam
 */
public class DragListener implements DragGestureListener {
    
    
    public DragListener () {
    	
    }
    
    @Override
    public void dragGestureRecognized(DragGestureEvent e){        
        Transferable transfer = new Transferable(){
            @Override
            public DataFlavor[] getTransferDataFlavors(){
                return DrawFrame.getDataFlavor();
            }
            
            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor){
                boolean result = false;
                DataFlavor[] dndFlavor = getTransferDataFlavors ();
                
                for ( int i = 0; i < dndFlavor.length; i++ ) {
                	if ( dndFlavor[i].equals(flavor)) {
                		result = true;
                		break;
                	}
                }
                return result;
            }
            
            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {           	
            	return e.getComponent();
            }
        };
        
        e.startDrag(null, transfer);
    }
}
