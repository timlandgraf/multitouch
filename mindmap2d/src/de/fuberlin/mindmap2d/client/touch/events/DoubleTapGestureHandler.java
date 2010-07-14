package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;


public interface DoubleTapGestureHandler extends EventHandler {

	/**
   * Called when DoubleTapTouchEvent is fired.
   *     
   * @param event the {@link DoubleTapGestureEvent} that was fired
   */
	void onDoubleTap(DoubleTapGestureEvent event);
}
