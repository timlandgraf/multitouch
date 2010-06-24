package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;

import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.VectorObjectContainer;

public abstract class InteractiveElement implements MouseDownHandler,
		MouseUpHandler, MouseMoveHandler, MouseOverHandler, MouseOutHandler, 
		ContextMenuHandler {
	enum State {
		NORMAL, HIGHLIGHTED, MOUSEDOWN, MOVING, RIGHTCLICK;
	};

	Group group = new Group();
	VectorObjectContainer parent;
	State state;
	
	abstract void setPosition(int x, int y);

	abstract void setState(State s);

	public void addThisTo(VectorObjectContainer parent) {
		this.parent = parent;
		parent.add(group);
		setState(State.NORMAL);

		// TODO: evtl nur bei group registrieren
		group.addMouseMoveHandler(this);
		group.addMouseUpHandler(this);
		group.addMouseDownHandler(this);

		// needed for highlightning
		group.addMouseOverHandler(this);
		group.addMouseOutHandler(this);
		
		group.addContextMenuHandler(this);
	}

	public void remove() {
		parent.remove(group);
	}

	public void onMouseOver(MouseOverEvent event) {
		if (state == State.NORMAL)
			setState(State.HIGHLIGHTED);
	}

	public void onMouseOut(MouseOutEvent event) {
		if (state == State.HIGHLIGHTED)
			setState(State.NORMAL);
	}

	public void onMouseDown(MouseDownEvent event) {
		if (event.getNativeButton() == NativeEvent.BUTTON_RIGHT){
			setState(State.RIGHTCLICK);
		}else if(state == State.HIGHLIGHTED ){
			setState(State.MOUSEDOWN);
			UserInterface.getUI().closeContextMenu();
		}
			
		event.stopPropagation();
	}

	public void onMouseUp(MouseUpEvent event) {
		
		switch (state) {
		case RIGHTCLICK:
			setState(State.HIGHLIGHTED);
			break;
		case MOVING:
		case MOUSEDOWN:
			setState(State.HIGHLIGHTED);
			UserInterface.getUI().closeContextMenu();
			break;
		}
		
		event.stopPropagation();
	}

	public void onMouseMove(MouseMoveEvent event) {
		switch (state) {
		case MOUSEDOWN:
			setState(State.MOVING);
			break;
		}

		event.stopPropagation();
	}

	@Override
	public void onContextMenu(ContextMenuEvent event) {
		event.stopPropagation();
	}
}