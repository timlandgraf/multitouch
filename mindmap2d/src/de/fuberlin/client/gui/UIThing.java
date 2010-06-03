package de.fuberlin.client.gui;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;


public abstract class UIThing implements MouseDownHandler, MouseUpHandler, MouseMoveHandler, MouseOverHandler, MouseOutHandler {
	enum State {NORMAL, HIGHLIGHTED, MOUSEDOWN_1, MOUSEDOWN_2, MOVING, ACTIVATED};
	Group group;
	DrawingArea canvas;
	State state;
	
	//==========================================================================
	
	abstract void addToGroup(Group group);
	abstract void setPosition(int x, int y);
	abstract void setState(State s);
	
	
	//==========================================================================
	public void addToCanvas(DrawingArea canvas){
		this.canvas = canvas;
		this.group = new Group();
		addToGroup(group);
		setState(State.NORMAL);
		canvas.add(group);
		
		//TODO: evtl nur bei group registrieren
		canvas.addMouseMoveHandler(this);
		canvas.addMouseUpHandler(this);
		group.addMouseDownHandler(this);
		
		//needed for highlightning
		group.addMouseOverHandler(this);
		group.addMouseOutHandler(this);
		
	}
	
	
	public void suicide(){
		canvas.remove(group);
	}
	
		
	//==========================================================================
	public void onMouseOver(MouseOverEvent event){
		if(state == State.NORMAL)
			setState(State.HIGHLIGHTED);
	}
	
	
	public void onMouseOut(MouseOutEvent event){
		if(state == State.HIGHLIGHTED)
			setState(State.NORMAL);
	}
	
	
	public void onMouseDown(MouseDownEvent event){
		switch (state) {
			case HIGHLIGHTED: setState(State.MOUSEDOWN_1); break;
			case ACTIVATED: setState(State.MOUSEDOWN_2); break;
		}
		//event.stopPropagation(); //prevent Drag & Drop
	}
	
	
	public void onMouseUp(MouseUpEvent event){
		switch (state) {
			case MOVING: setState(State.HIGHLIGHTED); break;
			case MOUSEDOWN_1: setState(State.ACTIVATED); break;
			case MOUSEDOWN_2: setState(State.HIGHLIGHTED); break;
		}
	}
	
	
	private int suspendRedraw(){
		return(suspendRedraw_((Element)canvas.getElement().getFirstChild()));
	}
	
	private native int suspendRedraw_(Element svgroot) /*-{
		var suspendID = svgroot.suspendRedraw(5000);
		return(suspendID);
	}-*/;
	
	
	private void unsuspendRedraw(int suspendID){
		unsuspendRedraw_((Element)canvas.getElement().getFirstChild(), suspendID);
	}
	
	private native void unsuspendRedraw_(Element svgroot, int suspendID) /*-{
		svgroot.unsuspendRedraw(suspendID);
	}-*/;
	
	
    public void onMouseMove(MouseMoveEvent event){
		switch (state) {
			case MOUSEDOWN_1: setState(State.MOVING); break;
			case MOUSEDOWN_2: setState(State.MOVING); break;
		}
		
		if(state == State.MOVING){
			int suspendID = suspendRedraw();
			setPosition(event.getRelativeX(canvas.getElement()),event.getRelativeY(canvas.getElement()));
			//Repulsion.do_repulsion();
			unsuspendRedraw(suspendID);
		}
		event.stopPropagation(); //might Drag & Drop - I hope
	}
  
}