package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;

public interface ZoomOutGestureHandler extends EventHandler {
	/**
	 * Called when ZoomOutTouchEvent is fired.
	 * 
	 * @param event
	 *            the {@link ZoomOutGestureEvent} that was fired
	 */
	void onTouchZoomOut(ZoomOutGestureEvent event);
}
