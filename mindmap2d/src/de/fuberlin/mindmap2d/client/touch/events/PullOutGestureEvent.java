package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class PullOutGestureEvent extends TouchEvent<PullOutGestureHandler> {

	/**
	 * Event type for flick out touch events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<PullOutGestureHandler> TYPE = new Type<PullOutGestureHandler>(
			"pullout", new PullOutGestureEvent());

	public static Type<PullOutGestureHandler> getType() {
		return TYPE;
	}

	protected PullOutGestureEvent() {

	}

	@Override
	protected void dispatch(PullOutGestureHandler handler) {
		handler.onPullOut(this);
	}

	@Override
	public final Type<PullOutGestureHandler> getAssociatedType() {
		return TYPE;
	}

}
