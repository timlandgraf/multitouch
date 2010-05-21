package de.fuberlin.client;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.GWT;

public class Bubble extends UIThing {

	
	public int x, y;
	public String text_str;
	public int width, height;
	
	private Circle circle;
	private Text text;
	private ContextMenu menu;
	
	//==========================================================================
	public Bubble(int x, int y, String text){
		super(true);
		this.x = x;
		this.y = y;
		this.text_str = text;
		
	}

	//==========================================================================
	public void addToGroup(Group group){
		circle = new Circle(x, y, 50);
		group.add(circle);
		text = new Text(x-30,y,text_str);
		text.setFillColor("black"); 
		text.setStrokeWidth(0);
		group.add(text);
	}

	//==========================================================================
	public void setState(State s){
		this.state = s;
		switch (s) {
			case NORMAL: circle.setFillColor("blue"); break;
			case HIGHLIGHTED: circle.setFillColor("yellow"); break;
			case MOUSEDOWN_1: //fall through
			case MOUSEDOWN_2:
			case MOVING:
			case ACTIVATED: circle.setFillColor("orange"); break;
		}
		
		if(s == State.ACTIVATED){
			GWT.log("adding menu1");
			menu = new ContextMenu(this);
			menu.addToCanvas(canvas);
		}
		
		if(!(s==State.MOUSEDOWN_2 || s==State.ACTIVATED) && menu!=null){
			GWT.log("removing menu");
			menu.suicide();
			menu = null;
		}	
			
		text.setText(""+s);
	}
	
	//==========================================================================
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		circle.setX(x);
		circle.setY(y);
		text.setX(x-30);
		text.setY(y);
		//TODO: tell all edges
	}
	
}