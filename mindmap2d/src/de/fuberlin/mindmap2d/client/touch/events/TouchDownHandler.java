package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;


/**
 * Handler interface for standard touch down, touch move and touch up events
 */
public interface TouchDownHandler extends EventHandler {

	/**
	 * Called when TouchDownEvent is fired.
	 * 
	 * @param event
	 *            the {@link TouchDownEvent} that was fired
	 */
	void onTouchDown(TouchDownEvent event);
}
