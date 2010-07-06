package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class TouchMoveEvent extends TouchEvent<TouchMoveHandler> {

	/**
	 * Event type for touch move events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<TouchMoveHandler> TYPE = new Type<TouchMoveHandler>(
			"touchmove", new TouchMoveEvent());

	public static Type<TouchMoveHandler> getType() {
		return TYPE;
	}

	protected TouchMoveEvent() {

	}

	@Override
	protected void dispatch(TouchMoveHandler handler) {
		handler.onTouchMove(this);
	}

	@Override
	public final Type<TouchMoveHandler> getAssociatedType() {
		return TYPE;
	}

}
