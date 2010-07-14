package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;


/**
 * Handler interface for standard touch move events
 */
public interface TouchMoveHandler extends EventHandler {

	/**
	 * Called when TouchMoveEvent is fired.
	 * 
	 * @param event
	 *            the {@link TouchMoveEvent} that was fired
	 */
	void onTouchMove(TouchMoveEvent event);
}
