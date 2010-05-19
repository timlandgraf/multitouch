package de.fuberlin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

public class UserInputHandler implements MouseListener {
	
	private Foam foam;
	
	//the bubble that is currently being dragged
	private Bubble grapped_bubble;
	
	public UserInputHandler(Foam foam){
		this.foam = foam;
		this.grapped_bubble = null;
	}
	
	public void onMouseDown(Widget sender, int x, int y){
		for(Bubble b: foam.bubbles)
			if(b.overlaps(x, y)){
				grapped_bubble = b;
				return;
			}
	}
	
	public void onMouseUp(Widget sender, int x, int y){
		grapped_bubble = null;
	}
	
	
	public void onMouseMove(Widget sender, int x, int y){
		//GWT.log("moved: "+x+"  "+y);
		if(grapped_bubble != null)
			grapped_bubble.setPosition(x,y);
	}
	
	public void onMouseEnter(Widget sender){}
	public void onMouseLeave(Widget sender){}
	
}
