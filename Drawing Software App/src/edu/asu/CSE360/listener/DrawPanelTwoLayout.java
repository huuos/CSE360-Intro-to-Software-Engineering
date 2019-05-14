package edu.asu.CSE360.listener;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class DrawPanelTwoLayout implements LayoutManager {

	@Override
	public void addLayoutComponent(String arg0, Component arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void layoutContainer(Container arg0) {
		// TODO Auto-generated method stub
            for (int i = 0 ; i < arg0.getComponentCount() ; i++) {
                Component c = arg0.getComponent(i);
                c.setBounds(c.getX(), c.getY(), (c.getWidth()==0)?30:c.getWidth(), (c.getHeight()==0)?30:c.getHeight());
            }
	}

	@Override
	public Dimension minimumLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension preferredLayoutSize(Container arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeLayoutComponent(Component arg0) {
		// TODO Auto-generated method stub
		
	}

}
