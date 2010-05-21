package de.fuberlin.client;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.GWT;

public class ContextMenu {

	private Bubble parent_bubble;
	
	private SVGButton btn1, btn2, btn3, btn4;
	
	//TODO: einen UIThing-Container erfinden
	public ContextMenu(Bubble parent_bubble){
		this.parent_bubble = parent_bubble;
	}

	public void addToCanvas(DrawingArea canvas){
		int x = parent_bubble.x;
		int y = parent_bubble.y;
		btn1 = new SVGButton(x+80, y, "btn1");
		btn2 = new SVGButton(x-80, y, "btn2");
		btn3 = new SVGButton(x, y+80, "btn3");
		btn4 = new SVGButton(x, y-80, "btn4");
		
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