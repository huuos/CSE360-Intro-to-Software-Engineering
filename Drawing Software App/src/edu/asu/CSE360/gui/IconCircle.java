/**
 * 
 */
package edu.asu.CSE360.gui;

import java.util.ArrayList;
import javax.swing.Icon;

/**
 * @author Tina Peng
 *
 */
public class IconCircle extends ObservableIcon implements Cloneable {	
    private static boolean iconExtend;
    private static int implementNum;
    private static int aggrNum;
    private static int assocNum;
    private static ArrayList<String> iconName;
    private static ArrayList<String> extendName;
    private static ArrayList<ArrayList<String>> implementName;
    private static ArrayList<String> iconImplementNum;
    private static ArrayList<String> iconAggrNum;
    private static ArrayList<String> iconAssocNum;
    private static ArrayList<ArrayList<String>> assocName;
    private static ArrayList<ArrayList<String>> aggrName;
	/**
	 * 
	 */
    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
    public IconCircle() {
    	super();
	// TODO Auto-generated constructor stub
        iconName = new ArrayList<String>(); 
        extendName = new ArrayList<String>(); 
        implementName = new ArrayList<ArrayList<String>>();
        iconImplementNum = new ArrayList<String>();
        iconAggrNum = new ArrayList<String>();
        iconAssocNum = new ArrayList<String>();
        assocName = new ArrayList<ArrayList<String>>();
        aggrName = new ArrayList<ArrayList<String>>();
               
    }
    
    /** function to clear **/
    public void clearIconName()
    {
        iconName.clear();
    }
    public void clearExtendName()
    {
        extendName.clear();
    }
    public void clearImplementName()
    {
        implementName.clear();
    }
    public void clearIconImplementNum()
    {
        iconImplementNum.clear();
    }
    public void clearIconAggrNum()
    {
        iconAggrNum.clear();
    }
    public void clearIconAssocNum()
    {
        iconAssocNum.clear();
    }
    /** function to get size **/
    public int getIconNameSize()
    {
        return iconName.size();
    }
    public int getExtendNameSize()
    {
        return extendName.size();
    }
    public int getImplementNameSize()
    {
        return implementName.size();
    }
    public int getIconImplementNumSize()
    {
        return iconImplementNum.size();
    }
    public int getIconAggrNumSize()
    {
        return iconAggrNum.size();
    }
    public int getIconAssocNumSize()
    {
        return iconAssocNum.size();
    }
    /** function to insert element **/
    public void insertIconName(int key)
    {
        iconName.add(Integer.toString(key));
    }
    public void insertExtendName(int key)
    {
        extendName.add(Integer.toString(key));
    }
    /*public void insertImplementName(int key)
    {
        implementName.add(Integer.toString(key));
    }*/
    public void insertIconImplementNum(int key)
    {
        iconImplementNum.add(Integer.toString(key));
    }
    public void insertIconAggrNum(int key)
    {
        iconAggrNum.add(Integer.toString(key));
    }
    public void insertIconAssocNum(int key)
    {
        iconAssocNum.add(Integer.toString(key));
    }
    /** function to insert element at specific index **/
    public void insertIconName(int index, int key)
    {
        iconName.add(index, Integer.toString(key));
    }
    public void insertExtendName(int index, int key)
    {
        extendName.add(index, Integer.toString(key));
    }
    /*public void insertImplementName(int index, int key)
    {
        implementName.add(index, Integer.toString(key));
    }*/
    public void insertIconImplementNum(int index, int key)
    {
        iconImplementNum.add(index, Integer.toString(key));
    }
    public void insertIconAggrNum(int index, int key)
    {
        iconAggrNum.add(index, Integer.toString(key));
    }
    public void insertIconAssocNum(int index, int key)
    {
        iconAssocNum.add(index, Integer.toString(key));
    }
    /**
    /** function to get element at index **/
    public String getIconName(int index)
    {
        if (index >= iconName.size())
            return "";
        return iconName.get(index);
    }
    public String getExtendName(int index)
    {
        if (index >= extendName.size())
            return "";
        return extendName.get(index);
    }
    /*public String getImplementName(int index)
    {
        if (index >= implementName.size())
            return "";
        return implementName.get(index);
    }*/
    public String getIconImplementNum(int index)
    {
        if (index >= iconImplementNum.size())
            return "";
        return iconImplementNum.get(index);
    }
    public String getIconAggrNum(int index)
    {
        if (index >= iconAggrNum.size())
            return "";
        return iconAggrNum.get(index);
    }
    public String getIconAssocNum(int index)
    {
        if (index >= iconAssocNum.size())
            return "";
        return iconAssocNum.get(index);
    }
    /** function to remove element at index **/
    public void removeIndexIconName(int index)
    {
        if (index >= iconName.size())
            return ;
        iconName.remove(index);
    }
    public void removeIndexExtendName(int index)
    {
        if (index >= extendName.size())
            return ;
        extendName.remove(index);
    }  
    public void removeIndexImplementName(int index)
    {
        if (index >= implementName.size())
            return ;
        implementName.remove(index);
    }  
    public void removeIndexIconImplementNum(int index)
    {
        if (index >= iconImplementNum.size())
            return;
        iconImplementNum.remove(index);
    }
    public void removeIndexIconAggrNum(int index)
    {
        if (index >= iconAggrNum.size())
            return;
        iconAggrNum.remove(index);
    }
    public void removeIndexIconAssocNum(int index)
    {
        if (index >= iconAssocNum.size())
            return;
        iconAssocNum.remove(index);
    }
    /** function to remove element **/
    public void removeKeyIconName(int key)
    {
        iconName.remove(Integer.toString(key));
    }
    public void removeKeyExtendName(int key)
    {
        extendName.remove(Integer.toString(key));
    }
    public void removeKeyImplementName(int key)
    {
        implementName.remove(Integer.toString(key));
    }
    public void removeKeyIconImplementNum(int key)
    {
        iconImplementNum.remove(Integer.toString(key));
    }
    public void removeKeyIconAggrNum(int key)
    {
        iconAggrNum.remove(Integer.toString(key));
    }
    public void removeKeyIconAssocNum(int key)
    {
        iconAssocNum.remove(Integer.toString(key));
    }
    /** function to display array **/
    public void displayIconName()
    {
        System.out.println("\nCircle Icon Name Array : "+ iconName);
        System.out.println();
    }
    public void displayExtendName()
    {
        System.out.println("\nCircle Extend Name Array : "+ extendName);
        System.out.println();
    }
    public void displayImplementName()
    {
        System.out.println("\nCircle Implement Name Array : "+ implementName);
        System.out.println();
    }
    public void displayIconImplementNum()
    {
        System.out.println("\nSquare Icon Implement Num Array : "+ iconImplementNum);
        System.out.println();
    }
    public void displayIconAggrNum()
    {
        System.out.println("\nSquare Icon Aggr Num Array : "+ iconAggrNum);
        System.out.println();
    }
    public void displayIconAssocNum()
    {
        System.out.println("\nSquare Icon Assoc Num Array : "+ iconAssocNum);
        System.out.println();
    }
    public void setIconExtend(boolean iconExtend){
        this.iconExtend = iconExtend;
    }
    public boolean getIconExtend(){
        return this.iconExtend;
    }
    /**  Function to set implement Num  **/
    public void setImplementNum(int implementNum){
        this.implementNum = implementNum;
    }
    /**  Function to get implement Num  **/
    public int getImplementNum(){
        return this.implementNum;
    }
    /**  Function to set aggr Num  **/
    public void setAggrNum(int aggrNum){
        this.aggrNum = aggrNum;
    }
    /**  Function to get aggr Num  **/
    public int getAggrNum(){
        return this.aggrNum;
    }
    /**  Function to set assoc Num  **/
    public void setAssocNum(int assocNum){
        this.assocNum = assocNum;
    }
    /**  Function to get assoc Num  **/
    public int getAssocNum(){
        return this.assocNum;
    }
    public boolean iconNameListContains(String string){
        boolean flag;
        if (iconName.contains(string)){
            flag = true;
        }
        else {
            flag = false;
        }
        return flag;
    }
	/**
	 * @param text
	 */
	public IconCircle(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 */
	public IconCircle(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param horizontalAlignment
	 */
	public IconCircle(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 * @param horizontalAlignment
	 */
	public IconCircle(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param icon
	 * @param horizontalAlignment
	 */
	public IconCircle(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}
        
    public void addImplementNameRow(ArrayList<String> row){
        implementName.add(row);
        }
    public void addImplementNameRow(int index, ArrayList<String> row){
            implementName.add(index, row);        
    }
    public String getImplementName(int row, int column) throws IllegalArgumentException{
        if (row >= implementName.size()){
            throw new IllegalArgumentException("row selected is larger than implementName size");
        }else if(column >= implementName.get(row).size()){
            throw new IllegalArgumentException("column selected is larger than implementName column size");
        }  
        return implementName.get(row).get(column);
    }
    public ArrayList<String> getImplementNameRow(int index){
        return implementName.get(index);
    }
    public void removeImplementName(int index){
        implementName.remove(index);
    }
    public void displayImplementNameArray(){
        System.out.println("Implement name 2d array: " + implementName);
    }
    public void addNewImplementNamRow(){
        implementName.add(new ArrayList<String>());
    }
    
    //Association methods
    public void addAssocNameRow(ArrayList<String> row){
            assocName.add(row);        
    }
    public void addAssocNameRow(int index, ArrayList<String> row){
            assocName.add(index, row);        
    }
    public String getAssocName(int row, int column) throws IllegalArgumentException {
        if (row >= assocName.size()){
            throw new IllegalArgumentException("row selected is larger than assocName size");
        }else if(column >= assocName.get(row).size()){
            throw new IllegalArgumentException("column selected is larger than assocName column size");
        }  
        return assocName.get(row).get(column);
    }
    public ArrayList<String> getAssocNameRow(int index){
        return assocName.get(index);
    }
    public void removeAssocName(int index){
        assocName.remove(index);
    }
    public void displayAssocNameArray(){
        System.out.println("Assoc name 2d array: " + assocName);
    }
    public void addNewAssocNamRow(){
        assocName.add(new ArrayList<String>());
    }
    
    //Aggregation methods
    public void addAggrNameRow(ArrayList<String> row){
            aggrName.add(row);        
    }
    public void addAggrNameRow(int index, ArrayList<String> row){
            aggrName.add(index, row);        
    }
    public String getAggrName(int row, int column) throws IllegalArgumentException {
        if (row >= aggrName.size()){
            throw new IllegalArgumentException("row selected is larger than aggrName size");
        }else if(column >= assocName.get(row).size()){
            throw new IllegalArgumentException("column selected is larger than aggrName column size");
        }  
        return aggrName.get(row).get(column);
    }
    public ArrayList<String> getAggrNameRow(int index){
        return aggrName.get(index);
    }
    public void removeAggrName(int index){
        aggrName.remove(index);
    }
    public void displayAggrNameArray(){
        System.out.println("Aggr name 2d array: " + aggrName);
    }
    public void addNewAggrNamRow(){
        aggrName.add(new ArrayList<String>());
    }

    public String toString () {
    	return "public Interface "+getName()+" {}";
    }
}
