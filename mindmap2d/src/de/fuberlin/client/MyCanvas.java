package de.fuberlin.client;

import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.ClickListenerCollection;
import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.MouseListenerCollection;
import com.google.gwt.user.client.ui.MouseWheelListener;
import com.google.gwt.user.client.ui.MouseWheelListenerCollection;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.SourcesMouseEvents;
import com.google.gwt.user.client.ui.SourcesMouseWheelEvents;

public class MyCanvas extends GWTCanvas 
	implements SourcesClickEvents, SourcesMouseEvents, SourcesMouseWheelEvents {
	
	public MyCanvas(int coordX, int coordY) {
		super(coordX, coordY);
		//Todo evtl getContext hier hin
	}

	public native int measureText(String text)/*-{
	 	ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
		return( ctx.measureText(text).width );
	}-*/;
	
	
	public native void fillText(String text, int x, int y)/*-{
		ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
		ctx.fillText(text, x, y);
	}-*/;
	
	
	public  native void setup_shadows()/*-{
		ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
  		ctx.shadowOffsetX = 2;
		ctx.shadowOffsetY = 2;
		ctx.shadowBlur = 2;
		ctx.shadowColor = "rgba(0, 0, 0, 0.5)";
	}-*/;
	
	
	public native void setup_text_style()/*-{
  		ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
		ctx.font = "20px Times New Roman";
		ctx.fillStyle = "Black";
	}-*/;
  
	
	/////////////////////////////////////////////////////////////////
	// EVENT HANDLING
	/////////////////////////////////////////////////////////////////
	
	private void impl_onMouseDown(Event event){}
	private void impl_onMouseUp(){}
	
	
	private ClickListenerCollection clickListeners;
	private MouseListenerCollection mouseListeners;
	private MouseWheelListenerCollection mouseWheelListeners;
	private boolean preventSelection = false;
	
	/**
	 * Fired whenever a browser event is received.
	 * 
	 * @param event
	 * @see com.google.gwt.user.client.ui.Widget#onBrowserEvent(Event)
	 */
	public void onBrowserEvent(Event event) {
		if (event == null) {
			throw new IllegalArgumentException();
		}
		switch (DOM.eventGetType(event)) {
		case Event.ONMOUSEDOWN:
			if (preventSelection) {
				impl_onMouseDown(event);
			}
		case Event.ONMOUSEMOVE:
		case Event.ONMOUSEOVER:
		case Event.ONMOUSEOUT:
			if (mouseListeners != null) {
				mouseListeners.fireMouseEvent(this, event);
			}
			break;
		case Event.ONMOUSEUP:
			if (preventSelection) {
				impl_onMouseUp();
			}
			if (mouseListeners != null) {
				mouseListeners.fireMouseEvent(this, event);
			}
			break;
		case Event.ONMOUSEWHEEL:
			if (mouseWheelListeners != null) {
				mouseWheelListeners.fireMouseWheelEvent(this, event);
			}
			break;
		case Event.ONCLICK:
			if (clickListeners != null) {
				clickListeners.fireClick(this);
			}
			break;
		}
	}

	/**
	 * Adds a listener interface to receive mouse click events such as
	 * <code>Event.ONCLICK</code>.
	 * 
	 * @param listener
	 * @see com.google.gwt.user.client.ui.SourcesClickEvents#addClickListener(ClickListener)
	 */
	public void addClickListener(ClickListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		if (clickListeners == null) {
			clickListeners = new ClickListenerCollection();
			sinkEvents(Event.ONCLICK);
		}
		clickListeners.add(listener);
	}

	/**
	 * Removes a previously added listener interface.
	 * 
	 * @param listener
	 * @see com.google.gwt.user.client.ui.SourcesClickEvents#removeClickListener(ClickListener)
	 */
	public void removeClickListener(ClickListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		if (clickListeners != null) {
			clickListeners.remove(listener);
		}
	}

	/**
	 * Adds a listener interface to receive mouse events such as:
	 * <ul>
	 * <li><code>Event.ONMOUSEDOWN</code>
	 * <li><code>Event.ONMOUSEUP</code>
	 * <li><code>Event.ONMOUSEMOVE</code>
	 * <li><code>Event.ONMOUSEOVER</code>
	 * <li><code>Event.ONMOUSEOUT</code>
	 * </ul>
	 * 
	 * @param listener
	 * @see com.google.gwt.user.client.ui.SourcesMouseEvents#addMouseListener(MouseListener)
	 */
	public void addMouseListener(MouseListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		if (mouseListeners == null) {
			mouseListeners = new MouseListenerCollection();
			sinkEvents(Event.MOUSEEVENTS);
		}
		mouseListeners.add(listener);
	}

	/**
	 * Removes a previously added listener interface.
	 * 
	 * @param listener
	 * @see com.google.gwt.user.client.ui.SourcesMouseEvents#removeMouseListener(MouseListener)
	 */
	public void removeMouseListener(MouseListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		if (mouseListeners != null) {
			mouseListeners.remove(listener);
		}
	}

	/**
	 * Adds a listener interface to receive mouse wheel events such as
	 * <code>Event.ONMOUSEWHEEL</code>.
	 * 
	 * @param listener
	 * @see com.google.gwt.user.client.ui.SourcesMouseWheelEvents#addMouseWheelListener(MouseWheelListener)
	 */
	public void addMouseWheelListener(MouseWheelListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		if (mouseWheelListeners == null) {
			mouseWheelListeners = new MouseWheelListenerCollection();
			sinkEvents(Event.ONMOUSEWHEEL);
		}
		mouseWheelListeners.add(listener);
	}

	/**
	 * Removes a previously added listener interface.
	 * 
	 * @param listener
	 * @see com.google.gwt.user.client.ui.SourcesMouseWheelEvents#removeMouseWheelListener(MouseWheelListener)
	 */
	public void removeMouseWheelListener(MouseWheelListener listener) {
		if (listener == null) {
			throw new IllegalArgumentException();
		}
		if (mouseWheelListeners != null) {
			mouseWheelListeners.remove(listener);
		}
	}

}
