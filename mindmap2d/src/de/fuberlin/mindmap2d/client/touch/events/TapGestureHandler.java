package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;

public interface TapGestureHandler extends EventHandler {

	/**
   * Called when TapTouchEvent is fired.
   *     
   * @param event the {@link TapGestureEvent} that was fired
   */
	void onTap(TapGestureEvent event);
}
