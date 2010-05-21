package de.fuberlin.client;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.GWT;

public class Bubble implements MouseDownHandler, MouseUpHandler, MouseMoveHandler, MouseOverHandler, MouseOutHandler {

	
	public int x, y;
	public String text_str;
	public int width, height;
	
	private Circle circle;
	private Text text;
	private Group group;
	
	private boolean active;
	private DrawingArea canvas;
	
	public Bubble(int x, int y, String text){
		this.x = x;
		this.y = y;
		this.text_str = text;
		this.active = false;
	}

	public void addToCanvas(DrawingArea canvas){
		this.canvas = canvas;
		group = new Group();
		circle = new Circle(x, y, 50);
		circle.setFillColor("blue");
		
		group.add(circle);
		text = new Text(x-30,y,text_str);
		text.setFillColor("black"); 
		text.setStrokeWidth(0);
		group.add(text);
		
		canvas.addMouseMoveHandler(this);
		canvas.addMouseUpHandler(this);
		
	    group.addMouseDownHandler(this);
		group.addMouseOverHandler(this);
		group.addMouseOutHandler(this);
		
		
		canvas.add(group);
	}
	
	public void onMouseDown(MouseDownEvent event){
		active = true;
		circle.setFillColor("orange");
		event.stopPropagation(); //prevent Drag & Drop
	}
  
	public void onMouseOver(MouseOverEvent event){
		if(!active)
			circle.setFillColor("yellow");
	}
  
	public void onMouseOut(MouseOutEvent event){
		if(!active)
			circle.setFillColor("blue");
	}
  
	public void onMouseUp(MouseUpEvent event){
		if(active)
			circle.setFillColor("yellow");
		active = false;
	}
	
    public void onMouseMove(MouseMoveEvent event){
		if(active)
			setPosition(event.getRelativeX(canvas.getElement()),event.getRelativeY(canvas.getElement()));
		event.stopPropagation(); //prevent Drag & Drop
	}
  
	
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		circle.setX(x);
		circle.setY(y);
		text.setX(x-30);
		text.setY(y);
		/*this.x = x;
		this.y = y;
		foam.repaint();*/
		//TODO: tell all edges
	}
	
	/*
	public void setText(String text){
		this.text = text;
		width = -1;
		height = -1;
		foam.repaint();
	}
	
	public boolean overlaps(int px, int py){
		if(width==-1 || height==-1)
			return(false);
		return(x < px && px < x+width && y < py && py < y+height);
	}
	
	public String toString(){
		return("Bubble(\""+text+"\")");
	}*/
}