package de.fuberlin.client;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.GWT;
import java.util.*;

public class Bubble extends UIThing {

	
	public int x, y;
	public String text_str;
	public int width, height;
	
	private Circle circle;
	private Text text;
	private ContextMenu menu;
	private List<Edge> edge_list;
	//==========================================================================
	public Bubble(int x, int y, String text){
		this.x = x;
		this.y = y;
		this.text_str = text;
		edge_list = new ArrayList();
		Repulsion.registerBubble(this);
	}
	
	//==========================================================================
	public void toFront(){
		canvas.pop(group);
	}
	//==========================================================================
	public String getText(){
		return(text_str);
	}
	
	//==========================================================================
	public void setText(String t){
		text_str = t;
		text.setText(t);
	}
	
	//==========================================================================
	public void addEdge(Edge e){
		edge_list.add(e);
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
			menu = new ContextMenu(this);
			menu.addToCanvas(canvas);
		}
		
		if(!(s==State.MOUSEDOWN_2 || s==State.ACTIVATED) && menu!=null){
			menu.suicide();
			menu = null;
		}	
			
		//text.setText(""+s);
	}
	
	//==========================================================================
	public void suicide(){
		if(menu != null)
			menu.suicide();
		
		for(Edge e: edge_list)
			e.suicide();
		Repulsion.unregisterBubble(this);
		super.suicide();
	}
	
	//==========================================================================
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		circle.setX(x);
		circle.setY(y);
		text.setX(x-30);
		text.setY(y);
		
		for(Edge e: edge_list)
			e.update();
	}
	
}