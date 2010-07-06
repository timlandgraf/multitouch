package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class MoveGestureEvent extends TouchEvent<MoveGestureHandler> {

	/**
	 * Event type for move touch gestures events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<MoveGestureHandler> TYPE = new Type<MoveGestureHandler>(
			"holdtap", new MoveGestureEvent());

	public static Type<MoveGestureHandler> getType() {
		return TYPE;
	}

	protected MoveGestureEvent() {

	}

	@Override
	protected void dispatch(MoveGestureHandler handler) {
		handler.onMoveTouch(this);
	}

	@Override
	public final Type<MoveGestureHandler> getAssociatedType() {
		return TYPE;
	}

}
