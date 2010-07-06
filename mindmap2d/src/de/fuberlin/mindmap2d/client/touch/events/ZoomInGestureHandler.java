package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;

public interface ZoomInGestureHandler extends EventHandler {

	/**
	 * Called when ZoomInTouchEvent is fired.
	 * 
	 * @param event
	 *            the {@link ZoomInGestureEvent} that was fired
	 */
	void onTouchZoomIn(ZoomInGestureEvent event);

}
