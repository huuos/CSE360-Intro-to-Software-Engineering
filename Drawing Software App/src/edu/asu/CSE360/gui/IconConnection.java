/**
 * 
 */
package edu.asu.CSE360.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import javax.swing.Icon;

/**
 * @author Tina Peng
 * @author Adam Hanrahan
 *
 */
public class IconConnection extends ObservableIcon {
	public static final int CONNECTION_TYPE_INHERITANCE = 1;
	public static final int CONNECTION_TYPE_AGGREGATION = 2;
	public static final int CONNECTION_TYPE_ASSOCIATION = 3;
	
	private int connectionType = 0;
	private ObservableIcon fromObj;
	private ObservableIcon toObj;
    private Point fromPoint, toPoint;
    private byte style;
    private Color col;
	
	/**
	 * 
	 */
	public IconConnection() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 */
	public IconConnection(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 */
	public IconConnection(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param horizontalAlignment
	 */
	public IconConnection(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 * @param horizontalAlignment
	 */
	public IconConnection(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param icon
	 * @param horizontalAlignment
	 */
	public IconConnection(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}
        
    public IconConnection(String text, Point to, Point from){
        super();
        col = Color.black;
        this.fromPoint = from;
        this.toPoint = to;
        fixPoint(toPoint,fromPoint);
    }
        
    public IconConnection(Color c, byte s, Point to, Point from){
    	super();
        this.col = c;
        this.style = s;
        this.fromPoint = from;
        this.toPoint = to;
        fixPoint(toPoint,fromPoint);
    }
        
    public IconConnection(int iType, ObservableIcon  fromObj, ObservableIcon toObj) {
    	super();
    	connectionType = iType;
    	this.fromObj = fromObj;
    	this.toObj = toObj;
    }
	public void draw (Graphics g) {
        if(connectionType == 0){
               g.setColor(col);
               if(style == 0){
                    g.drawLine(fromPoint.x,fromPoint.y,toPoint.x,toPoint.y);                   
               }
               else{
                    System.out.println("Drawing dashed");                    
                    Graphics2D g2d = (Graphics2D) g.create();
                    Stroke dashed = new BasicStroke(2, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2d.setStroke(dashed);
                    g2d.setColor(col);
                    g2d.drawLine(fromPoint.x,fromPoint.y,toPoint.x,toPoint.y);
                    g2d.dispose();                   
               }
        }
        else{
                g.setColor(col);
                g.drawLine(fromPoint.x,fromPoint.y,toPoint.x,toPoint.y);        //The arrow will always get drawn but the head will be different based on the connection type
                Graphics2D g2d = (Graphics2D)g;
                //Point sw = new Point(x1,y1);
                //Point ne = new Point(x2,y2);				
			
		if(connectionType == 1){
                    //INHERITANCE  Triange head
                    drawTriangle(g2d,toPoint,fromPoint,col);			
                }
                else if(connectionType == 2){
                    //AGGREGATION  Diamond head
                    drawDiamond(g2d,toPoint,fromPoint,col);
                }
                else if(connectionType == 3){
                    //ASSOCIATION  Arrow head
                    drawArrow(g2d,toPoint,fromPoint,col);
                }    
        }
	}
	
	public int getConnectionType() {
		return connectionType;
	}

	public ObservableIcon getFromObj() {
		return fromObj;
	}

	public ObservableIcon getToObj() {
		return toObj;
	}

	public Point getFromPoint () {
		return fromPoint;
	}
	
	public void setFromPoint ( Point sPoint) {
		fromPoint = sPoint;
	}
	public Point getToPoint () {
		return toPoint;
	}
	
	public void setToPoint ( Point ePoint) {
		toPoint = ePoint;
	}
	public void setToObj(ObservableIcon toObj) {
		this.toObj = toObj;
	}

	public void setFromObj(ObservableIcon fromObj) {
		this.fromObj = fromObj;
	}

	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}
        
	public String toString () {
		String result = "";
		
		switch (getConnectionType()) {
		case IconConnection.CONNECTION_TYPE_AGGREGATION:
			if ( getFromObj() instanceof IconCircle ) {
				result += "public Interface " + getFromObj().getName() + " { " + getToObj().getClass().getSimpleName() + " " + firstCharToLowerCase(getToObj().getName()) + ";}";
			} else {
				result += "public Class " + getFromObj().getName() + " { " + getToObj().getClass().getSimpleName() + " " + firstCharToLowerCase(getToObj().getName()) + ";}";
			}
			break;
		case IconConnection.CONNECTION_TYPE_ASSOCIATION:
			if ( getFromObj() instanceof IconCircle ) {
				result += "public Interface " + getFromObj().getName() + " { " + getToObj().getClass().getSimpleName() + " " + firstCharToLowerCase(getToObj().getName()) + ";}";
			} else {
				result += "public Class " + getFromObj().getName() + " { " + getToObj().getClass().getSimpleName() + " " + firstCharToLowerCase(getToObj().getName()) + ";}";
			}
			break;
		case IconConnection.CONNECTION_TYPE_INHERITANCE:
			if ( getFromObj() instanceof IconCircle ) {
				result += "public Interface " + getFromObj().getName() + " implements " + getToObj().getName()+ " {}";
			} else {
				result += "public Class " + getFromObj().getName() + " implements " + getToObj().getName()+ " {}";
			}
			break;
			
		}
		
		return result;
	}
	
	public void drawArrow(Graphics2D g2d, Point tip, Point tail, Color col){
            //This is used for drawing the association connection arrow
            double dy = tip.y - tail.y;
            double dx = tip.x - tail.x;
            double phi = Math.toRadians(45);
            double theta = Math.atan2(dy,dx);
            double x,y,rho = theta + phi;
            g2d.setColor(col);

            for(int i=0;i<2;i++){
                x = tip.x - 20 * Math.cos(rho);
                y = tip.y - 20 * Math.sin(rho);
                g2d.draw(new Line2D.Double(tip.x, tip.y, x, y));
                rho = theta - phi;
            }
        }        
        
        private void drawDiamond(Graphics2D g2d, Point tip, Point tail, Color col){
            //This is used for drawing the aggregation connection line
            double dy = tip.y - tail.y;
            double dx = tip.x - tail.x;
            double phi = Math.toRadians(45);    //degrees
            double theta = Math.atan2(dy,dx);   //theta
            double x1,y1,x2,y2,x3,y3,rho = theta + phi;
            g2d.setColor(col);
            GeneralPath diamond = new GeneralPath();
            diamond.moveTo(tip.getX(),tip.getY());
            x1 = tip.x - 20 * Math.cos(rho);
            y1 = tip.y - 20 * Math.sin(rho);
            diamond.lineTo(x1,y1);
            diamond.moveTo(tip.getX(),tip.getY());
            rho = theta - phi;
            x2 = tip.x - 20 * Math.cos(rho);
            y2 = tip.y - 20 * Math.sin(rho);
            diamond.lineTo(x2,y2);
            rho = -rho;
            x3 = x2 - 20 * Math.sin(rho);
            y3 = y2 - 20 * Math.cos(rho);
            diamond.lineTo(x3, y3);
            diamond.lineTo(x1, y1);
            g2d.draw(diamond);
    }
        private void drawTriangle(Graphics2D g2d, Point tip, Point tail, Color col){
            //This is uded for drawing the generalization (inheritance) connection line
            double dy = tip.y - tail.y;
            double dx = tip.x - tail.x;
            double phi = Math.toRadians(45);
            double theta = Math.atan2(dy,dx);
            double x1,y1,x2,y2,rho = theta + phi;
            g2d.setColor(col);
            GeneralPath triangle = new GeneralPath();
            triangle.moveTo(tip.getX(),tip.getY());
            x1 = tip.x - 20 * Math.cos(rho);
            y1 = tip.y - 20 * Math.sin(rho);
            triangle.lineTo(x1,y1);
            triangle.moveTo(tip.getX(),tip.getY());
            rho = theta - phi;
            x2 = tip.x - 20 * Math.cos(rho);
            y2 = tip.y - 20 * Math.sin(rho);
            triangle.lineTo(x2,y2);
            triangle.lineTo(x1,y1);      
            g2d.draw(triangle);
    }
	private void fixPoint(Point t, Point f){                                    //This method is used to correct the arrow drift effect
            if(t.x < f.x ){
                if(t.y < f.y){
                    this.toPoint.setLocation(toPoint.getX()+100,toPoint.getY()+30);
                    this.fromPoint.setLocation(fromPoint.getX()+94,fromPoint.getY());
                }
                if(t.y == f.y){
                    this.toPoint.setLocation(toPoint.getX()+100,toPoint.getY()+15);
                    this.fromPoint.setLocation(fromPoint.getX()+95,fromPoint.getY()+15);                    
                }
                if(t.y > f.y){
                    this.toPoint.setLocation(toPoint.getX()+100,toPoint.y);
                    this.fromPoint.setLocation(fromPoint.getX()+90,fromPoint.getY()+15);                    
                }
            }//End of if to object is to the left of from object
            if(t.x == f.x){
                if(t.y < f.y){
                    this.toPoint.setLocation(toPoint.getX()+95,toPoint.getY()+30);
                    this.fromPoint.setLocation(fromPoint.getX()+95,fromPoint.getY());                    
                }
                if(t.y == f.y){
                    //not going to happen
                }
                if(t.y > f.y){
                    this.toPoint.setLocation(toPoint.getX()+95,toPoint.getY());
                    this.fromPoint.setLocation(fromPoint.getX()+95,fromPoint.getY()+3);                    
                }                
            }
            if(t.x > f.x){
                if(t.y < f.y){
                    this.toPoint.setLocation(toPoint.getX()+90,toPoint.getY()+30);
                    this.fromPoint.setLocation(fromPoint.getX()+100,fromPoint.getY());                    
                }
                if(t.y == f.y){
                    this.toPoint.setLocation(toPoint.getX()+90,toPoint.getY()+15);
                    this.fromPoint.setLocation(fromPoint.getX()+100,fromPoint.getY()+15);                    
                }
                if(t.y > f.y){
                    this.toPoint.setLocation(toPoint.getX()+90,toPoint.getY());
                    this.fromPoint.setLocation(fromPoint.getX()+100,fromPoint.getY()+30);                    
                }                
            }
        
    }//End of fixPoint
	
	private String firstCharToLowerCase(String str) {
        
        if(str == null || str.length() == 0)
            return "";
        
        if(str.length() == 1) {
            return str.toLowerCase();
        }
        char[] chArr = str.toCharArray();
        chArr[0] = Character.toLowerCase(chArr[0]);
        
        return new String(chArr); 
    }
}
