package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class TapGestureEvent extends TouchEvent<TapGestureHandler> {

	/**
	 * Event type for tap touch events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<TapGestureHandler> TYPE = new Type<TapGestureHandler>(
			"tap", new TapGestureEvent());

	public static Type<TapGestureHandler> getType() {
		return TYPE;
	}

	protected TapGestureEvent() {

	}

	@Override
	protected void dispatch(TapGestureHandler handler) {
		handler.onTap(this);
	}

	@Override
	public final Type<TapGestureHandler> getAssociatedType() {
		return TYPE;
	}

}
