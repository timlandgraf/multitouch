package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;

public interface MoveGestureHandler extends EventHandler {

	/**
   * Called when MoveTouchEvent is fired.
   *     
   * @param event the {@link MoveGestureEvent} that was fired
   */
	void onMoveTouch(MoveGestureEvent event);
}
