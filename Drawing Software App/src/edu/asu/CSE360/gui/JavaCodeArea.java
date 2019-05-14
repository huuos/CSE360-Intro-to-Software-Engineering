/**
 * 
 */
package edu.asu.CSE360.gui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextArea;
import javax.swing.text.Document;

import edu.asu.CSE360.ObservableObj;

/**
 * @author Tina Peng
 *
 */
public class JavaCodeArea extends JTextArea implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int circleIndex = 1;
	private int squareIndex = 1;
	private int connectionIndex = 1;
	private int textIndex = 1;
	private ObservableObj obsObj = new  ObservableObj ();
    
        private final int zero = 0;
        private int iconNum = 0;
        private static int squareNum = 0;
        private static int circleNum = 0;
        private int implementNum, aggrNum, assocNum;
        private int iconImplementNum, iconAggrNum, iconAssocNum;
        private int j;
        private boolean iconExtend;
        IconSquare iconSquare = new IconSquare();
        IconCircle iconCircle = new IconCircle();
        private ArrayList<String> newImplementNameRow = new ArrayList<String>();
        // index of this list is the index of TabedPane. element is list of text
        private List textList = new ArrayList();
        private int currentListIndex = 0;
        
	/**
	 * 
	 */
	public JavaCodeArea() {
		textList.add(new ArrayList());
	}

	/**
	 * @param arg0
	 */
	public JavaCodeArea(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public JavaCodeArea(Document arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public JavaCodeArea(int arg0, int arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public JavaCodeArea(String arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public JavaCodeArea(Document arg0, String arg1, int arg2, int arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable obsObj, Object arg1) {
		String msg = getMessage(obsObj, arg1);
		int status = ((ObservableIcon)arg1).getStatus();
		if ( status == ObservableIcon.STATUS_ADD) {
			append(msg);
        	((List)textList.get(currentListIndex)).add(msg);
    	} else if ( status == ObservableIcon.STATUS_DELETE) {
    		List tList = (List)textList.get(currentListIndex);
    		if ( tList.contains(msg)) {
    			tList.remove(msg);
    			if ( tList.size() > 0) {
    				setText(" ");
	        		for ( int j = 0; j < tList.size(); j++) {
	        			append((String)tList.get(j));
	        		}
    			} else {
    				setText(" ");
    			}
    		}
    	}
		this.repaint();
			/* for project 3
		if ( arg1 instanceof IconCircle ) {
			iconNum++;
                    circleNum++;
                    iconCircle.insertIconName(iconNum);
                    iconSquare.insertIconName(zero);
                    iconSquare.insertExtendName(zero);
                    iconCircle.insertExtendName(zero);
                    //iconSquare.insertImplementName(zero);
                    //iconCircle.insertImplementName(zero);
                    iconSquare.displayImplementNameArray();
                    iconCircle.displayImplementNameArray();
                    iconSquare.addNewImplementNamRow();
                    iconCircle.addNewImplementNamRow();             
                    iconSquare.insertIconImplementNum(zero);
                    iconCircle.insertIconImplementNum(zero);
                    
                    iconSquare.addNewAggrNamRow();
                    iconCircle.addNewAggrNamRow();             
                    iconSquare.insertIconAggrNum(zero);
                    iconCircle.insertIconAggrNum(zero);
                    
                    iconSquare.addNewAssocNamRow();
                    iconCircle.addNewAssocNamRow();             
                    iconSquare.insertIconAssocNum(zero);
                    iconCircle.insertIconAssocNum(zero);
                    //msg = "public interface IconCircle"+(circleIndex++) + "{ \r\n} \r\n";
		} else if ( arg1 instanceof IconSquare ) {
                    iconNum++;
                    squareNum++;
                    iconSquare.insertIconName(iconNum);
                    iconCircle.insertIconName(zero);
                    iconSquare.insertExtendName(zero);
                    iconCircle.insertExtendName(zero);
                    iconSquare.displayImplementNameArray();
                    iconCircle.displayImplementNameArray();
                    //iconSquare.insertImplementName(zero);
                    //iconCircle.insertImplementName(zero);
                    iconSquare.addNewImplementNamRow();
                    iconCircle.addNewImplementNamRow();
                    iconSquare.insertIconImplementNum(zero);
                    iconCircle.insertIconImplementNum(zero);
                    
                    iconSquare.addNewAggrNamRow();
                    iconCircle.addNewAggrNamRow();             
                    iconSquare.insertIconAggrNum(zero);
                    iconCircle.insertIconAggrNum(zero);
                    
                    iconSquare.addNewAssocNamRow();
                    iconCircle.addNewAssocNamRow();             
                    iconSquare.insertIconAssocNum(zero);
                    iconCircle.insertIconAssocNum(zero);
                } else if ( arg1 instanceof IconConnection ) {
			int conType = ((IconConnection) arg1).getConnectionType();
			String fromObjName="", toObjName="";
			msg = "public class IconConnection"+(connectionIndex++) + " { \r\n} \r\n";
			try {
				fromObjName = getClassObjName (((IconConnection) arg1).getFromObj());
				toObjName = getClassObjName (((IconConnection) arg1).getToObj());
				
				switch (conType) {
				case IconConnection.CONNECTION_TYPE_AGGREGATION:
					break;
				case IconConnection.CONNECTION_TYPE_ASSOCIATION:
					msg += "public class " + fromObjName + " implements " + toObjName + "{\r\n }";
					break;
				case IconConnection.CONNECTION_TYPE_INHERITANCE:
					msg += "public class " + fromObjName +" {\r\n" +
					"\t " + toObjName + " " + toObjName.toLowerCase()+ ";\r\n}\r\n";
					break;
				
				}
			} catch ( Exception e) {
				
			}
			
		} else {
			msg = "public class Text"+(textIndex++) + " { } \r\n";
		}
		}
		
		//this.append(msg);
        this.writeCode(((ObservableIcon)arg1).getStatus());
        */
	}

	private String getClassObjName ( ObservableIcon oIcon) {
		String result = oIcon.getClass().getSimpleName();
		return result;
	}
    public ObservableObj getObsObj() {
		return obsObj;
	}

	public void setObsObj(ObservableObj obsObj) {
		this.obsObj = obsObj;
	}

	public void setText ( String text ) {
		super.setText(text);
		if ( text == null || text.trim().length() == 0) {
			obsObj.setChanged();
			obsObj.notifyObservers(this);
		}
	}
	public void writeCode( int status){  
        this.setText("");
        String msg = "";
        for (int i = 0; i < iconNum; i++){
            
            if(Integer.parseInt(iconCircle.getIconName(i)) == 0){ 
                implementNum = iconSquare.getImplementNum();
                aggrNum = iconSquare.getAggrNum();
                assocNum = iconSquare.getAssocNum();
                msg +="public class IconSquare" + iconSquare.getIconName(i);
                iconExtend = iconSquare.getIconExtend();
                
                if (iconExtend == true) {
                    if (Integer.parseInt(iconSquare.getExtendName(i)) == 0);
                   else{
                	   msg += " extends IconSquare" + iconSquare.getExtendName(i);
                    }
                }
                
                
                if (implementNum == 1) {
                    iconImplementNum = Integer.parseInt(iconSquare.getIconImplementNum(i));
                    if(iconImplementNum > 0){
                    	msg +=" implements IconCircle" + iconSquare.getImplementName(i, i);
                    }
                }
                else if (implementNum > 1) {
                    
                    iconImplementNum = Integer.parseInt(iconSquare.getIconImplementNum(i));
                    if(iconImplementNum > 0){
                    	msg +=" implements ";
                    }
                    for(int k = 0; k < iconImplementNum; k++)
                    {
                        if(k < iconImplementNum-1)
                        {
                        	msg +="IconSquare" + iconCircle.getImplementName(i, k) + ", ";
                        }else{
                        	msg +="IconSquare" + iconCircle.getImplementName(i, k);
                        }
                        
                    }
                }
                this.append(" {");
                if (aggrNum >= 1){
                    iconAggrNum = Integer.parseInt(iconSquare.getIconAggrNum(i));
                    for(int n = 0; n < iconAggrNum; n++){
                        ArrayList<String> aggrNameRowString = iconSquare.getAggrNameRow(i);
                        msg +="\n\tIconSquare" + aggrNameRowString.get(n) + " iconSquare" + aggrNameRowString.get(n) + ";";
                    }
                }
                if (assocNum >= 1){
                    iconAssocNum = Integer.parseInt(iconSquare.getIconAssocNum(i));
                    if(iconAssocNum > 0){
                    	msg +="\n\n\tpublic void method(){";
                    }
                    for(int m = 0; m < iconAssocNum; m++){
                        ArrayList<String> assocNameRowString = iconSquare.getAssocNameRow(i);
                        msg +="\n\t\tIconSquare" + assocNameRowString.get(m) + " iconSquare" + assocNameRowString.get(m) + ";";
                    }
                    msg +="\n\t}";
                }
                msg +="\n}\n";
            }            
            else if(Integer.parseInt(iconSquare.getIconName(i)) == 0){
                implementNum = iconCircle.getImplementNum();
                aggrNum = iconCircle.getAggrNum();
                assocNum = iconCircle.getAssocNum();
                msg +="public interface IconCircle" + iconCircle.getIconName(i);
                iconExtend = iconCircle.getIconExtend();
                
                if (iconExtend == true) {
                    if (Integer.parseInt(iconCircle.getExtendName(i)) == 0);
                   else{
                	   msg +=" extends IconCircle" + iconCircle.getExtendName(i);
                    }
                }
                
                if (implementNum == 1) {
                    iconImplementNum = Integer.parseInt(iconCircle.getIconImplementNum(i));
                    if(iconImplementNum > 0){
                    	msg +=" implements IconCircle" + iconCircle.getImplementName(i, i);
                    }
                }
                else if (implementNum > 1) {
                    iconImplementNum = Integer.parseInt(iconCircle.getIconImplementNum(i));
                    if(iconImplementNum > 0){
                    	msg +=" implements ";
                    }
                    for(int k = 0; k < iconImplementNum; k++)
                    {
                        if(k < iconImplementNum-1)
                        {
                        	msg +="IconCircle" + iconCircle.getImplementName(i, k) + ", ";
                        }else{
                        	msg +="IconCircle" + iconCircle.getImplementName(i, k);
                        }
                    }
                }
                
                msg +=" {";
                if (aggrNum >= 1){
                    iconAggrNum = Integer.parseInt(iconCircle.getIconAggrNum(i));
                    for(int n = 0; n < iconAggrNum; n++){
                        ArrayList<String> aggrNameRowString = iconCircle.getAggrNameRow(i);
                        msg +="\n\tIconCircle" + aggrNameRowString.get(n) + " iconCircle" + aggrNameRowString.get(n) + ";";
                    }
                }
                if (assocNum >= 1){
                    iconAssocNum = Integer.parseInt(iconCircle.getIconAssocNum(i));
                    if(iconAssocNum > 0){
                    	msg +="\n\n\tpublic void method(){";
                    }
                    for(int m = 0; m < iconAssocNum; m++){
                        ArrayList<String> assocNameRowString = iconCircle.getAssocNameRow(i);
                        msg +="\n\t\tIconCircle" + assocNameRowString.get(m) + " iconCircle" + assocNameRowString.get(m) + ";";
                    }
                    msg +="\n\t}";
                }
                
                msg +="\n}\n";
            }
            if ( status == ObservableIcon.STATUS_ADD) {
            	append(msg);
            	((List)textList.get(currentListIndex)).add(msg);
        	} else if ( status == ObservableIcon.STATUS_DELETE) {
            	iconNum --;
        		List tList = (List)textList.get(currentListIndex);
        		if ( tList.contains(msg)) {
        			tList.remove(msg);
        			if ( tList.size() > 0) {
            		for ( int j = 0; j < tList.size(); j++) {
            			append((String)tList.get(j));
            		}
        			} else {
        				setText(" ");
        			}
        		}
        	}
//            this.repaint();
            this.revalidate();
            
            
        }
    }
    
    public void setCurrentListIndex ( int index) {
    	setText(" ");
    	if ( index >= textList.size() ) {
    		textList.add(new ArrayList());
    		
    		currentListIndex = index-1;
    	} else {
    		currentListIndex = index-1;
    		List tList = (List)textList.get(currentListIndex);
    		for ( int i = 0; i < tList.size(); i++) {
    			append((String)tList.get(i));
    		}
    	}
    }
    
    public String getMessage(Observable obsObj, Object arg1) {
		String msg = "\r\n";
		if ( arg1 instanceof IconCircle ) {
			msg = "public interface "+((Component) arg1).getName() + "{ \r\n} \r\n";
		} else if ( arg1 instanceof IconSquare ) {
			msg = "public class "+((Component) arg1).getName() + " { \r\n} \r\n";
		} else if ( arg1 instanceof IconConnection ) {
			int conType = ((IconConnection) arg1).getConnectionType();
			String fromObjName="", toObjName="", tVarName = "";
			try {
				fromObjName = getClassObjName (((IconConnection) arg1).getFromObj());
				toObjName = getClassObjName (((IconConnection) arg1).getToObj());
				tVarName =((IconConnection) arg1).getToObj().getName();
				
				switch (conType) {
				case IconConnection.CONNECTION_TYPE_AGGREGATION:
					msg += "public class " + fromObjName +" {\r\n" +
							"\t " + toObjName + "    " + tVarName+ ";\r\n}\r\n";
					break;
				case IconConnection.CONNECTION_TYPE_ASSOCIATION:
					msg += "public class " + fromObjName +" {\r\n" +
							"\t " + toObjName + "    " + tVarName+ ";\r\n}\r\n";
					break;
				case IconConnection.CONNECTION_TYPE_INHERITANCE:
					msg += "public class " + fromObjName + " implements " + toObjName + "{\r\n }\r\n";
					break;
				
				}
			} catch ( Exception e) {
				e.printStackTrace();
			}
			
		} else {
			msg = "public class "+((Component) arg1).getName() + " {\r\n } \r\n";
		}
		return msg;

	}

}
