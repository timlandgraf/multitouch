package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class PushInGestureEvent extends TouchEvent<PushInGestureHandler> {

	/**
	 * Event type for flick in touch events. Represents the meta-data associated
	 * with this event.
	 */
	private static final Type<PushInGestureHandler> TYPE = new Type<PushInGestureHandler>(
			"pushin", new PushInGestureEvent());

	public static Type<PushInGestureHandler> getType() {
		return TYPE;
	}

	protected PushInGestureEvent() {

	}

	@Override
	protected void dispatch(PushInGestureHandler handler) {
		handler.onPushIn(this);
	}

	@Override
	public final Type<PushInGestureHandler> getAssociatedType() {
		return TYPE;
	}
}
