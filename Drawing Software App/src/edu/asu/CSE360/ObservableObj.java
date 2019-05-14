package edu.asu.CSE360;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.asu.CSE360.gui.IconConnection;
import edu.asu.CSE360.gui.ObservableIcon;


/**
 * @author Tina Peng
 *
 */
public class ObservableObj extends Observable {
	List<Component> cList = new ArrayList<Component>();
	List<IconConnection> connectionList = new ArrayList<IconConnection>();
        int[][] Connectiontable;
    List<Observer> obsList = new ArrayList<Observer>();
    
	public ObservableObj() {}

	public void addObserver ( Observer observer) {
		super.addObserver(observer);
		obsList.add(observer);
	}
	public Observer getObserver() {
		return obsList.get(0);
	}
	public Observer getObserver( int index) {
		return obsList.get(index);
	}
	public int getObserverCount( ) {
		return obsList.size();
	}
	public void add (Component cObj) {
//System.out.println("You added " + cObj.getClass());
		cList.add(cObj);
		((ObservableIcon)cObj).setStatus(ObservableIcon.STATUS_ADD);
		setChanged();
		notifyObservers(cObj);
	}
        
    public void addConnection (IconConnection cObj) {
		connectionList.add(cObj);
		((ObservableIcon)cObj).setStatus(ObservableIcon.STATUS_ADD);
//		setChanged();
//		notifyObservers(cObj);
	}
        
	public void remove () {
		
		for ( int i = cList.size() -1; i >= 0; i--) {
			remove(i);
		}
	}
     
	public void remove ( String name) {
		Component cObj;
		for ( int i = cList.size()-1; i>=0; i--) {
			cObj = cList.get(i);
			if ( cObj.getName().equals(name)) {
				cList.remove(i);
				((ObservableIcon)cObj).setStatus(ObservableIcon.STATUS_DELETE);
				setChanged();
				notifyObservers(cObj);
				break;
			}
		}
		String cName;
		for ( int i = connectionList.size()-1; i >= 0 ; i--) {
			cName = connectionList.get(i).getName();
			if (name.equals(cName)) {
				connectionList.remove(i);
				break;
			}
		}
		
	}
	public void remove ( int index) {
		Component cObj = cList.get(index);
		((ObservableIcon)cObj).setStatus(ObservableIcon.STATUS_DELETE);
		
		String name, cName = cObj.getName();
		for ( int i = connectionList.size()-1; i >= 0 ; i--) {
			name = connectionList.get(i).getName();
			if (name.equals(cObj.getName())) {
				connectionList.remove(i);
//				break;
			}
		}
		cList.remove(index);
		setChanged();
		//notifyObservers(cObj);
                
	}
        
    public Component getComponent(int i){
        return cList.get(i);
    }
    public Component[] getComponent(){
        return cList.toArray(new Component[cList.size()]);
    }
    public int getConnctionAmount(){
        return cList.size();
    }
        
    public void setChanged () {
    	super.setChanged();
    }
}
