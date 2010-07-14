package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.EventHandler;

public interface HoldAndTapGestureHandler extends EventHandler {

	/**
   * Called when HoldAndTapEvent is fired.
   *     
   * @param event the {@link HoldAndTapGestureEvent} that was fired
   */
	void onHoldAndTap(HoldAndTapGestureEvent event);
}
