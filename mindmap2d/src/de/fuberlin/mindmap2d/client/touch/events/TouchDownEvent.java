package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class TouchDownEvent extends TouchEvent<TouchDownHandler> {

	/**
	 * Event type for touch down events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<TouchDownHandler> TYPE = new Type<TouchDownHandler>(
			"touchdown", new TouchDownEvent());

	public static Type<TouchDownHandler> getType() {
		return TYPE;
	}

	protected TouchDownEvent() {

	}

	@Override
	protected void dispatch(TouchDownHandler handler) {
		handler.onTouchDown(this);
	}

	@Override
	public final Type<TouchDownHandler> getAssociatedType() {
		return TYPE;
	}

}
