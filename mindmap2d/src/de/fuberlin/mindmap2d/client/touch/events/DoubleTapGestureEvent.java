package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class DoubleTapGestureEvent extends TouchEvent<DoubleTapGestureHandler> {
	
	/**
	 * Event type for double tap touch events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<DoubleTapGestureHandler> TYPE = new Type<DoubleTapGestureHandler>(
			"doubletap", new DoubleTapGestureEvent());

	public static Type<DoubleTapGestureHandler> getType() {
		return TYPE;
	}

	protected DoubleTapGestureEvent() {

	}

	@Override
	protected void dispatch(DoubleTapGestureHandler handler) {
		handler.onDoubleTap(this);
	}

	@Override
	public final Type<DoubleTapGestureHandler> getAssociatedType() {
		return TYPE;
	}
}
