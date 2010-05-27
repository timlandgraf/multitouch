package de.fuberlin.client;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.GWT;


public class ContextMenu {

	private Bubble parent_bubble;
	
	private SVGButton btn1, btn2, btn3, btn4;
	private DrawingArea canvas;
	
	//TODO: einen UIThing-Container erfinden
	public ContextMenu(Bubble parent_bubble){
		this.parent_bubble = parent_bubble;
	}

	public void addToCanvas(DrawingArea canvas){
		int x = parent_bubble.x;
		int y = parent_bubble.y;
		btn1 = new SVGButton(x+80, y, "-"){
			public void onClick(){
				parent_bubble.suicide();
				//parent_bubble.setState(State.NORMAL); not necessary
		}};
		
		btn2 = new SVGButton(x-80, y, "+"){
			public void onClick(){
				Bubble b = new Bubble(parent_bubble.x+80, parent_bubble.y+80, "NEW");
				Edge e = new Edge(parent_bubble, b);
				b.addToCanvas(canvas);
				e.addToCanvas(canvas);
				parent_bubble.setState(State.NORMAL);
		}};
		
		btn3 = new SVGButton(x, y+80, "set"){
			public void onClick(){
				(new NewBubbleDialog(parent_bubble)).show();
		}};
		
		btn4 = new SVGButton(x, y-80, "btn4"){
			public void onClick(){
				GWT.log("btn4 clicked");
				//parent_bubble.setState(State.NORMAL);
		}};
		
		
		btn1.addToCanvas(canvas);
		btn2.addToCanvas(canvas);
		btn3.addToCanvas(canvas);
		btn4.addToCanvas(canvas);
		
	}
	
	public void suicide(){
		btn1.suicide();
		btn2.suicide();
		btn3.suicide();
		btn4.suicide();
		
	}
	
}