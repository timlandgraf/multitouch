package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;

public interface PushInGestureHandler extends EventHandler {

	/**
	 * Called when FlickInTouchEvent is fired.
	 * 
	 * @param event
	 *            the {@link PushInGestureEvent} that was fired
	 */
	void onPushIn(PushInGestureEvent event);
}
