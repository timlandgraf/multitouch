package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;


/**
 * Handler interface for standard touch up events
 */
public interface TouchUpHandler extends EventHandler {

	/**
	 * Called when TouchUpEvent is fired.
	 * 
	 * @param event
	 *            the {@link TouchUpEvent} that was fired
	 */
	void onTouchUp(TouchUpEvent event);
}
