package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class ZoomOutGestureEvent extends TouchEvent<ZoomOutGestureHandler> {
	/**
	 * Event type for zoom out touch events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<ZoomOutGestureHandler> TYPE = new Type<ZoomOutGestureHandler>(
			"zoomout", new ZoomOutGestureEvent());

	public static Type<ZoomOutGestureHandler> getType() {
		return TYPE;
	}

	protected ZoomOutGestureEvent() {

	}

	@Override
	protected void dispatch(ZoomOutGestureHandler handler) {
		handler.onTouchZoomOut(this);
	}

	@Override
	public final Type<ZoomOutGestureHandler> getAssociatedType() {
		return TYPE;
	}

}
