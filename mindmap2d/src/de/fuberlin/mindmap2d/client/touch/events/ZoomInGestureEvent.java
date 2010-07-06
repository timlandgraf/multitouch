package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class ZoomInGestureEvent extends TouchEvent<ZoomInGestureHandler> {
	/**
	 * Event type for zoom in touch events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<ZoomInGestureHandler> TYPE = new Type<ZoomInGestureHandler>(
			"zoomin", new ZoomInGestureEvent());

	public static Type<ZoomInGestureHandler> getType() {
		return TYPE;
	}

	protected ZoomInGestureEvent() {

	}

	@Override
	protected void dispatch(ZoomInGestureHandler handler) {
		handler.onTouchZoomIn(this);
	}

	@Override
	public final Type<ZoomInGestureHandler> getAssociatedType() {
		return TYPE;
	}

}
