package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class HoldAndTapGestureEvent extends TouchEvent<HoldAndTapGestureHandler> {

	/**
	 * Event type for tap touch events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<HoldAndTapGestureHandler> TYPE = new Type<HoldAndTapGestureHandler>(
			"holdtap", new HoldAndTapGestureEvent());

	public static Type<HoldAndTapGestureHandler> getType() {
		return TYPE;
	}

	protected HoldAndTapGestureEvent() {

	}

	@Override
	protected void dispatch(HoldAndTapGestureHandler handler) {
		handler.onHoldAndTap(this);
	}

	@Override
	public final Type<HoldAndTapGestureHandler> getAssociatedType() {
		return TYPE;
	}

}
