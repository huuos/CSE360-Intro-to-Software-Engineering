/**
 * 
 */
package edu.asu.CSE360.gui;

import javax.swing.Icon;
import javax.swing.JLabel;


/**
 * @author Tina Peng
 *
 */
public class ObservableIcon extends JLabel implements Cloneable {
	public static final int STATUS_ADD 		= 1;
	public static final int STATUS_DELETE 	= 2;
	
	private int status = 0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ObservableIcon() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 */
	public ObservableIcon(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 */
	public ObservableIcon(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param horizontalAlignment
	 */
	public ObservableIcon(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param image
	 * @param horizontalAlignment
	 */
	public ObservableIcon(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param text
	 * @param icon
	 * @param horizontalAlignment
	 */
	public ObservableIcon(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	//
	//	Methods
	//
	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	//
	//	getters and setters
	//

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
