package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;

public interface PullOutGestureHandler extends EventHandler {

	/**
	 * Called when FlickOutTouchEvent is fired.
	 * 
	 * @param event
	 *            the {@link PullOutGestureEvent} that was fired
	 */
	void onPullOut(PullOutGestureEvent event);
}
